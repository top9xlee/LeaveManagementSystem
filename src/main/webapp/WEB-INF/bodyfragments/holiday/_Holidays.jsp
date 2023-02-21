<%@page pageEncoding="UTF-8" %><%--// đọc chữ tiếng việt--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<c:choose>--%>
<%--    <c:when test="${param.get('status').equals('success')}">--%>
<%--        <p class="message-register" style="color: rgb(22, 161, 255); font-size: 16px;text-align: center">Tạo ngày nghỉ  thành công.</p>--%>
<%--    </c:when>--%>
<%--    <c:when test="${param.get('error').equals('fail')}">--%>
<%--        <p class="message-register" style="color: rgb(20, 157, 252); font-size: 16px;text-align: center">Xóa ngày nghỉ thành công.</p>--%>
<%--    </c:when>--%>
<%--</c:choose>--%>
<div>
    <c:choose>
        <c:when test="${param.get('status').equals('success')}">
            <p class='alert alert-success' role='alert' style="
    text-align: center;
    width: 25%;
    margin: auto;
    font-size: 1.1rem;
">Tạo mới thành công</p>
        </c:when>
        <c:when test="${param.get('status').equals('del_success')}">
            <p class='alert alert-success' role='alert' style="
    text-align: center;
    width: 25%;
    margin: auto;
    font-size: 1.1rem;
">Xóa thành công</p>
        </c:when>
    </c:choose>
</div>
<div class="row" style="padding-left: 50px;padding-right: 50px;padding-top: 20px">
    <div class="col-md-5" style="padding-left: 10px">
        <div class="m-portlet m-portlet--full-height ">
<%--            <div class="m-portlet__head">--%>
<%--                <div class="m-portlet__head-caption">--%>
<%--                    <div class="m-portlet__head-title">--%>
<%--                        <h3 class="m-portlet__head-text">--%>
<%--                            Danh sách nghỉ lễ--%>
<%--                        </h3>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--            </div>--%>

            <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--deskto  p m-body">
                <div class="m-portlet m-portlet--mobile "style="width: 100%">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
                                <h1 class="m-portlet__head-text">
                                    Danh sách nghỉ
                                </h1>
                            </div>
                        </div>
                        <div class="row">
                        <div class="m-portlet__head-tools">

                            <ul class="m-portlet__nav">
                                <li class="m-portlet__nav-item">
                                    <a href="#" class="btn btn-accent m-btn m-btn--custom m-btn--pill m-btn--icon m-btn--air" data-toggle="modal" data-target="#exampleModal1">
												<span>
													<i class="la la-plus"></i>
													<span>Thêm ngày mới</span>
												</span>
                                    </a>
                                    <div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1" aria-hidden="true">
                                        <div class="modal-dialog centered" role="document">
                                            <div class="modal-content" style="width: 750px;margin-left:-150px;margin-top: 20%;">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel1">Thêm ngày nghỉ linh động</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="/createWeekend?page=${newOutput.page}" method="post" id="form-create1"  class="m-form m-form--fit m-form--label-align-right" enctype="multipart/form-data">
                                                        <div class="m-portlet__body">


                                                            <div class="form-group m-form__group row">
                                                                <label for="DepartmentName4" class="col-5 col-form-label">Ngày nghỉ</label>
                                                                <div class="col-7">
                                                                    <input class="form-control m-input" type="date" min="2022-01-01"  id="DepartmentName4" name = "startDate" required>
                                                                </div>
                                                            </div>
                                                            <div class="form-group m-form__group row">
                                                                <label for="HeadId" class="col-5 col-form-label">Tên</label>
                                                                <div class="col-7">
                                                                    <input class="form-control m-input" type="text" id="HeadId5" name = "description" required>
                                                                </div>
                                                            </div>
                                                            <input hidden name="status" value="1">


                                                        </div>
                                                        <div class="m-portlet__foot m-portlet__foot--fit">
                                                            <div class="m-form__actions">
                                                                <div class="row">
                                                                    <div class="col-5">
                                                                    </div>
                                                                    <div class="col-5">
                                                                        <button type="submit" class="btn btn-success">Submit</button>
                                                                        <button type="reset" class="btn btn-secondary">Reset</button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                                </div>
                                    </div>
                                </li>

                            </ul>
                        </div>
                        </div>
                    </div>
                    <div class="m-portlet__body">
                        <!--begin: Datatable -->
                        <table width="100%"class="table table-striped- table-bordered table-hover table-checkable dataTable no-footer table" style="overflow: scroll" >
                            <thead>
                            <tr>
                                <th title="Field #1" data-field="Mã đơn" style="width: 15%">Số thứ tự </th>
                                <th title="Field #4" data-field="Contact" style="width: 30%">Ngày</th>
                                <th title="Field #3" data-field="Type"style="width: 35%">Lý do</th>
                                <th title="Field #9" data-field="OrderDate" style="width: 10%"></th>
<%--                                <th title="Field #10" data-field="Status" style="width: 10%"> </th>--%>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="weekend" items="${weekend}" varStatus="loopCounter">
                                <tr>
                                    <td >${loopCounter.count}</td>

                                    <td ><time><fmt:formatDate value="${weekend.startDate}" pattern="dd-MM-yyyy" /></time> </td>
                                    <td >${weekend.description}</td>

