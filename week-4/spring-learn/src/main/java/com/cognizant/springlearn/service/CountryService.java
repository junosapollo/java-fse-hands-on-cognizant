package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import java.util.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Service
public class CountryService {
    public List<Country> all() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            @SuppressWarnings("unchecked") List<Country> countries = (List<Country>) context.getBean("countryList");
            return List.copyOf(countries);
        }
    }
    public List<Country> search(String term) {
        return all().stream().filter(c -> c.getName().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT))).toList();
    }
}
