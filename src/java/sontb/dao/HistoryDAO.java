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
import sontb.dto.OrderDTO;
import sontb.utils.MyConnection;

/**
 *
 * @author ADMIN
 */
public class HistoryDAO {
static final Logger LOGGER = Logger.getLogger(HistoryDAO.class);
    public ArrayList<OrderDTO> getOrder(String optionSearch, String searchName, String searchDate) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<OrderDTO> listOrder = new ArrayList<>();

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "";
                //Neu lan dau thi optionsearch = null
                if (optionSearch == null) {
                    sql = "SELECT createDate,orderID,total,status,statusFeedBack\n"
                            + "	FROM dbo.tblOrderss \n"
                            + "	GROUP BY createDate,orderID,total,status,statusFeedBack";
                } else {
                    //
                    if (optionSearch.equals("searchByName")) {
                        sql = "SELECT o.createDate,o.orderID,o.total,o.status,o.statusFeedBack\n"
                                + "	FROM dbo.tblOrderss o JOIN (SELECT d.carID AS carID,d.orderID\n"
                                + "		FROM dbo.tblOderDetail d JOIN dbo.tblCars c ON c.carID = d.carID\n"
                                + "		WHERE c.carName LIKE '%" + searchName + "%'\n"
                                + "		GROUP BY d.carID, d.orderID) tmp ON o.orderID = tmp.orderID\n"
                                + "GROUP BY o.createDate,o.orderID,o.total,o.status,o.statusFeedBack";
                    } else if (optionSearch.equals("searchByDate")) {
                        sql = "SELECT createDate,orderID,total,status,statusFeedBack\n"
                                + "	FROM dbo.tblOrderss \n"
                                + "	WHERE createDate ='" + searchDate + "'\n"
                                + "	GROUP BY createDate,orderID,total,status,statusFeedBack";
                    }
                }

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    Date createDate = rs.getDate("createDate");
                    float total = rs.getFloat("total");
                    String status = rs.getString("status");
                    String statusFeedBack = rs.getString("statusFeedBack");
                    OrderDTO order = new OrderDTO(orderID, total, createDate, status,"",statusFeedBack);
                    listOrder.add(order);
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
        return listOrder;
    }
}
