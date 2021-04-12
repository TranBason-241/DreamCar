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
import java.util.HashMap;
import org.apache.log4j.Logger;
import sontb.dto.CarDTO;
import sontb.utils.MyConnection;

/**
 *
 * @author ADMIN
 */
public class CarDAO {
    static final Logger LOGGER = Logger.getLogger(CarDAO.class);
    public HashMap<String, String> getCategory() throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        HashMap<String, String> list = new HashMap<>();

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "SELECT categoryID,name\n"
                        + "FROM dbo.tblCategory";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();

                while (rs.next()) {
                    String cateName = rs.getString("name");
                    String categoryID = rs.getString("categoryID");
                    list.put(categoryID, cateName);
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

    public int getCount(String optionSearch, String category, String carName, String rentalDate, String returnDate, String quantity) throws SQLException {

        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "";
                if (optionSearch.equals("categorySearch")) {
                    sql = "select count(c.carName) as count\n"
                            + "from tblCars c left JOIN (select d.carID AS carID, d.quantity as quantity, o.status AS status\n"
                            + "                 from tblOderDetail d JOIN tblOrderss o on d.orderID = o.orderID\n"
                            + "                 where o.status = 'active' and not(rentalDate > '" + returnDate + "' or returnDate < '" + rentalDate + "'))tmp2 on tmp2.carID = c.carID\n"
                            + "WHERE c.categoryID = '" + category + "' and c.quantity - isnull(tmp2.quantity, 0) >= " + quantity + " and c.quantity - isnull(tmp2.quantity, 0) > 0";
                } else if (optionSearch.equals("nameSearch")) {
                    sql = "select count(c.carName) as count\n"
                            + "from tblCars c left JOIN (select d.carID AS carID, d.quantity as quantity, o.status AS status\n"
                            + "                 from tblOderDetail d JOIN tblOrderss o on d.orderID = o.orderID\n"
                            + "                 where o.status = 'active' and not(rentalDate > '" + returnDate + "' or returnDate < '" + rentalDate + "'))tmp2 on tmp2.carID = c.carID\n"
                            + "WHERE c.carName like '%" + carName + "%' and c.quantity - isnull(tmp2.quantity, 0) >= " + quantity + " and c.quantity - isnull(tmp2.quantity, 0) > 0";
                }

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    count = rs.getInt("count");
                }

                if (count % 3 == 0) {
                    //chia hết 20 vừa đủ k qua trang mới
                    count = count / 3;
                } else {
                    count /= 3;
                    count++;
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
        return count;
    }

    public ArrayList<CarDTO> getCar(String optionSearch, String category, String carName, String rentalDate, String returnDate, String quantity, String page) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<CarDTO> list = new ArrayList<>();

        int pageNumber = Integer.parseInt(page);
        pageNumber = (pageNumber - 1) * 3;
        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "";

                if (optionSearch.equals("categorySearch")) {
                    sql = "select c.carID ,c.image as image,c.carName as carName, color, year, c.price, c.quantity - isnull(tmp2.quantity, 0) as quantity, tmp.avg as rate,c.categoryID\n"
                            + "from tblCars c left join \n"
                            + "          (select carName, AVG(rate) as avg\n"
                            + "            from tblFeedBack JOIN dbo.tblCars ON tblCars.carID = tblFeedBack.carID\n"
                            + "            GROUP BY carName) tmp on c.carName = tmp.carName left JOIN (SELECT d.carID, sum(d.quantity) as quantity \n"
                            + "			FROM dbo.tblOderDetail d JOIN dbo.tblOrderss o ON d.orderID = o.orderID\n"
                            + "			WHERE o.status = 'active' AND NOT(d.rentalDate > '" + returnDate + "' OR d.returnDate < '" + rentalDate + "')"
                            + "                 group by d.carID)tmp2 on tmp2.carID = c.carID\n"
                            + "where c.quantity - isnull(tmp2.quantity, 0) >= " + quantity + " AND c.categoryID = '" + category + "' \n ORDER BY c.year, carName \n"
                            + "OFFSET " + pageNumber + " ROWS\n"
                            + "FETCH FIRST 3 ROWS ONLY";
                } else if (optionSearch.equals("nameSearch")) {
                    sql = "select c.carID ,c.carName as carName,c.image as image, color, year, c.price, c.quantity - isnull(tmp2.quantity, 0) as quantity, tmp.avg as rate,c.categoryID\n"
                            + "from tblCars c left join \n"
                            + "          (select carName, AVG(rate) as avg\n"
                            + "            from tblFeedBack JOIN dbo.tblCars ON tblCars.carID = tblFeedBack.carID\n"
                            + "            GROUP BY carName) tmp on c.carName = tmp.carName left JOIN (SELECT d.carID, sum(d.quantity) as quantity \n"
                            + "			FROM dbo.tblOderDetail d JOIN dbo.tblOrderss o ON d.orderID = o.orderID\n"
                            + "			WHERE o.status = 'active' AND NOT(d.rentalDate > '" + returnDate + "' OR d.returnDate < '" + rentalDate + "')"
                            + "                 group by d.carID)tmp2 on tmp2.carID = c.carID\n"
                            + "where c.quantity - isnull(tmp2.quantity, 0) >= " + quantity + " AND c.carName LIKE '%" + carName + "%' \n ORDER BY c.year, carName \n"
                            + "OFFSET " + pageNumber + " ROWS\n"
                            + "FETCH FIRST 3 ROWS ONLY";
                }

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString("carID");
                    String name = rs.getString("carName");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    float price = rs.getFloat("price");
                    int quantityy = rs.getInt("quantity");
                    float avg = rs.getFloat("rate");
                    String categoryID = rs.getString("categoryID");
                    String image = rs.getString("image");

                    CarDTO car = new CarDTO(carID, name, color, year, price, quantityy, categoryID, avg,rentalDate,returnDate,image);
                    list.add(car);

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

    public CarDTO getOneCar(String ID, String rentalDate, String returnDate) throws SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        CarDTO car = null;

        try {
            cn = MyConnection.getConnection();
            if (cn != null) {
                String sql = "select c.carID ,c.image as image,c.carName as carName, color, year, c.price, c.quantity - isnull(tmp2.quantity, 0) as quantity, tmp.avg as rate,c.categoryID\n"
                        + "from tblCars c left join \n"
                        + "          (select carName, AVG(rate) as avg\n"
                        + "            from tblFeedBack JOIN dbo.tblCars ON tblCars.carID = tblFeedBack.carID\n"
                        + "            GROUP BY carName) tmp on c.carName = tmp.carName left JOIN (SELECT d.carID, SUM(d.quantity) AS quantity\n"
                        + "			FROM dbo.tblOderDetail d JOIN dbo.tblOrderss o ON d.orderID = o.orderID\n"
                        + "			WHERE o.status = 'active' AND NOT(d.rentalDate > '" + returnDate + "' OR d.returnDate < '" + rentalDate + "')\n"
                        + "			GROUP BY d.carID)tmp2 on tmp2.carID = c.carID\n"
                        + "where c.carID = '" + ID + "' ";

                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String carID = rs.getString("carID");
                    String name = rs.getString("carName");
                    String color = rs.getString("color");
                    String year = rs.getString("year");
                    float price = rs.getFloat("price");
                    int quantityy = rs.getInt("quantity");
                    float avg = rs.getFloat("rate");
                    String categoryID = rs.getString("categoryID");
                    String image = rs.getString("image");

                    car = new CarDTO(carID, name, color, year, price, quantityy, categoryID, avg,rentalDate,returnDate,image);
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
        return car;
    }

}
