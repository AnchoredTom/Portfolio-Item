import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    void testContactCreation() {
        Contact contact = new Contact("1234567890", "Thomas", "Paige", "1234567890", "123 Main Street");

        assertEquals("1234567890", contact.getContactId());
        assertEquals("Thomas", contact.getFirstName());
        assertEquals("Paige", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    @Test
    void testContactIdRequirements() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "Thomas", "Paige", "1234567890", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345678901", "Thomas", "Paige", "1234567890", "123 Main Street"));
    }

    @Test
    void testFirstNameRequirements() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", null, "Paige", "1234567890", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "12345678901", "Paige", "1234567890", "123 Main Street"));
    }

    @Test
    void testLastNameRequirements() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Thomas", null, "1234567890", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Thomas", "12345678901", "1234567890", "123 Main Street"));
    }

    @Test
    void testPhoneRequirements() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Thomas", "Paige", null, "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Thomas", "Paige", "123456789", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Thomas", "Paige", "12345678901", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Thomas", "Paige", "12345abcde", "123 Main Street"));
    }

    @Test
    void testAddressRequirements() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Thomas", "Paige", "1234567890", null));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Thomas", "Paige", "1234567890",
                        "1234567890123456789012345678901"));
    }

    @Test
    void testSettersUpdateFields() {
        Contact contact = new Contact("1", "Thomas", "Paige", "1234567890", "123 Main Street");

        contact.setFirstName("Tom");
        contact.setLastName("Page");
        contact.setPhone("0987654321");
        contact.setAddress("456 Oak Road");

        assertEquals("Tom", contact.getFirstName());
        assertEquals("Page", contact.getLastName());
        assertEquals("0987654321", contact.getPhone());
        assertEquals("456 Oak Road", contact.getAddress());
    }

    @Test
    void testSettersRejectInvalidValues() {
        Contact contact = new Contact("1", "Thomas", "Paige", "1234567890", "123 Main Street");

        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123456789"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123456789a"));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
        assertThrows(IllegalArgumentException.class,
                () -> contact.setAddress("1234567890123456789012345678901"));
    }
}
