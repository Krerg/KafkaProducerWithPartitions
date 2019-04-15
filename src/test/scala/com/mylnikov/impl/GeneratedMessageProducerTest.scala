package com.mylnikov.impl

import com.mylnikov.KafkaMessageProducer
import org.scalatest.FunSuite

class GeneratedMessageProducerTest extends FunSuite {

  val kafkaMessageProducer = new GeneratedMessageProducer()

    test("should generate proper message") {
      val message = kafkaMessageProducer.getNextMessage
      assert(!message.getText.isEmpty)
      assert(!message.userName.isEmpty)
      assert(!message.location.isEmpty)
      assert(kafkaMessageProducer.locations.exists(message.location.contains))
      assert(KafkaMessageProducer.users.exists(message.userName.contains))
    }

}
