/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.controllers;

import java.io.IOException;
import java.util.ArrayList;
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
import sontb.dto.UserDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddController", urlPatterns = {"/AddController"})
public class AddController extends HttpServlet {
static final Logger LOGGER = Logger.getLogger(AddController.class);
    private static final String ERROR = "invalid.jsp";
    private static final String SEARCH_CONTROLLER = "SearchController";

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

        String url = ERROR;
        try {
            CarDAO dao = new CarDAO();

            HttpSession ss = request.getSession();
            UserDTO user = (UserDTO) ss.getAttribute("USER");

            String optionSearch = request.getParameter("optionSEARCH");
            String categorySearchName = request.getParameter("txtCategory");
            String nameSearch = request.getParameter("txtNameSearch");
            String rentalDay = request.getParameter("txtRental");
            String returnDay = request.getParameter("txtReturn");
            String quantitySearch = request.getParameter("txtQuantity");
            String activePage = request.getParameter("activePage");
            String carID = request.getParameter("carIDToADD");

            //L??u request ????? tr??? v??? controller
            request.setAttribute("optionSEARCHED", optionSearch);
            request.setAttribute("categorySEARCHED", categorySearchName);
            request.setAttribute("nameSEARCHED", nameSearch);
            request.setAttribute("rentalDaySEARCHED", rentalDay);
            request.setAttribute("returnDaySEARCHED", returnDay);
            request.setAttribute("quantitySEARCHED", quantitySearch);
            request.setAttribute("ACTIVE_PAGE", activePage);

            CartDTO cart = (CartDTO) ss.getAttribute("CART");
            if (cart == null) {
                cart = new CartDTO(user.getUserID(), null);
            }

            //-------- CHECK QUANTITY ADD ---------
            // id car v?? date l???y ra  car ????? check quantity c??n l???i
            CarDTO carCheck = dao.getOneCar(carID, rentalDay, returnDay);
             int quantityCartInDB = 0; 
             if(carCheck!=null){
                quantityCartInDB = carCheck.getQuantity();
             }
            //m???c ?????nh add l?? 1;
            //Check trong cart session coi c??i car c?? ch??a n???u c?? l???y quantity + v???i 1, r???i
            //so s??nh v???i quantity c???a carCheck

            ArrayList<CarDTO> listCart = cart.getCart();
           
            int quantityCartInCart = 0;
            if (listCart != null) {
                for (CarDTO carDTO : listCart) {
                    if (carDTO.getCarID().equals(carID) && carDTO.getRentalDate().equals(rentalDay) && carDTO.getReturnDate().equals(returnDay)) {
                        quantityCartInCart = carDTO.getQuantity();
                    }
                }
            }
            if (quantityCartInDB - (quantityCartInCart + 1) >= 0) { 

                cart.addToCart(carCheck);
                ss.setAttribute("CART", cart);

            } 
            else {
                //Thong bao het san pham o trang search
                request.setAttribute("MESS_QUANTITY", "out of stock");
            }

            url = SEARCH_CONTROLLER;
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        }finally{
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
