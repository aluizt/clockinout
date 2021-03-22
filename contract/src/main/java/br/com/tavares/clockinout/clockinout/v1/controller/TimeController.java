package br.com.tavares.clockinout.clockinout.v1.controller;

import br.com.tavares.clockinout.clockinout.v1.facade.TimeFacadeContract;
import br.com.tavares.clockinout.clockinout.v1.model.TimeRequest;
import br.com.tavares.clockinout.clockinout.v1.model.TimeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/time")
@Tag(name = "TimeController", description = "Method used to indicate the hours consumed")
public class TimeController {

    private TimeFacadeContract timeFacadeContract;

    public TimeController(TimeFacadeContract timeFacadeContract) {
        this.timeFacadeContract = timeFacadeContract;
    }

    @Operation(summary = "Add Hours", description = "", tags = {"contact"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hours successfully added",
                    content = @Content(schema = @Schema(implementation = TimeRequest.class))
            )
    })
    @PostMapping
    public TimeResponse timeSave(@Valid @RequestBody TimeRequest timeRequest) {
        return timeFacadeContract.timeSave(timeRequest);
    }
}
