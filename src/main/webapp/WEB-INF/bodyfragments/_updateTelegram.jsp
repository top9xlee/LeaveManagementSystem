<%@page pageEncoding="UTF-8" %><%--// đọc chữ tiếng việt--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row" style="padding-top: 20px">
    <div class = "col-md-4 "style="padding-left: 25px">
        <div class="m-portlet m-portlet--tab middle">
            <div class="m-portlet__head">
                <div class="m-portlet__head-caption">
                    <div class="m-portlet__head-title">
												<span class="m-portlet__head-icon m--hide">
													<i class="la la-gear"></i>
												</span>
                        <h3 class="m-portlet__head-text">
                            Cập nhật Group
                        </h3>
                    </div>
                </div>
            </div>

            <!--begin::Form-->
            <form action="/updateTelegram" method="post" id="form-update"  class="m-form m-form--fit m-form--label-align-right" enctype="multipart/form-data">
                <div class="m-portlet__body">
                    <input type="hidden" name="telegramId" value="${telegram.telegramId}">
                    <div class="form-group m-form__group row">
                        <label for="DepartmentName" class="col-3 col-form-label">Tên Group</label>
                        <div class="col-8">
                            <input class="form-control m-input" type="text"  id="DepartmentName" name = "telegramName" value=" ${telegram.telegramName} " required>
                        </div>
                    </div>
                    <div class="form-group m-form__group row">
                        <label  class="col-3 col-form-label">Api Token</label>
                        <div class="col-8">
                            <input class="form-control m-input" type="text"  id="DpartmentName" name = "apiToken" value=" ${telegram.apiToken} " required>
                        </div>
                    </div>
                    <div class="form-group m-form__group row">
                        <label class="col-3 col-form-label">Chat Id</label>
                        <div class="col-8">
                            <input class="form-control m-input" type="text"  id="DepartentName" name = "chatId" value=" ${telegram.chatId} " required>
                        </div>
                    </div>


                </div>
                <div class="m-portlet__foot m-portlet__foot--fit">
                    <div class="m-form__actions">
                        <div class="row">
                            <div class="col-2">
                            </div>
                            <div class="col-10">
                                <button type="submit" class="btn btn-success">Submit</button>
                                <button type="reset" class="btn btn-secondary"><a href="/telegram">Trở về</a></button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-md-8 " style="padding-left: 0px; padding-right: 25px">

        <!--begin:: Widgets/Sales States-->
        <div class="m-portlet m-portlet--full-height ">
            <div class="m-portlet__head">
                <div class="m-portlet__head-caption">
                    <div class="m-portlet__head-title">
                        <h3 class="m-portlet__head-text">
                            Danh sách Group
                        </h3>
                    </div>
                </div>
            </div>
            <div class="m-portlet__body">

                <div class="m-widget6__body">
                    <table width="100%"class="table table-striped- table-bordered table-hover table-checkable dataTable no-footer table" style="overflow: scroll" >
                        <thead>
                        <tr>
                            <th title="Field #1" data-field="Số thứ tự">STT</th>
                            <th title="Field #2" data-field="Owner">Tên group</th>
                            <th title="Field #3" data-field="Type">Api Token</th>
                            <th title="Field #3" data-field="Type">Chat Id</th>
                            <th title="Field #4" data-field="Contact">Sửa</th>
                            <th title="Field #5" data-field="CarMake">Xóa</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="tele" items="${telegramList}" varStatus="loopCounter" >


                            <tr>
                                <td >${loopCounter.count}</td>
                                <td >${tele.telegramName}</td>
                                <td >${tele.apiToken}</td>
                                <td >${tele.chatId}</td>
                                <td > <a href="/update-telegram/${tele.telegramId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Sửa">
                                    <i class="la la-edit"></i></a></td>
                                <td ><a href="/delete-telegram/${tele.telegramId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Xóa"
                                        onclick="return confirm('Bạn có muốn xóa group này không?');">    <i class="la la-remove"></i></a></td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

            </div>
        </div>
    </div>

    <!--end:: Widgets/Sales States-->
</div>
</div>