import java.io.StringReader

import au.com.bytecode.opencsv.CSVReader
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf


object scala_test {
  def main(args: Array[String]): Unit = {
    val inputFile =  "hdfs://master:9000/flume/20210407/test.csv"
//    val conf = new SparkConf().setAppName("scala_test").setMaster("local").set("spark.testing.memory", "400000000")
    val conf = new SparkConf().setAppName("scala_test").setMaster("local")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile(inputFile)
    textFile.collect().foreach(println)
    val result = textFile.map{ line =>
      val reader = new CSVReader(new StringReader(line));
      reader.readNext()
    }
    println(result.getClass)
    result.collect().foreach(x => {x.foreach(println);println("=======")})
//    val wordCount = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)
//    wordCount.foreach(println)
//    printf("hello scala")
  }
}
