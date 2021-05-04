package org.cms.events.apis.config;

import java.util.Properties;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerConfigs {

	private final Properties properties;
	private final String topic;

	public ProducerConfigs(Properties properties, String topic) {
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
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
