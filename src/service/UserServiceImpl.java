package service;

import Models.User;
import config.Validation;
import dao.UserDao;
import database.DataBase;
import exception.InvalidData;
import exception.NotFoundException;

public class UserServiceImpl implements UserService{
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String registration(User user) {
         if(!Validation.validate(user.getEmail())){
             throw new InvalidData("Invalid Email");
         }else{
           User[] users =  userDao.findAll();
             for (User u : users) {
                 if (u.getEmail().equals(user.getEmail())){
                     throw new IllegalArgumentException("This email already exist");
                 }
             }
         }
         if(!Validation.validatePas(user.getPassword())) {
             throw new InvalidData("Invalid Password");
         }
         userDao.Registration(user);

        return "successfully registrated!!";
    }

    @Override
    public User[] findAll() {
        return DataBase.users;
    }

    @Override
    public User signIn(String email, String password) {

        for(User user : findAll()){
            if(user.getEmail().equals(email)){
                if(user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        throw new NotFoundException("User not found");
    }
}
