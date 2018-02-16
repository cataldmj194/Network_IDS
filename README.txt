Network Intrusion Detection System

Theory is that anomalous network traffic is correlated with being malicious.

Uses kddcupData to train K-means clustering algorithm to classify
anomalous traffic. Will use Spark's Streaming libraries to capture
and classify network traffic so as to provide real-time alerts on
network attacks.

NOTES: Currently submitting with
       spark-submit --class "Network_IDS" --master local[4] 
       target/Network-IDS-1.0.jar

       toSelf:
       For debugging possible concurrency issues, use
       --master local[1] and --master local[2] 
