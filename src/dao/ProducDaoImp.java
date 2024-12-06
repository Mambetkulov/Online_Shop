package dao;

import Models.Product;
import Models.User;
import database.DataBase;

import java.util.Arrays;

public class ProducDaoImp  implements ProductDao{
    @Override
    public void addProduct(Product product) {
        DataBase.products = Arrays.copyOf(DataBase.products,DataBase.products.length + 1);
        DataBase.products[DataBase.products.length - 1] = product;
        System.out.println("successfully added");
    }

    @Override
    public Product[] findallP() {
        return DataBase.products;
    }

    @Override
    public void deleta(int index) {
        boolean turnOff = true;
        for (int i = 0; i < DataBase.products.length - 1; i++) {
            if(DataBase.products[i].getId() == index){
                for (int j = i; j <DataBase.products.length ; j++) {
                    turnOff = false;
                    DataBase.products[i] = DataBase.products[i+1];
                }
            }
        }

        DataBase.products = Arrays.copyOf(DataBase.products,DataBase.products.length - 1);
        if(turnOff){
            System.out.println("Product with this id not found ");
        }else{
            System.out.println("successfully deleted");
        }
    }

    @Override
    public void update(int index, Product product) {
        boolean turnOff = true;
        for (int i = 0; i < DataBase.products.length; i++) {
            if(DataBase.products[i].getId() == index){
                turnOff = false;
                DataBase.products[i] = product;
            }
        }

        if(turnOff){
            System.out.println("Product with this id not found ");
        }else{
            System.out.println("successfully updated");
        }

    }

    @Override
    public void favoriteP(Product[] favorite,int index) {
        boolean turnOff = true;
        for (int i = 0; i < DataBase.products.length; i++) {
            if(DataBase.products[i].getId() == index){
                turnOff = false;
                favorite = Arrays.copyOf(favorite,favorite.length + 1);
                favorite[favorite.length - 1] = DataBase.products[i];

            }

        }
        if(turnOff){
            System.out.println("product with such index not found");
        }

    }


    @Override
    public Product[] allfavoride(User user) {

        return user.getFavoriteProducts();
    }

    @Override
    public void getFavoriteById(Product[] products,int ind) {
        System.out.println("""
                \n _________________________________
                             My Product
                """);
        for (int i = 0; i < products.length; i++) {
            if(products[i].getId() == ind){
                System.out.println(" Category - " + products[i].getCategory() + "\n Name - " + products[i].getName() + "\n Id - " +
                        products[i].getId() + "\n Color - " + products[i].getColor() + "\n Size - " + products[i].getSize() + "\n Price - "
                        + products[i].getPrice() + "\n Image - "  + products[i].getImageUrl() + "\n Quantity - " + products[i].getQuantity());

                break;
            }else{
                System.out.println("Product with this " + ind + " id not found");
            }
        }
        System.out.println(" \n___________________________");

    }

    @Override
    public void categoryAndSize() {
        Product[] products1 = DataBase.products;
        for(Product i : products1){
            System.out.println(" Name - " + i.getName() + "| Category - " + i.getCategory() + "| Size - " + i.getSize() + "\n"  );
        }
    }
}
