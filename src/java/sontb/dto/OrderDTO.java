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
public class OrderDTO {
    private String orderID;
    private float total;
    private Date createDate;
    private String status;
    private String statusDate;
    private String statusFeedBack;

    public OrderDTO(String orderID, float total, Date createDate, String status, String statusDate, String statusFeedBack) {
        this.orderID = orderID;
        this.total = total;
        this.createDate = createDate;
        this.status = status;
        this.statusDate = statusDate;
        this.statusFeedBack = statusFeedBack;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
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

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public String getStatusFeedBack() {
        return statusFeedBack;
    }

    public void setStatusFeedBack(String statusFeedBack) {
        this.statusFeedBack = statusFeedBack;
    }

    

    
   
    
    
    
            
}
