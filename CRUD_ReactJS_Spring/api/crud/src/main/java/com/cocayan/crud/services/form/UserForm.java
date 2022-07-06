package com.cocayan.crud.services.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.cocayan.crud.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
    
    @NotNull
    @Length(max = 100, min = 2)
    private String userName;
    
    @NotNull
    private Integer userAge;

    private List<ContactForm> contactList;

    public UserForm() { }

    public UserForm(String userName, Integer userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }

    public UserForm(String userName, Integer userAge, List<ContactForm> contactList) {
        this.userName = userName;
        this.userAge = userAge;
        this.contactList = new ArrayList<>();
        this.contactList.addAll(contactList);
    }

    public User userFormToUser(UserForm userForm) {
        return new User(userForm.getUserName(), userForm.getUserAge());
    }
}
