name := "right-to-be-forgotten"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

play.Project.playScalaSettings

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.4"

libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "2.41.0"

unmanagedSourceDirectories in Compile += baseDirectory.value / "googlesrc"
