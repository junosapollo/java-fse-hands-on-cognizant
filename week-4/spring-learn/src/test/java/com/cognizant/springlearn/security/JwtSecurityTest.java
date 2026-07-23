package com.cognizant.springlearn.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest @AutoConfigureMockMvc
class JwtSecurityTest {
    @Autowired MockMvc mvc;
    @Test void basicCredentialsExchangeForToken() throws Exception { mvc.perform(get("/authenticate").with(httpBasic("user", "pwd"))).andExpect(status().isOk()); }
    @Test void unauthenticatedRequestIsRejected() throws Exception { mvc.perform(get("/hello")).andExpect(status().isUnauthorized()); }
}
