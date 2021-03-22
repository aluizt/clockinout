package br.com.tavares.clockinout.clockinout.v1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeRequest {
    @NotBlank
    @Schema(defaultValue = "25048")
    private String userId;

    @NotBlank
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Schema(defaultValue = "20-02-2021")
    private String date;

    @NotBlank
    @Schema(defaultValue = "13:17")
    private String time;
}
