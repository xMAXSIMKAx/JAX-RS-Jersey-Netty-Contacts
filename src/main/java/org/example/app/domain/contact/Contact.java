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
    private String Name;

    @Column(name = "phone")
    private int phone;


    public Contact() {
    }

    public Contact(Long id, String Name,int phone) {}{
        this.id = id;
        this.Name = Name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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
                ", \"Name\" : \"" + Name + "\"" +
                ", \"phone\" : \"" + phone + "\"" +
                "}";
    }
}
