package com.loncark.guitarapp;

import io.jsonwebtoken.ExpiredJwtException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest extends BaseControllerTest {

    @Test
    public void testSignUp() throws Exception {
        String requestBody = "{\"name\": \"Paul\", " +
                "\"password\": \"paulpassword\", " +
                "\"roles\": \"ROLE_USER\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testLoginAndReturnTokens() throws Exception {
        String requestBody = "{\"username\": \"John\", " +
                "\"password\": \"johnpassword\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accessToken").value(matchesPattern("[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refreshToken").value(matchesPattern("[A-Za-z0-9-_]+")));
    }

    @Test
    public void testDeleteAsAdminWithAValidJwt() throws Exception {
        String adminJwt = getValidAdminJwt();

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/1234")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminJwt))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteAsUserWithAValidJwt() throws Exception {
        String userJwt = getValidUserJwt();

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/1236")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + userJwt))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/1236")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteWithInvalidAdminJwt() throws Exception {
        String invalidAdminJwt = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2huIiwiaWF0IjoxNjg5Njc4OTM0LCJleHAiOjE2ODk2Nzg5OTR9.UCxnguP6gDiPHUFJsRKEciUepUklVSUHpf2ek38MSYM";

        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .delete("/1234")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + invalidAdminJwt));
        } catch (Exception e) {
            if (!(e instanceof ExpiredJwtException)) {
                fail();
            }
        }
    }

    @Test
    public void testRefreshTokenReturnsValidJwt() throws Exception {
        String requestBody1 = "{\"username\": \"John\", " +
                "\"password\": \"johnpassword\"}";
        String validAdminRefreshToken = getValidRefreshToken(requestBody1);
        String requestBody2 = "{\"refreshToken\": \"" + validAdminRefreshToken + "\"}";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.accessToken").value(matchesPattern("[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.refreshToken").value(validAdminRefreshToken))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        JSONObject jsonResponse = new JSONObject(responseContent);

        String newValidAdminJwt = jsonResponse.getString("accessToken");

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/1235")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + newValidAdminJwt))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/1235")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
