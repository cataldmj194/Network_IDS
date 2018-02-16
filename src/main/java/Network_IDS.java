/** Name: Michael Cataldo
 *  Date: 2/16/2018
 *  Project: Network Intrustion Detection System using Apache Spark's
 *           Machine Learning (in particular, k-means clustering) 
 *           and Streaming Libraries.
 **/
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;

public class Network_IDS {
  public static void main(String[] args){
    SparkSession spark = 
                 SparkSession.builder().appName("NIDS").getOrCreate();
    System.out.println("Hello World!");
    spark.stop();
  }
}
