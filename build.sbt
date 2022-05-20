

Global / onChangedBuildSource := ReloadOnSourceChanges

lazy val hello = (project in file("."))
  .settings(
    name := "roguelike",
    scalaVersion := "2.13.8",

    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % Test,
    scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature"),
  )

