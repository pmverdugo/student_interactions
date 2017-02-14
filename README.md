# student_interactions (WIP)

A Spark-based cluster-ready tool for performing lineal multivariable regression on student interaction datasets

## Dependencies
* Scala 2.11.8
* Spark 2.1.0
* SBT 0.13.13

## Compile
```bash
sbt package
```

## Run
```bash
spark-submit --master local[*] target/scala-2.11/student_interactions-1.0.jar
```
