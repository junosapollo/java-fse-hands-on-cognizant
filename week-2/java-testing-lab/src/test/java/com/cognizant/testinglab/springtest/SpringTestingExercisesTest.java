package com.cognizant.testinglab.springtest;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.cognizant.testinglab.users.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class SpringTestingExercisesTest {
    @Autowired MockMvc mvc;
    @MockBean UserService service;

    @Test
    void getsUserWithMockedService() throws Exception {
        when(service.getUserById(1L)).thenReturn(new User(1L, "Ada"));
        mvc.perform(get("/users/1")).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Ada")));
    }

    @Test
    void createsUserWithPost() throws Exception {
        when(service.saveUser(org.mockito.ArgumentMatchers.any(User.class))).thenReturn(new User(2L, "Grace"));
        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content("{\"id\":2,\"name\":\"Grace\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", is("Grace")));
    }
}
