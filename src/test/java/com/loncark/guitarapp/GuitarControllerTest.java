package com.loncark.guitarapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class GuitarControllerTest extends BaseControllerTest {

    @Test
    public void testGetByCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Gibson Les Paul Standard 60s"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(2999))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1234));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/0001")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSaveByUser() throws Exception {
        String validUserJwt = getValidUserJwt();
        String requestBody = "{\"code\": \"1239\", " +
                "\"body\": \"Mahogany\", " +
                "\"neck\": \"Maple\", " +
                "\"price\": 129.00, " +
                "\"stock\": 5, " +
                "\"name\": \"Squier Classic Vibe Telecaster\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validUserJwt)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Squier Classic Vibe Telecaster"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(129.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1239));
    }

    @Test
    public void testUpdateByUser() throws Exception {
        String validUserJwt = getValidUserJwt();
        String requestBody = "{" +
                "\"id\": 3, " +
                "\"code\": \"1111\", " +
                "\"body\": \"Mahogany\", " +
                "\"neck\": \"Maple\", " +
                "\"price\": 129.00, " +
                "\"stock\": 5, " +
                "\"name\": \"Squier Classic Vibe Telecaster\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + validUserJwt)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Squier Classic Vibe Telecaster"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(129.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3));
    }
}
