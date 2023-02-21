<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page pageEncoding="UTF-8" %><%--// đọc chữ tiếng việt--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div class="col-md-12" style="margin-top: 30px;margin: auto"  >
        <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
            <div class="m-portlet m-portlet--mobile "style="margin: auto;width: 88%;margin-bottom: 10px;max-width: 1600px">

                <div class="m-portlet__head">
                    <div class="m-portlet__head-caption" style="margin: auto;">
                        <h2 class="m-portlet__head-text" style="color:black;font-size:2rem">
                            Lịch sử ngày nghỉ
                        </h2>
                    </div>
                </div>
                <div class="m-portlet__body">
                    <div  >
                        <c:if test="${param.get('status').equals('success')}">
                            <div class ='alert alert-success' role='alert' style="
    text-align: center;
    width: 25%;
    margin: auto;
    font-size: 1.1rem;
">Tạo đơn thành công</div>
                        </c:if>
                        <c:if test="${param.get('status').equals('update_success')}">
                            <div class ='alert alert-success' role='alert' style="
    text-align: center;
    width: 25%;
    margin: auto;
    font-size: 1.1rem;
">Cập nhật đơn thành công</div>
                        </c:if>
                        <c:if test="${param.get('status').equals('del_success')}">
                            <div class ='alert alert-success' role='alert' style="
    text-align: center;
    width: 25%;
    margin: auto;
    font-size: 1.1rem;
">Xóa đơn thành công</div>
                        </c:if>
                        <c:if test="${param.get('status').equals('hide_success')}">
                            <div class ='alert alert-success' role='alert' style="
    text-align: center;
    width: 25%;
    margin: auto;
    font-size: 1.1rem;
">Hủy đơn thành công</div>
                        </c:if>
                    </div>
                    <div class = "row">
                        <div class="col-md-9" style="padding-top: 15px">
                            <div class="form-group m-form__group row">
                                <label  class="col-4 col-form-label1 " style="padding-left: 9% ">Tên của bạn</label>
                                <div class="col-7">
                                    <input disabled class="form-control m-input" type="text" value="${users.fullName}" }>
                                </div>
                            </div>
                            <div class="form-group m-form__group row">
                                <label  class="col-4 col-form-label1" style="padding-left: 9%;">Vị trí công việc</label>
                                <div class="col-7">
                                    <input disabled class="form-control m-input" type="text" value="${users.jobName}" }>
                                </div>
                            </div>

                        </div>
                        <div class="col-md-3">

<c:if test="${pageContext.request.isUserInRole('ROLE_MANAGER')}">
                                <div style="margin: auto"><button type="button" class="btn btn-outline-primary" style="width: 180px; margin: 2px"><a href ="/department" style="color: #0f0f11">
                                   Số đơn chưa duyệt: ${users.numberOfNotEnable} </a></button></div>
</c:if>

<%--                                <div style="margin: auto"><button onclick="sweetalertclick()"  type="button" class="btn btn-outline-primary" style="width: 180px; margin: 2px"  >Try me!</button></div>--%>

<%--                            <div><button type="button" class="btn btn-outline-primary" style="width: 130px; margin: 2px"><a href = "/list-posted/${department.departmentId}" style="color: #0f0f11">Tất cả đơn </a></button></div>--%>
                            <div style="margin: auto"><button type="button" class="btn btn-outline-primary" style="width: 180px; margin: 2px"><a href ="/create-absence" style="color: #0f0f11">Tạo đơn nghỉ phép </a></button></div>
<%--                            <div><button type="button" class="btn btn-outline-primary" style="width: 130px; margin: 2px"><a href =  "/list-enabled/${department.departmentId}"style="color: #0f0f11">Đã duyệt</a></button></div>--%>
<%--                            <div><button type="button" class="btn  btn-outline-primary" style="width: 130px; margin: 2px"><a href ="/list-rejected/${department.departmentId}"style="color: #0f0f11">Từ chối </a></button></div>--%>
                        </div>
                        <!--end: Search Form -->
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

