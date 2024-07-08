package dk.eg.global.assignmentbe.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import dk.eg.global.assignmentbe.enums.InvoiceStatus;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDTO {
    @JsonView({InvoiceViews.Id.class, InvoiceViews.Full.class, InvoiceViews.ToCreate.class})
    long id;

    @JsonView({InvoiceViews.Amount.class, InvoiceViews.Full.class, InvoiceViews.ToCreate.class})
    @JsonProperty("amount")
    double amount;

    @JsonView({InvoiceViews.PaidAmount.class, InvoiceViews.Full.class})
    @JsonProperty("paid_amount")
    double paidAmount;

    @JsonView({InvoiceViews.DueDate.class, InvoiceViews.Full.class})
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    @JsonProperty("due_date")
    LocalDate dueDate;

    @JsonView({InvoiceViews.Status.class, InvoiceViews.Full.class})
    @JsonProperty("invoice_status")
    InvoiceStatus invoiceStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceDTO that)) return false;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
