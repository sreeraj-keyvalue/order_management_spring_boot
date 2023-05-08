package com.ecommerce.order.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

  @GetMapping("/")
  public Map<String, String> healthCheck() {
    Map<String, String> message = new HashMap<>();
    message.put("message", "Order management service is up");
    return message;
  }
}
