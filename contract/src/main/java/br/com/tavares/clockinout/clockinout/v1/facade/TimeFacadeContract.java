package br.com.tavares.clockinout.clockinout.v1.facade;

import br.com.tavares.clockinout.clockinout.facade.TimeFacade;
import br.com.tavares.clockinout.clockinout.v1.model.TimeRequest;
import br.com.tavares.clockinout.clockinout.v1.model.TimeResponse;
import org.springframework.stereotype.Component;

import static br.com.tavares.clockinout.clockinout.v1.mapper.TimeMapperContract.mapToTimeModel;
import static br.com.tavares.clockinout.clockinout.v1.mapper.TimeMapperContract.mapToTimeResponse;

@Component
public class TimeFacadeContract {

    private TimeFacade timeFacade;

    public TimeFacadeContract(TimeFacade timeFacade) {
        this.timeFacade = timeFacade;
    }

    public TimeResponse timeSave(TimeRequest timeRequest) {
        return mapToTimeResponse(timeFacade.timeSave(mapToTimeModel(timeRequest)));
    }
}