<div class="col-md-12" style="margin-top: 30px;margin: auto">
    <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
        <div class="m-portlet m-portlet--mobile "style="margin: auto;width: 90%;max-width: 1600px" >
            <div class="m-portlet__body">
                <!--begin: Datatable -->
                <table width="100%"class="table table-striped- table-bordered table-hover table-checkable dataTable no-footer table" style="overflow: scroll" >
                    <thead>
                    <tr>

                        <th title="Field #1" data-field="Mã đơn"style="width: 4%">STT</th>

                        <th title="Field #3" data-field="Contact"style="width: 9%">Từ ngày</th>
                        <th title="Field #4" data-field="CarMake"style="width: 9%">Đến ngày</th>
                        <th title="Field #5" data-field="CarModel"style="width: 6%">Số ngày</th>
                        <th title="Field #6" data-field="Color" style="width: 9%">Ngày gửi đơn</th>
                        <th title="Field #2" data-field="Type"style="width: 22%">Tiêu đề</th>
                        <th title="Field #7" data-field="DepositPaid" style="width: 11%">Trạng thái đơn</th>
                        <th title="Field #8" data-field="DepositPaid" style="width: 10%">Ngày duyệt đơn</th>
                        <th title="Field #9" data-field="DepositPaid" style="margin: auto">Người duyệt </th>
                        <th title="Field #9" data-field="DepositPaid" style="width: 3%"> </th>
                        <th title="Field #9" data-field="DepositPaid" style="width: 3%"> </th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="absens" items="${absence}" varStatus="loopCounter">
                        <tr>
                            <td >${loopCounter.count}</td>

                            <td ><time><fmt:formatDate value="${absens.startDate}" pattern="dd-MM-yyyy " /></time> </td>
                            <td ><time><fmt:formatDate value="${absens.endDate}" pattern="dd-MM-yyyy" /></time></td>
                            <td ><fmt:formatNumber value="${absens.dayOff}"></fmt:formatNumber></td>
                            <td ><time><fmt:formatDate value="${absens.createDate}" pattern="dd-MM-yyyy " /></time></td>
                            <td >${absens.title}</td>
                            <td >  <c:if test="${absens.status ==0}">
                                <span class=" m-badge  m-badge--danger m-badge--wide" style="margin: auto">Chưa duyệt</span>
                            </c:if>
                                <c:if test="${absens.status ==1}">
                                    <span class=" m-badge  m-badge--info m-badge--wide"style="margin: auto">Đã duyệt</span>
                                </c:if>
                                <c:if test="${absens.status ==2}">
                                    <span class=" m-badge  m-badge--brand m-badge--wide"style="margin:auto">Từ chối</span>
                                </c:if></td>

                            <td ><time><fmt:formatDate value="${absens.enableDate}" pattern="dd-MM-yyyy " /></time></td>
                            <td>
                                    ${absens.applicationReviewer}
                                 </td>
                            <td>
                                <c:if test="${absens.status ==0}">
                                    <a class = "m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Chỉnh sửa">
                                        <i type="button" class=" la la-edit" data-toggle="modal" data-target="#exampleModal${loopCounter.count}"></i>
                                        <div class="modal fade" id="exampleModal${loopCounter.count}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document" style="width: 100%;margin-left: 17%;padding-top:60px">
                                                <form class="modal-content" style="width: 200%" action="/updateAbsence" method="post">
                                                    <input hidden name="page" id="paeValue" value="${newOutput.page}">
                                                    <div class="modal-header"style="margin: auto; width: 100%">
                                                        <h5 class="modal-title" id="exampleModalLabel1" style="text-align: center;width: 100%;font-size: 1.5rem">Chi tiết đơn nghỉ</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <input hidden value="${absens.absenceId}" name ="absenceId" >
                                                    <div class="modal-body" style = "color:black;margin-top: -20px;text-align: left">

                                                            <%--        < class="m-portlet__body">--%>

                                                        <div class="form-group m-form__group row" style="padding-top: 20px">

                                                            <label for="example-text-input" class="col-2 col-form-label">Tên người gửi</label>
                                                            <div class="col-2">
                                                                <div>
                                                                    <label for="example-text-input" class="col-12 col-form-label">${absens.userName}</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-2">
                                                            </div>
                                                            <label for="example-text-input" class="col-2 col-form-label">Chức danh công việc</label>
                                                            <div class="col-2">
                                                                <div>
                                                                    <label for="example-text-input" class="col-12 col-form-label">${absens.jobName}</label>
                                                                </div>
                                                            </div>

                                                        </div>
