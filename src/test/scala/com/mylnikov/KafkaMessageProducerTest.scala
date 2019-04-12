package com.mylnikov

import com.mylnikov.model.Message
import org.scalatest.FunSuite

class KafkaMessageProducerTest extends FunSuite {

  val kafkaMessageProducer = KafkaMessageProducer

  test("Should get partition number by username") {
    val partitionNumber = kafkaMessageProducer.getPartitionNumberByUsername("sd");
    assert(partitionNumber == 2)
  }

  test("Should filter messages without big data words") {
    assert(!kafkaMessageProducer.containsBigData(new Message(message = "someMessage")))
    assert(kafkaMessageProducer.containsBigData(new Message(message = "big data message")))
  }

}
