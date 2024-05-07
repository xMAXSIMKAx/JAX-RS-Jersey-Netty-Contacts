package org.example.app.domain.contact;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private int phone;


    // Constructor
    public Contact(Long id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Contact() {

    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\" : " + id +
                ", \"name\" : \"" + name + "\"" +
                ", \"phone\" : \"" + phone + "\"" +
                "}";
    }

    // Example of using the Contact class
}