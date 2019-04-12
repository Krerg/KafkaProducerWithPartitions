package com.mylnikov

import com.mylnikov.model.Message

/**
  * Interface for message producer
  */
trait MessageProducer {

  /**
    * @return next message in queue
    */
  def getNextMessage: Message

}
