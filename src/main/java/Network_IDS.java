/** Name: Michael Cataldo
 *  Date: 2/16/2018
 *  Project: Network Intrustion Detection System using Apache Spark's
 *           Machine Learning (in particular, k-means clustering) 
 *           and Streaming Libraries.
 **/
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.Iterator;
import java.util.Arrays;

public class Network_IDS {
  public static void main(String[] args) throws org.apache.spark
    .sql.streaming.StreamingQueryException {

    String kddcup = "src/main/resources/kddcup99/kddcup.data_10_percent";
    SparkSession spark = SparkSession
      .builder()
      .appName("NIDS")
      .getOrCreate();

    Dataset<String> kddData = spark.read().textFile(kddcup).cache();  

    Dataset<Row> lines = spark
      .readStream().format("socket")
      .option("host","localhost")
      .option("port", 9999)
      .load();

    Dataset<String> words = lines
      .as(Encoders.STRING())
      .flatMap((FlatMapFunction<String, String>) x -> Arrays.asList(x.split(" ")).iterator(), Encoders.STRING());

    Dataset<Row> wordCounts = lines.groupBy("value").count();
    StreamingQuery query = wordCounts.writeStream()
      .outputMode("complete")
      .format("console")
      .start();

    query.awaitTermination();

    spark.stop();
  }
}
