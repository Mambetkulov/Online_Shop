import Models.Product;
import Models.User;
import config.Validation;
import dao.ProducDaoImp;
import dao.UserDaoImp;

import enumpackage.Category;
import enumpackage.Color;
import enumpackage.Role;
import enumpackage.Size;
import exception.NotFoundException;
import service.ProductServiceImpl;
import service.UserServiceImpl;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

   public static Category[] categories = new Category[]{Category.MAN,Category.CHILDREN,Category.WOMAN};
   public static Size[] sizes = new Size[]{Size.M,Size.S,Size.L,Size.XS,Size.XL,Size.XXL,Size.XXS};
   public static Color[] colors = new Color[]{Color.BLACK,Color.BLUE,Color.RED,Color.GREEN,Color.ORANGE,Color.PURPLE,Color.WHITE,Color.YELLOW};

    public static void main(String[] args) {
        ProducDaoImp producDao = new ProducDaoImp();
        ProductServiceImpl productService = new ProductServiceImpl(producDao);
        UserDaoImp userDao = new UserDaoImp();
        UserServiceImpl userService = new UserServiceImpl(userDao);
        Scanner scanner = new Scanner(System.in);
        User admin = new User("admin@gmail.com", "Admin123!", "Admin", Role.ADMIN);
        User current = null;
        while (true) {
            System.out.println("""
                    \n
                    press to 1 : sign up
                    press to 2 : get all user
                    press to 3 : sign in
                    \n
                    """);



            switch(checkValidChoice()){
                case 1: {
                    System.out.println("Registration");
                  String result =  validEmailOrP("Email ",Validation.emailPattern());
                    System.out.println(result);

                    String result2 = validEmailOrP("Password ",Validation.passwordPattern());
                    System.out.println(result2);

                    System.out.println("write name ");
                    String line = scanner.nextLine();
                    userService.registration(new User(result,result2,line,Role.CLIENT));
                     break;
                }
                case 2 : {
                    System.out.println("All users : ");
                    for(User i : userService.findAll()){
                        System.out.println(i);
                    }
                    break;
                }
                case 3 : {
                    System.out.println("Sign in");
                    String result =  validEmailOrP("Email ",Validation.emailPattern());
                    String result2 = validEmailOrP("Password ",Validation.passwordPattern());
                    if(result.equals(admin.getEmail())&& result2.equals(admin.getPassword())) {
                       adminPannel(productService);
                    }else {
                        try {
                            current = userService.signIn(result, result2);
                            userPannel(current, productService);
                        } catch (NotFoundException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    break;
                }
                default:{
                    System.out.println("\n There is no such command | please try again ");
                }


            }
        }






    }
    public static String validEmailOrP (String label, String pattern){
        System.out.printf("enter the %s",label);
        String data;
        do{
            data = new Scanner(System.in).nextLine();
            if(!data.matches(pattern)){
                System.out.printf("Invalid %s :  enter valid %s  ",label,label);
            }
        }while(!data.matches(pattern));

        return data;
    }








    public static int checkValidChoice(){
        int choice = 0;
        boolean valid ;
        System.out.println("write your choice");
        do {
            try {
                choice = new Scanner(System.in).nextInt();
                valid = false;
            } catch (InputMismatchException e) {
                valid = true;
                System.out.println("Invalid command ! write valid one");
            }
        }while(valid);

       return choice;
    }


    public static void userPannel (User user,ProductServiceImpl productService){
        if(user != null){
            boolean turnOff = true;
            while(turnOff){
                System.out.println("""
                        \n
                        all products : press 1
                        category and size : press 2
                        add product to favorite or delete : press 3
                        get all favorite products : press 4
                        get product by id : press 5
                        logout : 6
                        \n
                        """);

                switch (checkValidChoice()){
                    case 1 : {

                            System.out.println(" all products in the store ");
                            for (Product i : productService.findAllP()) {
                                    System.out.println(i);


                            }

                        break;
                    }
                    case 2 :{
                        productService.categoryAndSize();
                        break;
                    }
                    case 3:{
                        System.out.println("write index");
                        int ind = new Scanner(System.in).nextInt();
                            user.setFavoriteProducts(ind);
                            break;
                    }
                    case 4: {

                         Product[] products = productService.allfavoride(user);
                        System.out.println("""
                                _________________________________
                                      my favorite products
                                """);
                         for(Product i : products){
                             System.out.println(" Category - " + i.getCategory() + "\n Name - " + i.getName() + "\n Id - " + i.getId()
                             + "\n Color - " + i.getColor() + "\n Size - " + i.getSize() + "\n Image - " + i.getImageUrl() + "\n Price - " + i.getPrice()
                               + "\n Quantity - " + i.getQuantity());

                         }
                        System.out.println("________________________________");
                        break;
                    }
                    case 5 : {
                        System.out.println("write index");
                        int ind = new Scanner(System.in).nextInt();
                        productService.getFavoriteById(user.getFavoriteProducts(), ind);
                        break;
                    }
                    case 6 : {
                        turnOff = false;
                        break;
                    }

                    default :{
                        System.out.println("\n There is no such command | please try again ");
                    }

                }

            }
        }

    }



    public static void adminPannel (ProductServiceImpl productService){
        boolean turnOff = true;
        while(turnOff){
            System.out.println("""
                    \n
                    add product : 1
                    delete product : 2
                    update product : 3
                    get all product : 4
                    logout : 5
                    
                    """);

            switch(checkValidChoice()){
                case 1:{
                  Product product = fill();
                   productService.addProduct(product);
                   break;
                }
                case 2: {
                    System.out.println("write id");
                    int index = new Scanner(System.in).nextInt();
                    productService.delete(index);
                    break;
                }
                case 3:{
                    System.out.println("write id");
                    int index = new Scanner(System.in).nextInt();
                    Product product2 = fill();
                    productService.update(index,product2);
                    break;
                }
                case 4 :{
                    System.out.println(" all products in the store \n");
                    for (Product i : productService.findAllP()) {

                            System.out.println(i);

                    }
                   break;
                }
                case 5:{
                    turnOff = false;
                    break;
                }
                default :{
                    System.out.println("\n There is no such command | please try again ");
                }
            }
        }
    }


    public static Product fill(){
        Product pr = new Product();
        Scanner num = new Scanner(System.in);
        Scanner text = new Scanner(System.in);
        int number;
        int choice1 = 0;
        int choice2 = 0;
        int choice3 = 0;
        double price = 0.0;
        int quantity = 1;
        String name = "";

        boolean turn = true;
        while(turn) {
            System.out.println("write category \n");
            int count = 0;
            for (Category i : categories) {
                System.out.println( count + ") " + i );
                count++;
            }
             choice1 = num.nextInt();
            if (choice1 <= 2) {
                turn = false;
                pr.setCategory(categories[choice1]);
            }else{
                System.out.println("write valid number");
            }
        }
            turn = true;
        while(turn) {

            System.out.println("write name");
            name = text.nextLine();
            if(name.isEmpty()){
                System.out.println("write name please");
            }else{
                turn = false;
                pr.setName(name);
            }
        }



            do {
                System.out.println("write price");
                number = 1;
                try {
                     price = new Scanner(System.in).nextDouble();
                     if(price > 1.0) {
                         pr.setPrice(price);
                     }else{
                         System.out.println("price must be greater than 1,0 $");
                         number = 0;
                     }
                } catch (Exception e) {
                    number = 0;
                    System.out.println("write right price");
                }
            }while(number == 0);


            turn = true;
        while(turn) {
            System.out.println("write size");
            int count = 0;
            for (Size i : sizes) {
                System.out.print("( " + count + " - press > " + i + " )");
                count++;
            }
            System.out.println(" ");
             choice2 = num.nextInt();
            if (choice2 <= 6) {
                turn = false;
                pr.setSize(sizes[choice2]);
            }else{
                System.out.println("write right size");
            }
        }


           turn = true;
        while(turn) {
            System.out.println("write color");
            int count = 0;
            for (Color i : colors) {
                System.out.print("( " + count + " - press > " + i + " )");
                count++;
            }
            System.out.println(" ");
             choice3 = num.nextInt();
            if (choice3 <= 7) {
                turn = false;
                pr.setColor(colors[choice3]);
            }else{
                System.out.println("write right color");
            }
        }

        System.out.println("write Url photo ");
        String photo = text.nextLine();
        pr.setImageUrl(photo);

        do {
            System.out.println("write quantity");
            number = 1;
            try {
                 quantity = new Scanner(System.in).nextInt();
                 pr.setQuantity(quantity);
            } catch (Exception e) {
                number = 0;
                System.out.println("write number");
            }
        }while(number == 0);

        return new Product(categories[choice1],name,price,sizes[choice2],colors[choice3],photo,quantity);



    }
}