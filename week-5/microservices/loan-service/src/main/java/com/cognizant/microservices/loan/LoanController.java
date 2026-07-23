package com.cognizant.microservices.loan;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/loans") public class LoanController {
 @GetMapping("/{number}") public Map<String,Object> loan(@PathVariable String number) { return Map.of("number",number,"type","PERSONAL","principal",15000,"interest",10.5); }
}
