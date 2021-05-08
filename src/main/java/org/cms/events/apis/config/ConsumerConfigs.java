package org.cms.events.apis.config;

import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumerConfigs {

	private final Properties properties;
	private final String topic;

	public ConsumerConfigs(Properties properties, String topic) {
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		this.properties = properties;
		this.topic = topic;
	}

	public Properties getProperties() {
		return properties;
	}

	public String getTopic() {
		return topic;
	}
}
