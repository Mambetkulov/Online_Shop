package service;

import Models.User;

public interface UserService {
    String registration(User user);

    User[] findAll();

    User signIn (String email , String password);
}