<%--                                                        <div class="form-group m-form__group row">--%>
<%--                                                            <label for="example-text-input" class="col-2 col-form-label">Gửi đến ban/phòng</label>--%>
<%--                                                            <div class="col-2">--%>
<%--                                                                <div>--%>
<%--                                                                    <label for="example-text-input" class="col-12 col-form-label">${absens.departmentName}</label>--%>
<%--                                                                </div>--%>
<%--                                                            </div>--%>
<%--                                                            <div class="col-2">--%>
<%--                                                            </div>--%>
<%--                                                                                                                        <label for="example-text-input" class="col-2 col-form-label">Tên trưởng phòng</label>--%>
<%--                                                                                                                        <div class="col-2">--%>
<%--                                                                                                                            <div>--%>
<%--                                                                                                                                <label for="example-text-input" class="col-12 col-form-label1">${absence.fullNameOfDepartment}</label>--%>
<%--                                                                                                                            </div>--%>
<%--                                                                                                                        </div>--%>
<%--                                                        </div>--%>

                                                        <div class="form-group m-form__group row">
                                                            <label for="title" class="col-2 col-form-label">Tiêu đề</label>
                                                            <div class="col-3">
                                                                <input  class="form-control m-input" type="text" value="${absens.title}"  id="title" name = "title">
                                                            </div>
                                                            <div class ="col-1">

                                                            </div>
                                                            <label for="example-text-input" class="col-2 col-form-label">Người phụ trách</label>
                                                            <div class="col-2">
                                                                <div>
                                                                    <label  class="col-12 col-form-label" style="text-align: left"
                                                                            style="text-align: left">${absens.namePersonInCharge}</label>
                                                                </div>
                                                            </div>
                                                            <input hidden name = "id" value="${absens.absenceId}">

                                                        </div>
                                                                <div class="form-group m-form__group row" style = "color:black">
                                                                    <label for="example-text-input" class="col-2 col-form-label">Lí do chi tiết</label>
                                                                    <div class="col-9">
                                                                        <textarea class="form-control m-input" name ="description"  placeholder="Lý do xin nghỉ" id="example-text-input" style="height: 150px">${absens.description} </textarea>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group m-form__group row" style = "color:black">
                                                                    <label for="dayIn" class="col-2 col-form-label" >Ngày bắt đầu nghỉ</label>
                                                                    <div class="col-2">
                                                                        <input  class="form-control m-input" onclick="enableEndDate()" value="<fmt:formatDate value="${absens.startDate}" pattern="yyyy-MM-dd"/>" type="date"  id="dayIn" name ="startDate" required>
                                                                    </div>

                                                                    <label for="dayOut" class="col-2 col-form-label" >Ngày kết thúc nghỉ</label>
                                                                    <div class="col-2">
                                                                        <input  class="form-control m-input" type="date" onclick="myFunction()" value="<fmt:formatDate value="${absens.endDate}" pattern="yyyy-MM-dd"/>"  id="dayOut" required name ="endDate" >
                                                                    </div>
                                                                    <div  id ="dayOff" class="col-3 row">
                                                                        <label for="example-text-input" class="col-6 col-form-label">Số ngày </label>
                                                                        <div class="col-6">
                                                                            <label id="daysOff" for="example-text-input" class="col-12 col-form-label"><fmt:formatNumber  value= "${absens.dayOff}"></fmt:formatNumber></label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="form-group m-form__group row">
                                                                    <label for="dayIn" class="col-2 col-form-label"
                                                                           style="text-align: left!important;padding-left: 50px;"></label>
                                                                    <div class="col-2">
                                                                        <select name="typeFromDate" id="fromDate"  onclick="enableToDate()" class="form-control m-input"
                                                                                required>
                                                                            <option id="input1" class="form-control m-input" disabled selected value="">Chọn buổi</option>
                                                                            <option class="form-control m-input" value="1">Buổi sáng</option>
                                                                            <option class="form-control m-input" value="2">Buổi chiều</option>
                                                                        </select>
                                                                    </div>

                                                                    <label for="dayOut" class="col-2 col-form-label"
                                                                           style="text-align: left!important;padding-left: 50px;"></label>
                                                                    <div class="col-2">
                                                                        <select name="typeToDate" id="toDate" class="form-control m-input" onclick="validateToDate()" required
                                                                                disabled>
                                                                            <option class="form-control m-input" disabled selected value="">Chọn buổi</option>
                                                                            <option id="toDateMidDay" class="form-control m-input" value="1">Buổi sáng</option>
                                                                            <option id="toDateEndDay" class="form-control m-input" value="2">Buổi chiều
                                                                            </option>
                                                                        </select>
                                                                    </div>

                                                                </div>
                                                    </div>




                                                    <div class="m-portlet__foot m-portlet__foot--fit">
                                                        <div class="m-form__actions">
                                                            <div class="row" >
                                                                    <button type="submit" class="btn btn-primary"  aria-expanded="false" style="height: 40px;width:95px;margin: auto " onclick="return confirm('Bạn có muốn cập nhật đơn này không?'); ">Cập nhật</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>

                                        </div>
                                    </a>
                                </c:if>
                                <c:if test="${absens.status ==2}">
                                <a class = "m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill"title="Xem lí do">
                                    <i type="button" class=" la la-angle-down" data-toggle="modal" data-target="#exampleModal${loopCounter.count}"></i>
                                    <div class="modal fade" id="exampleModal${loopCounter.count}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document"style ="padding-top: 30px;">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Lí do</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                        <%--                                                            <input hidden value="${absens.postId}" name="postId">--%>
                                                    <div class="form-group row">
                                                        <div class="col-md-6">
                                                            <label class="col-form-label-modal ">Người duyệt đơn:</label>
                                                            <text disabled class="form-control " id="text" name="description">${absens.applicationReviewer}</text>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="col-form-label-modal ">Duyệt vào ngày</label>
                                                            <text disabled class="form-control " id="text" name="description"><time><fmt:formatDate value="${absens.enableDate}" pattern="dd-MM-yyyy " /></time></text>
                                                        </div>
                                                    </div>

                                                        <%--                                                    <form method="absence" enctype="multipart/form-data" action="/note">--%>

                                                        <%--                                                            <input hidden value="${absens.postId}" name="postId">--%>
                                                    <div class="form-group">
                                                        <label for="message-text" class="col-form-label-modal">Lí do:</label>
                                                        <textarea disabled class="form-control" id="message-text" name="description" style="height: 100px">${absens.note}</textarea>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                        <%--                                                            <button type="reset" class="btn btn-secondary">Reset</button>--%>
                                                        <%--                                                            <button type="submit" class="btn btn-primary">Send message</button>--%>
                                                </div>
                                                    <%--                                                    </form>--%>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                                </c:if>
                            </td>
                            <td>


                                <c:if test="${absens.status ==0}">
                                    <form action="/delete-absence/${absens.absenceId}" method="GET">
                                        <button
                                                class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill"
                                                title="Xóa"
                                                onclick="return confirm('Bạn có muốn xóa đơn nghỉ này không?');">
                                            <i class="la la-remove"></i>
                                            <input hidden name="page" id="Vale" value="${newOutput.page}">
                                            <input hidden name="numOfList" id="p" value="${loopCounter.index}">
                                        </button>
                                    </form>
