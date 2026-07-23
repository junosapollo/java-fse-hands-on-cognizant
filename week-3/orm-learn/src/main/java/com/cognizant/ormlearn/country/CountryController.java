package com.cognizant.ormlearn.country;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService service;
    public CountryController(CountryService service) { this.service = service; }
    @GetMapping("/search") public List<Country> search(@RequestParam String term) { return service.search(term); }
    @GetMapping("/prefix") public List<Country> prefix(@RequestParam String value) { return service.findByPrefix(value); }
    @PostMapping public Country save(@RequestBody Country country) { return service.save(country); }
}
