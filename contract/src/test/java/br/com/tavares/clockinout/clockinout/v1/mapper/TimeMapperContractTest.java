package br.com.tavares.clockinout.clockinout.v1.mapper;

import br.com.tavares.clockinout.clockinout.model.TimeModel;
import br.com.tavares.clockinout.clockinout.stub.TimeModelStub;
import br.com.tavares.clockinout.clockinout.stub.TimeRequestStub;
import br.com.tavares.clockinout.clockinout.stub.TimeResponseStub;
import br.com.tavares.clockinout.clockinout.v1.model.TimeResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class TimeMapperContractTest {

    /**
     * Dado que o metodo [mapToTimeModel] seja chamado...
     * Dado que recebe um objeto TimeRequest
     * Deve retornar um objeto TimeModel
     */
    @Test
    public void deve_retornar_um_objeto_timemodel() {
        TimeModel response = TimeMapperContract.mapToTimeModel(TimeRequestStub.getTimeRequest());
        Assert.assertEquals(TimeModelStub.getTimeModel(), response);
    }

    /**
     * Dado que o metodo [mapToTimeResponse] seja chamado...
     * Dado que recebe um objeto TimeModel
     * Deve retornar um objeto TimeResponse
     */
    @Test
    public void deve_retornar_um_objeto_timeresponse() {
        TimeResponse response = TimeMapperContract.mapToTimeResponse(TimeModelStub.getTimeModelComId());
        Assert.assertEquals(TimeResponseStub.getTimeResponse(), response);
    }
}