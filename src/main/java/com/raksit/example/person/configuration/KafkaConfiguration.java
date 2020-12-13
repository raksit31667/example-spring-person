package com.raksit.example.person.configuration;

import com.raksit.example.person.event.PersonCreatedEvent;
import com.raksit.example.person.event.PersonNameUpdatedEvent;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafkaConfiguration {

  @Value("${spring.kafka.consumer.group-id}")
  private String groupId;

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrap;

  @Bean
  public Map<String, Object> consumerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
    return props;
  }

  @Bean
  public ConsumerFactory<String, PersonCreatedEvent> personCreatedConsumerFactory() {
    JsonDeserializer<PersonCreatedEvent> deserializer = new JsonDeserializer<>(PersonCreatedEvent.class);
    deserializer.addTrustedPackages("*");
    deserializer.ignoreTypeHeaders();
    return new DefaultKafkaConsumerFactory<>(
        consumerConfigs(), new StringDeserializer(), deserializer);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, PersonCreatedEvent> personCreatedKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, PersonCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(personCreatedConsumerFactory());

    return factory;
  }

  @Bean
  public ConsumerFactory<String, PersonNameUpdatedEvent> personNameUpdatedConsumerFactory() {
    JsonDeserializer<PersonNameUpdatedEvent> deserializer = new JsonDeserializer<>(PersonNameUpdatedEvent.class);
    deserializer.addTrustedPackages("*");
    deserializer.ignoreTypeHeaders();
    return new DefaultKafkaConsumerFactory<>(
        consumerConfigs(), new StringDeserializer(), deserializer);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, PersonNameUpdatedEvent> personNameUpdatedKafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, PersonNameUpdatedEvent> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(personNameUpdatedConsumerFactory());

    return factory;
  }
}
