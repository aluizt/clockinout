package br.com.tavares.clockinout.clockinout.stub;

import br.com.tavares.clockinout.clockinout.enuns.TimeCourseEnum;
import br.com.tavares.clockinout.clockinout.repository.entity.TimeEntity;

import java.util.Arrays;
import java.util.List;

public class TimeEntityStub {

    public static List<TimeEntity> getTimeEntityList() {
        return Arrays.asList(getTimeEntity());
    }

    public static TimeEntity getTimeEntity() {
        return TimeEntity.builder()
                .userId("20458")
                .time("10:30")
                .date("21-03-2021")
                .id("12345678910")
                .period(TimeCourseEnum.FIRST_PERIOD_IN)
                .build();
    }
}
