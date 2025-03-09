package com.ms.kafkapoc.service;


import com.ms.kafkapoc.model.Customer;
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

  private final KafkaTemplate<String, Customer> kafkaTemplate;


  public boolean sendMessage (Customer customer) {
    //kafkaTemplate.send(topic, customer.getCustomerId(), customer);

    CompletableFuture<SendResult<String, Customer>> sendResult = kafkaTemplate.send(
        topic,
        String.valueOf(customer.getCustomerId()),
        customer
    );
    log.info("Sent message: {}", customer.toString());

    return sendResult.isDone();
  }

}
