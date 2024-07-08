package dk.eg.global.assignmentbe.services;

import dk.eg.global.assignmentbe.dtos.InvoiceDTO;
import dk.eg.global.assignmentbe.dtos.PaymentDTO;
import dk.eg.global.assignmentbe.enums.InvoiceStatus;
import dk.eg.global.assignmentbe.repositories.InvoiceInMemoryRepository;
import org.springframework.stereotype.Service;

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
}
