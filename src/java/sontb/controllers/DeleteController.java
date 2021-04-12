/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sontb.dto.CartDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteController", urlPatterns = {"/DeleteController"})
public class DeleteController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(DeleteController.class);
    public static final String VIEW_CART = "ViewCartController";
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
            String carID = request.getParameter("carID");
            String rentalDateDELETE = request.getParameter("rentalDateDELETE");
            String returnDateDELETE = request.getParameter("returnDateDELETE");
            
            
            
            String optionSearch = request.getParameter("optionSEARCH");
            String categorySearchName = request.getParameter("txtCategory");
            String nameSearch = request.getParameter("txtNameSearch");
            String rentalDay = request.getParameter("txtRental");
            String returnDay = request.getParameter("txtReturn");
            String quantitySearch = request.getParameter("txtQuantity");
            String activePage = request.getParameter("activePage");

            //Lưu request để trả về controller
            request.setAttribute("optionSEARCHED", optionSearch);
            request.setAttribute("categorySEARCHED", categorySearchName);
            request.setAttribute("nameSEARCHED", nameSearch);
            request.setAttribute("rentalDaySEARCHED", rentalDay);
            request.setAttribute("returnDaySEARCHED", returnDay);
            request.setAttribute("quantitySEARCHED", quantitySearch);
            request.setAttribute("ACTIVE_PAGE", activePage);

            
            CartDTO cart = (CartDTO) ss.getAttribute("CART");
            if (cart != null) {
            
                cart.delete(carID, rentalDateDELETE, returnDateDELETE);
                ss.setAttribute("CART", cart);
            
            }
            
            
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
