package com.ms.kafkapoc.config;


import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.kafkapoc.model.Customer;


public class CustomerSerializer implements Serializer<Customer> {

  private final ObjectMapper objectMapper = new ObjectMapper();


  @Override
  public byte[] serialize (String topic, Customer data) {

    try {
      return objectMapper.writeValueAsBytes(data);
    } catch (Exception e) {
      throw new RuntimeException("Error serializing Customer", e);
    }
  }

}
