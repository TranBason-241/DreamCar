/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.apache.log4j.Logger;
import sontb.dto.VoucherDTO;
import sontb.utils.MyConnection;

/**
 *
 * @author ADMIN
 */
public class VoucherDAO {

    static final Logger LOGGER = Logger.getLogger(VoucherDAO.class);

    public VoucherDTO getVoucher(String code) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        VoucherDTO voucher = null;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "SELECT code,percentDiscount,expiryDate\n"
                        + "FROM dbo.tblDiscount \n"
                        + "WHERE code ='" + code + "'";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String codee = rs.getString("code");
                    int present = rs.getInt("percentDiscount");
                    Date expiryDate = rs.getDate("expiryDate");
                    voucher = new VoucherDTO(codee, present, expiryDate);
                }
            }
        } catch (Exception e) {

            LOGGER.error("error: ", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return voucher;
    }
}
