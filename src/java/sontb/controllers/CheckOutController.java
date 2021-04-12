/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import sontb.dao.CartDAO;
import sontb.dto.CarDTO;
import sontb.dto.CartDTO;
import sontb.dto.UserDTO;
import sontb.dto.VoucherDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {

static final Logger LOGGER = Logger.getLogger(CheckOutController.class);
    public static final String VIEW_CART = "cart.jsp";

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
        String url = VIEW_CART;
        try {
            HttpSession ss = request.getSession();
            UserDTO user = (UserDTO) ss.getAttribute("USER");
            CartDAO cartDao = new CartDAO();
            CartDTO cart = (CartDTO) ss.getAttribute("CART");

            VoucherDTO voucher = (VoucherDTO) ss.getAttribute("VOUCHER");

            float total = 0;
            for (CarDTO carDTO : cart.getCart()) {
                Date rental = new SimpleDateFormat("yyyy-MM-dd").parse(carDTO.getRentalDate());
                Date returnn = new SimpleDateFormat("yyyy-MM-dd").parse(carDTO.getReturnDate());
                long time = returnn.getTime() - rental.getTime();
                long numOfDate = time / (24 * 60 * 60 * 1000);
                float numOfDateInt = numOfDate;
                total += numOfDateInt * carDTO.getPrice() * carDTO.getQuantity();

            }

            if (voucher != null) {
                int pre = voucher.getPercent();
                total -= (total/100) * pre;
            }

            int rs = cartDao.checkOut(user, total, cart, voucher);
            if (rs != -1) {
                ss.setAttribute("CART", null);
                request.setAttribute("MESS_SUCCESS", "Order successfuly");
                ss.setAttribute("VOUCHER", null);
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
