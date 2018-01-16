name := """redis-test"""
organization := "com.tzimisce"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq (
  guice,
  cacheApi,
  "com.github.karelcemus" %% "play-redis" % "2.0.1"
)
