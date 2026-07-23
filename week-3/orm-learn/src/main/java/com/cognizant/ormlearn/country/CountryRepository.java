package com.cognizant.ormlearn.country;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
    List<Country> findByNameStartingWithIgnoreCaseOrderByNameAsc(String prefix);
    @Query("select c from Country c where lower(c.name) like lower(concat('%', :term, '%')) order by c.code")
    List<Country> search(@Param("term") String term);
}
