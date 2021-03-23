package br.com.tavares.clockinout.clockinout.v1.controller;

import br.com.tavares.clockinout.clockinout.stub.TimeRequestStub;
import br.com.tavares.clockinout.clockinout.stub.TimeResponseStub;
import br.com.tavares.clockinout.clockinout.v1.facade.TimeFacadeContract;
import br.com.tavares.clockinout.clockinout.v1.model.TimeRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
@SpringBootConfiguration
class TimeControllerTest {
    @Mock
    private TimeFacadeContract timeFacadeContract;
    @InjectMocks
    private TimeController timeController;
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(timeController).build();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Dado que a url [/v1/time] receba uma request...
     * Deve retornar um objeto TimeResponse
     * Deve gerar status 201
     */
    @Test
    public void deve_retornar_um_objeto_time_response_com_httpstatus_201() throws Exception {
        when(timeFacadeContract.timeSave(any())).thenReturn(TimeResponseStub.getTimeResponse());
        TimeRequest request = TimeRequestStub.getTimeRequest();
        String json = objectMapper.writeValueAsString(request);
        this.mockMvc.perform(post("/v1/time")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().is(201))
                .andExpect(content().json(this.objectMapper.writeValueAsString(TimeResponseStub.getTimeResponse())));
    }
}