
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Sign Up</title>

        <!-- Font Icon -->
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="css/style.css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body>

        <div class="main">

            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h3 style="color: greenyellow">${requestScope.MESSAGE_SUCCESS}</h3>
                            <h2 class="form-title">Sign up</h2>
                            <form action="RegisterController" method="POST" class="register-form" id="register-form">
                                <div class="form-group">

                                    <input type="text" required="required" maxlength="40" name="txtName" value="${requestScope.NAME_REGISTER == null ? '' : requestScope.NAME_REGISTER}" placeholder="Your Name" />
                                    <h4 style="margin-top: 0;margin-bottom: 0;color: red">${requestScope.ERROR.getNameError()}</h4>
                                </div>

                                <div class="form-group">

                                    <input type="email" name="txtEmail" id="email" maxlength="50" required="required" value="${requestScope.NAME_EMAIL == null ? '' : requestScope.NAME_EMAIL}" placeholder="Your Email" />
                                    <h6 style="margin-top: 0;margin-bottom: 0;color: red">${requestScope.MESSAGE_EMAIL }</h6>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="txtAddress" id="address" maxlength="50" required="required" value="${requestScope.ADRESS_REGISTER == null ? '' :requestScope.ADRESS_REGISTER }" placeholder="Your address" />
                                    <h6 style="margin-top: 0;margin-bottom: 0;color: red"></h6>
                                </div>
                                <div class="form-group">
                                    <input type="text" pattern="[0-9]{10,10}" minlength="10" maxlength="10" name="txtPhoneNumber" required="required" value="${requestScope.PHONE_REGISTER == null ? "" : requestScope.PHONE_REGISTER}" placeholder="Your Phone number" title="phone number's length is 10 and only number" />
                                    <h6 style="margin-top: 0;margin-bottom: 0;color: red"></h6>
                                </div>



                                <div class="form-group">

                                    <input type="password" name="txtPassword" minlength="6" maxlength="20" required="required" id="pass" value="" placeholder="Password" />
                                    <h6 style="margin-top: 0;margin-bottom: 0;color: red">${requestScope.MESSAGE_PASS}</h6>
                                </div>
                                <div class="form-group">

                                    <input required="required" type="password" name="txtPassword2" minlength="6" maxlength="20" id="re_pass" value="" placeholder="Repeat your password" />

                                </div>

                                <div class="form-group form-button" style="display: flex">
                                    <input type="submit" name="btnAction"  class="form-submit" value="register" />
                                    <h4  style="margin-top: 0;margin-bottom: 0;color: green">${requestScope.success}</h4>

                                    <c:url var="back" value="LoginController" > </c:url>
                                    <a href="${back}" style="height: 53px;margin-top: 25px;margin-left: 60px; padding-top: 15px" class='btn btn-success'> Back to login </a>
                                </div>
                            </form>
                        </div>
                        <!-- <div class="signup-image">
                            <figure><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRTHeejRD7eonbJ70L4WhHyFgjpsA7fqOWAhA&usqp=CAU" alt="sing up image"></figure>
                             <a href="#" class="signup-image-link">I am already member</a> -->
                    </div>
                </div>
        </div>
    </section>

    <!-- Sing in  Form -->


</div>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>