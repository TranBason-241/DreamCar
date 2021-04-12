

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>feedback Page</title>  <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->


    </head>
    <body>

        <c:if test="${requestScope.MESS_QUANTITY!=null}">
            <div class="alert alert-danger" role="alert">
                <h3 style="text-align: center">${requestScope.MESS_QUANTITY}</h3>
            </div>
        </c:if>



        <c:set var="list" value="${sessionScope.LIST_ORDER_DETAIL}" ></c:set>
        <c:if test="${ list !=null }">
            <div class="row">
                <div class="col-sm-12 col-md-10 col-md-offset-1">
                    <form action="MakeOderDetailController" method="POST">
                        <table class="table table-hover">
                            <thead>
                                <tr>

                                    <th style="text-align: center;">Date</th>
                                    <th style="text-align: center;">Numer of Date</th>
                                    <th style="text-align: center;">Rate</th>

                                </tr>
                            </thead>
                            <tbody>

                                <c:if test="${not empty list}">
                                    <c:set var="total"  value="0" ></c:set>
                                    <c:forEach var="elem" items="${list}" >
                                        <tr>
                                            <td>
                                                <p style="text-align: center">name</p>
                                            </td>
                                            <td  style="text-align: center">

                                                <h5>${elem.rentalDate} - ${elem.returnDate}</h5>
                                            </td>
                                            <td  style="text-align: center">
                                                <input name="${elem.carID}" type="number"  min="1" max="10" required="required" >
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            



                            </tbody>
                        </table>
                        <input type="hidden" name="OrderIDFeedBack" value="${requestScope.OrderIDFeedBack}" >
                        <button  style="margin-left: 84%"type="submit" class="btn btn-success" > Submit </button>
                    </form>
                </div>
            </div>
        </c:if>

    </body>
</html>
