package com.cocayan.crud.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

    @OneToMany(mappedBy = "user")
    private List<Contact> contactList = new ArrayList<>();
 
    public User(String userName, Integer userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public void setContactList(Contact contact) {
        this.contactList.add(contact);
    }
}
