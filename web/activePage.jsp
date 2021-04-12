<%-- 
    Document   : activePage
    Created on : Feb 27, 2021, 5:32:55 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>active Page</title>
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div style="width: 100%; height: 250px;background-color: #555555" >
            <form action="ActiveController" method="POST" >
                <h2 style="text-align: center; color: yellow; margin-top: 10px; ">Last Step!!</h2>
                <p style="color: white; text-align: center">A code has been sent to your email. Please enter to activate your account.</p>
                <input name="txtCode" style="width: 10%;margin: 0 auto" minlength="6" maxlength="6" type="text"  required="required" >
                <button type="summit" style="margin-left: 47.5%; margin-top: 10px"class="btn btn-success">summit</button>
                <p style="color: red;text-align: center; font-size: 30px">${requestScope.txtMess}</p>
                <c:url var="back" value="LoginController" > </c:url>
                <a href="${back}" style="height: 53px;margin-top: 5px;margin-left: 91%; padding-top: 15px" class='btn btn-warning'> Back to login </a>

            </form>

        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
