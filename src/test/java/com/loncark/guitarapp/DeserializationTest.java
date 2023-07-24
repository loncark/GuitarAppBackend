package com.loncark.guitarapp;

//UserInfo(id=1, name=John, password=johnpassword, roles=ROLE_ADMIN)
//Message: This is a valid user.

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class DeserializationTest extends BaseControllerTest {

    @Test
    public void testDeserialization() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/serialize"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Serialization of user John successful"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/deserialize"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        "UserInfo(id=1, name=John, password=johnpassword, roles=ROLE_ADMIN)\n" +
                        "Message: This is a valid user."));
    }

    @Test
    public void testMaliciousDeserialization() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/serializeMalicious"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Serialization of MaliciousJohn successful"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/deserialize"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        "UserInfo(id=1, name=MaliciousJohn, password=johnpassword, roles=ROLE_ADMIN,ROLE_USER)\n" +
                                "Message: MALICIOUS USER"));
    }

}
