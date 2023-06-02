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
}
