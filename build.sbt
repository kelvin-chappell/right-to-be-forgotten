name := "right-to-be-forgotten"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.scalatest" %% "scalatest" % "2.1.4",
  "org.seleniumhq.selenium" % "selenium-java" % "2.41.0",
  "org.apache.opennlp" % "opennlp-tools" % "1.5.3"
)

play.Project.playScalaSettings
