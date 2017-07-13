/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$.api

import java.util.UUID

import akka.NotUsed
import com.inocybe.pfm.lib.serialization.JsonFormats._
import com.inocybe.pfm.lib.service.inbound.v2.shared.ErrorResponse
import com.lightbend.lagom.scaladsl.api.deser.DefaultExceptionSerializer
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

//TODO move this to the pfm-lib project in package com.inocybe.pfm.lib.service.inbound.v2.$name;format="Camel"$

trait $name;format="Camel"$Service extends Service {

  def getState                                : ServiceCall[NotUsed, Either[ErrorResponse, $name;format="Camel"$State]]
  def get$name;format="Camel"$Ids             : ServiceCall[NotUsed, Either[ErrorResponse, Iterable[UUID]]]
  def create$name;format="Camel"$             : ServiceCall[$name;format="Camel"$, Either[ErrorResponse, $name;format="Camel"$]]
  def read$name;format="Camel"$(id: UUID)     : ServiceCall[NotUsed, Either[ErrorResponse, $name;format="Camel"$]]
  def update$name;format="Camel"$(id: UUID)   : ServiceCall[$name;format="Camel"$, Either[ErrorResponse, $name;format="Camel"$]]
  def delete$name;format="Camel"$(id: UUID)   : ServiceCall[NotUsed, Either[ErrorResponse, $name;format="Camel"$]]


  override final def descriptor: Descriptor = {
  import Service._
  // @formatter:off
  named("$name;format="norm"$-service").withCalls(
  restCall(Method.GET,    s"$apiSuffix/$name;format="norm"$s/state",                    getState _),
  restCall(Method.GET,    s"$apiSuffix/$name;format="norm"$s/ids",                      get$name;format="Camel"$Ids _),
  restCall(Method.GET,    s"$apiSuffix/$name;format="norm"$s/$name;format="norm"$/:id", read$name;format="Camel"$ _),
  restCall(Method.POST,   s"$apiSuffix/$name;format="norm"$s/$name;format="norm"$",     create$name;format="Camel"$ _),
  restCall(Method.PATCH,  s"$apiSuffix/$name;format="norm"$s/$name;format="norm"$/:id", update$name;format="Camel"$ _),
  restCall(Method.DELETE, s"$apiSuffix/$name;format="norm"$s/$name;format="norm"$/:id", delete$name;format="Camel"$ _)
  )
  .withAutoAcl(true)
  .withExceptionSerializer(new DefaultExceptionSerializer(Environment.simple(mode = Mode.Prod)))
  // @formatter:on
}
}
