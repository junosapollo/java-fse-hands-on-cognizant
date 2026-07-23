package com.cognizant.microservices.account;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/accounts") public class AccountController {
 @GetMapping("/{number}") public Map<String,Object> account(@PathVariable String number) { return Map.of("number",number,"type","SAVINGS","balance",BigDecimal.valueOf(1000)); }
}
