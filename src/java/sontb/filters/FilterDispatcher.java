/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sontb.filters;

import sontb.dto.UserDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author macbook
 */
@WebFilter(filterName = "FilterDispatcher", urlPatterns = {"/*"})
public class FilterDispatcher implements Filter {
static final Logger LOGGER = Logger.getLogger(FilterDispatcher.class);
    private final List<String> USER;
    private final String LOGIN = "login.jsp";
    private final String SEARCH = "SearchController";
    private final String REGISTER = "register.jsp";
//    private final String ACTIVE = "active";
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public FilterDispatcher() {
        USER = new ArrayList<>();
//        USER.add("LoginController");
//        USER.add("ActiveController");
//        USER.add("search.jsp");
//        USER.add("SearchController");
        USER.add("historyPage.jsp");
        USER.add("AddController");
        USER.add("LogoutController");
        USER.add("ViewCartController");
        USER.add("cart.jsp");
        USER.add("UpdateController");
        USER.add("DeleteController");
        USER.add("CheckOutController");
        USER.add("HistoryController");
        USER.add("historyPage.jsp");
        USER.add("OrderDetailController");
        USER.add("orderDetail.jsp");
        USER.add("voucherPage.jsp");
        
        USER.add("DeleteOrderController");
        USER.add("MakeOderDetailController");
        USER.add("VoucherController");
        USER.add("FeedBackController");
        USER.add("finalCheckCout.jsp");
        USER.add("feedBack.jsp");
        
        

    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("FilterDispatcher:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String uri = req.getRequestURI();
            if (uri.contains("login.jsp")
                    || uri.contains("register.jsp")
                    || uri.contains(".jpg")
                    || uri.contains("LoginController")
                    || uri.contains("RegisterController")
                    || uri.contains(".css")
                    || uri.contains("SearchController")
                    || uri.contains("ActiveController")
                    || uri.contains("search.jsp")
                    || uri.contains(".html")
                    || uri.contains("register.jsp")) {
                chain.doFilter(request, response);
            } else if (uri.contains("Register")) {
                res.sendRedirect(REGISTER);
            } else {
                int index = uri.lastIndexOf("/");
                String resource = uri.substring(index + 1);
                HttpSession session = req.getSession();
                if (session == null || session.getAttribute("USER") == null) {
                    if (uri.contains("AddController")) {
                        res.sendRedirect(LOGIN);
                    } else {
                        res.sendRedirect(SEARCH);
                    }
                } else {
                    UserDTO user = (UserDTO) session.getAttribute("USER");
//                    if (status.equals(ACTIVE) && USER.contains(resource)) {
                    if (USER.contains(resource) && user != null) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect(LOGIN);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("FilterDispatcher:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("FilterDispatcher()");
        }
        StringBuffer sb = new StringBuffer("FilterDispatcher(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
                LOGGER.error("error: ", ex);
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
                LOGGER.error("error: ", ex);
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
            LOGGER.error("error: ", ex);
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
