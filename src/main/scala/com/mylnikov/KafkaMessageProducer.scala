package com.mylnikov

import java.util.Properties

import com.mylnikov.impl.GeneratedMessageProducer
import com.mylnikov.model.Message
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

/**
  * Entry point. Produces some messages to kafka distributed bu message's username.
  */
object KafkaMessageProducer {

  /**
    * Big data words to filter message with big data.
    */
  val searchWords = Array("big data", "ai",  "machine learning" , "course")

  /**
    * Countru name to filter messages
    */
  var currentCountry = "Russia"

  /**
    * @param args bootstrap server and kafka topic, third argument could be current country
    */
  def main(args: Array[String]): Unit = {

    if (args.length < 2) {
      throw new IllegalArgumentException("You should specify bootstrap server and topic in arguments in arguments")
    }

    if (args.length == 3 ) {
      currentCountry = args(2)
    }

    // Kafka config
    val props = new Properties()
    props.put("bootstrap.servers", args(0))
    props.put("value.serializer", "com.mylnikov.model.MessageSerializer")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, Message](props)

    val messageProducer = new GeneratedMessageProducer()

    while(true) {
      val message = messageProducer.getNextMessage
      if (containsBigData(message) && isInRussian(message)) {
        producer.send(new ProducerRecord[String, Message](args(1),
          getPartitionNumberByUsername(message.userName),
          "key",
          message))
      }
    }

  }

  def isInRussian(message: Message): Boolean = {
    currentCountry == message.location
  }

  /**
    * @param name message's name
    * @return partition number for this user. Uses length modulo.
    */
  def getPartitionNumberByUsername(name: String): Integer = {
    name.length % 4
  }

  /**
    * @param message input message
    * @return true is text contains big data words, otherwise false.
    */
  def containsBigData(message: Message): Boolean = {
    for(word <- searchWords) {
      val indexOfWord = message.text.indexOf(word)
      if(indexOfWord >= 0) {
        val beforeCharIndex = indexOfWord-1
        val afterCharIndex = indexOfWord+word.length
        if(!isCharIsLetter(message.text, beforeCharIndex) && !isCharIsLetter(message.text, afterCharIndex)) {
          return true
        }
      }
    }
    false
  }

  /**
    * @param word input word
    * @param index index of character to check
    * @return true if index of character is a letter, otherwise false
    */
  def isCharIsLetter(word: String, index: Int) : Boolean = {
    if(index < 0) {
      return false
    }
    if(index > (word.length - 1)) {
      return false
    }
    word.charAt(index).isLetter
  }

}