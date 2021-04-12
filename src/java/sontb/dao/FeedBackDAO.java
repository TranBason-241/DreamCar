/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import sontb.utils.MyConnection;

/**
 *
 * @author ADMIN
 */
public class FeedBackDAO {
static final Logger LOGGER = Logger.getLogger(FeedBackDAO.class);
    public int doFeedBack(String Carid, String userID, String rate) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        int rs = -1;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = " INSERT dbo.tblFeedBack\n"
                        + "				         ( userID, carID, rate )\n"
                        + "				 VALUES  ( '" + userID + "', -- userID - varchar(50)\n"
                        + "				           " + Carid + ", -- carID - int\n"
                        + "				           " + rate + "  -- rate - int\n"
                        + "	"
                        + "			           )";
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
}
