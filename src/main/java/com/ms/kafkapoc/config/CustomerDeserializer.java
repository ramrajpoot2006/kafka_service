package com.ms.kafkapoc.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.kafkapoc.model.Customer;
import org.apache.kafka.common.serialization.Deserializer;


public class CustomerDeserializer implements Deserializer<Customer> {

  private final ObjectMapper objectMapper = new ObjectMapper();


  @Override
  public Customer deserialize (String topic, byte[] data) {

    try {
      return objectMapper.readValue(data, Customer.class);
    } catch (Exception e) {
      throw new RuntimeException("Error deserializing Customer", e);
    }
  }
}
