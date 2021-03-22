package br.com.tavares.clockinout.clockinout.v1.model;

import br.com.tavares.clockinout.clockinout.enuns.TimeCourseEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeResponse {
    private String id;
    private String userId;
    private TimeCourseEnum period;
    private String date;
    private String time;
}
