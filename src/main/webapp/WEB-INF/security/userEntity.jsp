<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng kí thành viên</title>
</head>
<body>
<div class="m-portlet m-portlet--tab" class="col-md-7" >
    <div class="m-portlet__head">
        <div class="m-portlet__head-caption">
            <div class="m-portlet__head-title">
												<span class="m-portlet__head-icon m--hide">
													<i class="la la-gear"></i>
												</span>
                <h3 class="m-portlet__head-text">
                    Đăng kí thành viên
                </h3>
            </div>
        </div>
    </div>
    <div class="col-md-12" >
        <div style="background-color: white;margin: auto;width: 200px">
            <c:choose>
                <c:when test="${param.get('error').equals('fail_userName')}">
                    <p class='alert alert-success' role='alert' style="
    text-align: center;
    width: max-content;
    margin: auto;
    font-size: 1.1rem;
">Tên tài khoản bị trùng lặp</p>
                </c:when>
                <c:when test="${param.get('error').equals('fail_mail')}">
                    <p class='alert alert-success' role='alert' style="
    text-align: center;
    width: max-content;
    margin: auto;
    font-size: 1.1rem;
">Email đã được đăng kí</p>
                </c:when>
                <c:when test="${param.get('error').equals('fail_phoneNumber')}">
                    <p class='alert alert-success' role='alert' style="
    text-align: center;
    width: max-content;
    margin: auto;
    font-size: 1.1rem;
">Số điện thoại đã được đăng kí</p>
                </c:when>
                <c:when test="${param.get('status').equals('success')}">
                    <p class='alert alert-success' role='alert' style="
    text-align: center;
    width: max-content;
    margin: auto;
    font-size: 1.1rem;
">Tạo tài khoản thành công</p>
                </c:when>
            </c:choose>
        </div>
    </div>
    <div style=" display: flex; justify-content: center;">
        <!--begin::Form-->
        <form class="cangiua" style="width: 50%;"  class="m-form m-form--fit m-form--label-align-right"name='f' action="/register" method='POST' modelAttribute="appUserDTO" id="submit">
            <div class="m-portlet__body" >
                <div class="form-group m-form__group" >
                    <label class="">Tên người dùng</label>
                    <input type="text" class="form-control m-input m-input--solid"  aria-describedby="emailHelp" placeholder="Nhập tên người dùng" name="userName" required>
                </div>
                <div class="form-group m-form__group">
                    <label class="">Tên đầy đủ</label>
                    <input type="text" class="form-control m-input m-input--solid"  placeholder="Nhập tên đầy đủ" name="fullName" required>
                </div>
<%--                <div class="form-group m-form__group">--%>
<%--                    <label class="">Email</label>--%>
<%--                    <input type="email" class="form-control m-input m-input--solid"  placeholder="Nhập email" name="email" value="@vconnex.vn">--%>
<%--                </div>--%>
                <label class="">Email</label>
                <div class="input-group mb-3">
                    <input type="text" class="form-control m-input m-input--solid" placeholder="Nhập email"  name="email"required>
                    <span class="input-group-text" id="basic-addon2">@vconnex.vn</span>
                </div>
                <div class="form-group m-form__group">
                    <label class="">Số điện thoại</label>
                    <input type="tel" class="form-control m-input m-input--solid"  placeholder="Nhập số điện thoại" name='phoneNumber' required minlength="10" maxlength="11">
                </div>
                <div class="form-group m-form__group">
                    <label class="">Mật khẩu</label>
                    <input type="password" class="form-control m-input m-input--solid"  placeholder="Nhập mật khẩu" name='encrytedPassword' required minlength="6" maxlength="15">
                </div>
                <div class="form-group m-form__group">
                    <label class="">Nhập lại mật khẩu</label>
                    <input type="password" class="form-control m-input m-input--solid"  placeholder="Nhập lại mật khẩu" name='repassword'required minlength="6" maxlength="15">
                </div>
                <div class="form-group m-form__group">
                    <label for="exampleSelect1">Chọn phòng ban</label>
                    <select class="form-control m-input m-input--solid" id="exampleSelect1" name="departmentId" required>
                        <option id="input" class="form-control m-input" disabled selected >Chọn phòng ban</option >
                        <c:forEach var="departments" items="${departments}">
                            <option  class="form-control m-input" value="${departments.departmentId}">${departments.departmentName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group m-form__group">
                    <label for="exampleSelect1">Phân quyền</label>
                    <select class="form-control m-input m-input--solid" id="exampleSelect" name="roleId" required>
                        <option id="input" class="form-control m-input" disabled selected>Phân quyền</option>
                        <c:forEach var="appRoles" items="${appRoles}">

                            <option  class="form-control m-input" value="${appRoles.roleId}">${appRoles.roleName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group m-form__group">
                    <label class="">Tên công việc</label>
                    <input type="text" class="form-control m-input m-input--solid"  placeholder="Nhập tên công việc" name="jobName" required>
                </div>
            </div>
            <div class="m-portlet__foot m-portlet__foot--fit" style="display: flex; justify-content: center">
                <div class="m-form__actions">
                    <button type="submit" class="btn btn-success">Đăng kí</button>
                    <a class="btn btn-secondary" href="/export">Thoát</a>
                </div>
            </div>
        </form>
    </div>


</div>
</body>
</html>