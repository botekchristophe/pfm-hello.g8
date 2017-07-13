/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$.impl.command

import java.util.UUID

import $package$.api.{$name;format="Camel"$, $name;format="Camel"$State}
import com.inocybe.pfm.lib.service.inbound.v2.shared.ErrorResponse
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType

/**
  * This interface defines all the commands that the $name;format="Camel"$ entity supports.
  */
sealed trait $name;format="Camel"$Command[R] extends ReplyType[R]

case object Get$name;format="Camel"$Ids extends $name;format="Camel"$Command[Set[UUID]]
case object GetState extends $name;format="Camel"$Command[$name;format="Camel"$State]

case class $name;format="Camel"$$name;format="Camel"$($name;format="norm"$: $name;format="Camel"$) extends $name;format="Camel"$Command[Either[ErrorResponse, $name;format="Camel"$]]
object Add$name;format="Camel"$ {
  implicit val format: Format[Add$name;format="Camel"$] = Json.format[Add$name;format="Camel"$]
}

case class Get$name;format="Camel"$(id: UUID) extends $name;format="Camel"$Command[Either[ErrorResponse, $name;format="Camel"$]]
object Get$name;format="Camel"$ {
  implicit val format: Format[Get$name;format="Camel"$] = Json.format[Get$name;format="Camel"$]
}

case class Update$name;format="Camel"$(id: UUID, $name;format="norm"$: $name;format="Camel"$) extends $name;format="Camel"$Command[Either[ErrorResponse, $name;format="Camel"$]]
object Update$name;format="Camel"$ {
  implicit val format: Format[Update$name;format="Camel"$] = Json.format[Update$name;format="Camel"$]
}

case class Delete$name;format="Camel"$(id: UUID) extends $name;format="Camel"$Command[Either[ErrorResponse, $name;format="Camel"$]]
object Delete$name;format="Camel"$ {
  implicit val format: Format[Delete$name;format="Camel"$] = Json.format[Delete$name;format="Camel"$]
}
