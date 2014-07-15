import sbt.Keys._

name := "right-to-be-forgotten"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.scalatest" %% "scalatest" % "2.1.4",
  "org.seleniumhq.selenium" % "selenium-java" % "2.41.0",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.3.1",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.3.1" classifier "models"
)

play.Project.playScalaSettings ++ Seq(
  javaOptions ++= Seq("-Xms6G", "-Xmx14G", "-XX:-UseGCOverheadLimit")
)
