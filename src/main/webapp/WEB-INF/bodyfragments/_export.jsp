<%@page pageEncoding="UTF-8" %><%--// đọc chữ tiếng việt--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="m-portlet m-portlet--mobile " style="margin: auto;width: 90%;max-width: 1600px">
    <div class="m-portlet__head">
        <div class="m-portlet__head-caption" style="margin: auto;">
            <h2 class="m-portlet__head-text" style="color: black">
                Tạo báo cáo theo tháng
            </h2>
        </div>

    </div>

    <div class="m-portlet__body">
        <div class="row" style="width: 100%;">
            <div class="m-portlet__head-tools" style="width: 100%;">

                <ul class="m-portlet__nav row" style="width: 100%;">
                    <form action="/export" class="row col-7" method="post">
                        <div class="form-group m-form__group row"
                             style="width: 80%;font-size: 1.3rem;font-weight: 400">
                            <label class="col-6 col-form-label">Danh sách đơn nghỉ theo tháng</label>
                            <div class="col-6">
                                <input class="form-control m-input" id="data" type="month" value="${date}" name="data" min="2022-01"
                                       required>
                            </div>
                        </div>
                        <div class="col-2" style="padding-top:5px;padding-left: 40px">
                            <button class="btn btn-accent m-btn m-btn--custom m-btn--pill m-btn--icon m-btn--air col"
                                    style="height: 40px;margin-left: 10%;width: 90px">
												<span>
													<i class="la la-search"></i>
												</span>
                            </button>
                        </div>
                    </form>

                    <c:if test="${post == 1 }">
                        <div class="col-2" style="padding-top:5px;padding-left: 40px">

                            <button class="btn btn-primary m-btn m-btn--custom m-btn--pill m-btn--icon m-btn--air col"
                                    style="height: 40px;margin-left: 10%;width: 180px" onclick="exportExcel()">Xuất excel
                            </button>

                        </div>
                        <div class="col-2"
                              style="padding-top:5px;padding-left: 40px">

                            <button class="btn btn-primary m-btn m-btn--custom m-btn--pill m-btn--icon m-btn--air col"
                                    style="height: 40px;margin-left: 10%;width: 180px" onclick="exportWord()">
                                Xuất word
                            </button>
                            <button hidden type="submit" id="click-word"></button>
                        </div>
                        <%--                    <button class="btn btn-primary m-btn m-btn--custom m-btn--pill m-btn--icon m-btn--air col"--%>
                        <%--                            style="height: 40px;margin-left: 10%;width: 180px" onclick="onclick1()">--%>


                    </c:if>
                </ul>
            </div>
        </div>

    </div>


</div>
</div>
<c:if test="${post == 1 }">
<div class="col-md-12" style="margin-top: 30px;margin: auto">
    <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">

        <div class="m-portlet m-portlet--mobile " style="margin: auto;width: 90%;max-width: 1600px">
<%--            <div>--%>
<%--
<%--            </div>--%>
    <div class="m-portlet__head">
        <div class="row" style="width: 100%;">
            <div class="m-portlet__head-tools" style="width: 100%; margin: auto">
                <h2 style="margin: auto"> Danh sách đơn tháng ${month} năm ${year}</h2>
            </div>
        </div>
    </div>
            <form class="m-portlet__body" id ="testData" method="post" action="/export/all-user">
                <!--begin: Datatable -->
                <input hidden name="data" value="${date}" id="export-excel">
                <table width="100%"
                       class="table table-striped- table-bordered table-hover table-checkable dataTable no-footer table"
                       style="overflow: scroll">
                    <thead>
                    <tr>
                        <th title="Field #1" data-field="Mã đơn" style="width: 6%">Số thứ tự</th>
                        <th title="Field #2" data-field="Owner" style="width: 18%">Tên nhân viên</th>
                        <th title="Field #4" data-field="Contact" style="width: 8%;">Từ ngày</th>
                        <th title="Field #5" data-field="CarMake" style="width:8%">Đến ngày</th>
                        <th title="Field #6" data-field="CarModel" style="width: 6%">Số ngày</th>
                        <th title="Field #3" data-field="Type" style="width: 18%">Người duyệt</th>
                        <th title="Field #8" data-field="DepositPaid">Lý do</th>
                        <th title="Field #8" data-field="DepositPaid" style="width: 9%">Kiểu nghỉ</th>

                        <%--                        <th title="Field #9" data-field="OrderDate" style="width: 6%">Chi tiết</th>--%>


                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="absence" items="${absence}" varStatus="loopCounter">

                        <fmt:formatDate var="startDate" value="${absence.startDate}" pattern="yyyy-MM-dd"/>
                        <fmt:formatDate var="endDate" value="${absence.endDate}" pattern="yyyy-MM-dd"/>
                        <tr>
                            <input hidden id="id-${loopCounter.count}">
                            <td>${loopCounter.count}</td>
                            <td>${absence.userName}</td>
                            <td>
                                <time><fmt:formatDate value="${absence.startDate}" pattern="dd-MM-yyyy"/></time>
                            </td>
                            <td>
                                <time><fmt:formatDate value="${absence.endDate}" pattern="dd-MM-yyyy "/></time>
                            </td>
                            <td><fmt:formatNumber value="${absence.dayOff}"></fmt:formatNumber></td>
                            <td>${absence.applicationReviewer}</td>
                            <td>${absence.description}</td>
                            <td>
                                <select name="type" class="form-control m-input"  required>
                                    <c:if test="${absence.type == 1}">
                                    <option class="form-control m-input" value="1" >Có phép</option>
                                    <option class="form-control m-input" value="2" >Không phép</option>
                                    </c:if>
                                    <c:if test="${absence.type == 2}">
                                        <option class="form-control m-input" value="2" >Không phép</option>
                                        <option class="form-control m-input" value="1" >Có phép</option>
                                    </c:if>
                                    <c:if test="${absence.type ==0}">
                                    <option value="" disabled selected></option>
                                    <option class="form-control m-input" value="1" >Có phép</option>
                                    <option class="form-control m-input" value="2" >Không phép</option>
                                    </c:if>
                                </select>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <input hidden name ="status" id="status">
                <button id="click-excel" hidden type="submit"></button>
<%--                <button id="click-excel" hidden type="submit"></button>--%>
            </form>
        </div>
    </div>
</div>
</c:if>
</div>

<script>
    function exportExcel() {
        // var x = document.getElementById('data').value;
        // document.getElementById('export-excel').setAttribute('value', x);
        document.getElementById('status').setAttribute('value',1);
        document.getElementById('click-excel').click();
    }

    function exportWord() {
        document.getElementById('status').setAttribute('value',2);
        document.getElementById('click-excel').click();
    }

    // function onClick(){
    //     console.log(document.getElementsBy())
    // }
    function onclick1() {
        document.getElementById('test').click();
    }

    // var data = document.getElementById("data").value;
    // console.log(data);
    // alert("Thành công");
    //
    // $.ajax({
    //     type:"post",
    //     url:"/export/all-user",
    //     dataType:"json",
    //     contentType:'application/json',
    //     success:function (result) {
    //         alert("hello")
    //     },
    //     data: JSON.stringify(data)
    // });
    function ok() {
        alert("Thành công");
    }
</script>