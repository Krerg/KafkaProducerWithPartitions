package com.mylnikov.impl

import com.mylnikov.MessageProducer
import com.mylnikov.model.Message

import scala.concurrent.forkjoin.ThreadLocalRandom
import scala.util.Random

/**
  * Self-generated message producer. Using scala random returns new message
  */
class GeneratedMessageProducer extends MessageProducer {

  val users = Array[String]("Alex", "So", "Johnatan", "Lex")

  val locations = Array[String]("Russia", "UK", "Australia", "California")

  val bigDataWords = Array("big data", "ai",  "machine learning" , "course")

  override def getNextMessage: Message = {
    val user = users(ThreadLocalRandom.current().nextInt(0,4))
    val location = locations(ThreadLocalRandom.current().nextInt(0,4))
    val mesage = generateMessage()
    Message(user, location, mesage)
  }

  def generateMessage() : String = {
    if( ThreadLocalRandom.current().nextInt(0,2) == 1) {
      Random.nextString(10) + " " + bigDataWords(ThreadLocalRandom.current().nextInt(0,4))
    } else {
      Random.nextString(10)
    }
  }

}
