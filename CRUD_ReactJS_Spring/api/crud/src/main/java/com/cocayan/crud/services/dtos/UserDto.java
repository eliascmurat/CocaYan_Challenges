package com.cocayan.crud.services.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.cocayan.crud.entities.User;

import lombok.Getter;

@Getter
public class UserDto {
    private Long userId;
    private String userName;
    private Integer userAge;
    private List<ContactDto> contactList = new ArrayList<>();

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userAge = user.getUserAge();
        this.contactList.addAll(user.getContactList().stream().map(ContactDto::new).collect(Collectors.toList()));
    }

    public static Page<UserDto> listUserToListUserDto(Page<User> users) {
        return users.map(UserDto::new);
    }
}
