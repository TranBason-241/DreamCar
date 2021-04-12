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
import sontb.dao.FeedBackDAO;
import sontb.dao.OrderDAO;
import sontb.dto.OrderDetailDTO;
import sontb.dto.UserDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "MakeOderDetailController", urlPatterns = {"/MakeOderDetailController"})
public class MakeOderDetailController extends HttpServlet {
static final Logger LOGGER = Logger.getLogger(MakeOderDetailController.class);
    public static final String HISTORY = "HistoryController";

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

        String url = HISTORY;

        try {
            OrderDAO oderDao = new OrderDAO();
            FeedBackDAO dao = new FeedBackDAO();
            HttpSession ss = request.getSession();
            ArrayList<OrderDetailDTO> list = new ArrayList<>();

            UserDTO user = (UserDTO)ss.getAttribute("USER");
            String userID = user.getUserID();
            list = (ArrayList) ss.getAttribute("LIST_ORDER_DETAIL");
            
            String orderID = request.getParameter("OrderIDFeedBack");
            for (OrderDetailDTO detail : list) {
                String carID = detail.getCarID();
                String rate = request.getParameter(carID);
                dao.doFeedBack(carID, userID, rate);
                
            }
            
            //dua trang thai order thanh Da update roi
            oderDao.SetStatusFeedBack(orderID);
            
            
            ss.setAttribute("LIST_ORDER_DETAIL", null);
            request.setAttribute("MESS_FEEDBACK", "FeedBack successfuly!!!");
            
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
