package br.com.tavares.clockinout.clockinout.mapper;

import br.com.tavares.clockinout.clockinout.model.TimeModel;
import br.com.tavares.clockinout.clockinout.repository.entity.TimeEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeMapper {
    public static TimeEntity mapToTimeEntity(TimeModel timeModel) {
        return TimeEntity.builder()
                .date(timeModel.getDate())
                .period(timeModel.getPeriod())
                .userId(timeModel.getUserId())
                .time(timeModel.getTime())
                .build();
    }

    public static TimeModel mapToTimeModel(TimeEntity timeEntity) {
        return TimeModel.builder()
                .id(timeEntity.getId())
                .date(timeEntity.getDate())
                .period(timeEntity.getPeriod())
                .userId(timeEntity.getUserId())
                .time(timeEntity.getTime())
                .build();
    }

}
