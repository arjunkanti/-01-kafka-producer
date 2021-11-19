package io.classpath.kafka.examples.config;

public class AppConfig {
    public final static String applicationID = "Producer-Demo";
    public final static String bootstrapServers = "broker-1:9092,broker-2:9092,broker-3:9092";
    public final static String topicName = "test-producer";
    public final static int numEvents = 1000;
}
