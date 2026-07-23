package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService service;
    public CountryController(CountryService service) { this.service=service; }
    @GetMapping public List<Country> all() { return service.all(); }
    @GetMapping("/search") public List<Country> search(@RequestParam String term) { return service.search(term); }
}
