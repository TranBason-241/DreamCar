
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>  <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
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



        <c:set var="cart" value="${sessionScope.CART}" ></c:set>
        <c:if test="${ not empty cart.getCart()}">
            <c:if test="${ cart.getCart() !=null }">
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
                                    <th> </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${not empty cart}">
                                    <c:set var="total"  value="0" ></c:set>
                                    <c:forEach var="elem" items="${cart.getCart()}" >

                                        <fmt:parseDate value="${elem.rentalDate}" var="rentalDate" pattern="yyyy-MM-dd"/>
                                        <fmt:parseDate value="${elem.returnDate}" var="returnDate" pattern="yyyy-MM-dd"/>
                                        <c:set var="date" value="${returnDate.getTime() - rentalDate.getTime()}"></c:set>
                                        <c:set var="totalDate" value="${date / (24 * 60 * 60 * 1000)}"></c:set>

                                        <c:set var="total" value="${total + elem.getPrice()*totalDate*elem.quantity}" ></c:set>
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

                                                <div style="width: 30px;height: 30px;display: inline">
                                                    <c:url var="downn" value="UpdateController">

                                                        <c:param name="txtOpetator" value="-"></c:param>
                                                        <c:param name="carIDToUPDATE" value="${elem.carID}" ></c:param>
                                                        <c:param name="rentalDateUPDATE" value="${elem.rentalDate}" ></c:param>
                                                        <c:param name="returnDateUPDATE" value="${elem.returnDate}" ></c:param>


                                                        <c:param name="optionSEARCH" value="${requestScope.optionSEARCHED}" ></c:param>
                                                        <c:param name="txtCategory" value="${requestScope.categorySEARCHED}" ></c:param>
                                                        <c:param name="txtNameSearch" value="${requestScope.nameSEARCHED}" ></c:param>
                                                        <c:param name="txtRental" value="${requestScope.rentalDaySEARCHED}" ></c:param>
                                                        <c:param name="txtReturn" value="${requestScope.returnDaySEARCHED}" ></c:param>
                                                        <c:param name="txtQuantity" value="${requestScope.quantitySEARCHED}" ></c:param>
                                                        <c:param name="activePage" value="${requestScope.ACTIVE_PAGE}" ></c:param>
                                                    </c:url>

                                                    <a href="${downn}"><button style="width: 30px;height: 30px" class="btn btn-success" style="display: inline">-</button></a>

                                                </div>
                                                <h5  class="pl-0 pr-0" style="margin-bottom: 50px; display: inline" >${elem.quantity}</h5>

                                                <div style="width: 30px;height:  30px; display: inline">
                                                    <c:url var="up" value="UpdateController">

                                                        <c:param name="txtOpetator" value="+"></c:param>
                                                        <c:param name="carIDToUPDATE" value="${elem.carID}" ></c:param>
                                                        <c:param name="rentalDateUPDATE" value="${elem.rentalDate}" ></c:param>
                                                        <c:param name="returnDateUPDATE" value="${elem.returnDate}" ></c:param>

                                                        <c:param name="optionSEARCH" value="${requestScope.optionSEARCHED}" ></c:param>
                                                        <c:param name="txtCategory" value="${requestScope.categorySEARCHED}" ></c:param>
                                                        <c:param name="txtNameSearch" value="${requestScope.nameSEARCHED}" ></c:param>
                                                        <c:param name="txtRental" value="${requestScope.rentalDaySEARCHED}" ></c:param>
                                                        <c:param name="txtReturn" value="${requestScope.returnDaySEARCHED}" ></c:param>
                                                        <c:param name="txtQuantity" value="${requestScope.quantitySEARCHED}" ></c:param>
                                                        <c:param name="activePage" value="${requestScope.ACTIVE_PAGE}" ></c:param>
                                                    </c:url>
                                                    <a href="${up}"><button style="width: 30px;height: 30px" class="btn btn-success" style="display: block">+</button></a>
                                                </div>

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

                                                <a href="${delete}">
                                                    <button type="button" class="btn btn-danger"  onclick="return confirm('Ok to Remove?');">>
                                                        <span class="glyphicon glyphicon-remove"></span> Remove
                                                    </button></td>
                                            </a>

                                        </tr>
                                    </c:forEach>
                                </c:if>






                                <tr>
                                    <td>   </td>
                                    <td>   </td>
                                    <td>   </td>


                                </tr>
                                <tr>
                                    <td>   </td>
                                    <td>   </td>
                                    <td>   </td>
                                    <td>
                                        <h3>Total</h3>
                                    </td>
                                    <td class="text-right">
                                        <h3><strong>${total}$</strong></h3>

                                    </td>
                                </tr>
                                <tr>
                                    <td>   </td>
                                    <td>   </td>
                                    <td>   </td>
                                    <td>
                                        <c:url var="Back" value="SearchController">


                                            <c:param name="optionSEARCH" value="${requestScope.optionSEARCHED}" ></c:param>
                                            <c:param name="txtCategory" value="${requestScope.categorySEARCHED}" ></c:param>
                                            <c:param name="txtNameSearch" value="${requestScope.nameSEARCHED}" ></c:param>
                                            <c:param name="txtRental" value="${requestScope.rentalDaySEARCHED}" ></c:param>
                                            <c:param name="txtReturn" value="${requestScope.returnDaySEARCHED}" ></c:param>
                                            <c:param name="txtQuantity" value="${requestScope.quantitySEARCHED}" ></c:param>
                                            <c:param name="activePage" value="${requestScope.ACTIVE_PAGE}" ></c:param>
                                        </c:url>
                                        <a href="${Back}">
                                            <button type="button" class="btn btn-default">
                                                <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                                            </button></td>
                                    </a>                            
                                    <td>


                                        <c:url var="checkOut" value="VoucherController">
                                            <c:param name="total" value="${total}" ></c:param>
                                
                                        </c:url>
                                        <a href="${checkOut}">
                                            <button type="button" class="btn btn-success">
                                                <span class="glyphicon glyphicon-play"> Checkout</span>
                                            </button>
                                            <h1 style="color: green">${requestScope.SUCCESS} </h1>
                                    </td>

                                    </a>                         

                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </c:if>

        <c:if test="${requestScope.MESS_SUCCESS!=null}">
        <div  class="alert alert-success" role="alert">
            <h3 style="text-align: center">${requestScope.MESS_SUCCESS}</h3>
        </div>
        </c:if>

        <c:if test="${empty cart.getCart() || cart.getCart() == null}">
            <div  class="alert alert-warning" role="alert">
                <h3 style="text-align: center">Your cart is empty</h3>
            </div>

            <c:url var="Back" value="SearchController">


                <c:param name="optionSEARCH" value="${requestScope.optionSEARCHED}" ></c:param>
                <c:param name="txtCategory" value="${requestScope.categorySEARCHED}" ></c:param>
                <c:param name="txtNameSearch" value="${requestScope.nameSEARCHED}" ></c:param>
                <c:param name="txtRental" value="${requestScope.rentalDaySEARCHED}" ></c:param>
                <c:param name="txtReturn" value="${requestScope.returnDaySEARCHED}" ></c:param>
                <c:param name="txtQuantity" value="${requestScope.quantitySEARCHED}" ></c:param>
                <c:param name="activePage" value="${requestScope.ACTIVE_PAGE}" ></c:param>
            </c:url>

            <a style="margin-left: 44%" href="${Back}">
                <button type="button" class="btn btn-default">
                    <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                </button>
            </a>      
        </c:if>


    </body>
</html>