<%--                                    <a href="delete-absence/${absens.absenceId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Xóa"--%>
<%--                                       onclick="return confirm('Bạn có muốn xóa đơn nghỉ này không?');">--%>
<%--                                        <i class="la la-remove"></i>--%>
<%--                                    </a>--%>
                                </c:if>
                                <c:if test="${absens.status ==1}">
                                    <form action="/hide-absence/${absens.absenceId}" method="GET">
                                        <button
                                                class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill"
                                                title="Xóa"
                                                onclick="return confirm('Bạn có muốn hủy đơn nghỉ này không?');">
                                            <i class="la la-remove"></i>
                                            <input hidden name="page" id="PValue" value="${newOutput.page}">
                                            <input hidden name="numOfList" id="pae" value="${loopCounter.index}">
                                        </button>
                                    </form>
<%--                                    <a href="hide-absence/${absens.absenceId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Xóa đơn nghỉ đã duyệt"--%>
<%--                                       onclick="return confirm('Bạn có muốn xóa đơn nghỉ đã được duyệt này không?');">--%>
<%--                                        <i class="la la-remove"></i>--%>
<%--                                    </a>--%>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <nav aria-label="Page navigation example">
                    <ul class="pagination" style="padding-left: 45%">
                        <li class="page-item"><a class="page-link" href="/user/1">first</a></li>
                        <li class="page-item"><a class="page-link"
                                                 href="/user/${newOutput.previousPage}">previous</a>
                        </li>
                        <li class="page-item"><a class="page-link" href="/user/${newOutput.nextPage}">next</a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="/user/${newOutput.totalPage}">last</a></li>
                    </ul>
                </nav>

            </div>
        </div>
    </div>
