<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8" %><%--// đọc chữ tiếng việt--%>


<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Đằng nhập</title>
    <meta name="description" content="Latest updates and statistic charts">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet">
    <link href="/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
    <link href="/demo/demo2/base/style.bundle.css" rel="stylesheet" type="text/css" />

    <!--end::Web font -->

    <!--begin:: Global Mandatory Vendors -->
    <%--    <link href="../../../../../vendors/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" type="text/css" />--%>


</head>

<!-- end::Head -->

<!-- begin::Body -->
<body class="m--skin- m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--fixed m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default">

<!-- begin:: Page -->
<div class="m-grid m-grid--hor m-grid--root m-page">
    <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-grid--tablet-and-mobile m-grid--hor-tablet-and-mobile m-login m-login--1 m-login--signin" id="m_login">
        <div class="m-grid__item m-grid__item--order-tablet-and-mobile-2 m-login__aside">
            <div class="m-stack m-stack--hor m-stack--desktop">
                <div class="m-stack__item m-stack__item--fluid">
                    <div class="m-login__wrapper">
                        <div class="m-login__logo">
                            <a href="#">
                                <img src="/image/logo-vconnex.png" height="80px">
                            </a>
                        </div>
                        <div class="m-login__signin">
                            <div class="m-login__head">

                                <h3 class="m-login__title">Mật khẩu mới</h3>
                                <div class="m-login__desc">Hãy nhập mật khẩu mới để cài đặt lại password của bạn</div>
                                <c:choose>
                                    <c:when test="${param.get('status').equals('success')}">
                                        <p class="message-register" style="color: rgb(22, 161, 255); font-size: 16px;text-align: center">Thành công! Xin hãy kiểm tra lại email</p>
                                    </c:when>
                                    <c:when test="${param.get('error').equals('emailNotFound')}">
                                        <p class="message-register" style="color: rgb(20, 157, 252); font-size: 16px;text-align: center">Không tìm thấy email mà bạn yêu cầu</p>
                                    </c:when>
                                </c:choose>
                            </div>
                            <form class="m-login__form m-form" action="/changePassword" method="post" enctype="multipart/form-data">
                                <input hidden name = "userId" value="${users.userId}">
                                <div class="form-group m-form__group">
                                    <input class="form-control m-input" type="password" placeholder="Mật khẩu mới" name="password"  autocomplete="off" required minlength="6" maxlength="15">
                                </div>
                                <div class="form-group m-form__group">
                                    <input class="form-control m-input" type="password" placeholder="Nhập lại mật khẩu" name="rePassword"  autocomplete="off" required minlength="6" maxlength="15">
                                </div>
                                <div class="row m-login__form-sub">
                                    <%--                                    <div class="col m--align-left">--%>
                                    <%--                                        <label class="m-checkbox m-checkbox--focus">--%>
                                    <%--                                            <input type="checkbox" name="remember"> Remember me--%>
                                    <%--                                            <span></span>--%>
                                    <%--                                        </label>--%>
                                    <%--                                    </div>--%>
                                    <div class="col m--align-center">
                                        <a href="/login" class="m-link">Đăng nhập ?</a>
                                    </div>
                                </div>
                                <div class="m-login__form-action">

                                    <button  class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air" type="submit">Request</button>
                                    <%--                                    <button  class="btn btn-outline-focus m-btn m-btn--pill m-btn--custom">Cancel</button>--%>
                                </div>
                            </form>
                        </div>
                        <div class="m-login__signup">
                            <div class="m-login__head">
                                <h3 class="m-login__title">Sign Up</h3>
                                <div class="m-login__desc">Enter your details to create your account:</div>
                            </div>
                            <form class="m-login__form m-form" action="">
                                <div class="form-group m-form__group">
                                    <input class="form-control m-input" type="text" placeholder="Fullname" name="fullname">
                                </div>
                                <div class="form-group m-form__group">
                                    <input class="form-control m-input" type="text" placeholder="Email" name="email" autocomplete="off">
                                </div>
                                <div class="form-group m-form__group">
                                    <input class="form-control m-input" type="password" placeholder="Password" name="password">
                                </div>
                                <div class="form-group m-form__group">
                                    <input class="form-control m-input m-login__form-input--last" type="password" placeholder="Confirm Password" name="rpassword">
                                </div>
                                <div class="row form-group m-form__group m-login__form-sub">
                                    <div class="col m--align-left">
                                        <label class="m-checkbox m-checkbox--focus">
                                            <input type="checkbox" name="agree"> I Agree the <a href="#" class="m-link m-link--focus">terms and conditions</a>.
                                            <span></span>
                                        </label>
                                        <span class="m-form__help"></span>
                                    </div>
                                </div>
                                <div class="m-login__form-action">
                                    <button id="m_login_signup_submit" class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air">Sign Up</button>
                                    <button id="m_login_signup_cancel" class="btn btn-outline-focus  m-btn m-btn--pill m-btn--custom">Cancel</button>
                                </div>
                            </form>
                        </div>
                        <div class="m-login__forget-password">
                            <div class="m-login__head">
                                <h3 class="m-login__title">Quên mật khẩu?</h3>
                                <div class="m-login__desc">Hãy nhập email để cài đặt lại password của bạn</div>
                            </div>
                            <form class="m-login__form m-form" action="/forget-password">























































































































                                <div class="form-group m-form__group">
                                    <input class="form-control m-input" type="text" placeholder="Email" name="email"  autocomplete="off">
                                </div>
                                <div class="m-login__form-action">
                                    <button  class="btn btn-focus m-btn m-btn--pill m-btn--custom m-btn--air" type="submit">Gửi yêu cầu</button>
                                    <button  class="btn btn-outline-focus m-btn m-btn--pill m-btn--custom"><a href="/login">Hủy</a></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="m-stack__item m-stack__item--center">
                    <%--                    <div class="m-login__account">--%>
                    <%--								<span class="m-login__account-msg">--%>
                    <%--									Bạn chưa có tài khoản?--%>
                    <%--								</span>&nbsp;&nbsp;--%>
                    <%--                        <a href="javascript:;" id="m_login_signup" class="m-link m-link--focus m-login__account-link">Đăng ký</a>--%>
                    <%--                    </div>--%>
                </div>
            </div>
        </div>
        <div class="m-grid__item m-grid__item--fluid m-grid m-grid--center m-grid--hor m-grid__item--order-tablet-and-mobile-1	m-login__content m-grid-item--center" style="background-image: url(/image/Vconnex-IoT.jpg)">
            <div class="m-grid__item">
                <h3 class="m-login__welcome">Vconnex</h3>
                <p class="m-login__msg">
                    Công ty cổ phần công nghệ công nghiệp
                </p>
            </div>
        </div>
    </div>
