/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$.api

import java.util.UUID

import com.inocybe.pfm.lib.service.inbound.v2.BaseModel
import play.api.libs.json._

//TODO move this to the pfm-lib project in package com.inocybe.pfm.lib.service.inbound.v2.$name;format="Camel"$

case class $name;format="Camel"$(id: UUID, name: String, description: String) extends BaseModel
object $name;format="Camel"$ {
  implicit val format: Format[$name;format="Camel"$] = Json.format[$name;format="Camel"$]
}