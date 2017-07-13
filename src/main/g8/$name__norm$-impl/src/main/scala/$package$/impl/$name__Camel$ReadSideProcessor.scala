/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$.impl

import com.datastax.driver.core.PreparedStatement
import $package$.api.$name;format="Camel"$Cassandra
import $package$.impl.event._
import com.lightbend.lagom.scaladsl.persistence.ReadSideProcessor.ReadSideHandler
import com.lightbend.lagom.scaladsl.persistence.cassandra.{CassandraReadSide, CassandraSession}
import com.lightbend.lagom.scaladsl.persistence.{AggregateEventTag, EventStreamElement, ReadSideProcessor}
import org.slf4j.LoggerFactory

import scala.concurrent.{ExecutionContext, Future}

class $name;format="Camel"$ReadSideProcessor(readSide: CassandraReadSide, session: CassandraSession)(implicit ec: ExecutionContext)
  extends ReadSideProcessor[$name;format="Camel"$Event] {

  private val log = LoggerFactory.getLogger(classOf[$name;format="Camel"$ReadSideProcessor])

  private var insert$name;format="Camel"$Statement: PreparedStatement = _
  private var update$name;format="Camel"$Statement: PreparedStatement = _
  private var delete$name;format="Camel"$Statement: PreparedStatement = _


  def buildHandler: ReadSideHandler[$name;format="Camel"$Event] = {
    readSide.builder[$name;format="Camel"$Event]("$name;format="norm"$sOffset")
      .setGlobalPrepare(createTable)
      .setPrepare { tag =>
        prepareStatements()
      }.setEventHandler[$name;format="Camel"$Created](insert$name;format="Camel"$)
      .setEventHandler[$name;format="Camel"$Deleted](delete$name;format="Camel"$)
      .setEventHandler[$name;format="Camel"$Updated](update$name;format="Camel"$)
      .build()
  }

  private def createTable(): Future[Done] = {
    log.debug(s"CREATE TABLE: " + $name;format="Camel"$Cassandra.createTable)
    for {
      _ <- session.executeCreateTable($name;format="Camel"$Cassandra.createTable)
    } yield Done
  }

  private def prepareStatements(): Future[Done] = {
    for {
      insert <- session.prepare($name;format="Camel"$Cassandra.insert)
      delete <- session.prepare($name;format="Camel"$Cassandra.delete)
      update <- session.prepare($name;format="Camel"$Cassandra.update)
    } yield {
      insert$name;format="Camel"$Statement = insert
      log.debug(s"INSERT: " + $name;format="Camel"$Cassandra.insert)
      update$name;format="Camel"$Statement = update
      log.debug(s"UPDATE: " + $name;format="Camel"$Cassandra.update)
      delete$name;format="Camel"$Statement = delete
      log.debug(s"DELETE: " + $name;format="Camel"$Cassandra.delete)
      Done
    }
  }

  private def insert$name;format="Camel"$(created: EventStreamElement[$name;format="Camel"$Created]) = {
    log.info("Cassandra received INSERT event")
    Future.successful {
      val r = created.event.$name;format="norm"$
      List(insert$name;format="Camel"$Statement.bind(
        r.id.toString,
        r.name,
        r.description
      ))
    }
  }

  private def delete$name;format="Camel"$(deleted: EventStreamElement[$name;format="Camel"$Deleted]) = {
    log.info("Cassandra received DELETE event")
    Future.successful(
      List(delete$name;format="Camel"$Statement.bind(deleted.event.$name;format="norm"$.id.toString)
      ))
  }

  private def update$name;format="Camel"$(updated: EventStreamElement[$name;format="Camel"$Updated]) = {
    log.info("Cassandra received UPDATE event")
    Future.successful {
      val r = updated.event.$name;format="norm"$
      List(update$name;format="Camel"$Statement.bind(
        r.name,
        r.description,
        r.id.toString
      ))
    }
  }

  override def aggregateTags: Set[AggregateEventTag[$name;format="Camel"$Event]] = $name;format="Camel"$Event.Tag.allTags
}



