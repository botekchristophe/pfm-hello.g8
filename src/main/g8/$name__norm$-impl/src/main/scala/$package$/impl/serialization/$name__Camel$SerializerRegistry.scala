/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$.impl.serialization

import $package$.impl.command._
import $package$.impl.event._
import $package$.api._
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}

import scala.collection.immutable.Seq

/**
  * Akka serialization, used by both persistence and remoting, needs to have
  * serializers registered for every type serialized or deserialized. While it's
  * possible to use any serializer you want for Akka messages, out of the box
  * Lagom provides support for JSON, via this registry abstraction.
  *
  * The serializers are registered here, and then provided to Lagom in the
  * application loader.
  */
object $name;format="Camel"$SerializerRegistry extends JsonSerializerRegistry {
  override def serializers: Seq[JsonSerializer[_]] = Seq(
    // Command
    JsonSerializer[Add$name;format="Camel"$],
    JsonSerializer[Get$name;format="Camel"$],
    JsonSerializer[Update$name;format="Camel"$],
    JsonSerializer[Delete$name;format="Camel"$],
    // Event
    JsonSerializer[$name;format="Camel"$Created],
    JsonSerializer[$name;format="Camel"$Updated],
    JsonSerializer[$name;format="Camel"$Deleted],
    // Model
    JsonSerializer[$name;format="Camel"$State],
    JsonSerializer[$name;format="Camel"$]
  )
}
