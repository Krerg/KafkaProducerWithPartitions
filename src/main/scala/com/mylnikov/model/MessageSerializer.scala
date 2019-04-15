package com.mylnikov.model

import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Serializer

/**
  * Kafka serializer for message. Null safe.
  */
class MessageSerializer extends Serializer[Message] {

  val mapper= new ObjectMapper()

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {

  }

  override def serialize(topic: String, data: Message): Array[Byte] = {
    try {
      mapper.writeValueAsString(data).getBytes
    } catch {
      case ex: Exception => Array.emptyByteArray
    }
  }

  override def close(): Unit = {

  }

}
