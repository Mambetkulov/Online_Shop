package dao;

import Models.User;
import database.DataBase;

import java.util.Arrays;


public class UserDaoImp implements UserDao{
    @Override
    public void Registration(User user) {
        DataBase.users = Arrays.copyOf(DataBase.users, DataBase.users.length + 1);
        DataBase.users[DataBase.users.length - 1] = user;
    }

    @Override
    public User[] findAll() {
        return DataBase.users;
    }


}
