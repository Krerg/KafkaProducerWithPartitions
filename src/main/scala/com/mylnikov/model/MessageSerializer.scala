package com.mylnikov.model

import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Serializer

class MessageSerializer extends Serializer[Message] {

  val mapper= new ObjectMapper()

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {

  }

  override def serialize(topic: String, data: Message): Array[Byte] = {
    try mapper.writeValueAsString(data).getBytes match {
      case bytes: Array[Byte] => bytes
      case ex => Array.emptyByteArray
    }
  }

  override def close(): Unit = {

  }

}
