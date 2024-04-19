val scala3Version = "3.4.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Inventory System",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq("org.scalameta" %% "munit" % "0.7.29" % Test,
    "com.github.tototoshi" %% "scala-csv" % "1.3.10",
    "com.github.nscala-time" %% "nscala-time" % "2.32.0")
  )