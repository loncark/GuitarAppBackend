package com.loncark.guitarapp.aspects;

import com.loncark.guitarapp.model.Material;

import java.util.Objects;

public class Alerts {

    public static boolean oneIsNull(Object... args) {
        for (Object arg : args) {
            if (arg == null) {
                System.out.println("No variable should be null.");
                return true;
            }
        }
        return false;
    }

    public static boolean isAllOk(String name, String price, String stock, String neck, String body, String code) {
        if (isNameOk(name) && isBodyMaterialOk(body) && isNeckMaterialOk(neck) && isCodeOk(code)
                && isPriceOk(price) && isStockOk(stock)) {
            return true;
        }
        return false;
    }

    public static boolean isNameOk(String name) {
        if (name.matches("^(?![\\s.]+$)[a-zA-Z0-9\\s.]*$") == false || name.equals("")) {
            System.out.println("The name should consist solely of letters and numbers.");
            return false;
        }
        return true;
    }

    public static boolean isPriceOk(String price) {              // real numbers
        if (price.equals("") || !price.matches("((\\+|-)?([0-9]+)(\\.[0-9]+)?)|((\\+|-)?\\.?[0-9]+)")
                || Double.parseDouble(price) < 0.0) {
            System.out.println("The price should be larger than 0.");
            return false;
        }
        return true;
    }

    public static boolean isStockOk(String stock) { // positive, non null integer regex
        if (!stock.matches("^[1-9]\\d*$") || Integer.parseInt(stock) < 1 || Integer.parseInt(stock) > 6) {
            System.out.println("The stock should be larger than 0 and less than 7.");
            return false;
        }
        return true;
    }

    public static boolean isNeckMaterialOk(String neck) {
        if (!Objects.equals(neck, Material.Rosewood.toString()) && !Objects.equals(neck, Material.Maple.toString())) {
            System.out.println("The neck material should be one of the following: Rosewood, Maple");
            return false;
        }
        return true;
    }

    public static boolean isBodyMaterialOk(String body) {
        if (!Objects.equals(body, Material.Alder.toString()) && !Objects.equals(body, Material.Mahogany.toString())
                && !Objects.equals(body, Material.Ebony.toString())) {
            System.out.println("The body material should be one of the following: Alder, Mahogany, Ebony.");
            return false;
        }
        return true;
    }

    public static boolean isCodeOk(String code) {
        if (code.matches("^\\d{4}$") == false) {
            System.out.println("The code should have exactly 4 digits.");
            return false;
        }
        return true;
    }
}

