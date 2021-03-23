package br.com.tavares.clockinout.clockinout.stub;

import br.com.tavares.clockinout.clockinout.model.TimeModel;

public class TimeModelStub {
    public static TimeModel getTimeModel() {
        return TimeModel.builder()
                .userId("20458")
                .time("10:30")
                .date("21-03-2021")
                .build();
    }

    public static TimeModel getTimeModelComId() {
        return TimeModel.builder()
                .userId("20458")
                .time("10:30")
                .date("21-03-2021")
                .id("12345678910")
                .build();
    }
}
