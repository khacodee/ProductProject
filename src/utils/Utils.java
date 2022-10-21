/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import ProductManagement.ProductManagement;
import data.Product;
import java.util.Scanner;

/**
 *
 * @author NhiLamHet
 */
public class Utils {

    //private static final String IGNORE_CASE_PATTERN = "(?i)";
    private static Scanner sc = new Scanner(System.in);

    public static boolean validateString(String str, String regex) {
        if (str != null && regex != null) {

            if (str.toUpperCase().matches(regex)) {
                return true;
            }           
        }
        return false;
    }

    public static String getString(String inputMsg, String errorMsg, boolean allowBlank) {
        String str;
        while (true) {
            System.out.print(inputMsg);
            str = sc.nextLine().trim().toUpperCase();
            if (str.length() == 0 || str.isEmpty()) {
                if (allowBlank) {
                    return "";
                } else {
                    System.out.println(errorMsg);
                }

            } else {
                return str;
            }
        }
    }

    public static float getAFloat(String inputMsg, String errorMsg, float lowerBound, float upperBound) {
        float n, tmp;
        if (lowerBound > upperBound) {     //swap lowerBound and upperBound
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n, tmp;
        if (lowerBound > upperBound) {     //swap lowerBound and upperBound
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static Boolean getBoolean(String inputMsg, String errorMsg, boolean allowBlank) {
        Boolean result;
        do {
            try {
                String input = getString(inputMsg, errorMsg, allowBlank);
                if (input.isEmpty() && allowBlank) {
                    throw new Exception();
                } else if (!input.toUpperCase().equals("N") && !input.toUpperCase().equals("Y")) {
                    throw new Exception();
                }
                result = input.toUpperCase().equals("Y");
                return result;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        } while (true);
    }

    public static String getName(String inputMsg, String errorMsg, String format) {
        String name;
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            name = sc.nextLine().trim().toUpperCase();
            match = name.matches(format);
            if (name.length() == 0 || name.isEmpty() || match == false) {
                System.out.println(errorMsg);
            } else {
                return name;
            }
        }
    }
    
     public static boolean checkExistsProductName(String name) {
        Product obj = new Product();
        obj.setProductName(name);
        return ProductManagement.getInstance().getProductList().contains(obj);
    }
}
