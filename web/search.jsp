

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Search</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>

    <body>

        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <nav class="pr-0 pl-0 navbar navbar-expand-md navbar-dark bg-dark main-menu" style="box-shadow:none">
            <div  class="container-fluid" >

                <button type="button" id="sidebarCollapse" class="btn btn-link d-block d-md-none">
                    <i class="bx bx-menu icon-single"></i>
                </button>

                <a class="navbar-brand" href="#">
                    <h2 class="font-weight-bold">DreamCar</h2>
                </a>



                <div class="collapse navbar-collapse">
                    <form action="SearchController" method="post" class="form-inline my-2 my-lg-0 mx-auto">
                        <c:set var="optionSEARCHED" value="${requestScope.optionSEARCHED == null ? '' : requestScope.optionSEARCHED }"></c:set>
                            <ul style=" list-style: none" >
                                <li style="margin-right: 30px">
                                    <h4 style="color: white; ">Category</h4>
                                    <span>
                                    <c:if test="${optionSEARCHED.equals('searchByCategory')}">
                                        <input checked="checked" required="required" type="radio" name="optionSEARCH" value="searchByCategory" >
                                    </c:if>
                                    <c:if test="${!optionSEARCHED.equals('searchByCategory')}">
                                        <input  required="required" type="radio" name="optionSEARCH" value="searchByCategory" >
                                    </c:if>

                                    <select class="form-control mr-2" name="txtCategory" >
                                        <c:set var="categorySEARCHED" value="${requestScope.categorySEARCHED == null ? '' : requestScope.categorySEARCHED}" ></c:set>
                                        <c:set var="listCate"  value="${sessionScope.LIST_CATEGORY}"></c:set>
                                        <c:forEach var="category" items="${listCate.values()}" >

                                            <c:if test="${!category.equals(categorySEARCHED)}" >
                                                <option   value="${category}" >${category}</option> 
                                            </c:if>
                                            <c:if test="${category.equals(categorySEARCHED)}" >
                                                <option  selected="selected" value="${category}" >${category}</option> 
                                            </c:if>


                                        </c:forEach>
                                    </select>
                                </span>
                            </li>
                            <li style="margin-right: 30px" >
                                <h4 style="color: white;">Car Name</h4>
                                <span>

                                    <c:if test="${optionSEARCHED.equals('searchByName')}">
                                        <input checked="checked" required="required" type="radio" name="optionSEARCH" value="searchByName" >
                                    </c:if>
                                    <c:if test="${!optionSEARCHED.equals('searchByName')}">
                                        <input  required="required" type="radio" name="optionSEARCH" value="searchByName" >
                                    </c:if>



                                    <input class="form-control mr-2" value="${requestScope.nameSEARCHED == null ? "" : requestScope.nameSEARCHED}"  type="text" name="txtNameSearch"> 

                                </span>
                            </li>



                        </ul>
                        <div>
                            <h4 style="color: white;"> Rental date</h4>
                            <span>
                                <input value="${requestScope.rentalDaySEARCHED == null ? "" : requestScope.rentalDaySEARCHED}" min="${sessionScope.DATE_NOW}" name="txtRental" required="required" class="form-control mr-2" type="date" >
                            </span>
                        </div>
                        <div >
                            <h4 style="color: white;margin-top: 18px"> Return date</h4>
                            <span>
                                <input value="${requestScope.returnDaySEARCHED == null ? "" : requestScope.returnDaySEARCHED}" min="${sessionScope.DATE_NOW}" name="txtReturn" required="required" class="form-control mr-2" type="date" >
                                <span style="margin-top: 30px"><p style="color: yellow" >${requestScope.messDateSearch == null? "" : requestScope.messDateSearch}</p></span>
                            </span>
                        </div>
                        <div>
                            <h4 style="color: white;">Quantity</h4>
                            <span>
                                <input value="${requestScope.quantitySEARCHED == null ? "" : requestScope.quantitySEARCHED}" name="txtQuantity"   max="100" min="1" required="required" class="form-control mr-2" type="number" >
                            </span>
                        </div>



                        <button class=" btn btn-success" type="submit" style="margin-top: 38px">Search</button>
                        
                    </form>



                    <p style="color: white;margin-top: 40px" class="mb-0 ml-2 ">${sessionScope.USER.userName} </p>
                    <i class="fas fa-creative-commons-zero    "></i>
                </div>

                <div> 
                    <ul class="navbar-nav" style="display: flex">


                        <c:url var="viewCart" value="ViewCartController" >

                            <c:param name="optionSEARCH" value="${requestScope.optionSEARCHED}" ></c:param>
                            <c:param name="txtCategory" value="${requestScope.categorySEARCHED}" ></c:param>
                            <c:param name="txtNameSearch" value="${requestScope.nameSEARCHED}" ></c:param>
                            <c:param name="txtRental" value="${requestScope.rentalDaySEARCHED}" ></c:param>
                            <c:param name="txtReturn" value="${requestScope.returnDaySEARCHED}" ></c:param>
                            <c:param name="txtQuantity" value="${requestScope.quantitySEARCHED}" ></c:param>
                            <c:param name="activePage" value="${requestScope.ACTIVE_PAGE}" ></c:param>


                        </c:url>
                        <c:if test="${sessionScope.USER!=null}">
                            <li><a style="margin-top: 40px;margin-left: 60px" class="btn btn-primary" href="${viewCart}" >CART</a></li> 
                            </c:if>
                            <c:if test="${sessionScope.USER == null}">
                            <li style="margin-top: 40px" class="nav-item ml-md-3">
                                <c:url var="login" value="LoginController" >  
                                    <c:param name="check" value="check" ></c:param>
                                </c:url>
                                <a class="btn btn-success" href="${login}"><i class="bx bxs-user-circle mr-1"></i>login</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.USER != null}">
                            <li style="margin-top: 40px" class="nav-item ml-md-3">
                                <c:url var="history" value="HistoryController" >  

                                </c:url>
                                <a class="btn btn-success" href="${history}"><i class="bx bxs-user-circle mr-1"></i>History</a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.USER != null}">
                            <li style="margin-top: 40px" class="nav-item ml-md-3">
                                <c:url var="logout" value="LogoutController" >  

                                </c:url>
                                <a class="btn btn-danger" href="${logout}"><i class="bx bxs-user-circle mr-1"></i>Logout</a>
                            </li>
                        </c:if>

                    </ul>
                </div>
            </div>
        </nav>
        <section  class="jumbotron text-center">
            <div style="height: 50px" class="container">
                <h1 class="jumbotron-heading">CAR RENTAL</h1>
            </div>
        </section>



        <!----------------------------------------PRODUCT-------------------------------------------->


        <c:if test="${requestScope.MESS_QUANTITY!=null}">
            <div class="alert alert-primary" role="alert">
                ${requestScope.MESS_QUANTITY}
            </div>
        </c:if>

        <div class="container">
            <div class="row">

                <c:set value="${requestScope.LIST_CAR}" var="listCar"></c:set>

                <c:if test="${listCar!=null}">
                    <c:forEach var="car" items="${listCar}">

                        <div class="card col-4 mt-5" style="width: 18rem;">
                            <img src="images/${car.image}" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">${car.carName}</h5>
                                <p>Year: ${car.year}</p>
                                <h4>Price: <span>${car.price}$/day</span></h4>
                                <h4>Quantity: ${car.quantity}</span></h4>
                                <p>feedback: ${car.avg == 0.0 ? "none" : car.avg}</p>
                                <c:url var="add" value="AddController" >
                                    <c:param name="optionSEARCH" value="${requestScope.optionSEARCHED}" ></c:param>
                                    <c:param name="txtCategory" value="${requestScope.categorySEARCHED}" ></c:param>
                                    <c:param name="txtNameSearch" value="${requestScope.nameSEARCHED}" ></c:param>
                                    <c:param name="txtRental" value="${requestScope.rentalDaySEARCHED}" ></c:param>
                                    <c:param name="txtReturn" value="${requestScope.returnDaySEARCHED}" ></c:param>
                                    <c:param name="txtQuantity" value="${requestScope.quantitySEARCHED}" ></c:param>
                                    <c:param name="activePage" value="${requestScope.ACTIVE_PAGE}" ></c:param>
                                    <c:param name="carIDToADD" value="${car.carID}" ></c:param>
                                </c:url>
                                <a href="${add}" class="btn btn-success">Add</a>
                            </div>
                        </div>

                    </c:forEach>
                </c:if>
                <c:if test="${(listCar==null || listCar.isEmpty()) && requestScope.quantitySEARCHED != null}">
                    <h1 style="text-align: center; margin: 0 auto; color: yellow">Not found</h1>
                </c:if>





            </div>
            <div>
                <ul style="width: 300px; list-style-type: none; margin: 0 auto; font-size: 20px" class="d-flex mt-5 mb-4" >

                    <c:if test="${sessionScope.NUMBER_OF_PAGE!=null}">
                        <c:if test="${sessionScope.NUMBER_OF_PAGE > 1 }">
                            <c:if test="${ listCar!=null || listCar.size() > 0}">
                                <c:forEach begin="1" end="${sessionScope.NUMBER_OF_PAGE}"  varStatus="counter">
                                    <li class="ml-2"><a href="SearchController?activePage=${counter.index}&txtNameSearch=${requestScope.nameSEARCHED}&txtCategory=${requestScope.categorySEARCHED}&optionSEARCH=${requestScope.optionSEARCHED}&txtQuantity=${requestScope.quantitySEARCHED}&txtRental=${requestScope.rentalDaySEARCHED}&txtReturn=${requestScope.returnDaySEARCHED}">${counter.index}</a></li>  
                                    </c:forEach>
                                </c:if>

                        </c:if>

                    </c:if>
                </ul>
            </div>
        </div>

        <!-- Footer -->


        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
