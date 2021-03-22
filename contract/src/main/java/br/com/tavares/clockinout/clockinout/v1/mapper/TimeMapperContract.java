package br.com.tavares.clockinout.clockinout.v1.mapper;

import br.com.tavares.clockinout.clockinout.model.TimeModel;
import br.com.tavares.clockinout.clockinout.v1.model.TimeRequest;
import br.com.tavares.clockinout.clockinout.v1.model.TimeResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeMapperContract {
    public static TimeModel mapToTimeModel(TimeRequest timeRequest) {
        return TimeModel.builder()
                .date(timeRequest.getDate())
                .time(timeRequest.getTime())
                .userId(timeRequest.getUserId())
                .build();
    }

    public static TimeResponse mapToTimeResponse(TimeModel timeModel) {
        return TimeResponse.builder()
                .id(timeModel.getId())
                .date(timeModel.getDate())
                .period(timeModel.getPeriod())
                .time(timeModel.getTime())
                .userId(timeModel.getUserId())
                .build();
    }
}