</div>

<!-- end:: Page -->

<!--begin:: Global Mandatory Vendors -->
<script src="/vendors/jquery/dist/jquery.js" type="text/javascript"></script>
<script src="/vendors/popper.js/dist/umd/popper.js" type="text/javascript"></script>
<script src="/vendors/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/vendors/js-cookie/src/js.cookie.js" type="text/javascript"></script>
<script src="/vendors/moment/min/moment.min.js" type="text/javascript"></script>
<script src="/vendors/tooltip.js/dist/umd/tooltip.min.js" type="text/javascript"></script>
<script src="/vendors/perfect-scrollbar/dist/perfect-scrollbar.js" type="text/javascript"></script>
<script src="/vendors/wnumb/wNumb.js" type="text/javascript"></script>
<script src="/vendors/base/scripts.bundle.js" type="text/javascript"></script>
<script>
    //== Class Definition
    var SnippetLogin = function() {

        var login = $('#m_login');

        var showErrorMsg = function(form, type, msg) {
            var alert = $('<div class="m-alert m-alert--outline alert alert-' + type + ' alert-dismissible" role="alert">\
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>\
			<span></span>\
		</div>');

            form.find('.alert').remove();
            alert.prependTo(form);
            //alert.animateClass('fadeIn animated');
            mUtil.animateClass(alert[0], 'fadeIn animated');
            alert.find('span').html(msg);
        }

        //== Private Functions

        var displaySignUpForm = function() {
            login.removeClass('m-login--forget-password');
            login.removeClass('m-login--signin');

            login.addClass('m-login--signup');
            mUtil.animateClass(login.find('.m-login__signup')[0], 'flipInX animated');
        }

        var displaySignInForm = function() {
            login.removeClass('m-login--forget-password');
            login.removeClass('m-login--signup');

            login.addClass('m-login--signin');
            mUtil.animateClass(login.find('.m-login__signin')[0], 'flipInX animated');
            //login.find('.m-login__signin').animateClass('flipInX animated');
        }

        var displayForgetPasswordForm = function() {
            login.removeClass('m-login--signin');
            login.removeClass('m-login--signup');

            login.addClass('m-login--forget-password');
            //login.find('.m-login__forget-password').animateClass('flipInX animated');
            mUtil.animateClass(login.find('.m-login__forget-password')[0], 'flipInX animated');

        }

        var handleFormSwitch = function() {
            $('#m_login_forget_password').click(function(e) {
                e.preventDefault();
                displayForgetPasswordForm();
            });

            $('#m_login_forget_password_cancel').click(function(e) {
                e.preventDefault();
                displaySignInForm();
            });

            $('#m_login_signup').click(function(e) {
                e.preventDefault();
                displaySignUpForm();
            });

            $('#m_login_signup_cancel').click(function(e) {
                e.preventDefault();
                displaySignInForm();
            });
        }

        var handleSignInFormSubmit = function() {
            $('#m_login_signin_submit').click(function(e) {
                e.preventDefault();
                var btn = $(this);
                var form = $(this).closest('form');

                form.validate({
                    rules: {
                        email: {
                            required: true,
                            email: true
                        },
                        password: {
                            required: true
                        }
                    }
                });

                if (!form.valid()) {
                    return;
                }

                btn.addClass('m-loader m-loader--right m-loader--light').attr('disabled', true);

                form.ajaxSubmit({
                    url: '',
                    success: function(response, status, xhr, $form) {
                        // similate 2s delay
                        setTimeout(function() {
                            btn.removeClass('m-loader m-loader--right m-loader--light').attr('disabled', false);
                            showErrorMsg(form, 'danger', 'Incorrect username or password. Please try again.');
                        }, 2000);
                    }
                });
            });
        }

        var handleSignUpFormSubmit = function() {
            $('#m_login_signup_submit').click(function(e) {
                e.preventDefault();

                var btn = $(this);
                var form = $(this).closest('form');

                form.validate({
                    rules: {
                        fullname: {
                            required: true
                        },
                        email: {
                            required: true,
                            email: true
                        },
                        password: {
                            required: true
                        },
                        rpassword: {
                            required: true
                        },
                        agree: {
                            required: true
                        }
                    }
                });

                if (!form.valid()) {
                    return;
                }

                btn.addClass('m-loader m-loader--right m-loader--light').attr('disabled', true);

                form.ajaxSubmit({
                    url: '',
                    success: function(response, status, xhr, $form) {
                        // similate 2s delay
                        setTimeout(function() {
                            btn.removeClass('m-loader m-loader--right m-loader--light').attr('disabled', false);
                            form.clearForm();
                            form.validate().resetForm();

                            // display signup form
                            displaySignInForm();
                            var signInForm = login.find('.m-login__signin form');
                            signInForm.clearForm();
                            signInForm.validate().resetForm();

                            showErrorMsg(signInForm, 'success', 'Thank you. To complete your registration please check your email.');
                        }, 2000);
                    }
                });
            });
        }

        var handleForgetPasswordFormSubmit = function() {
            $('#m_login_forget_password_submit').click(function(e) {
                e.preventDefault();

                var btn = $(this);
                var form = $(this).closest('form');

                form.validate({
                    rules: {
                        email: {
                            required: true,
                            email: true
                        }
                    }
                });

                if (!form.valid()) {
                    return;
                }

                btn.addClass('m-loader m-loader--right m-loader--light').attr('disabled', true);

                form.ajaxSubmit({
                    url: '',
                    success: function(response, status, xhr, $form) {
                        // similate 2s delay
                        setTimeout(function() {
                            btn.removeClass('m-loader m-loader--right m-loader--light').attr('disabled', false); // remove
                            form.clearForm(); // clear form
                            form.validate().resetForm(); // reset validation states

                            // display signup form
                            displaySignInForm();
                            var signInForm = login.find('.m-login__signin form');
                            signInForm.clearForm();
                            signInForm.validate().resetForm();

                            showErrorMsg(signInForm, 'success', 'Cool! Password recovery instruction has been sent to your email.');
                        }, 2000);
                    }
                });
            });
        }

        //== Public Functions
        return {
            // public functions
            init: function() {
                handleFormSwitch();
                handleSignInFormSubmit();
                handleSignUpFormSubmit();
                handleForgetPasswordFormSubmit();
            }
        };
    }();

    //== Class Initialization
    jQuery(document).ready(function() {
        SnippetLogin.init();
    });
