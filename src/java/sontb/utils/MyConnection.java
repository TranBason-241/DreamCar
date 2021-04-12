/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author ADMIN
 */
public class MyConnection {
   public static Connection getConnection() throws NamingException, SQLException {
        Connection cn = null;
        Context context = (Context) new InitialContext();
        Context end = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) end.lookup("DBCon");
        cn = ds.getConnection();
        return cn;
    }
}
