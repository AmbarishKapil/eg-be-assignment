package dk.eg.global.assignmentbe.entities;

import dk.eg.global.assignmentbe.dtos.InvoiceDTO;
import dk.eg.global.assignmentbe.enums.InvoiceStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceDTOTest {
    @Test
    public void testInvoiceDTOCreation() {
        InvoiceDTO invoice = new InvoiceDTO(1L, 100.0, 0, LocalDate.of(2012, 02,02), InvoiceStatus.PENDING);

        assertNotNull(invoice);
        assertEquals(1L, invoice.getId());
        assertEquals(100.0, invoice.getAmount());
    }

    @Test
    public void testInvoiceDTOSettersAndGetters() {
        InvoiceDTO invoice = new InvoiceDTO();
        invoice.setId(2L);
        invoice.setAmount(200.0);

        assertEquals(2L, invoice.getId());
        assertEquals(200.0, invoice.getAmount());
    }

    @Test
    public void testInvoiceDTOEquality() {
        InvoiceDTO invoice1 = new InvoiceDTO(1L, 100.0, 0, LocalDate.of(2012, 02,02), InvoiceStatus.PENDING);

        InvoiceDTO invoice2 = new InvoiceDTO(1L, 200.0, 20, LocalDate.of(2022, 02,02), InvoiceStatus.VOID);


        assertEquals(invoice1, invoice2);
    }

}
