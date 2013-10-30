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

    // selenium
    "org.seleniumhq.selenium" % "selenium-java" % "2.35.0" % "test",

    // superCsv
    "net.sf.supercsv" % "super-csv" % "2.1.0",

    // i18n javascripts
    "com.github.julienrf" %% "play-jsmessages" % "1.5.0",

    /*****************************************************************
     *
     *            Webjars
     *
     ****************************************************************/

    // webJars
    "org.webjars" % "webjars-play" % "2.1.0-1",

    // twitter bootstrap
    "org.webjars" % "bootstrap" % "3.0.0",

    // datatables twitter bootstrap styles
    "org.webjars" % "datatables-bootstrap" % "2-20120201-1",

    // jquery
    "org.webjars" % "jquery" % "2.0.3",

    // datatables
    "org.webjars" % "datatables" % "1.9.4-2",

    // fontAwesome
    "org.webjars" % "font-awesome" % "3.2.1",

    // Bootstrap Date Timepicker
    "org.webjars" % "bootstrap-datetimepicker" % "6aa746736d"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Resolver pour le module i18n javascripts
    resolvers += "julienrf.github.com" at "http://julienrf.github.com/repo/",

    // require.js
    requireJs += "admin/restaurant.list.js",
    requireJs += "admin/restaurant.edit.js"

    // debug in test
    //, Keys.fork in (Test) := true
    //, javaOptions in (Test) += "-Xdebug"
    //, javaOptions in (Test) += "-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=9998"
  )

}
