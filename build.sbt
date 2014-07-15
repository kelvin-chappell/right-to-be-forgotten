import sbt.Keys._

name := "right-to-be-forgotten"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.scalatest" %% "scalatest" % "2.1.4",
  "org.seleniumhq.selenium" % "selenium-java" % "2.41.0"
)

play.Project.playScalaSettings
