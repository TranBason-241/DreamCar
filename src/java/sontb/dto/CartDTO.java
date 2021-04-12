/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.dto;

import java.util.ArrayList;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author ADMIN
 */
public class CartDTO {

    private String customerID;
    private ArrayList<CarDTO> cart;

    public CartDTO(String customerID, ArrayList<CarDTO> cart) {
        this.customerID = customerID;
        this.cart = cart;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public ArrayList<CarDTO> getCart() {
        return cart;
    }

    public void setCart(ArrayList<CarDTO> cart) {
        this.cart = cart;
    }

    public void addToCart(CarDTO car) {
        if (cart == null) {
            cart = new ArrayList<>();
        }
        boolean flag = false;

        for (CarDTO carDTO : cart) {
            if (carDTO.getCarID().equals(car.getCarID()) && carDTO.getRentalDate().equals(car.getRentalDate()) && carDTO.getReturnDate().equals(car.getReturnDate())) {
                int quantity = carDTO.getQuantity() + 1;
                carDTO.setQuantity(quantity);
                flag = true;
                return;
            }
        }
        if (!flag) {
            car.setQuantity(1);
            cart.add(car);
        }
    }

    public void update(CarDTO car, String operator) {
        if (this.cart == null) {
            return;
        }

        if (operator.equals("+")) {

            for (CarDTO carDTO : cart) {
                if (carDTO.getCarID().equals(car.getCarID()) && carDTO.getRentalDate().equals(car.getRentalDate()) && carDTO.getReturnDate().equals(car.getReturnDate())) {
                    int quantity = carDTO.getQuantity() + 1;
                    carDTO.setQuantity(quantity);
                    return;
                }

            }

        } else if (operator.equals("-")) {
            for (CarDTO carDTO : cart) {
                if (carDTO.getCarID().equals(car.getCarID()) && carDTO.getRentalDate().equals(car.getRentalDate()) && carDTO.getReturnDate().equals(car.getReturnDate())) {
                    if (carDTO.getQuantity() > 1) {
                        int quantity2 = carDTO.getQuantity() - 1;
                        carDTO.setQuantity(quantity2);
                        return;
                    }
                }

            }
        }
    }

    public void delete(String carID, String rentalDate, String returnDate) {
        if (cart == null) {
            return;
        }
        for (CarDTO carDTO : cart) {
            if (carDTO.getCarID().equals(carID) && carDTO.getRentalDate().equals(rentalDate) && carDTO.getReturnDate().equals(returnDate)) {
                cart.remove(carDTO);
                return;
            }
        }

    }

}
