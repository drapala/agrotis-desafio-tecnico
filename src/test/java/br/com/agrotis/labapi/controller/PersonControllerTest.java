package br.com.agrotis.labapi.controller;

import br.com.agrotis.labapi.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class PersonControllerTest {

    private static final String ENDPOINT_GET_PERSON = "/v1/person";

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void givenNullRequest_whenCallRetrievePersonById_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get(ENDPOINT_GET_PERSON + "/" + null))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}
