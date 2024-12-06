package dao;

import Models.User;

public interface UserDao {

    void Registration(User user);

    User[] findAll();




}
