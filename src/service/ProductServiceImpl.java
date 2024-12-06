package service;

import Models.Product;
import Models.User;
import dao.ProductDao;
import database.DataBase;


public class ProductServiceImpl implements ProductService{
    private final ProductDao productDao ;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public String addProduct(Product product) {
        productDao.addProduct(product);
        return " Successfully added ";
    }

    public Product[] findAllP (){return productDao.findallP();}

    @Override
    public void delete(int index) {
        productDao.deleta(index);
    }

    @Override
    public void update(int index, Product product) {
        productDao.update(index,product);
    }

    @Override
    public Product[] allfavoride(User user) {
        return productDao.allfavoride(user);
    }

    @Override
    public void getFavoriteById(Product[] products, int ind) {
        productDao.getFavoriteById(products,ind);
    }

    @Override
    public void categoryAndSize() {
        productDao.categoryAndSize();
    }
}
