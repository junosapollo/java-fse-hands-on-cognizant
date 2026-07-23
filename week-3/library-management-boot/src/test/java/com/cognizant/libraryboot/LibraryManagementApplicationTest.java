package com.cognizant.libraryboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryManagementApplicationTest {
    @Autowired MockMvc mvc;

    @Test
    void supportsBookCrud() throws Exception {
        mvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"Domain-Driven Design\",\"author\":\"Eric Evans\"}"))
            .andExpect(status().isOk()).andExpect(jsonPath("$.title").value("Domain-Driven Design"));
        mvc.perform(get("/books")).andExpect(status().isOk()).andExpect(jsonPath("$[0].author").value("Eric Evans"));
    }
}
