import sbt._

name         := "sample-ssr-api"
organization := "io.github.takapi327"

ThisBuild / organizationName := "Takahiko Tominaga"

ThisBuild / scalaVersion := "2.13.3"

version := "1.0.0"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-Ywarn-dead-code",
  "-Ymacro-annotations"
)

javaOptions ++= Seq(
  "-Dconfig.file=conf/env.dev/application.conf",
  "-Dconfig.file=conf/env.dev/logback.xml"
)

Universal / javaOptions ++= Seq(
  "Dpidfile.path=/dev/null"
)

run / fork := true
