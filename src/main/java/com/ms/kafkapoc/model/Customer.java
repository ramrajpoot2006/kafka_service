package com.ms.kafkapoc.model;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {

  private String customerId;

  private String name;

  private String email;


}
