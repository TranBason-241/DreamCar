<%-- 
    Document   : voucherPage.jsp
    Created on : Mar 7, 2021, 3:24:31 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>voucher</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>

        <c:if test="${requestScope.MESS_VOUCHER!=null}">
            <div class="alert alert-danger" role="alert">
                <h3 style="text-align: center">${requestScope.MESS_VOUCHER}</h3>
            </div>
        </c:if>
        
        <div style="background-color: #9a9a9a;height: 210px">
            <h3 style="text-align: center;color: red">Do you have a code ?</h3>
            <form action="VoucherController" method="post">
                
                <input name="txtCode" required="required" style="width: 10%;margin-left: 45%" type="text" maxlength="6"  class="form-control mr-2">
                <button type="submit" style="margin-left: 47.1%;margin-top: 10px" class="btn btn-success">UseCode</button>
                <input type="hidden" name="total" value="${requestScope.total==null ? "" : requestScope.total}" >
            </form>
            <c:url var="checkOut"  value="CheckOutController">

            </c:url>
            <a href="${checkOut}" style="margin-left: 42%; margin-top: 20px" class="btn btn-warning">Check out and don't use CODE</a>
        </div>




        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
