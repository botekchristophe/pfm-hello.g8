/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$.api

import play.api.libs.json._

//TODO move this to the pfm-lib project in package com.inocybe.pfm.lib.service.inbound.v2.$name;format="Camel"$

case class $name;format="Camel"$State($name;format="norm"$s: Set[$name;format="Camel"$])

object $name;format="Camel"$State {
  implicit val format: Format[$name;format="Camel"$State] = Json.format[$name;format="Camel"$State]
}