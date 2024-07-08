//package dk.eg.global.assignmentbe.entities;
//
//import dk.eg.global.assignmentbe.enums.InvoiceStatus;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//import java.util.Objects;
//
//@Entity
//@Table(name = "invoice")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Invoice {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "invoice_id")
//    long invoiceId;
//
//    @Column(name = "amount")
//    double amount;
//
//    @Column(name = "paid_amount")
//    double paidAmount;
//
//    @Column(name = "due_date")
//    LocalDate dueDdate;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "invoice_status")
//    InvoiceStatus invoiceStatus;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Invoice invoice)) return false;
//        return getInvoiceId() == invoice.getInvoiceId();
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(getInvoiceId());
//    }
//}
