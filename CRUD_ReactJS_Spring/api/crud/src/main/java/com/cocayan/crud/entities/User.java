package com.cocayan.crud.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "userage")
    private Integer userAge;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contact> contactList = new ArrayList<>();
 
    public User(String userName, Integer userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }

    public boolean hasContact(Long contactId) {
        return this.contactList.stream().anyMatch(c -> contactId.equals(c.getContactId()));
    }
}
