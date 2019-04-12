package com.mylnikov.model

import com.fasterxml.jackson.databind.ObjectMapper
import org.scalatest.FunSuite

class MessageSerializerTest extends FunSuite{

  val messageSerializer = new MessageSerializer()

  test("Should serialize event") {
    val message = Message("UserName", "Location", "Message")
    val serializedEvent = messageSerializer.serialize("test",message)
    val objectMapper = new ObjectMapper()
    val deserializedMessage = objectMapper.readValue(serializedEvent, classOf[Message])
    assert(deserializedMessage.equals(message))
  }

  test("Should return null in case null object") {
    val deserializedEvent = messageSerializer.serialize("test", null)
    assert(deserializedEvent.length == 4)
  }

}
