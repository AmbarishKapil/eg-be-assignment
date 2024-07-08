package dk.eg.global.assignmentbe.repositories;

import dk.eg.global.assignmentbe.dtos.InvoiceDTO;
import dk.eg.global.assignmentbe.dtos.PaymentDTO;
import dk.eg.global.assignmentbe.enums.InvoiceStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InvoiceInMemoryRepository {

    private final List<InvoiceDTO> invoices = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idGenerator = new AtomicLong(0);

    public InvoiceDTO save(InvoiceDTO invoice) {
        if(invoice.getId() == 0){
            Long id = idGenerator.incrementAndGet();
            invoice.setId(id);
            invoices.add(invoice);
        }

        return invoice;
    }

    public Optional<InvoiceDTO> findById(long id) {
        synchronized (invoices) {
            return invoices.stream()
                    .filter(inv -> inv.getId() == id)
                    .findFirst();
        }
    }

    public List<InvoiceDTO> findAll() {
        synchronized (invoices) {
            return new ArrayList<>(invoices);
        }
    }

    public void update(InvoiceDTO updatedInvoice) {
        synchronized (invoices) {
            for (int i = 0; i < invoices.size(); i++) {
                if (invoices.get(i).getId() == updatedInvoice.getId()) {
                    invoices.set(i, updatedInvoice);
                    break;
                }
            }
        }
    }


    public void delete(Long id) {
        invoices.removeIf(inv -> inv.getId() == id);
    }

}
