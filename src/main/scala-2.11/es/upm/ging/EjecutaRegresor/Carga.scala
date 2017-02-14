package es.upm.ging.EjecutaRegresor

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Created by peter on 1/31/17.
  * Echar un vistazo a http://www.cakesolutions.net/teamblogs/spark-mllib-linear-regression-example-and-vocabulary
  */


class Carga(sc: SparkSession) {

  def mysql(): DataFrame => {

    val sqlcontext = new SQLContext(sc)
    sqlcontext.read.
    format("jdbc").option("url", "jdbc:mysql://SERVIDOR_VISH:3306/DB_NAME").
    option("driver", "com.mysql.jdbc.Driver").
    option("dbtable", "interacciones").
    option("user", "ginguser").
    option("password", "ginguser").
    load()
  }

  def json(): DataFrame => {
    val path = "interacciones.json"
    val peopleDF = spark.read.json(path)

  }
}