</div>
</div>
<script>
    $(document).ready(function() {
        $("#toDate").click(function () {
            var toDate = document.getElementById("toDate").value
            if (toDate === '') {
                console.log("hello")
                return;
            } else {

                var absence =  {
                    startDate:$("#dayIn").val(),
                    endDate:$("#dayOut").val(),
                    typeFromDate:$("#fromDate").val(),
                    typeToDate:$("#toDate").val()
                }
                $.ajax({

                    type: "POST",

                    url: '/api/getDay',

                    dataType: "json",

                    data: JSON.stringify(absence),

                    contentType: "application/json; charset=utf-8",

                    dataType: "json",

                    success: function(response){
                        var x = response;
                        // document.getElementById("dayOff").hidden = false;
                        document.getElementById("daysOff").innerHTML =  x ;
                        document.getElementById("dayOff").hidden = false;
                    },


                });

            }});

    })

    function validateToDate() {
        var dayIn = document.getElementById("dayIn").value;
        var dayOut = document.getElementById("dayOut").value;
        var toDate = document.getElementById("fromDate").value;
        if (dayIn === dayOut && toDate == 2) {
            document.getElementById("toDateMidDay").hidden = true;
        } else document.getElementById("toDateMidDay").hidden = false;

    }
    // function sweetalertclick(){
    //     Swal('Hello world!');
    // }
    function myFunction() {
        var dayIn = document.getElementById("dayIn").value
        if(dayIn === ''){
            alert("Xin hãy nhập ngày bắt đầu nghỉ trước");
            document.getElementById("dayOut").disabled = true;
            return;
        }
        document.getElementById("dayOut").setAttribute('min',dayIn);
    };
    function enableEndDate() {
        document.getElementById("dayOut").disabled = false;
        document.getElementById("dayOff").hidden = true;
    };
    function enableToDate() {
        document.getElementById("toDate").disabled = false;

    };
</script>