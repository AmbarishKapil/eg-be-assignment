package dk.eg.global.assignmentbe.enums;

public enum InvoiceStatus {
    PENDING("pending"),
    PAID("paid"),
    VOID("void");

    private final String invoiceStatus;

    InvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }
}
