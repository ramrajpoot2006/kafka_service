package com.ms.kafkapoc.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

  @Value("${spring.kafka.topic.name}")
  private String topic;

  private final KafkaTemplate<String, String> kafkaTemplate;


  public boolean sendMessage (String message) {
    //kafkaTemplate.send(topic, customer.getCustomerId(), customer);

    CompletableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send(
        topic,
        message
    );
    try {
      SendResult<String, String> result = sendResult.get(); // Blocks until result is available
      System.out.println("Message sent to partition: " + result.getRecordMetadata().partition());
      log.info("Sent message: {}", message);
    } catch (Exception e) {
      log.error("Failed to send message: " + e.getMessage());
    }

    return sendResult.isDone();
  }

/*
  public boolean sendMessage (Customer customer) {
    //kafkaTemplate.send(topic, customer.getCustomerId(), customer);

    CompletableFuture<SendResult<String, Customer>> sendResult = kafkaTemplate.send(
        topic,
        customer.customerId(),
        customer
    );
    log.info("Sent message: {}", customer.toString());

    return sendResult.isDone();
  }

 */

}
