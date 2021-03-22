package br.com.tavares.clockinout.clockinout.model;

import br.com.tavares.clockinout.clockinout.enuns.TimeCourseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeModel {
    private String id;
    private String userId;
    private TimeCourseEnum period;
    private String date;
    private String time;
}
