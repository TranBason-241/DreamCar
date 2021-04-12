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
import sontb.dto.UserDTO;
import sontb.utils.MyConnection;

/**
 *
 * @author ADMIN
 */
public class UserDAO {

static final Logger LOGGER = Logger.getLogger(UserDAO.class);
    public UserDTO getUser(String userID, String password) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserDTO user = null;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "SELECT userID, password,name,address,createDate,status,code,phoneNumber\n"
                        + "		FROM dbo.tblUsers\n"
                        + "		WHERE userID = '" + userID + "' AND password = '" + password + "'";

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String ID = rs.getString("userID");
                    String pw = rs.getString("password");
                    String userName = rs.getString("name");
                    String address = rs.getString("address");
                    Date createDate = rs.getDate("createDate");
                    String status = rs.getString("status");
                    String code = rs.getString("code");
                    String phoneNumber = rs.getString("phoneNumber");

                    user = new UserDTO(ID, pw, userName, address, createDate, status, code, phoneNumber);
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
        return user;

    }

    //Tao moi user
    public int createUser(String userID, String password, String name, String address, String code, String phoneNumber) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = -1;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "INSERT dbo.tblUsers\n"
                        + "        ( userID ,\n"
                        + "          password ,\n"
                        + "          name ,\n"
                        + "          address ,\n"
                        + "          createDate ,\n"
                        + "          status ,\n"
                        + "          code ,\n"
                        + "          phoneNumber\n"
                        + "        )\n"
                        + "VALUES  ( '" + userID + "' , -- userID - varchar(50)\n"
                        + "          '" + password + "' , -- password - varchar(50)\n"
                        + "          '" + name + "' , -- name - varchar(24)\n"
                        + "          '" + address + "' , -- address - varchar(30)\n"
                        + "          GETDATE() , -- createDate - date\n"
                        + "          'new' , -- status - varchar(10)\n"
                        + "          '" + code + "' , -- code - varchar(6)\n"
                        + "          '" + phoneNumber + "'  -- phoneNumber - varchar(10)\n"
                        + "        )";
                pst = cn.prepareStatement(sql);
                rs = pst.executeUpdate();
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;

    }

    public int activeUser(String userID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = -1;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "UPDATE dbo.tblUsers SET status='active'\n"
                        + "		WHERE userID = '" + userID + "';";
                pst = cn.prepareStatement(sql);
                rs = pst.executeUpdate();

            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return rs;
    }

    public UserDTO checkUser(String userID) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserDTO user = null;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "SELECT userID, password,name,address,createDate,status,code,phoneNumber\n"
                        + "		FROM dbo.tblUsers\n"
                        + "		WHERE userID = '" + userID + "' ";

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String ID = rs.getString("userID");
                    String pw = rs.getString("password");
                    String userName = rs.getString("name");
                    String address = rs.getString("address");
                    Date createDate = rs.getDate("createDate");
                    String status = rs.getString("status");
                    String code = rs.getString("code");
                    String phoneNumber = rs.getString("phoneNumber");

                    user = new UserDTO(ID, pw, userName, address, createDate, status, code, phoneNumber);
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
        return user;

    }

}
