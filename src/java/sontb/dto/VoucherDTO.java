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
public class VoucherDTO {
   private String code;
   private int percent;
   private Date expiryDate;

    public VoucherDTO(String code, int percent, Date expiryDate) {
        this.code = code;
        this.percent = percent;
        this.expiryDate = expiryDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

   
}
