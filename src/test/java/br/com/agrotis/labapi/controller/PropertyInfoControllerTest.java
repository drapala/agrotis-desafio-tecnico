package br.com.agrotis.labapi.controller;

import br.com.agrotis.labapi.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class PropertyInfoControllerTest {

    private static final String ENDPOINT_GET_PROPERTY_INFO = "/v1/property-info/";

    @Autowired
    protected MockMvc mockMvc;

    @Test
    void givenNullRequest_whenCallRetrievePropertyInfosById_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get(ENDPOINT_GET_PROPERTY_INFO + "/" + null))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}
