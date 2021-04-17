import java.text.SimpleDateFormat
import java.util.Date

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.types.{StringType, StructField, StructType}


object hive_test {
  def main(args: Array[String]): Unit = {
    def NowDate(): String = {
      val now: Date = new Date()
      //      val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
      val date = dateFormat.format(now)
      return date
    }
    println(NowDate())
  }
  val ss = SparkSession.builder().master("local[2]").appName("the test of SparkSession").enableHiveSupport().getOrCreate()
  val df = ss.sql("show databases")
  df.show
  ss.stop()
}
