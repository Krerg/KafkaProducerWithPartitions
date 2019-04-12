package com.mylnikov

import com.mylnikov.model.Message

trait MessageProducer {

  def getNextMessage: Message

}
