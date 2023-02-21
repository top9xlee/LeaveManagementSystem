<%@page pageEncoding="UTF-8" %><%--// đọc chữ tiếng việt--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row" style="padding-top: 20px">
<div class = "col-md-5" style="padding-left: 4%">
    <div>
        <c:choose>
            <c:when test="${param.get('status').equals('success')}">
                <p class='alert alert-success' role='alert' style="
    text-align: center;
    width: max-content      ;
    margin: auto;
    margin-bottom: 4%;
    background-color: #0a8cf0;
    font-size: 1.1rem;
">Tạo mới thành công</p>
            </c:when>
            <c:when test="${param.get('status').equals('update_success')}">
                <p class='alert alert-success' role='alert' style="
    text-align: center;
    width: max-content      ;
    margin: auto;
    margin-bottom: 4%;
    background-color: #0a8cf0;
    font-size: 1.1rem;
">Cập nhật thành công</p>
            </c:when>
            <c:when test="${param.get('status').equals('del_success')}">
                <p class='alert alert-success' role='alert' style="
    text-align: center;
    width: max-content      ;
    margin: auto;
    margin-bottom: 4%;
    background-color: #0a8cf0;
    font-size: 1.1rem;
">Xóa thành công</p>
            </c:when>
        </c:choose>
    </div>
    <div class="m-portlet m-portlet--tab middle">
        <div class="m-portlet__head">
            <div class="m-portlet__head-caption">
                <div class="m-portlet__head-title text-center">
												<span class="m-portlet__head-icon m--hide">
													<i class="la la-gear"></i>
												</span>
                    <h3 class="m-portlet__head-text text-center">
                        Tạo Phòng Ban
                    </h3>
                </div>
            </div>
        </div>

        <!--begin::Form-->
        <form action="createDepartment" method="post" id="form-create"  class="m-form m-form--fit m-form--label-align-right" enctype="multipart/form-data">
            <div class="m-portlet__body">

                <div class="form-group m-form__group row">
                    <label for="DepartmentName" class="col-4 col-form-label">Tên Phòng/Ban</label>
                    <div class="col-7">
                        <input class="form-control m-input" type="text" id="DepartmentName" name="departmentName" required>
                    </div>
                </div>
<%--                <div class="form-group m-form__group row">--%>
<%--                    <label for="HeadId" class="col-3 col-form-label">Mã trưởng phòng</label>--%>
<%--                    <div class="col-8">--%>
<%--                        <input class="form-control m-input" type="text" id="HeadId" name="headId">--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="form-group m-form__group row">
                    <label  class="col-4 col-form-label">Trưởng phòng</label>
                    <div class = "col-7">
                        <select name="headId" class="form-control m-input" required>
                            <option id="input" class="form-control m-input" disabled>Chọn tên trưởng phòng</option>
                            <c:forEach var="user" items="${users}">
                                <option  class="form-control m-input" value="${user.userId}">${user.userId} | ${user.fullName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

            </div>
            <div class="m-portlet__foot m-portlet__foot--fit">
                <div class="m-form__actions">
                    <div class="row">
                        <div class="col-1">
                        </div>
                        <div class="col-11">
                            <button type="submit" class="btn btn-success">Tạo mới</button>
<%--                            <button type="reset" class="btn btn-secondary">Reset</button>--%>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="col-md-6"style="margin-left: 5%" >

    <!--begin:: Widgets/Sales States-->
    <div class="m-portlet m-portlet--full-height ">
        <div class="m-portlet__head">
            <div class="m-portlet__head-caption text-center">
                <div class="m-portlet__head-title">
                    <h3 class="m-portlet__head-text text-center">
                        Danh sách phòng ban
                    </h3>
                </div>
            </div>

        </div>
        <div class="m-portlet__body"style="color: #3f4047">

    <table width="100%"class="table table-striped- table-bordered table-hover table-checkable dataTable no-footer table" style="overflow: scroll" >
        <thead>
        <tr>
            <th title="Field #1" data-field="Số thứ tự">STT</th>
            <th title="Field #2" data-field="Owner">Tên phòng ban</th>
            <th title="Field #3" data-field="Type">Tên trưởng ban</th>
            <th title="Field #4" data-field="Contact">Sửa</th>
            <th title="Field #5" data-field="CarMake">Xóa</th>
<%--            <th title="Field #6" data-field="CarModel">Số ngày</th>--%>
<%--            <th title="Field #7" data-field="Color">Ngày gửi đơn</th>--%>
<%--            <th title="Field #8" data-field="DepositPaid">Tình trạng đơn</th>--%>
<%--            <th title="Field #9" data-field="OrderDate">Chi tiết</th>--%>


        </tr>
        </thead>
        <tbody>
        <c:forEach var="depa" items="${departments}" varStatus="loopCounter" >


            <tr>
                <td >${loopCounter.count}</td>
                <td >${depa.departmentName}</td>
                <td >${depa.headName}</td>
                <td > <a href="/update-department/${depa.departmentId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Sửa">
                    <i class="la la-edit"></i></a></td>
                <td ><a href="/delete-department/${depa.departmentId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Xóa"
                        onclick="return confirm('Bạn có muốn xóa phòng ban này không?');">    <i class="la la-remove"></i></a></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>

    <!--end:: Widgets/Sales States-->
</div>
</div>
</div>
