package com.cognizant.ormlearn.stock;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "stocks", indexes = @Index(name = "idx_stock_code_date", columnList = "code,trade_date"))
public class Stock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String code;
    @Column(name = "trade_date", nullable = false) private LocalDate date;
    private BigDecimal openPrice;
    private BigDecimal closePrice;
    private Long volume;
    protected Stock() { }
    public Stock(String code, LocalDate date, BigDecimal openPrice, BigDecimal closePrice, Long volume) {
        this.code = code; this.date = date; this.openPrice = openPrice; this.closePrice = closePrice; this.volume = volume;
    }
    public Long getId() { return id; } public String getCode() { return code; } public LocalDate getDate() { return date; }
    public BigDecimal getOpenPrice() { return openPrice; } public BigDecimal getClosePrice() { return closePrice; } public Long getVolume() { return volume; }
}
