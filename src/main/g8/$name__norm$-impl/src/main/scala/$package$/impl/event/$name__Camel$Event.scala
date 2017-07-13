/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$.impl.event

import $package$.api._
import com.inocybe.pfm.lib.service.inbound.v2.shared.ErrorResponse
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventShards, AggregateEventTag, AggregateEventTagger}
import play.api.libs.json.{Format, Json}

trait $name;format="Camel"$Event extends AggregateEvent[$name;format="Camel"$Event] {
  override def aggregateTag: AggregateEventTagger[$name;format="Camel"$Event] = $name;format="Camel"$Event.Tag
}

object $name;format="Camel"$Event {
  val NumShards = 4
  val Tag: AggregateEventShards[$name;format="Camel"$Event] = AggregateEventTag.sharded[$name;format="Camel"$Event](NumShards)
}

case class $name;format="Camel"$Created($name;format="norm"$: $name;format="Camel"$) extends $name;format="Camel"$Event
object $name;format="Camel"$Created {
  implicit val format: Format[$name;format="Camel"$Created] = Json.format[$name;format="Camel"$Created]
}

case class $name;format="Camel"$Updated($name;format="norm"$: $name;format="Camel"$) extends $name;format="Camel"$Event
object $name;format="Camel"$Updated {
  implicit val format: Format[$name;format="Camel"$Updated] = Json.format[$name;format="Camel"$Updated]
}

case class $name;format="Camel"$Deleted($name;format="norm"$: $name;format="Camel"$) extends $name;format="Camel"$Event
object $name;format="Camel"$Deleted {
  implicit val format: Format[$name;format="Camel"$Deleted] = Json.format[$name;format="Camel"$Deleted]
}

case class ErrorEvent(e: ErrorResponse) extends $name;format="Camel"$Event
object ErrorEvent {
  implicit val format: Format[ErrorEvent] = Json.format[ErrorEvent]
}
