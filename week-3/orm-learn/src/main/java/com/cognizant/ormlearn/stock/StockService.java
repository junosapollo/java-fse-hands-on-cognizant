package com.cognizant.ormlearn.stock;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private final StockRepository repository;
    public StockService(StockRepository repository) { this.repository = repository; }
    public List<Stock> between(String code, LocalDate from, LocalDate to) { return repository.findByCodeAndDateBetween(code, from, to); }
    public List<Stock> topVolume() { return repository.findTop3ByOrderByVolumeDesc(); }
}
