package dk.eg.global.assignmentbe.services;

import dk.eg.global.assignmentbe.dtos.InvoiceDTO;
import dk.eg.global.assignmentbe.dtos.PaymentDTO;
import dk.eg.global.assignmentbe.enums.InvoiceStatus;
import dk.eg.global.assignmentbe.repositories.InvoiceInMemoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService {
    private InvoiceInMemoryRepository invoiceInMemoryRepository;

    public InvoiceService(InvoiceInMemoryRepository invoiceInMemoryRepository) {
        this.invoiceInMemoryRepository = invoiceInMemoryRepository;
    }

    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {

        invoiceDTO.setPaidAmount(0);
        invoiceDTO.setInvoiceStatus(InvoiceStatus.PENDING);

        InvoiceDTO savedInvoice = invoiceInMemoryRepository.save(invoiceDTO);
        return savedInvoice;
    }

    public List<InvoiceDTO> getAllInvoices() {
        return invoiceInMemoryRepository.findAll();
    }

    public void updateInvoicePayment(Long id, PaymentDTO paymentDTO){
        if(invoiceInMemoryRepository.findById(id).isEmpty()){
            throw new IllegalStateException("Invoice with id " + id + " is not Present!");
        }

        InvoiceDTO savedInvoice = invoiceInMemoryRepository.findById(id).get();

        double stillToPayAmount = savedInvoice.getAmount() - savedInvoice.getPaidAmount();

        if(stillToPayAmount < paymentDTO.getAmount()){
            throw new IllegalStateException("Payment amount is more than pending amount");
        }

        savedInvoice.setPaidAmount(savedInvoice.getPaidAmount() + paymentDTO.getAmount());
        if(savedInvoice.getAmount() == savedInvoice.getPaidAmount()){
            savedInvoice.setInvoiceStatus(InvoiceStatus.PAID);
        }

        invoiceInMemoryRepository.update(savedInvoice);
    }

    public void processOverdueInvoices(double lateFee, int overdueDays) {
        List<InvoiceDTO> invoices = invoiceInMemoryRepository.findAll();
        LocalDate currentDate = LocalDate.now();

        for (InvoiceDTO invoice : invoices) {
            if (invoice.getInvoiceStatus() == InvoiceStatus.PENDING && invoice.getDueDate().plusDays(overdueDays).isBefore(currentDate)) {
                if (invoice.getPaidAmount() > 0) {
                    // Partially paid invoice
                    invoice.setInvoiceStatus(InvoiceStatus.PAID);
                    invoiceInMemoryRepository.update(invoice);

                    double remainingAmount = invoice.getAmount() - invoice.getPaidAmount();
                    InvoiceDTO newInvoice = new InvoiceDTO();
                    newInvoice.setAmount(remainingAmount + lateFee);
                    newInvoice.setDueDate(currentDate.plusDays(overdueDays));
                    createInvoice(newInvoice);
                } else {
                    // Not paid at all
                    invoice.setInvoiceStatus(InvoiceStatus.VOID);
                    invoiceInMemoryRepository.update(invoice);

                    InvoiceDTO newInvoice = new InvoiceDTO();
                    newInvoice.setAmount(invoice.getAmount() + lateFee);
                    newInvoice.setDueDate(currentDate.plusDays(overdueDays));
                    createInvoice(newInvoice);
                }
            }
        }
    }

}
