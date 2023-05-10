package com.ecommerce.order.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HealthControllerTest {

  @Autowired
  private HealthController healthController;

  @Test
  void healthCheckTest() {
    Map<String, String> expectedMessage = new HashMap<>();
    expectedMessage.put("message", "Order management service is up");
    Map<String, String> actualMessage = healthController.healthCheck();

    assertEquals(expectedMessage, actualMessage);
  }

}
