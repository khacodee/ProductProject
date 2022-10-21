/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import data.Product;

/**
 *
 * @author NhiLamHet
 */
public class ProductList extends ObjectList<Product> {

    public ProductList() {
    }

    public ProductList(String productFilePath) {
        super(productFilePath); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    protected Product parseString(String stringObject) {
        Product obj = new Product();
        obj.parseString(stringObject);
        return obj; //To change body of generated methods, choose Tools | Templates.
    }

}
