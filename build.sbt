name := "testprogs"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "net.liftweb"   %% "lift-json"   % "2.6",
  "org.json4s" % "json4s-native_2.11" % "3.4.0",
  "org.apache.spark" %% "spark-core" % "2.0.0-preview"
)
    