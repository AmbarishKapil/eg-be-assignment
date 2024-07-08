package dk.eg.global.assignmentbe.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import dk.eg.global.assignmentbe.dtos.InvoiceDTO;
import dk.eg.global.assignmentbe.dtos.InvoiceViews;
import dk.eg.global.assignmentbe.dtos.OverdueDTO;
import dk.eg.global.assignmentbe.dtos.PaymentDTO;
import dk.eg.global.assignmentbe.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/invoices")
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("")
    @JsonView(InvoiceViews.Id.class)
    public ResponseEntity<InvoiceDTO> createInvoice(
            @JsonView(InvoiceViews.ToCreate.class) @RequestBody InvoiceDTO invoiceDTO){
        InvoiceDTO createdInvoiceDTO = invoiceService.createInvoice(invoiceDTO);
        return new ResponseEntity<>(createdInvoiceDTO, HttpStatus.CREATED);
    }

    @GetMapping("")
    @JsonView(InvoiceViews.Full.class)
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices(){
        List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping("/{id}/payments")
    public ResponseEntity<?> updateInvoicePayment(
            @PathVariable Long id,
            @RequestBody PaymentDTO paymentDTO){
        invoiceService.updateInvoicePayment(id, paymentDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/process-overdue")
    public ResponseEntity<?> updateProcessOverdue(@RequestBody OverdueDTO overdueDTO){

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
