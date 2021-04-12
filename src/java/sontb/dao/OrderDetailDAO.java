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
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import sontb.dto.OrderDetailDTO;
import sontb.utils.MyConnection;

/**
 *
 * @author ADMIN
 */
public class OrderDetailDAO {
static final Logger LOGGER = Logger.getLogger(OrderDetailDAO.class);
    public ArrayList<OrderDetailDTO> getOrderDetail(String orderID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<OrderDetailDTO> list = new ArrayList<>();

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "SELECT tblCars.carID,carName,tmp.rentalDate,tmp.returnDate,price,tblCars.quantity,tmp.detailID\n"
                        + "				FROM dbo.tblCars JOIN (SELECT d.carID,d.rentalDate,d.returnDate,d.quantity,d.detailID\n"
                        + "				FROM dbo.tblOderDetail d JOIN dbo.tblOrderss  o ON o.orderID = d.orderID\n"
                        + "				WHERE o.orderID = '"+orderID+"') tmp ON tmp.carID = tblCars.carID";

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString("carID");
                    String carName = rs.getString("carName");
                    Date retalDate = rs.getDate("rentalDate");
                    Date returnDate = rs.getDate("returnDate");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    int orderDetailID = rs.getInt("detailID");

                    OrderDetailDTO orderDetail = new OrderDetailDTO(carID, carName, retalDate, returnDate, price, quantity, orderDetailID);
                    list.add(orderDetail);
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
        return list;

    }
}
