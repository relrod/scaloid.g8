import sbt._
import Keys._
import AndroidKeys._

object ScaloidBuild extends Build {
  val androidVersionCode = $versioncode$

  val androidProguard = """
    -target 6
    -keep class scala.collection.SeqLike { public protected *; }
  """

  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = Defaults.defaultSettings ++ Seq(
      version               := "1.0." + androidVersionCode,
      organization          := "$organization$",
      scalaVersion          := "2.10.1",
      libraryDependencies   += "org.scaloid" % "scaloid" % "1.1_8_2.10",
      versionCode           := androidVersionCode,
      platformName in Android   := "android-$androidapi$",
      useProguard in Android    := true,
      proguardOption in Android := androidProguard,
      keyalias in Android   := "change-me",
      scalacOptions         := Seq(
        "-encoding", "utf8",
        "-target:jvm-1.6"
      ),
      javacOptions          ++= Seq(
        "-encoding", "utf8",
        "-source", "1.6",
        "-target", "1.6")
    ) ++
    AndroidProject.androidSettings ++
    TypedResources.settings ++
    AndroidManifestGenerator.settings ++
    AndroidMarketPublish.settings
  )
}
