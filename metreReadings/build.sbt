ThisBuild / scalaVersion := "2.13.2"

lazy val root = (project in file("."))
  .settings(
    name := "metreReadings",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.0" % Test
    )
  )