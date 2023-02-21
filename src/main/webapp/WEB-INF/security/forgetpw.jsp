
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form name='f'  method='POST'action="/forgetpw" class="m-form m-form--fit m-form--label-align-right m-form--group-seperator-dashed">
    <div class="m-portlet__body">
        <div class="form-group m-form__group row">
            <div class="col-lg-10">
                <label>Email:</label>
                <input type="tel" class="form-control m-input" name="email" placeholder="Nhập Email">
<%--                <span class="m-form__help">Mời Nhập Email của bạn</span>--%>
            </div>
        </div>
    </div>
    <div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">
        <div class="m-form__actions m-form__actions--solid">
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-8">
                    <button type="submit" class="btn btn-primary">Đăng kí mật khẩu</button>
                    <button type="submit" class="btn btn-secondary">Thoát</button>
                </div>
            </div>
        </div>
    </div>
</form>
