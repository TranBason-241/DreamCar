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
public class UserDTO {
     private String userID;
     private String password;
     private String userName;
     private String address;
     private Date createDate;
     private String status;
     private String code;
     private String phoneNumber;

    public UserDTO(String userID, String password, String userName, String address, Date createDate, String status, String code, String phoneNumber) {
        this.userID = userID;
        this.password = password;
        this.userName = userName;
        this.address = address;
        this.createDate = createDate;
        this.status = status;
        this.code = code;
        this.phoneNumber = phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
     
     
    
     
    
}
