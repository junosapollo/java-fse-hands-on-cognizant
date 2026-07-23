package com.cognizant.microservices.greeting;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
@RestController public class GreetingController { @GetMapping("/greet") public Map<String,String> greet(@RequestParam(defaultValue="learner") String name) { return Map.of("message","Hello, "+name+"!"); } }
