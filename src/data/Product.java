/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import list.IObject;
import utils.Utils;

/**
 *
 * @author NhiLamHet
 */
public class Product implements IObject, Comparable<Product> {

    private String productID;
    private String productName;
    private float unitPrice;
    private int quantity;
    private boolean status;

    private static final String NAME_PATTERN = "[0-9a-zA-Z]{5,}";
    private static final String SEPARATOR = ",";
    private static final String NAME_FORMAT = "xxxxx";
    private static final int ATTRIBUTE_COUNT = 5;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {

        this.productID = productID;

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (utils.Utils.validateString(productName, Product.NAME_PATTERN)) {
                this.productName = productName.toUpperCase();
        }
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        if (unitPrice >= 0 && unitPrice <= 10000) {
            this.unitPrice = unitPrice;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0 && quantity <= 1000) {
            this.quantity = quantity;
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Product() {
    }

    public Product(String productID, String productName, float unitPrice, int quantity, boolean status) {
        setProductID(productID);
        setProductName(productName);
        setUnitPrice(unitPrice);
        setQuantity(quantity);
        setStatus(status);
    }

    public void input() {
        System.out.println("Input Product ...");
        this.productID = Utils.getString("Enter product ID: ", "ID invalid! ", false);
        this.productName = inputProductName();
        this.unitPrice = Utils.getAFloat("Enter unit price: ", "Unit Price is invalid!", 0, 10000);
        this.quantity = Utils.getAnInteger("Enter quantity: ", "Quantity is invalid!", 0, 1000);
        this.status = Utils.getBoolean("Available or Not Available (Y/N): ", "Invalid!", false);;
    }

    private boolean validateProductName(String name) {
        return Utils.checkExistsProductName(name);
    }

    private String inputProductName() {
        String name;
        do {
            try {
                name = Utils.getName("Enter Name: ", "Please enter the id with the pattern(" + Product.NAME_FORMAT + ")", Product.NAME_PATTERN).trim();
                if (validateProductName(name)) {
                    System.out.println("The product name is already exists!! ");
                    throw new Exception();
                }
                return name;
            } catch (Exception e) {
            }

        } while (true);

    }

    public int parseString(String stringObject) {
        if (stringObject != null) {
            return setAttribute(stringObject.split(Product.SEPARATOR));
        }
        return 0;
    }

    public int setAttribute(String[] attributes) {
        int idx = 0;
        if (attributes != null && attributes.length >= getAttributeCount()) {
            setProductID(attributes[idx++].trim());
            setProductName(attributes[idx++].trim());
            setUnitPrice(Float.parseFloat(attributes[idx++].trim()));
            setQuantity(Integer.parseInt(attributes[idx++].trim()));
            setStatus(Boolean.parseBoolean(attributes[idx++].trim()));
        }
        return idx;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.productID);
        sb.append(Product.SEPARATOR);
        sb.append(this.productName);
        sb.append(Product.SEPARATOR);
        sb.append(this.unitPrice);
        sb.append(Product.SEPARATOR);
        sb.append(this.quantity);
        sb.append(Product.SEPARATOR);
        sb.append(this.status);
        return sb.toString();
    }

    protected int getAttributeCount() {
        return Product.ATTRIBUTE_COUNT;
    }

    @Override
    public void output() {
        StringBuilder sb = new StringBuilder();
        sb.append(toString());
        System.out.println(sb.toString());
    }

    @Override
    public String getName() {
        return productName; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Product o) {
        if (Integer.compare(this.getQuantity(), o.getQuantity()) == 0) {
            return Float.compare(this.getUnitPrice(), o.getUnitPrice());
        }
        return Integer.compare(o.getQuantity(), this.getQuantity());
    }

}
