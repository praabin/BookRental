package com.bookrental.bookrental.service.user;

import com.bookrental.bookrental.pojo.user.UserRequestPojo;
import com.bookrental.bookrental.pojo.user.UserResponsePojo;

import java.security.Principal;
import java.util.List;

public interface UserService {
    void createUser(UserRequestPojo user);
    UserResponsePojo getUserById(Integer id);
    List<UserResponsePojo> getAllUser();
    void deleteUser(Integer id);

    Boolean changePassword(String oldPassword, String newPassword, Principal principal);
}
