package es.upm.ging.EjecutaRegresor

import org.apache.spark.ml.regression.{LinearRegression, LinearRegressionModel}
import org.apache.spark.sql.SparkSession

/**
  * Created by peter on 2/14/17.
  */

object EjecutaRegresor {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().getOrCreate()
    spark.sparkContext.setLogLevel("OFF")
    val carga = new Carga(spark)

    // midf = carga.json()
    val midf = carga.mysql()
    midf.cache()

    // Imprimimos el esquema detectado
    midf.printSchema()

    // Construimos el modelo
    val iteraciones = 100
    val model = LinearRegression.train(midf, iteraciones)

    // Evaluar el modelo para el dataset de entrenamiento
    val valoresYPrediccion = midf.map { punto =>
      val prediccion = model.predict(punto.features)
      (punto.label, prediccion)
    }

    val MSE = valoresYPrediccion.map{case(v, p) => math.pow((v - p), 2)}.mean()
    println("Mean Squared Error del Entrenador = " + MSE)

    // Carga y guarda
    model.save(sc, "MiModeloLinReg")
    val MiModelo = LinearRegressionModel.load(sc, "MiModeloLinReg")

    println(valuesAndPreds.collect)
  }
}
