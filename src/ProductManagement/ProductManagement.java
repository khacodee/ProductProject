/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductManagement;

import data.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import list.ProductList;
import utils.Utils;

/**
 *
 * @author NhiLamHet
 */
public class ProductManagement {

    private static final ProductManagement instance = new ProductManagement();
    private final String productFilePath;
    private final ProductList productList;

    public static ProductManagement getInstance() {
        return instance;
    }

    public ProductList getProductList() {
        return productList;
    }

    private ProductManagement() {
        productFilePath = "Product.dat";
        productList = new ProductList(productFilePath);

    }

    private void init() {
        loadProduct();
    }

    private void loadProduct() {
        productList.load();
    }

    private void saveProduct() {
        productList.save();
    }

    private void showProduct() {
        productList.show();
    }

    private void addProduct() {
        Product e = new Product();
        e.input();
        productList.add(e);

    }

    private void checkExist() {
        String name = Utils.getName("Enter name: ", "Name is invalid!", "[0-9a-zA-Z]{5,}");
        if (productList.containsProduct(name)) {
            System.out.println("Exist Product!!!");
        } else {
            System.out.println("No Product Found!");
        }
    }

    private int[] searchNames(String name) {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().contains(name)) {
                index.add(i);
            }
        }
        int[] idx = new int[index.size()];
        for (int i = 0; i < idx.length; i++) {
            idx[i] = index.get(i);
        }
        return idx;

    }

    private void searchInfoByName() {
        String name = Utils.getString("Enter a part of product name: ", "Cannot Blank!", false);
        List<Product> resultList = productList.filterByName(name);
        if (resultList.isEmpty()) {
            System.out.println("");
        } else {
            for (Product product : resultList) {
                product.output();
            }
        }
//        int[] pos = searchNames(name);
//        if (pos.length == 0) {
//            System.out.println("Not Found");
//        } else {
//            System.out.println("Founded: ");
//            for (int i = 0; i < pos.length; i++) {
//                System.out.println(productList.get(pos[i]));
//            }
//        }

    }

    //searh to updates
    private int searchAName(String name) {

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    private void updateProduct() {
        String produceName = Utils.getName("Enter name to update: ", "Name is invalid!", "[0-9a-zA-Z]{5,}");
        int pos = searchAName(produceName);
        if (pos == -1) {
            System.out.println("Productname does not exist.\n");
        } else {
            productList.get(pos).input();
        }
    }

    private void deleteProduct() {
        String produceName = Utils.getName("Enter name: ", "Name is invalid!", "[0-9a-zA-Z]{5,}");
        int pos = searchAName(produceName);
        if (pos == -1) {
            System.out.println("Productname does not exist.\n");
        } else {
            productList.remove(pos);
        }
    }

    private void printFromFile() {
        ProductList tmp = new ProductList(productFilePath);
        tmp.load();
        Collections.sort(tmp);
        if (tmp.isEmpty()) {
            System.out.println("The file is empty.");
        } else {

            tmp.show();
        }
    }

    private void process() {
        Menu mainMenu = new Menu("Product Management");
        mainMenu.addNewOption("1. Print all");
        mainMenu.addNewOption("2. Create a Product");
        mainMenu.addNewOption("3. Check exits Product");
        mainMenu.addNewOption("4. Search Product information by Name");
        mainMenu.addNewOption("5. Update Product");
        mainMenu.addNewOption("6. Save Products to file.");
        mainMenu.addNewOption("7. Print list Products from the file");

        Menu subMenu5 = new Menu("Update Product");
        subMenu5.addNewOption("1. Update information");
        subMenu5.addNewOption("2. Delete Product");
        int choice = 0, sChoice = 0;

        boolean askReturn = false;
        do {
            mainMenu.printMenu();
            choice = mainMenu.getChoice();
            switch (choice) {
                case 1:
                    showProduct();
//                    askReturn = Utils.getBoolean("\nReturn main menu (Y/N)?\nEnter: ", "Please enter Y or N", false);
                    break;
                case 2:
                    addProduct();
//                    askReturn = Utils.getBoolean("\nReturn main menu (Y/N)?\nEnter: ", "Please enter Y or N", false);
                    break;
                case 3:
                    checkExist();
//                    askReturn = Utils.getBoolean("\nReturn main menu (Y/N)?\nEnter: ", "Please enter Y or N", false);
                    break;
                case 4:
                    searchInfoByName();
//                    askReturn = Utils.getBoolean("\nReturn main menu (Y/N)?\nEnter: ", "Please enter Y or N", false);
                    break;
                case 5:
                    subMenu5.printMenu();
                    sChoice = subMenu5.getChoice();
                    switch (sChoice) {
                        case 1:
                            updateProduct();
                            break;
                        case 2:
                            deleteProduct();
                            break;
                    }
//                    askReturn = Utils.getBoolean("\nReturn main menu (Y/N)?\nEnter: ", "Please enter Y or N", false);
                    break;
                case 6:
                    saveProduct();
                    System.out.println("Store data successfully!");
//                    askReturn = Utils.getBoolean("\nReturn main menu (Y/N)?\nEnter: ", "Please enter Y or N", false);
                    break;
                case 7:
                    printFromFile();
//                    askReturn = Utils.getBoolean("\nReturn main menu (Y/N)?\nEnter: ", "Please enter Y or N", false);
                    break;
            }
            askReturn = Utils.getBoolean("\nReturn main menu (Y/N)?\nEnter: ", "Please enter Y or N", false);

        } while (askReturn);
    }

    public static void main(String[] args) {
        ProductManagement productManagement = ProductManagement.getInstance();
        productManagement.init();
        productManagement.process();
    }
}
