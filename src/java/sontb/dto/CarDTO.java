/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.dto;

/**
 *
 * @author ADMIN
 */
public class CarDTO {
    private String carID;
    private String carName;
    private String color;
    private String year;
    private float price;
    private int quantity;
    private String categoryID;
    private float avg;
    private String rentalDate;
    private String returnDate;
    private String image;

    public CarDTO(String carID, String carName, String color, String year, float price, int quantity, String categoryID, float avg, String rentalDate, String returnDate, String image) {
        this.carID = carID;
        this.carName = carName;
        this.color = color;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.avg = avg;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.image = image;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   
    
    
}
