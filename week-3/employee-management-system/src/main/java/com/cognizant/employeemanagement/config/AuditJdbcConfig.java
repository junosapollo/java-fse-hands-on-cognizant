package com.cognizant.employeemanagement.config;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.Profile;

/** A second, explicitly named datasource used by the multi-datasource exercise.
 * The default Boot datasource remains the JPA datasource. */
@Configuration
@Profile("audit")
public class AuditJdbcConfig {
    @Bean(name = "auditDataSource")
    DataSource auditDataSource() {
        return DataSourceBuilder.create().url("jdbc:h2:mem:audit;DB_CLOSE_DELAY=-1").username("sa").build();
    }
    @Bean(name = "auditJdbcTemplate")
    JdbcTemplate auditJdbcTemplate(@Qualifier("auditDataSource") DataSource dataSource) { return new JdbcTemplate(dataSource); }
}
