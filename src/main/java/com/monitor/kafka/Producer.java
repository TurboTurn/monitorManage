package com.monitor.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


import java.util.Properties;
import java.util.Scanner;

public class Producer {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "stephenyi.cn:9092");
		properties.put("acks", "all");
		properties.put("retries", 0);
		properties.put("batch.size", 16384);
		properties.put("linger.ms", 1);
		properties.put("buffer.memory", 33554432);
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()){
			String msg = sc.nextLine();
			producer.send(new ProducerRecord<>("test", "第一条消息"));
		}

	}
}
