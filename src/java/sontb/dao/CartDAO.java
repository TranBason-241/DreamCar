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
import org.apache.log4j.Logger;
import sontb.dto.CarDTO;
import sontb.dto.CartDTO;
import sontb.dto.UserDTO;
import sontb.dto.VoucherDTO;
import sontb.utils.MyConnection;

/**
 *
 * @author ADMIN
 */
public class CartDAO {

    static final Logger LOGGER = Logger.getLogger(CartDAO.class);

    public int checkOut(UserDTO user, float total, CartDTO cart, VoucherDTO voucher) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int result = -1;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                //callback
                cn.setAutoCommit(false);
                String code = null;
                int percent = 0;
                if (voucher != null) {
                    code = voucher.getCode();
                    percent = voucher.getPercent();
                }
                //Vi cai code thêm vào sql, có hoặc null, mà null thì k có '' nên phải tách 2 trường hợp
                String sqlOrder = "";
                if (code == null) {
                    sqlOrder = "INSERT dbo.tblOrderss\n"
                            + "        ( userID ,\n"
                            + "          total ,\n"
                            + "          createDate ,\n"
                            + "          address ,\n"
                            + "          phone ,\n"
                            + "          code ,\n"
                            + "          percentOfDiscount ,\n"
                            + "          status\n"
                            + "        )\n"
                            + "VALUES  ( '" + user.getUserID() + "' , -- userID - varchar(50)\n"
                            + "          " + total + " , -- total - float\n"
                            + "          GETDATE() , -- createDate - date\n"
                            + "          '" + user.getAddress() + "' , -- address - varchar(30)\n"
                            + "          '" + user.getPhoneNumber() + "' , -- phone - varchar(15)\n"
                            + "          " + code + " , -- code - varchar(2)\n"
                            + "          " + percent + " , -- percentOfDiscount - int\n"
                            + "          'active'  -- status - varchar(30)\n"
                            + "        )";
                } else {
                    sqlOrder = "INSERT dbo.tblOrderss\n"
                            + "        ( userID ,\n"
                            + "          total ,\n"
                            + "          createDate ,\n"
                            + "          address ,\n"
                            + "          phone ,\n"
                            + "          code ,\n"
                            + "          percentOfDiscount ,\n"
                            + "          status\n"
                            + "          statusFeedBack\n"
                            + "        )\n"
                            + "VALUES  ( '" + user.getUserID() + "' , -- userID - varchar(50)\n"
                            + "          " + total + " , -- total - float\n"
                            + "          GETDATE() , -- createDate - date\n"
                            + "          '" + user.getAddress() + "' , -- address - varchar(30)\n"
                            + "          '" + user.getPhoneNumber() + "' , -- phone - varchar(15)\n"
                            + "          '" + code + "' , -- code - varchar(2)\n"
                            + "          " + percent + " , -- percentOfDiscount - int\n"
                            + "          'active'  -- status - varchar(30)\n"
                            + "          'no'  -- statusFeedBack - varchar(30)\n"
                            + "        )";
                }

                pst = cn.prepareStatement(sqlOrder);
                result = pst.executeUpdate();
                if (result != -1) {
                    //Get orderID
                    String sqlOrderID = "SELECT MAX(orderID) AS orderID\n"
                            + "		FROM dbo.tblOrderss";
                    pst = cn.prepareStatement(sqlOrderID);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        int orderID = rs.getInt("orderID");
                        for (CarDTO carDTO : cart.getCart()) {
                            String carID = carDTO.getCarID();
                            int quantity = carDTO.getQuantity();
                            float price = carDTO.getPrice();
                            String rentalDate = carDTO.getRentalDate();
                            String returnDate = carDTO.getReturnDate();

                            String sqlDetail = "INSERT dbo.tblOderDetail\n"
                                    + "		        ( carID ,\n"
                                    + "		          quantity ,\n"
                                    + "		          price ,\n"
                                    + "		          returnDate ,\n"
                                    + "		          rentalDate ,\n"
                                    + "		          orderID\n"
                                    + "		        )\n"
                                    + "		VALUES  ( " + carID + " , -- carID - int\n"
                                    + "		          " + quantity + " , -- quantity - int\n"
                                    + "		          " + price + " , -- price - float\n"
                                    + "		          '" + returnDate + "' , -- returnDate - varchar(50)\n"
                                    + "		          '" + rentalDate + "' , -- rentalDate - varchar(50)\n"
                                    + "		          " + orderID + "  -- orderID - int\n"
                                    + "		        )";
                            pst = cn.prepareStatement(sqlDetail);
                            result = pst.executeUpdate();

                        }
                    }
                } else {
                    result = -1;
                }
                cn.commit();
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
        return result;
    }
}
