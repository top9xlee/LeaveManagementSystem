<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="m-portlet m-portlet--tab" class="col-md-7">
    <div class="m-portlet__head">
        <div class="m-portlet__head-caption">
            <div class="m-portlet__head-title">
												<span class="m-portlet__head-icon m--hide">
													<i class="la la-gear"></i>
												</span>
                <h3 class="m-portlet__head-text">
                    Chỉnh sửa tài khoản
                </h3>
            </div>
        </div>
    </div>
    <div>
        <c:choose>
            <c:when test="${param.get('status').equals('success')}">
                <p class='alert alert-success' role='alert' style="
    text-align: center;
    width: 25%;
    margin: auto;
    font-size: 1.1rem;
">Thay đổi thông tin thành công</p>
            </c:when>
            <c:when test="${param.get('error').equals('fail')}">
                <p class="message-register" style="color: rgb(20, 157, 252); font-size: 16px;text-align: center">Vui
                    lòng nhập lại mật khẩu </p>
            </c:when>
        </c:choose>
    </div>
    <div class="row">
        <div style="justify-content: center;" class="col-md-2"></div>
        <div style="justify-content: center;" class="col-md-8">
            <form action="/changepw" class="m-form m-form--fit m-form--label-align-right" name='f' method='POST'
                  modelAttribute="appUserDTO" id="submit">
                <input type="hidden" value='${user.userId}' name="userId">
                <%--            <input type="hidden" value="'${user.encrytedPassword}" name="encrytedPassword">--%>
                <div class="m-portlet__body">
                    <div class="form-group m-form__group">
                        <label class="">Tên người dùng</label>
                        <input disabled type="text" class="form-control m-input m-input--solid"
                               aria-describedby="emailHelp" placeholder="Nhập tên người dùng" name="userName"
                               value='${user.userName}'>
                    </div>
                    <div class="form-group m-form__group">
                        <label class="">Tên đầy đủ</label>
                        <input disabled type="text" class="form-control m-input m-input--solid"
                               placeholder="Nhập tên đầy đủ" name="fullName" value="${user.fullName}">
                    </div>
                    <div class="form-group m-form__group">
                        <label class="">Email</label>
                        <input disabled type="email" class="form-control m-input m-input--solid"
                               placeholder="Nhập email" name="email" value="${user.email}">
                    </div>
                    <div class="form-group m-form__group">
                        <label class="">Mật khẩu cũ</label>
                        <input type="password" class="form-control m-input m-input--solid"
                               placeholder="Nhập mật khẩu cũ" name='changePw' required>
                    </div>
                    <div class="form-group m-form__group">
                        <label class="">Mật khẩu mới</label>
                        <input type="password" class="form-control m-input m-input--solid"
                               placeholder="Nhập mật khẩu mới" name='newPassword' required>
                    </div>
                    <div class="form-group m-form__group">
                        <label class="">Nhập lại mật khẩu</label>
                        <input type="password" class="form-control m-input m-input--solid"
                               placeholder="Nhập lại mật khẩu mới" name='repassword' required>
                    </div>
                    <div class="form-group m-form__group">
                        <label class="">Thuộc phòng ban</label>
                        <input disabled type="text" class="form-control m-input m-input--solid"
                               placeholder="Thuộc phòng ban" name="departmentId"
                               value="${departmentResponse.departmentName}">
                    </div>

                </div>
                <div class="m-portlet__foot m-portlet__foot--fit">
                    <div class="m-form__actions">
                        <button type="submit" class="btn btn-success">Thay đổi</button>
                        <button class="btn btn-secondary"><a href="/">Thoát</a></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

