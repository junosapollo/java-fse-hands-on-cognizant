package com.cognizant.ormlearn.stock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByCodeAndDateBetween(String code, LocalDate from, LocalDate to);
    List<Stock> findByCodeAndClosePriceGreaterThan(String code, BigDecimal price);
    List<Stock> findTop3ByOrderByVolumeDesc();
    List<Stock> findTop3ByCodeOrderByClosePriceAsc(String code);
    @Query("select s from Stock s where s.code = :code and s.closePrice > s.openPrice")
    List<Stock> findProfitable(@Param("code") String code);
    @Query(value = "select * from stocks where volume >= :volume", nativeQuery = true)
    List<Stock> findByMinimumVolume(@Param("volume") long volume);
}
