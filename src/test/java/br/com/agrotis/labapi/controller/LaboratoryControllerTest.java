package br.com.agrotis.labapi.controller;

import br.com.agrotis.labapi.IntegrationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IntegrationTest
class LaboratoryControllerTest {

    private static final String ENDPOINT_GET_LABORATORY = "/v1/laboratory";

    @Autowired
    protected MockMvc mockMvc;

    @Disabled("necessario massa")
    @Test
    void givenAnValidRequest_whenCallRetrieveLaboratoryById_shouldReturnOk() throws Exception {
        mockMvc.perform(get(ENDPOINT_GET_LABORATORY + "/" + 1L))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void givenNullRequest_whenCallRetrieveLaboratoryById_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get(ENDPOINT_GET_LABORATORY + "/" + null))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }


}
