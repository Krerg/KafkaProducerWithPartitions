package com.mylnikov

import java.util.Properties

import com.mylnikov.impl.GeneratedMessageProducer
import com.mylnikov.model.Message
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object KafkaMessageProducer {

  val searchWords = Array("big data", "ai",  "machine learning" , "course")

  def main(args: Array[String]): Unit = {

    if (args.length < 2) {
      throw new IllegalArgumentException("You should specify bootstrap server and topic in arguments in arguments")
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
      if (containsBigData(message)) {
        producer.send(new ProducerRecord[String, Message](args(1),
          getPartitionNumberByUsername(message.userName),
          "key",
          message))
      }
    }

  }

  def getPartitionNumberByUsername(name: String): Integer = {
    name.length % 4
  }

  def containsBigData(message: Message): Boolean = {
    searchWords.exists(message.message.contains)
  }

}