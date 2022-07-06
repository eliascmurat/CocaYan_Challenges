package com.cocayan.crud.services.dtos;

import com.cocayan.crud.entities.Contact;

import lombok.Getter;

@Getter
public class ContactDto {
    private Long contactId;
    private String phoneNumber;

    public ContactDto(Contact contact) {
        this.contactId = contact.getContactId();
        this.phoneNumber = contact.getPhoneNumber();
    }
}
