package com.mylnikov.model

import scala.beans.BeanProperty

case class Message(@BeanProperty userName: String = null,
                   @BeanProperty location: String = null,
                   @BeanProperty message: String = null) {

}
