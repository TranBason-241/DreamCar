
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>orderDetail</title>  <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
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



        <c:set var="list" value="${requestScope.LIST_ORDER_DETAIL}" ></c:set>
        <c:if test="${ list !=null }">
            <div class="row">
                <div class="col-sm-12 col-md-10 col-md-offset-1">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th style="text-align: center;">Date</th>
                                <th style="text-align: center;">Numer of Date</th>
                                <th style="text-align: center;">Quantity</th>
                                <th class="text-center">Price</th>
                                <th class="text-center">Total</th>
                                    <c:url var="Back" value="HistoryController">
                                    </c:url>

                                <th><a href="${Back}">
                                        <button type="button" class="btn btn-success">
                                            Back
                                        </button></td>
                                    </a>  </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${not empty list}">
                                <c:set var="total"  value="0" ></c:set>
                                <c:forEach var="elem" items="${list}" >

                                    <fmt:parseDate value="${elem.rentalDate}" var="rentalDate" pattern="yyyy-MM-dd"/>
                                    <fmt:parseDate value="${elem.returnDate}" var="returnDate" pattern="yyyy-MM-dd"/>
                                    <c:set var="date" value="${returnDate.getTime() - rentalDate.getTime()}"></c:set>
                                    <c:set var="totalDate" value="${(date / (24 * 60 * 60 * 1000))+1}"></c:set>


                                    <c:set var="total" value="${elem.getPrice()*totalDate*elem.quantity}" ></c:set>
                                        <tr>
                                            <td class="col-sm-8 col-md-5">
                                                <div class="media">
                                                    <a class="thumbnail pull-left" href="#"> <img class="media-object" src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png" style="width: 72px; height: 72px;"> </a>
                                                    <div class="media-body">
                                                        <h4 class="media-heading"><a href="#">${elem.getCarName()}</a></h4>
                                                    <h5 class="media-heading">  <a href="#">sale 0%</a></h5>
                                                    <span>Status: </span><span class="text-success"><strong>No Voucher</strong></span>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="col-sm-1 col-md-4" style="text-align: center">

                                            <h5>${elem.rentalDate} - ${elem.returnDate}</h5>
                                        </td>
                                        <td class="col-sm-1 col-md-4" style="text-align: center">

                                            <h5> ${totalDate}</h5>
                                        </td>
                                        <td class="col-sm-1 col-md-4" style="text-align: center">


                                            <h5  class="pl-0 pr-0" style="margin-bottom: 50px; display: inline" >${elem.quantity}</h5>



                                        </td>
                                        <td class="col-sm-1 col-md-1 text-center"><strong>${elem.price}$</strong></td>

                                        <td class="col-sm-1 col-md-1 text-center"><strong>${elem.price*totalDate*elem.quantity}$</strong></td>


                                        <td class="col-sm-1 col-md-1 text-center"><strong></strong></td>
                                        <td class="col-sm-1 col-md-1">

                                            <c:url var="delete" value="DeleteController">
                                                <c:param name="carID" value="${elem.carID}"></c:param>
                                                <c:param name="rentalDateDELETE" value="${elem.rentalDate}" ></c:param>
                                                <c:param name="returnDateDELETE" value="${elem.returnDate}" ></c:param>
                                                <c:param name="optionSEARCH" value="${requestScope.optionSEARCHED}" ></c:param>
                                                <c:param name="txtCategory" value="${requestScope.categorySEARCHED}" ></c:param>
                                                <c:param name="txtNameSearch" value="${requestScope.nameSEARCHED}" ></c:param>
                                                <c:param name="txtRental" value="${requestScope.rentalDaySEARCHED}" ></c:param>
                                                <c:param name="txtReturn" value="${requestScope.returnDaySEARCHED}" ></c:param>
                                                <c:param name="txtQuantity" value="${requestScope.quantitySEARCHED}" ></c:param>
                                                <c:param name="activePage" value="${requestScope.ACTIVE_PAGE}" ></c:param>
                                            </c:url>

                                      

                                    </tr>
                                </c:forEach>
                            </c:if>


                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>

    </body>
</html>
