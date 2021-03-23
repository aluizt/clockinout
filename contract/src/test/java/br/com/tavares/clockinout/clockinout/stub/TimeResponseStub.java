package br.com.tavares.clockinout.clockinout.stub;

import br.com.tavares.clockinout.clockinout.v1.model.TimeResponse;

public class TimeResponseStub {
    public static TimeResponse getTimeResponse() {
        return TimeResponse.builder()
                .userId("20458")
                .time("10:30")
                .date("21-03-2021")
                .id("12345678910")
                .build();
    }
}
