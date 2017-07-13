/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$.impl

import com.inocybe.pfm.lib.service.inbound.v2.authentication.AuthenticationService
import com.inocybe.pfm.lib.service.inbound.v2.authorization.AuthorizationService
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.client.ConfigurationServiceLocatorComponents
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import $package$.api.$name;format="Camel"$Service
import $package$.impl.serialization._
import com.lightbend.lagom.scaladsl.broker.kafka.LagomKafkaComponents
import com.softwaremill.macwire._
import play.api.libs.ws.ahc.AhcWSComponents

class $name;format="Camel"$Loader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new $name;format="Camel"$Application(context) with ConfigurationServiceLocatorComponents

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new $name;format="Camel"$Application(context) with LagomDevModeComponents

  override def describeServices = List(
    readDescriptor[$name;format="Camel"$Service]
  )
}

abstract class $name;format="Camel"$Application(context: LagomApplicationContext)
  extends LagomApplication(context)
    with CassandraPersistenceComponents
    with LagomKafkaComponents
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer: LagomServer = serverFor[$name;format="Camel"$Service](wire[$name;format="Camel"$ServiceImpl])

  // Register the JSON serializer registry
  override lazy val jsonSerializerRegistry = $name;format="Camel"$SerializerRegistry

  // Register the $name$ persistent entity
  persistentEntityRegistry.register(wire[$name;format="Camel"$Entity])
  readSide.register(wire[$name;format="Camel"$ReadSideProcessor])
  lazy val authenticationService: AuthenticationService = serviceClient.implement[AuthenticationService]
  lazy val authorizationService: AuthorizationService = serviceClient.implement[AuthorizationService]
}
