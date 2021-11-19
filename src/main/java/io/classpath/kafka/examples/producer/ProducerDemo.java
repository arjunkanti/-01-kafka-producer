package io.classpath.kafka.examples.producer;

import io.classpath.kafka.examples.config.AppConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Properties;

public class ProducerDemo {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Creating Kafka Producer...");
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfig.applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);

        logger.info("Start sending messages...");
        String message = null;
        for (int i = 1; i <= AppConfig.numEvents; i++) {
             message = "Message-" + i;
            producer.send(new ProducerRecord<>(AppConfig.topicName, i, message));
            logger.info("Successfully sent message to Broker:: "+ message);
        }

        logger.info("Finished - Closing Kafka Producer.");
        producer.close();
    }
}