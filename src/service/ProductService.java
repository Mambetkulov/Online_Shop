package service;

import Models.Product;
import Models.User;

public interface ProductService {

    String addProduct (Product product);

    Product[] findAllP();

    void delete (int index);

    void update (int index ,Product product);

    Product[] allfavoride (User user);

    void getFavoriteById (Product[] products,int ind);

    void categoryAndSize ();


}
