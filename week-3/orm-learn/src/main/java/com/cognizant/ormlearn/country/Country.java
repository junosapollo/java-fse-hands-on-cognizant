package com.cognizant.ormlearn.country;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "countries")
public class Country {
    @Id @NotBlank private String code;
    @NotBlank private String name;

    protected Country() { }
    public Country(String code, String name) { this.code = code; this.name = name; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
