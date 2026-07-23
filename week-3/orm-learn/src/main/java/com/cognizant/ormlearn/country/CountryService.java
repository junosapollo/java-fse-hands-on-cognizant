package com.cognizant.ormlearn.country;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    private final CountryRepository repository;
    public CountryService(CountryRepository repository) { this.repository = repository; }
    public Country save(Country country) { return repository.save(country); }
    public List<Country> findByName(String name) { return repository.findByNameContainingIgnoreCaseOrderByNameAsc(name); }
    public List<Country> findByPrefix(String prefix) { return repository.findByNameStartingWithIgnoreCaseOrderByNameAsc(prefix); }
    public List<Country> search(String term) { return repository.search(term); }
}
