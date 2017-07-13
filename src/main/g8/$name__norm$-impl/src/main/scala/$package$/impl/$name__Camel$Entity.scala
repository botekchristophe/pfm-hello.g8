/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$.impl

import java.util.UUID

import $package$.api._
import $package$.api.$name;format="Camel"$Ops._
import $package$.impl.command._
import $package$.impl.event._
import com.inocybe.pfm.lib.service.inbound.v2.shared.{ErrorResponse, ErrorResponses => ER}
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity
import org.slf4j.LoggerFactory
import cats.syntax.either._

object $name;format="Camel"$Entity {
  val id: String = $name;format="Camel"$Entity.getClass.toString
}

class $name;format="Camel"$Entity extends PersistentEntity {

  override type Command = $name;format="Camel"$Command[_]
  override type Event = $name;format="Camel"$Event
  override type State = $name;format="Camel"$State

  type OnCommandHandler[M] = PartialFunction[(Command, CommandContext[M], State), Persist]
  type ReadOnlyHandler[M] = PartialFunction[(Command, ReadOnlyCommandContext[M], State), Unit]

  /**
    * The initial state. This is used if there is no snapshotted state to be found.
    */
  override def initialState: $name;format="Camel"$State = $name;format="Camel"$State(Set.empty[$name;format="Camel"$])

  var currentState: $name;format="Camel"$State = initialState

  private val log = LoggerFactory.getLogger(classOf[$name;format="Camel"$Entity])

  /**
    * An entity can define different behaviours for different states, so the behaviour
    * is a function of the current state to a set of actions.
    */
  override def behavior: Behavior = {
    case $name;format="Camel"$State(message) => Actions()
      .onReadOnlyCommand[Get$name;format="Camel"$Ids.type, Set[UUID]] {
      readOnlyCommandHandler.asInstanceOf[ReadOnlyHandler[Set[UUID]]]

    }.onReadOnlyCommand[GetState.type, $name;format="Camel"$State] {
      readOnlyCommandHandler.asInstanceOf[ReadOnlyHandler[$name;format="Camel"$State]]

    }.onReadOnlyCommand[Get$name;format="Camel"$, Either[ErrorResponse, $name;format="Camel"$]] {
      readOnlyCommandHandler.asInstanceOf[ReadOnlyHandler[Either[ErrorResponse, $name;format="Camel"$]]]

    }
      .onCommand[Add$name;format="Camel"$, Either[ErrorResponse, $name;format="Camel"$]] { onCommandHandler }
      .onCommand[Update$name;format="Camel"$, Either[ErrorResponse, $name;format="Camel"$]] { onCommandHandler }
      .onCommand[Delete$name;format="Camel"$, Either[ErrorResponse, $name;format="Camel"$]] { onCommandHandler }
      .onEvent { eventHandler }
  }

  /**
    * Manage all readOnlyCommands received.
    */
  val readOnlyCommandHandler: ReadOnlyHandler[Any] = {
    case (Get$name;format="Camel"$Ids, ctx, state) =>
      ctx.reply(state.$name;format="norm"$s.map(_.id))

    case (GetState, ctx, state) =>
      ctx.reply(state)

    case (Get$name;format="Camel"$($name;format="norm"$Id), ctx, state) =>
      val $name;format="norm"$ = state.$name;format="norm"$s.find(_.id == $name;format="norm"$Id)
        .toRight(ER.NotFound($name;format="Camel"$ +" not found with id=" + $name;format="norm"$Id))
      ctx.reply($name;format="norm"$)
  }

  /**
    * manage all command received.
    */
  val onCommandHandler: OnCommandHandler[Either[ErrorResponse, $name;format="Camel"$]] = {
    case (Add$name;format="Camel"$($name;format="norm"$), ctx, state) =>
      val response = state.$name;format="norm"$s.find(_.name == $name;format="norm"$.name)
        .toLeft($name;format="norm"$)
        .leftMap(_ => ER.Conflict("$name;format="Camel"$ already exists."))
      response.fold(
        { e => ctx.thenPersist(ErrorEvent(e)) {_ => ctx.reply(response) }},
        { r => ctx.thenPersist($name;format="Camel"$Created(r)) {_ => ctx.reply(response) }})

    case (Update$name;format="Camel"$(id, $name;format="norm"$), ctx, state) =>
      val response = state.$name;format="norm"$s.find(_.id == id)
        .toRight(ER.NotFound("$name;format="Camel"$ not found."))
      response.fold(
        { e => ctx.thenPersist(ErrorEvent(e)) {_ => ctx.reply(response) }},
        { r => ctx.thenPersist($name;format="Camel"$Updated(r.update($name;format="norm"$))) {_ => ctx.reply(response) }})

    case (Delete$name;format="Camel"$(id), ctx, state) =>
      val response = state.$name;format="norm"$s.find(_.id == id)
        .toRight(ER.NotFound("$name;format="Camel"$ not found."))
      response.fold(
        { e => ctx.thenPersist(ErrorEvent(e)) {_ => ctx.reply(response) }},
        { $name;format="norm"$ => ctx.thenPersist($name;format="Camel"$Deleted($name;format="norm"$)) {_ => ctx.reply(response) }})
  }


  /**
    * Manage all event received
    */
  val eventHandler: EventHandler = {
    case ($name;format="Camel"$Created($name;format="norm"$), state) =>
      log.info("Persistence actor received a CREATED EVENT")
      state.copy($name;format="norm"$s = state.$name;format="norm"$s + $name;format="norm"$)

    case ($name;format="Camel"$Deleted(a), state) =>
      log.info("Persistence actor received a DELETE EVENT")
      state.copy($name;format="norm"$s = state.$name;format="norm"$s.filterNot($name;format="norm"$ => $name;format="norm"$.id == a.id))

    case ($name;format="Camel"$Updated(a), state) =>
      log.info("Persistence actor received a UPDATE EVENT")
      val $name;format="norm"$s = state.$name;format="norm"$s.filterNot($name;format="norm"$ => $name;format="norm"$.id == a.id)
      state.copy($name;format="norm"$s = $name;format="norm"$s + a)

    case (ErrorEvent(e), _) =>
      log.info(e.toString)
      currentState
  }
}
