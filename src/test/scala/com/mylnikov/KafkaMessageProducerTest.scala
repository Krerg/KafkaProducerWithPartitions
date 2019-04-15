package com.mylnikov

import com.mylnikov.model.Message
import org.scalatest.FunSuite

class KafkaMessageProducerTest extends FunSuite {

  val kafkaMessageProducer = KafkaMessageProducer

  test("Should get partition number by username") {
    val partitionNumber = kafkaMessageProducer.getPartitionNumberByUsername("Lex");
    assert(partitionNumber == 4)
  }

  test("Should filter messages without big data words") {
    assert(!kafkaMessageProducer.containsBigData(new Message(text = "someMessage")))
    assert(kafkaMessageProducer.containsBigData(new Message(text = "big data message")))
    assert(!kafkaMessageProducer.containsBigData(new Message(text = "the air is fresh")))
  }

}
