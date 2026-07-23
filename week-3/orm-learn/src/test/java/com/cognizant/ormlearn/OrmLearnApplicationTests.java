package com.cognizant.ormlearn;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.cognizant.ormlearn.country.*;
import com.cognizant.ormlearn.stock.*;

@SpringBootTest
class OrmLearnApplicationTests {
    @Autowired CountryRepository countries;
    @Autowired StockRepository stocks;

    @Test @Transactional
    void derivedAndCustomQueriesWork() {
        countries.save(new Country("IN", "India"));
        countries.save(new Country("ID", "Indonesia"));
        assertThat(countries.findByNameContainingIgnoreCaseOrderByNameAsc("ind")).hasSize(2);
        stocks.save(new Stock("CTSH", LocalDate.of(2024, 1, 1), BigDecimal.TEN, BigDecimal.valueOf(12), 100L));
        assertThat(stocks.findProfitable("CTSH")).hasSize(1);
    }
}
