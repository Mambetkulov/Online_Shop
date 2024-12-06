package Models;

import database.DataBase;
import enumpackage.Role;

import java.util.Arrays;

public class User {
    private long id;
    private String email;
    private String password ;
    private String name;
    private Role role;
    private Product[] FavoriteProducts = new Product[0];

    long generateId = 1;



    public User (){
     this.id = generateId++;
    }
    public User(String email, String password , String name, Role role ) {
        this.id = generateId++;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }


    public User(String email, String password , String name, Role role,Product[] favoriteProducts ){
        this.id = generateId++;
        this.email = email;
        this.password = password;
        this.name = name ;
        this.role = role;
        FavoriteProducts = favoriteProducts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Product[] getFavoriteProducts() {
        return FavoriteProducts;
    }

    public void setFavoriteProducts(int index) {
        for (int i = 0; i < DataBase.products.length; i++) {
            if(DataBase.products[i].getId() == index){
                FavoriteProducts = Arrays.copyOf(FavoriteProducts,FavoriteProducts.length + 1);
                FavoriteProducts[FavoriteProducts.length - 1] = DataBase.products[i];
                break;
            }

        }


    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +

                '}';
    }
}
