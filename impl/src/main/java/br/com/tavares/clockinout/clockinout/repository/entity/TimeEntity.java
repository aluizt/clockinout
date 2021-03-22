package br.com.tavares.clockinout.clockinout.repository.entity;

import br.com.tavares.clockinout.clockinout.enuns.TimeCourseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "TIME_MANAGEMENT")
public class TimeEntity {
    @Id
    private String id;
    private String userId;
    private TimeCourseEnum period;
    private String date;
    private String time;
}
