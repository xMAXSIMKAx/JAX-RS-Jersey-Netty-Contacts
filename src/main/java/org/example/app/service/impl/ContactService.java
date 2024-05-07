package org.example.app.service.impl;

import org.example.app.domain.contact.Contact;
import org.example.app.repository.impl.ContactRepository;
import org.example.app.service.AppService;

import java.util.Collections;
import java.util.List;

public class ContactService implements AppService<Contact> {

    ContactRepository repository = new ContactRepository();

    public Contact create(Contact contact) {
        repository.create(contact);
        return repository.getLastEntity()
                .orElse(null);
    }

    public List<Contact> fetchAll() {
        return repository.fetchAll()
                .orElse(Collections.emptyList());
    }

    // ---- Path Param ----------------------

    public Contact fetchById(Long id) {
        return repository.fetchById(id).orElse(null);
    }

    public Contact update(Long id, Contact contact) {
        if (repository.fetchById(id).isPresent()) {
            repository.update(id, contact);
        }
        return repository.fetchById(id).orElse(null);
    }

    public boolean delete(Long id) {
        if (repository.isIdExists(id)) {
            repository.delete(id);
            return true;
        } else return false;
    }
}
