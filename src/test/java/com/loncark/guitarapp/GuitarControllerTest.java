package com.loncark.guitarapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class GuitarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetByCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/1233")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Fender Gold Foil Jazzmaster"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1649))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1233));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/0001")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteByCode() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/1234"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSave() throws Exception {
        String requestBody = "{\"code\": \"1239\", " +
                "\"body\": \"Mahogany\", " +
                "\"neck\": \"Maple\", " +
                "\"price\": 129.00, " +
                "\"stock\": 5, " +
                "\"name\": \"Squier Classic Vibe Telecaster\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Squier Classic Vibe Telecaster"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(129.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stock").value(5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1239));
    }

    @Test
    public void testUpdate() throws Exception {
        String requestBody = "{" +
                "\"id\": \"3\", " +
                "\"code\": \"1111\", " +
                "\"body\": \"Mahogany\", " +
                "\"neck\": \"Maple\", " +
                "\"price\": 129.00, " +
                "\"stock\": 5, " +
                "\"name\": \"Squier Classic Vibe Telecaster\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/")
                        .contentType(MediaType.APPLICATION_JSON)
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
