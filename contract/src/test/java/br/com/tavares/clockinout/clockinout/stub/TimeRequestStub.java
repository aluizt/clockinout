package br.com.tavares.clockinout.clockinout.stub;

import br.com.tavares.clockinout.clockinout.v1.model.TimeRequest;

public class TimeRequestStub {
    public static TimeRequest getTimeRequest() {
        return TimeRequest.builder()
                .userId("20458")
                .time("10:30")
                .date("21-03-2021")
                .build();
    }
}
