package com.ms.kafkapoc.config;


import com.ms.kafkapoc.model.Customer;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
//import org.springframework.kafka.listener.ContainerProperties;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class KafkaConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  //@Value("${spring.kafka.consumer.group-id.name}")
  //private String groupId;

  @Bean
  public ProducerFactory<String, String> producerFactory () {

    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    //configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);//Fix: Prevent type headers issue

    // Important fix: Ensure JSON serializer is explicitly used
    return new DefaultKafkaProducerFactory<>(configProps);
  }


  @Bean
  public KafkaTemplate<String, String> kafkaTemplate () {

    return new KafkaTemplate<>(producerFactory());
  }

/*
  @Bean
  public ProducerFactory<String, Customer> producerFactory () {

    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);//Fix: Prevent type headers issue

    // Important fix: Ensure JSON serializer is explicitly used
    return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), new JsonSerializer<>());
  }


  @Bean
  public KafkaTemplate<String, Customer> kafkaTemplate () {

    return new KafkaTemplate<>(producerFactory());
  }

 */

/*
  @Bean
  public ConsumerFactory<String, Customer> consumerFactory () {

    JsonDeserializer<Customer> deserializer = new JsonDeserializer<>(Customer.class);
    deserializer.setRemoveTypeHeaders(false);
    deserializer.addTrustedPackages("*");//Allow deserialization of custom objects
    deserializer.setUseTypeMapperForKey(false);

    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
  }


  @Bean
  public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory () {

    ConcurrentKafkaListenerContainerFactory<String, Customer> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());

    // ✅ Ensure manual acknowledgment mode is enabled
    //ensures that the message is acknowledged as soon as the listener processes it.
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

    return factory;
  }

 */


}
