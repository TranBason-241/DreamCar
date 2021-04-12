/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sontb.dao.CarDAO;
import sontb.dto.CarDTO;
import sontb.dto.CartDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {
static final Logger LOGGER = Logger.getLogger(SearchController.class);
    public static final String SEARCH_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = SEARCH_PAGE;
        try {
            //Lấy category để đưa ra giao diện
            HashMap<String, String> listCate = new HashMap<String, String>();
            CarDAO dao = new CarDAO();
            listCate = dao.getCategory();
            HttpSession ss = request.getSession();
            ss.setAttribute("LIST_CATEGORY", listCate);

            //Lấy time để đưa ra giao diện (validate cái ngày thuê chứ k user chọn quá khứ)
            ss.setAttribute("DATE_NOW", java.time.LocalDate.now());

            //Xử lí search
            String optionSearch = request.getParameter("optionSEARCH");
            String categorySearchName = request.getParameter("txtCategory");
            String categoryID = "";
            //Lấy về được name, mà lỡ làm sql cần id nên giờ phải đổi name ra id;

            Set<String> keySet = listCate.keySet();
            for (String key : keySet) {
                if (listCate.get(key).equals(categorySearchName)) {
                    categoryID = key;
                }
            }

            String nameSearch = request.getParameter("txtNameSearch");
            String rentalDay = request.getParameter("txtRental");
            String returnDay = request.getParameter("txtReturn");
            String quantitySearch = request.getParameter("txtQuantity");
            String optionSearchToDataBase = "";

            //Check xem có phải lần đầu không, nếu phải hiển thị ra hết
            if (optionSearch == null && categorySearchName == null && nameSearch == null && rentalDay == null && returnDay == null && quantitySearch == null) {
                optionSearchToDataBase = "";
                request.setAttribute("messFirst", "Choose Car and Date you want to rent");

            } else {
//               
                //Check cái rentalDay và returnDay
                Date rental = new SimpleDateFormat("yyyy-MM-dd").parse(rentalDay);
                Date returnn = new SimpleDateFormat("yyyy-MM-dd").parse(returnDay);
                //Xử xí giữ lại những gì đã search
                request.setAttribute("optionSEARCHED", optionSearch);
                request.setAttribute("categorySEARCHED", categorySearchName);
                request.setAttribute("nameSEARCHED", nameSearch);
                request.setAttribute("rentalDaySEARCHED", rentalDay);
                request.setAttribute("returnDaySEARCHED", returnDay);
                request.setAttribute("quantitySEARCHED", quantitySearch);

                //Lưu date và session để chuyển sang addController
                ss.setAttribute("rentalDateADD", dao);
                ss.setAttribute("returnDateADD", dao);

                if (returnn.compareTo(rental) < 0) {
                    request.setAttribute("messDateSearch", "Date is invalid");
                } else {

                    //xử lí option search
                    if (optionSearch.equals("searchByCategory")) {
                        optionSearchToDataBase = "categorySearch";
                    } else if (optionSearch.equals("searchByName")) {
                        optionSearchToDataBase = "nameSearch";
                    }

                    //Xử lí phân 
                    int count = 0;
                    count = dao.getCount(optionSearchToDataBase, categoryID, nameSearch, rentalDay, returnDay, quantitySearch);
                    ss.setAttribute("NUMBER_OF_PAGE", count);

                    String activePage = request.getParameter("activePage");

                    if (activePage == null) {
                        //chua click
                        activePage = "1";

                    }
                    request.setAttribute("ACTIVE_PAGE", activePage);

                    //Xử lí list question ra giao diện
                    ArrayList<CarDTO> listCar = new ArrayList<>();
                    listCar = dao.getCar(optionSearchToDataBase, categoryID, nameSearch, rentalDay, returnDay, quantitySearch, activePage);

                    //Sau khi load lên listCart. Chưa render ra giao diện luôn mà kiểm tra trong Giỏ hàng session trước
                    CartDTO cart = (CartDTO) ss.getAttribute("CART");
                    ArrayList<CarDTO> ListCarOfCart = null;
                    if (cart != null) {
                        ListCarOfCart = cart.getCart();
                    }

                    if (ListCarOfCart != null) {
//                        for (CarDTO carSearch : listCar) {
//                            for (CarDTO carInCart : ListCarOfCart) {
//                                if (carInCart.getCarID().equals(carSearch.getCarID()) && carInCart.getRentalDate().equals(carSearch.getRentalDate()) && carInCart.getReturnDate().equals(carSearch.getReturnDate())) {
//                                    int quantity = carSearch.getQuantity() - carInCart.getQuantity();
//                                    if (quantity > 0) {
//                                        carSearch.setQuantity(quantity);
//                                    } else if (quantity == 0) {
//                                        listCar.remove(carSearch);
//                                        return;
//
//                                    }
//                                }
//                            }
//                        }

                        for (int i = 0; i < listCar.size(); i++) {
                            for (int j = 0; j < ListCarOfCart.size(); j++) {
                                if (listCar.get(i).getCarID().equals(ListCarOfCart.get(j).getCarID()) && listCar.get(i).getRentalDate().equals(ListCarOfCart.get(j).getRentalDate()) && listCar.get(i).getReturnDate().equals(ListCarOfCart.get(j).getReturnDate())) {
                                    int quantity = listCar.get(i).getQuantity() - ListCarOfCart.get(j).getQuantity();
                                    if (quantity > 0) {
                                        listCar.get(i).setQuantity(quantity);
                                    } else if (quantity == 0) {
                                        listCar.remove(i);
                                    }
//                                
                                }
                            }
                        }

                    }
//                    int newCount = listCar.size();
//                    if (newCount % 3 == 0) {
//                        //chia hết 20 vừa đủ k qua trang mới
//                        newCount = count / 3;
//                    } else {
//                        newCount /= 3;
//                        newCount++;
//                    }

//                    ss.setAttribute("NUMBER_OF_PAGE", newCount);
                    if (listCar != null) {
                        request.setAttribute("LIST_CAR", listCar);
                    }
                }

            }

        } catch (Exception e) {

            LOGGER.error("error: ", e);

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
