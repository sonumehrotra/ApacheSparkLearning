/**
  * Created by sonu on 22/7/16.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object SparkAssignment {

  def sparkFlow: Unit = {

    val conf = new SparkConf().setAppName("newApp").setMaster("local")
    val sc = new SparkContext(conf)

    // 1) Rdd Names pagecounts
    val pagecounts = sc.textFile("/home/sonu/workspace/testprogs/pagecounts")

    // 2) Getting 10 records from the RDD and printing them
    val tenLines = pagecounts.take(10).toList
    tenLines foreach (line => println(line))

    // 3) Finding total number of records in data set and printing them
    val total = pagecounts.count()
    println("total number of records " + total)

    // 4) RDD containing only English pages
    val englishRdd = pagecounts.filter {
      line => {
        val firstCol = line.split(" ")(0)
        firstCol equals "en"
      }
    }


    // 5) Number of records for the English pages
    val englishRecords = englishRdd.count()
    println("Number of english records >>> " + englishRecords)

    // 6) Finding pages with more than 200,000 requests
    val pageNameCountPair = pagecounts.map{
      line => {
        val column = line.split(" ")
        (column(1), column(2).toLong)
      }
    }

    val countAdd = pageNameCountPair.reduceByKey((x,y) => x+y)
    val highCount = countAdd.filter(line => line._2 > 200000L).count()
    println("High searched pages >> " + highCount)


  }
}

object SparkAssignmentApp extends App {

  val sparkAssignment = SparkAssignment
  sparkAssignment.sparkFlow
}
