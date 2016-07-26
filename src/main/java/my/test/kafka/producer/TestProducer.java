package my.test.kafka.producer;

import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import scala.Function1;
import scala.Tuple2;
import scala.collection.Map;

import java.util.Properties;

/**
 * Created by kkulagin on 7/26/2016.
 */
public class TestProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.11.18.30:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            for (int i = 0; i < 100; i++) {
                producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i)));
            }
        }


//        ZkUtils zkUtils = ZkUtils.apply(new ZkClient("10.11.18.17:2181", Integer.MAX_VALUE, 10000, ZKStringSerializer$.MODULE$), false);
//        Map<String, Properties> propertiesMap = AdminUtils.fetchAllTopicConfigs(zkUtils);
//        AdminUtils.deleteTopic(zkUtils, "foo");
//        System.out.println("yo");
    }
}
