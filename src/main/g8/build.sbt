
organization in ThisBuild := "$organization$"

version in ThisBuild := "$version$"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test
val pfmLib = "com.inocybe.pfm" %% "pfm-lib" % "0.0.93"

lazy val `$name;format="norm"$` = (project in file("."))
  .aggregate(`$name;format="norm"$-api`, `$name;format="norm"$-impl`)

lazy val `$name;format="norm"$-api` = (project in file("$name;format="norm"$-api"))
.settings(
  resolvers += "Artifactory Realm" at "https://inocybe.jfrog.io/inocybe/pfm-libraries",
  libraryDependencies ++= Seq(
    lagomScaladslApi,
    pfmLib
  )
)

lazy val `$name;format="norm"$-impl` = (project in file("$name;format="norm"$-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`$name;format="norm"$-api`)

lagomUnmanagedServices in ThisBuild := Map(
  "authentication-service" -> "https://pfm-dev-master.inocybe.com:443",
  "authorization-service" -> "https://pfm-dev-master.inocybe.com:443")