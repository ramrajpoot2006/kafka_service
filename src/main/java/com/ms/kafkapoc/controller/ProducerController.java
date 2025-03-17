package com.ms.kafkapoc.controller;


//import com.ms.kafkapoc.model.Customer;
//import com.ms.kafkapoc.model.Customer2;
import com.ms.kafkapoc.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
@Slf4j
public class ProducerController {

  private final ProducerService producerService;

  @PostMapping("/send")
  public String sendMessage (@RequestParam String message) {

    boolean status = producerService.sendMessage(message);
    log.info("Controller: Producer status: {}", status);
    return "Message sent successfully!";
  }

/*
  @PostMapping("/send")
  public String sendMessage (@RequestBody Customer customer) {

    boolean status = producerService.sendMessage(customer);
    log.info("Controller: Producer status: {}", status);
    return "Message sent successfully!";
  }

 */

}