</script>
</body>
</html>
<>





































<%--<c:if test="${param.error == 'true'}">--%>
<%--    <div style="color:red;margin:10px 0px;">--%>

<%--        Đăng nhập thất bại<br />--%>
<%--        Lí do :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}--%>

<%--    </div>--%>
<%--</c:if>--%>

<%--&lt;%&ndash;<div th:if="${#request.getParameter('error') == 'true'}"&ndash;%&gt;--%>
<%--&lt;%&ndash;     style="color:red;margin:10px 0px;">&ndash;%&gt;--%>
<%--&lt;%&ndash;    Login Failed!!!<br />&ndash;%&gt;--%>
<%--&lt;%&ndash;    Reason :&ndash;%&gt;--%>
<%--&lt;%&ndash;    <span th:if="${#session!= null and #session.getAttribute('SPRING_SECURITY_LAST_EXCEPTION') != null}"&ndash;%&gt;--%>
<%--&lt;%&ndash;          th:utext="${#session.getAttribute('SPRING_SECURITY_LAST_EXCEPTION').message}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                Static summary&ndash;%&gt;--%>
<%--&lt;%&ndash;         </span>&ndash;%&gt;--%>

<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<form name='f' action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">--%>
<%--    <div class="m-portlet__body">--%>
<%--        <div class="form-group m-form__group row">--%>
<%--            <div class="col-lg-7">--%>
<%--                <label>Tên người dùng:</label>--%>
<%--                <input type="text" class="form-control m-input" placeholder="Nhập tên" name="userName">--%>
<%--&lt;%&ndash;                <span class="m-form__help">Mời nhập tên của bạn</span>&ndash;%&gt;--%>
<%--            </div>--%>
<%--            <div class="col-lg-7">--%>
<%--                <label class="">Mật khẩu:</label>--%>
<%--                <input type="password" class="form-control m-input" placeholder="Nhập mật khẩu" name="encrytedPassword">--%>
<%--&lt;%&ndash;                <span class="m-form__help">Mời nhập mật khẩu</span>&ndash;%&gt;--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">--%>
<%--        <div class="m-form__actions m-form__actions--solid">--%>
<%--            <div class="row">--%>
<%--                <div class="col-lg-4"></div>--%>
<%--                <div class="col-lg-8">--%>
<%--                    <button type="submit" class="btn btn-primary">Đăng nhập</button>--%>
<%--                    <button type="submit" class="btn btn-secondary">Thoát</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="sp7vspb"><p><a href="/forgetpw1">Bạn quên mật khẩu?</a></p></div>--%>
<%--        <div class="sp7vspb"><p><a href="/changepw">Thay đổi mật khẩu?</a></p></div>--%>
<%--    </div>--%>
<%--</form>--%>