<%--                                    <td >    <a href="/update-holiday/${weekend.holidayId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Sửa">--%>
<%--                                        <i class="la la-edit"></i></a>--%>
<%--                                    </td>--%>
                                    <td > <a href="/delete-holiday/${weekend.holidayId}?page=${newOutput.page}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Xóa"
                                             onclick="return confirm('Bạn có muốn xóa ngày nghỉ này không?');">
                                        <i class="la la-remove"></i>
                                </a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">
                                <li class="page-item"><a class="page-link" href="/create-holiday">first</a></li>
                                <li class="page-item"><a class="page-link"
                                                         href="/create-holiday?page=${newOutput.previousPage}">previous</a>
                                </li>
                                <li class="page-item"><a class="page-link" href="/create-holiday?page=${newOutput.nextPage}">next</a>
                                </li>
                                <li class="page-item"><a class="page-link"
                                                         href="/create-holiday?page=${newOutput.totalPage}">last</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
<div class="col-md-7" style="">
    <div class="m-portlet m-portlet--full-height ">
        <%--            <div class="m-portlet__head">--%>
        <%--                <div class="m-portlet__head-caption">--%>
        <%--                    <div class="m-portlet__head-title">--%>
        <%--                        <h3 class="m-portlet__head-text">--%>
        <%--                            Danh sách nghỉ lễ--%>
        <%--                        </h3>--%>
        <%--                    </div>--%>
        <%--                </div>--%>

        <%--            </div>--%>

        <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--deskto  p m-body">
            <div class="m-portlet m-portlet--mobile "style="width: 100%">
                <div class="m-portlet__head">
                    <div class="m-portlet__head-caption">
                        <div class="m-portlet__head-title">
                            <h1 class="m-portlet__head-text">
                                Danh sách ngày lễ
                            </h1>
                        </div>
                    </div>
                    <div class="m-portlet__head-tools">
                        <ul class="m-portlet__nav">
                            <li class="m-portlet__nav-item">
                                <a href="#" class="btn btn-accent m-btn m-btn--custom m-btn--pill m-btn--icon m-btn--air" data-toggle="modal" data-target="#exampleModal">
												<span>
													<i class="la la-plus"></i>
													<span>Thêm ngày mới</span>
												</span>
                                </a>
                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content" style="width: 750px;margin-left:-150px;margin-top: 20%;">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Thêm ngày nghỉ lễ</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form action="/createHoliday" method="post" id="form-create"  class="m-form m-form--fit m-form--label-align-right" enctype="multipart/form-data">
                                                    <div class="m-portlet__body">
                                                        <div class="form-group m-form__group row">
                                                            <label  class="col-5 col-form-label">Ngày bắt đầu ngày lễ</label>
                                                            <div class="col-7">
                                                                <input class="form-control m-input" type="date"  min="2022-01-01" id="dayIn" name = "startDate" required onclick="enableEndDate()">
                                                            </div>
                                                        </div>
                                                        <div class="form-group m-form__group row">
                                                            <label  class="col-5 col-form-label">Ngày kết thúc ngày lễ</label>
                                                            <div class="col-7">
                                                                <input class="form-control m-input" onclick="myFunction()" type="date"  id="dayOut" name = "endDate" required disabled>
                                                            </div>
                                                        </div>
                                                        <div class="form-group m-form__group row">
                                                            <label for="HeadId" class="col-5 col-form-label">Tên ̉</label>
                                                            <div class="col-7">
                                                                <input class="form-control m-input" type="text" id="HeadId" name = "description" required>
                                                            </div>
                                                        </div>
                                                        <input hidden name="status" value="2">


                                                    </div>
                                                    <div class="m-portlet__foot m-portlet__foot--fit">
                                                        <div class="m-form__actions">
                                                            <div class="row">
                                                                <div class="col-5">
                                                                </div>
                                                                <div class="col-5">
                                                                    <button type="submit" class="btn btn-success">Submit</button>
                                                                    <button type="reset" class="btn btn-secondary">Reset</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </li>

                        </ul>
                    </div>
                </div>
                <div class="m-portlet__body">
                    <!--begin: Datatable -->
                    <table width="100%"class="table table-striped- table-bordered table-hover table-checkable dataTable no-footer table" style="overflow: scroll" >
                        <thead>
                        <tr>
                            <th title="Field #1" data-field="Mã đơn" style="width: 12%">Số thứ tự </th>

                            <th title="Field #4" data-field="Contact" style="width: 20%">Từ ngày</th>
                            <th title="Field #5" data-field="CarMake" style="width: 20%">Đến ngày</th>
                            <th title="Field #3" data-field="Type">Lý do</th>
                            <th title="Field #10" data-field="Status" style="width: 5%"> </th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="holidays" items="${holiday}" varStatus="loopCounter">
                            <tr>
                                <td >${loopCounter.count}</td>

                                <td ><time><fmt:formatDate value="${holidays.startDate}" pattern="dd-MM-yyyy" /></time> </td>
                                <td ><time><fmt:formatDate value="${holidays.endDate}" pattern="dd-MM-yyyy " /></time></td>


                                <td >${holidays.description}</td>
                                <td > <a href="/delete-holiday/${holidays.holidayId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Xóa"
                                         onclick="return confirm('Bạn có muốn xóa ngày nghỉ này không?');">
                                    <i class="la la-remove"></i>
                                </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    function myFunction() {
        var dayIn = document.getElementById("dayIn").value
        if(dayIn === ''){
            alert("Xin hãy nhập ngày bắt đầu nghỉ trước");
            document.getElementById("dayOut").disabled = true;
            return;
        }
        var d = new Date( dayIn );
        var month = d.getMonth()+1;
        var year = d.getFullYear();
        var f = new Date(year, month , 0);
        var localDate = f.toLocaleDateString('en-CA');
        document.getElementById("dayOut").setAttribute('min',dayIn);

    };
    function enableEndDate() {
        document.getElementById("dayOut").disabled = false;


    };
</script>
    <!--end:: Widgets/Sales States-->