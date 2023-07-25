package com.loncark.guitarapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.InvalidClassException;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@AutoConfigureMockMvc
public class DeserializationTest extends BaseControllerTest {

    @Test
    public void testUnsafeDeserializationWithGoodUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/serialize/good"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Serialization of user John successful"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/deserialize/unsafe"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        "UserInfo(id=1, name=John, password=johnpassword, roles=ROLE_ADMIN)\n" +
                        "Message: This is a valid user."));
    }

    @Test
    public void testUnsafeDeserializationWithBadUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/serialize/bad"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Serialization of MaliciousJohn successful"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/deserialize/unsafe"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        "UserInfo(id=1, name=MaliciousJohn, password=johnpassword, roles=ROLE_ADMIN,ROLE_USER)\n" +
                                "Message: MALICIOUS USER"));
    }

    @Test
    public void testSafeDeserializationWithGoodUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/serialize/good"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Serialization of user John successful"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/deserialize/safe"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        "UserInfo(id=1, name=John, password=johnpassword, roles=ROLE_ADMIN)\n" +
                                "Message: This is a valid user."));
    }

    @Test
    public void testSafeDeserializationWithBadUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/serialize/bad"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Serialization of MaliciousJohn successful"));

        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/deserialize/safe"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string("Deserialization unsuccessful"));
        } catch (Exception e) {
            if (!(e instanceof InvalidClassException)) {
                fail();
            }
        }
    }

}
