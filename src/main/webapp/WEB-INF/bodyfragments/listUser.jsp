<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@page pageEncoding="UTF-8" %><%--// đọc chữ tiếng việt--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div class="col-md-12" style="margin-top: 30px;margin: auto">
        <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
            <div class="m-portlet m-portlet--mobile "
                 style="margin: auto;width: 88%;margin-bottom: 10px;max-width: 1600px">
                <div class="m-portlet__head">
                    <div class="m-portlet__head-caption" style="margin: auto;">
                        <h2 class="m-portlet__head-text" style="color:black;font-size:2rem">
                            Danh Sách Người Dùng
                        </h2>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <%--    <div class="col-md-5" style="margin-top: 10px;margin-bottom: 10px">--%>

    <%--        <div>--%>
    <%--            <iframe src="https://calendar.google.com/calendar/embed?src=lut19mefheusa4ioesapbtog0k%40group.calendar.google.com&ctz=Asia%2FHo_Chi_Minh" style="border: 0" width="650" height="310" frameborder="0" scrolling="no"></iframe>--%>
    <%--        </div>--%>

    <%--    </div>--%>

</div>
<div>
    <c:choose>
        <c:when test="${param.get('status').equals('success')}">
            <p class='alert alert-success' role='alert' style="
    text-align: center;
    width: 25%;
    margin: auto;
    margin-bottom: 1%;
    font-size: 1.1rem;
">Thay đổi thông tin thành công</p>
        </c:when>
        <c:when test="${param.get('status').equals('del_success')}">
            <p class='alert alert-success' role='alert' style="
    text-align: center;
    width: 25%;
    margin: auto;
    margin-bottom: 1%;
    font-size: 1.1rem;
">Xóa thành công </p>
        </c:when>
    </c:choose>
</div>
<div class="row"></div>

