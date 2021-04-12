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
import sontb.dao.VoucherDAO;
import sontb.dto.VoucherDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "VoucherController", urlPatterns = {"/VoucherController"})
public class VoucherController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(VoucherController.class);

    public static final String VOUCHER_PAGE = "voucherPage.jsp";
    public static final String FINAL_CHECK_OUT = "finalCheckOut.jsp";

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

        String url = VOUCHER_PAGE;
        try {
            String code = request.getParameter("txtCode");
            String total = request.getParameter("total");
            request.setAttribute("total", total);
            if (code != null) {
                //checkCode
                HttpSession ss = request.getSession();
                VoucherDAO dao = new VoucherDAO();
                VoucherDTO voucher = dao.getVoucher(code);
                if (voucher == null) {
                    request.setAttribute("MESS_VOUCHER", "The code is not correct ");
                } else {
                    Date expiryDate = voucher.getExpiryDate();
                    String a = java.time.LocalDate.now() + "";
                    Date now = new SimpleDateFormat("yyyy-MM-dd").parse(a);
                    if (now.compareTo(expiryDate) >= 0) {
                        request.setAttribute("MESS_VOUCHER", "out of date ");
                    } else {
                        ss.setAttribute("VOUCHER", voucher);
                        float totalMoney = Float.parseFloat(total);
                        float saleMoney = (totalMoney / 100) * voucher.getPercent();
                        float finalTotal = totalMoney - saleMoney;
                        request.setAttribute("SALE_MONEY", saleMoney);
                        request.setAttribute("FINAL_TOTAL", finalTotal);
                        url = FINAL_CHECK_OUT;
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
