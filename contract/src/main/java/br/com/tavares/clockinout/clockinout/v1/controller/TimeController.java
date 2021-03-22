package br.com.tavares.clockinout.clockinout.v1.controller;

import br.com.tavares.clockinout.clockinout.v1.facade.TimeFacadeContract;
import br.com.tavares.clockinout.clockinout.v1.model.TimeRequest;
import br.com.tavares.clockinout.clockinout.v1.model.TimeResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/time")
public class TimeController {

    private TimeFacadeContract timeFacadeContract;

    public TimeController(TimeFacadeContract timeFacadeContract) {
        this.timeFacadeContract = timeFacadeContract;
    }

    @PostMapping
    public TimeResponse timeSave(@Valid @RequestBody TimeRequest timeRequest) {
        return timeFacadeContract.timeSave(timeRequest);
    }
}
