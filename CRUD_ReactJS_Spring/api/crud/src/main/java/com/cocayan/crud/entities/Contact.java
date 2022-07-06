package com.cocayan.crud.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@Table(name = "contacts")
@Entity
public class Contact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactid")
    private Long contactId;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonBackReference
    private User user;

    public Contact(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
        
}
