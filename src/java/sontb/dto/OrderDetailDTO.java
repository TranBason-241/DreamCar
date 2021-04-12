/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.dto;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class OrderDetailDTO {

    private String carID;
    private String carName;
    private Date rentalDate;
    private Date returnDate;
    private float price;
    private int quantity;
    private int OrderDetailID;

    public OrderDetailDTO(String carID, String carName, Date rentalDate, Date returnDate, float price, int quantity, int OrderDetailID) {
        this.carID = carID;
        this.carName = carName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.price = price;
        this.quantity = quantity;
        this.OrderDetailID = OrderDetailID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(int OrderDetailID) {
        this.OrderDetailID = OrderDetailID;
    }

    
}
