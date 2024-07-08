package dk.eg.global.assignmentbe.dtos;

public class InvoiceViews {
    public interface Id {}
    public interface Amount {}
    public interface PaidAmount {}
    public interface DueDate {}
    public interface Status {}
    public interface Full extends Id, Amount, PaidAmount, DueDate, Status {}
    public interface ToCreate extends Amount, DueDate {}
}
