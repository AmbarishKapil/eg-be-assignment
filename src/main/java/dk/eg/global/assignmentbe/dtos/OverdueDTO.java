package dk.eg.global.assignmentbe.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OverdueDTO {
    @JsonProperty("late_fee")
    double lateFee;

    @JsonProperty("overdue_days")
    int overdueDays;
}
