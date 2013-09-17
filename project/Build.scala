import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "laFranceHalal"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,

    // Selenium
    "org.seleniumhq.selenium" % "selenium-java" % "2.35.0" % "test",

    /*****************************************************************
     *
     *            Webjars
     *
     ****************************************************************/

    // WebJars
    "org.webjars" % "webjars-play" % "2.1.0-1",

    // Bootstrap
    "org.webjars" % "bootstrap" % "3.0.0",

    // Jquery
    "org.webjars" % "jquery" % "2.0.3",

    // Datatables
    "org.webjars" % "datatables" % "1.9.4-2"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
  )

}
