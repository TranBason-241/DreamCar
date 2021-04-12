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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sontb.dao.HistoryDAO;
import sontb.dao.OrderDetailDAO;
import sontb.dto.OrderDTO;
import sontb.dto.OrderDetailDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "HistoryController", urlPatterns = {"/HistoryController"})
public class HistoryController extends HttpServlet {
static final Logger LOGGER = Logger.getLogger(HistoryController.class);

    public static final String HISTORY = "historyPage.jsp";

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
            String nameSearch = request.getParameter("txtNameSEARCH");
            String dateSearch = request.getParameter("txtDateSEARCH");
            String optionSearch = request.getParameter("optionSEARCH");

            request.setAttribute("NAME_SEARCHED", nameSearch);
            request.setAttribute("DATE_SEARCHED", dateSearch);
            request.setAttribute("OPTION_SEARCHED", optionSearch);

            HistoryDAO dao = new HistoryDAO();
            OrderDetailDAO oderDetailDao = new OrderDetailDAO();
            ArrayList<OrderDTO> listOrder = new ArrayList<>();
            listOrder = dao.getOrder(optionSearch, nameSearch, dateSearch);
            ArrayList<OrderDetailDTO> listOrderDetailToCheckDeleteButton = new ArrayList<>();

            for (OrderDTO orderDTO : listOrder) {
                listOrderDetailToCheckDeleteButton = oderDetailDao.getOrderDetail(orderDTO.getOrderID());
                for (OrderDetailDTO orderDetailDTO : listOrderDetailToCheckDeleteButton) {
                    String a = java.time.LocalDate.now() + "";

                    Date now = new SimpleDateFormat("yyyy-MM-dd").parse(a);
                    //check delete
                    if (now.compareTo(orderDetailDTO.getRentalDate()) < 0 && orderDTO.getStatus().equals("active")) {
                        orderDTO.setStatusDate("canDelete");

                    } else {
                        orderDTO.setStatusDate("not");
                    }
                    //check FeedBack
                    if (now.compareTo(orderDetailDTO.getRentalDate()) > 0 && orderDTO.getStatus().equals("active") && orderDTO.getStatusFeedBack().equals("no")) {
                        orderDTO.setStatusFeedBack("canFB");

                    } else {
                        orderDTO.setStatusFeedBack("not");
                    }

                }

            }
            //Check Xem cái nào có thể xóa. Lấy detail ra, coi ngày nhận phải lớn hơn = ngày mai mới set canDelete

            request.setAttribute("LIST_ORDER", listOrder);

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
