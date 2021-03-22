package br.com.tavares.clockinout.clockinout.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeRequest {
    @NotBlank(message = "User ID is required !")
    @Size(min = 0, max = 5)
    @Schema(description = "User ID", example = "25048")
    private String userId;

    @NotBlank(message = "The date is required !")
    @Schema(description = "Date period worked", example = "20-02-2021")
    private String date;

    @NotBlank(message = "Time is required !")
    @Schema(description = "Worked hours", example = "08:30")
    private String time;
}
