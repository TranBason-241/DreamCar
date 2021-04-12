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
import sontb.dao.UserDAO;
import sontb.dto.UserDTO;
import sontb.utils.VerifyUtils;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private static final String ERROR = "invalid.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String ACTIVE_PAGE = "activePage.jsp";
    private static final String SEARCH_PAGE = "SearchController";

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
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean valid = VerifyUtils.verify(gRecaptchaResponse);

        try {
            String check = request.getParameter("check");
                
            if (valid || check!=null ) {
                String email = request.getParameter("txtEmail");
                String password = request.getParameter("txtPassword");

                if (email != null) {
                    UserDAO dao = new UserDAO();
                    UserDTO user = dao.getUser(email, password);
                    HttpSession ss = request.getSession();
                    if (user != null) {
                        //Chua xac thuc
                        if (user.getStatus().equals("new")) {
                            url = ACTIVE_PAGE;
                            ss.setAttribute("USER", user);
                        } else if (user.getStatus().equals("active")) {
                            url = SEARCH_PAGE;
                            ss.setAttribute("USER", user);
                        }
                    } else {
                        url = LOGIN;
                        request.setAttribute("MESSAGE_LOGIN", "Password or email is not correct");
                        request.setAttribute("EMAIL_LOGIN", email);
                    }
                } else {
                    url = LOGIN;
                }
            }else {
                url = LOGIN;
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
