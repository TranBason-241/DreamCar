/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.controllers;

import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sontb.dao.UserDAO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(RegisterController.class);

//    private static final String SUCCESS = "index.html";
//    private static final String ERROR = "register.jsp";
    public static final String REGIS = "register.jsp";
    public static final String ACTIVE_PAGE = "activePage.jsp";

    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

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

        String url = REGIS;
        try {
            UserDAO dao = new UserDAO();

            String name = request.getParameter("txtName");
            String email = request.getParameter("txtEmail");
            String pass1 = request.getParameter("txtPassword");
            String pass2 = request.getParameter("txtPassword2");
            String address = request.getParameter("txtAddress");
            String phoneNumber = request.getParameter("txtPhoneNumber");

            request.setAttribute("ADRESS_REGISTER", address);
            request.setAttribute("PHONE_REGISTER", phoneNumber);
            request.setAttribute("NAME_REGISTER", name);
            request.setAttribute("NAME_EMAIL", email);

            if (name != null) {  // Check null la lần đầu từ trang login qua chưa làm gì, vì nếu k qua
                //COntroller thì vi phạm mvc2
                if (!pass1.equals(pass2)) {
                    request.setAttribute("MESSAGE_PASS", "Password's not same");
                } else {
                    //Check id có chưa
                    if (dao.checkUser(email) != null) {  //Co roi
                        request.setAttribute("MESSAGE_EMAIL", "Email already exists");
                    } else {
//                        //Nếu chưa có thì randome gửi code, thành công thì mới lưu database new
                        String code = randomString(6);
                        if (!email.isEmpty()) {
                            if (EmailUtility.sendMail(email, "Active your account!", "Code: " + code)) {
                                //Lưu db
                                int rs = dao.createUser(email, pass2, name, address, code, phoneNumber);
                                if (rs != -1) {
                                    request.setAttribute("MESSAGE_SUCCESS", "Create successfully");
                                    //thành công thì xóa đi chứ k nó lại liện
                                    request.setAttribute("NAME_REGISTER", null);
                                    request.setAttribute("PHONE_REGISTER", null);
                                    request.setAttribute("ADRESS_REGISTER", null);
                                    request.setAttribute("NAME_EMAIL", null);
                                    url = ACTIVE_PAGE;
                                } else {
                                    request.setAttribute("MESSAGE_SUCCESS", "failed");
                                    url = REGIS;
                                }
                            } else {
                                request.setAttribute("MESSAGE_SUCCESS", "failed");
                                url = REGIS;
                            }

                        }

                    }
                }
            } else {
                url = REGIS;
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