<div class="col-md-12" style="margin-top: 30px;margin: auto">
    <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
        <div class="m-portlet m-portlet--mobile " style="margin: auto;width: 90%;max-width: 1600px">
            <div class="m-portlet__body">
                <!--begin: Datatable -->
                <table width="100%"
                       class="table table-striped- table-bordered table-hover table-checkable dataTable no-footer table"
                       style="overflow: scroll">
                    <thead>
                    <tr>

                        <th title="Field #1" data-field="Mã đơn" style="width: 4%">STT</th>
                        <th title="Field #3" data-field="Contact" style="width: 15%">Họ Tên</th>
                        <th title="Field #3" data-field="Contact" style="width: 15%">Số Điện Thoại</th>
                        <th title="Field #4" data-field="CarMake" style="width: 9%">Email</th>
                        <th title="Field #5" data-field="CarModel" style="width: 6%">Tên Account</th>
                        <th title="Field #6" data-field="Color" style="width: 15%">Phòng Ban</th>
                        <th title="Field #2" data-field="Type" style="width: 17%">Chức Vụ</th>
                        <th title="Field #7" data-field="DepositPaid" style="width: 11%">Role</th>
                        <th title="Field #7" data-field="DepositPaid" style="width: 5%"></th>
                        <th title="Field #7" data-field="DepositPaid" style="width: 5%"></th>

                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="userEntityDtos" items="${userEntityDtos}" varStatus="loopCounter">
                        <%--                        <c:if test="${userEntityDtos.deparment.deparmentId == 1}">--%>
                        <tr>

                            <td>${loopCounter.count}</td>
                            <td>${userEntityDtos.fullName}</td>
                            <td>${userEntityDtos.phoneNumber}</td>
                            <td>${userEntityDtos.email}</td>
                            <td>${userEntityDtos.userName}</td>
                            <td>${userEntityDtos.department.departmentName}</td>
                            <td>${userEntityDtos.jobName}</td>
                            <td><c:choose>
                                <c:when test="${userEntityDtos.role == 1}">
                                    Admin
                                </c:when>
                                <c:when test="${userEntityDtos.role == 2}">
                                    User
                                </c:when>
                                <c:when test="${userEntityDtos.role == 3}">
                                    Manager
                                </c:when>

                            </c:choose>


                            </td>

                            <td>
                                <a class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill"
                                   title="Chỉnh sửa">
                                    <i type="button" class=" la la-edit" data-toggle="modal"
                                       data-target="#exampleModal${loopCounter.count}"></i>
                                    <div class="modal fade" id="exampleModal${loopCounter.count}" tabindex="-1"
                                         role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document"
                                             style="width: 100%;margin-left: 15%;padding-top:60px">
                                            <form class="modal-content" style="width: 200%"
                                                  action="/updateUser/${userEntityDtos.userId}" method="post">
                                                <input hidden name="page" id="pageValue" value="${newOutput.page}">
                                                <div class="modal-header" style="margin: auto; width: 100%">
                                                    <h5 class="modal-title" id="exampleModalLabel1"
                                                        style="text-align: center;width: 100%;font-size: 1.5rem">Chi
                                                        Tiết</h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>

                                                <div class="modal-body"
                                                     style="color:black;margin-top: -20px;text-align: left">

                                                        <%--        < class="m-portlet__body">--%>

                                                    <div class="form-group m-form__group row" style="padding-top: 20px">

                                                        <label class="col-2 col-form-label">Họ
                                                            Tên</label>
                                                        <div class="col-2">
                                                            <div>
                                                                <label
                                                                        class="col-12 col-form-label">${userEntityDtos.fullName}</label>
                                                            </div>
                                                        </div>
                                                        <div class="col-2">
                                                        </div>
                                                        <label class="col-2 col-form-label">Tên
                                                            Account</label>
                                                        <div class="col-2">
                                                            <div>
                                                                <label
                                                                        class="col-12 col-form-label">${userEntityDtos.userName}</label>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class="form-group m-form__group row">
                                                        <label
                                                                class="col-2 col-form-label">Email</label>
                                                        <div class="col-2">
                                                            <div>
                                                                <label
                                                                        class="col-12 col-form-label">${userEntityDtos.email}</label>
                                                            </div>
                                                        </div>
                                                        <div class="col-2">
                                                        </div>
                                                            <%--                                                        <label for="example-text-input" class="col-2 col-form-label">Tên trưởng phòng</label>--%>
                                                            <%--                                                        <div class="col-2">--%>
                                                            <%--                                                            <div>--%>
                                                            <%--                                                                <label for="example-text-input" class="col-12 col-form-label1">${absence.fullNameOfDepartment}</label>--%>
                                                            <%--                                                            </div>--%>
                                                            <%--                                                        </div>--%>
                                                    </div>
                                                    <div class="form-group m-form__group row">
                                                        <label for="title" class="col-2 col-form-label">Vị Trí</label>
                                                        <div class="col-3">
                                                            <input class="form-control m-input" type="text"
                                                                   value="${userEntityDtos.jobName}" id="title"
                                                                   name="jobName">
                                                        </div>

                                                        <div class="col-1">
                                                        </div>

                                                    </div>

                                                    <div>
                                                        <div class="col-1">
                                                        </div>
                                                        <div class="col-1">
                                                        </div>
                                                        <div class="form-group m-form__group">
                                                            <label for="exampleSelect1">Chọn phòng ban</label>
                                                            <select class="form-control m-input m-input--solid"
                                                                    id="exampleSelect1" name="department" required>
                                                                <option id="input" class="form-control m-input"
                                                                        disabled>
                                                                    Chọn phòng ban
                                                                </option>
                                                                <c:forEach var="departments" items="${departments}">
                                                                    <option class="form-control m-input"
                                                                            value="${departments.departmentId}">${departments.departmentName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                        <div class="col-1">
                                                        </div>

                                                        <div class="form-group m-form__group">
                                                            <label for="exampleSelect1">Chọn Role</label>
                                                            <select class="form-control m-input m-input--solid"
                                                                    id="exampleSelect" name="appRole" required>
                                                                <option id="input" class="form-control m-input"
                                                                        disabled>
                                                                    Phân quyền
                                                                </option>
                                                                <c:forEach var="appRoles" items="${appRoles}">
                                                                    <option class="form-control m-input"
                                                                            value="${appRoles.roleId}">${appRoles.roleName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                    </div>

                                                </div>

                                                <div class="m-portlet__foot m-portlet__foot--fit">
                                                    <div class="m-form__actions">
                                                        <div class="row">
                                                            <button type="submit" class="btn btn-primary"
                                                                    aria-expanded="false"
                                                                    style="height: 40px;width:95px;margin: auto "
                                                                    onclick="return confirm('Bạn có muốn cập nhật thông tin không?'); ">
                                                                Cập nhật
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                    </div>
                                </a>
                            </td>

                            <td>
                                <form action="/deleteUser/${userEntityDtos.userId}" method="post">
                                    <button
                                            class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill"
                                            title="Xóa"
                                            onclick="return confirm('Bạn có muốn xóa tài khoản này không?');">
                                        <i class="la la-remove"></i>
                                        <input hidden name="page" id="paeValue" value="${newOutput.page}">
                                        <input hidden name="numOfList" id="paeVaue" value="${loopCounter.index}">
                                    </button>
                                </form>
                            </td>
                        </tr>
                        <%--                        </c:if>--%>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item"><a class="page-link" href="/getAllUserEntityEnable/1">first</a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/getAllUserEntityEnable/${newOutput.previousPage}">previous</a>
                        </li>
                        <li class="page-item"><a class="page-link" href="/getAllUserEntityEnable/${newOutput.nextPage}">next</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="/getAllUserEntityEnable/${newOutput.totalPage}">last</a></li>
                    </ul>
                </nav>

            </div>
        </div>
    </div>
</div>
</div>
<script>

</script>