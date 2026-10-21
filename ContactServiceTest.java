import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    private ContactService service;
    private Contact contact;

    @BeforeEach
    void setUp() {
        service = new ContactService();
        contact = new Contact("1", "Thomas", "Paige", "1234567890", "123 Main Street");
        service.addContact(contact);
    }

    @Test
    void testAddContactWithUniqueId() {
        Contact secondContact = new Contact("2", "John", "Smith", "0987654321", "456 Oak Road");
        assertDoesNotThrow(() -> service.addContact(secondContact));
    }

    @Test
    void testAddDuplicateContactId() {
        Contact duplicate = new Contact("1", "John", "Smith", "0987654321", "456 Oak Road");
        assertThrows(IllegalArgumentException.class, () -> service.addContact(duplicate));
    }

    @Test
    void testAddNullContact() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    void testDeleteContactById() {
        service.deleteContact("1");
        assertDoesNotThrow(() -> service.addContact(
                new Contact("1", "John", "Smith", "0987654321", "456 Oak Road")));
    }

    @Test
    void testDeleteMissingContact() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("999"));
    }

    @Test
    void testUpdateContactFieldsById() {
        service.updateFirstName("1", "Tom");
        service.updateLastName("1", "Page");
        service.updatePhone("1", "0987654321");
        service.updateAddress("1", "456 Oak Road");

        assertEquals("Tom", contact.getFirstName());
        assertEquals("Page", contact.getLastName());
        assertEquals("0987654321", contact.getPhone());
        assertEquals("456 Oak Road", contact.getAddress());
    }

    @Test
    void testUpdateMissingContact() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("999", "Tom"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("999", "Page"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("999", "0987654321"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("999", "456 Oak Road"));
    }

    @Test
    void testUpdateRejectsInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("1", null));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("1", null));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", "123456789"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("1", null));
    }
}
