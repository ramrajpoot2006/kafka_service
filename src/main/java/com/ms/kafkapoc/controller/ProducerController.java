package com.ms.kafkapoc.controller;


import com.ms.kafkapoc.model.Customer;
import com.ms.kafkapoc.model.Customer1;
import com.ms.kafkapoc.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class ProducerController {

  private final ProducerService producerService;


  @PostMapping("/send")
  public String sendMessage (@RequestBody Customer customer) {

    producerService.sendMessage(customer);
    return "Message sent successfully!";
  }

}
