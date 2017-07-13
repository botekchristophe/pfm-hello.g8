/**
  * Copyright (C) 2017 Inocybe Technologies inc.
  */

package $package$.impl

import java.util.UUID

import $package$.impl.command._
import $package$.impl._
import com.inocybe.pfm.lib.service.RestService
import com.inocybe.pfm.lib.service.inbound.v2.authentication.AuthenticationService
import com.inocybe.pfm.lib.service.inbound.v2.authorization.AuthorizationService
import com.inocybe.pfm.lib.service.inbound.v2.shared.ErrorResponse
import com.inocybe.pfm.lib.service.inbound.v2.shared.Plans.{Admin, Free}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import com.lightbend.lagom.scaladsl.server.ServerServiceCall

import scala.concurrent.{ExecutionContext, Future}

/**
  * Implementation of the $name;format="Camel"$Service.
  */
class $name;format="Camel"$ServiceImpl(persistentEntityRegistry: PersistentEntityRegistry,
                            authenticationService: AuthenticationService, authorizationService: AuthorizationService)
                           (implicit ec: ExecutionContext) extends $name;format="Camel"$Service with RestService {

  private def ref = persistentEntityRegistry.refFor[$name;format="Camel"$Entity]($name;format="Camel"$Entity.id)

  implicit val auth: AuthenticationService = authenticationService
  implicit val authz: AuthorizationService = authorizationService

  override def getState: ServiceCall[NotUsed, Either[ErrorResponse, $name;format="Camel"$State]] =
    oAuthorized(oauth_id, oauth_secret)(auth =>
      ServerServiceCall((_, _) =>
        auth.fold[Future[Either[ErrorResponse, $name;format="Camel"$State]]](
          e => Future.successful(Left(e)), info => ref.ask(GetState).map(Right(_)))
          .map(_.marshall)
      )
    )

  override def get$name;format="Camel"$Ids: ServiceCall[NotUsed, Either[ErrorResponse, Iterable[UUID]]] =
    authenticated(Free)(auth =>
      ServerServiceCall((_, _) =>
        auth
          .flatMap(_.fold[Future[Either[ErrorResponse, Iterable[UUID]]]](
            e => Future.successful(Left(e)),
            info => ref.ask(Get$name;format="Camel"$Ids).map(Right(_))))
          .map(_.marshall)
      )
    )

  override def create$name;format="Camel"$: ServiceCall[$name;format="Camel"$, Either[ErrorResponse, $name;format="Camel"$]] =
    oAuthorized(oauth_id, oauth_secret)(auth =>
      ServerServiceCall((_, $name;format="norm"$) =>
        auth
          .fold(e => Future.successful(Left(e)), info => ref.ask(Add$name;format="Camel"$($name;format="norm"$)))
          .map(_.marshall)
      )
    )

  override def read$name;format="Camel"$(id: UUID): ServiceCall[NotUsed, Either[ErrorResponse, $name;format="Camel"$]] =
    authenticated(Free)(auth =>
      ServerServiceCall((_, _) =>
        auth
          .flatMap(_.fold(e => Future.successful(Left(e)), info => ref.ask(Get$name;format="Camel"$(id))))
          .map(_.marshall)
      )
    )

  override def update$name;format="Camel"$(id: UUID): ServiceCall[$name;format="Camel"$, Either[ErrorResponse, $name;format="Camel"$]] =
    authenticated(Admin)(auth =>
      ServerServiceCall((_, $name;format="norm"$) =>
        auth
          .flatMap(_.fold(e => Future.successful(Left(e)), info => ref.ask(Update$name;format="Camel"$(id, $name;format="norm"$))))
          .map(_.marshall)
      )
    )

  override def delete$name;format="Camel"$(id: UUID): ServiceCall[NotUsed, Either[ErrorResponse, $name;format="Camel"$]] =
    authenticated(Admin)(auth =>
      ServerServiceCall((_, _) =>
        auth
          .flatMap(_.fold(e => Future.successful(Left(e)), info => ref.ask(Delete$name;format="Camel"$(id))))
          .map(_.marshall)
      )
    )
}

