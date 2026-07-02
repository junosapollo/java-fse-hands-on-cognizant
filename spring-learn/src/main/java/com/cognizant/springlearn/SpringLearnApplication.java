package com.cognizant.springlearn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START");
        SpringApplication.run(SpringLearnApplication.class, args);

        SpringLearnApplication application = new SpringLearnApplication();
        application.displayDate();
        application.displayCountry();
        application.displayCountries();

        LOGGER.info("END");
    }

    public void displayDate() {
        LOGGER.info("START");

        try (ClassPathXmlApplicationContext context = loadContext("date-format.xml")) {
            SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
            Date date = parseDate(format, "31/12/2018");
            LOGGER.debug("Date : {}", date);
        }

        LOGGER.info("END");
    }

    public void displayCountry() {
        LOGGER.info("START");

        try (ClassPathXmlApplicationContext context = loadContext("country.xml")) {
            Country country = context.getBean("country", Country.class);
            Country anotherCountry = context.getBean("country", Country.class);

            LOGGER.debug("Country : {}", country);
            LOGGER.debug("Another Country : {}", anotherCountry);
            LOGGER.debug("Same country object : {}", country == anotherCountry);
        }

        LOGGER.info("END");
    }

    public void displayCountries() {
        LOGGER.info("START");

        try (ClassPathXmlApplicationContext context = loadContext("country.xml")) {
            List<Country> countries = loadCountryList(context);
            LOGGER.debug("Countries : {}", countries);
        }

        LOGGER.info("END");
    }

    private ClassPathXmlApplicationContext loadContext(String fileName) {
        return new ClassPathXmlApplicationContext(fileName);
    }

    private Date parseDate(SimpleDateFormat format, String value) {
        try {
            return format.parse(value);
        } catch (ParseException exception) {
            throw new IllegalArgumentException("date should match " + format.toPattern(), exception);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Country> loadCountryList(ClassPathXmlApplicationContext context) {
        return context.getBean("countryList", List.class);
    }
}
