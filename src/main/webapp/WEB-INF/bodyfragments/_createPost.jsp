<%@page pageEncoding="UTF-8" %><%--// đọc chữ tiếng việt--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="m-portlet text-center " style="width: 1500px;margin : auto">
    <div class="m-portlet__head text-center">
        <div class="col-md-12" style="padding-top: 20px">
            <h2 class="text-center" style="text-align: center;font-size: 26px">
                Tạo đơn nghỉ phép
            </h2>
        </div>
    </div>
    <div class="col-md-12 text-center">
        <c:choose>
            <c:when test="${param.get('status').equals('success')}">
                <p class="message-register" style="color: rgb(22, 161, 255); font-size: 16px;text-align: center">Tạo
                    đơn thành công</p>
            </c:when>
        </c:choose>
        <div id="response"></div>
        <i id="ajaxspinner" class="fas fa-spinner fa-spin fa-3x fa-fw" style="display:none"></i>
    </div>

    <!--begin::Form-->
    <form action="createAbsence" method="post" id="form-create" class="m-form m-form--fit m-form--label-align-right"
          enctype="multipart/form-data">
        <%--        < class="m-portlet__body">--%>

        <div class="form-group m-form__group row" style="padding-top: 20px">

            <label class="col-2 col-form-label"
                   style="text-align: left!important;padding-left: 50px;">Tên người gửi</label>
            <div class="col-3">
                <div>
                    <label class="col-12 col-form-label1"
                           style="text-align: left">${user.fullName}</label>
                </div>
            </div>
            <div class="col-1">
            </div>
            <label class="col-2 col-form-label"
                   style="text-align: left!important;padding-left: 50px;">Chức danh công việc</label>
            <div class="col-2">
                <div>
                    <label class="col-12 col-form-label1" style="text-align: left"
                           style="text-align: left">${user.jobName}</label>
                </div>
            </div>
            <%--                <label for="example-text-input" class="col-2 col-form-label">Tên trưởng phòng</label>--%>
            <%--                <div class="col-3">--%>
            <%--&lt;%&ndash;                    <input id="input" type="text">&ndash;%&gt;--%>
            <%--&lt;%&ndash;                    <button id="button">Set value to "Hello"</button>&ndash;%&gt;--%>
            <%--&lt;%&ndash;                    <span>Value: <strong id="value"></strong></span>&ndash;%&gt;--%>

            <%--                </div>--%>
        </div>
        <%--    <div class="form-group m-form__group row">--%>
        <%--        <label  class="col-2 col-form-label" style="text-align: left!important;padding-left: 50px;" >Gửi đến ban/phòng</label>--%>
        <%--        <div class="col-3">--%>
        <%--            <div>--%>
        <%--                <label for="example-text-input" class="col-12 col-form-label1" style="text-align: left">${departments.departmentName}</label>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--        <div class="col-1">--%>
        <%--        </div>--%>
        <%--        <label for="example-text-input" class="col-2 col-form-label"style="text-align: left!important;padding-left: 50px;">Tên trưởng phòng</label>--%>
        <%--        <div class="col-2">--%>
        <%--            <div>--%>
        <%--                <label for="example-text-input" class="col-12 col-form-label1" style="text-align: left">${departments.headName}</label>--%>
        <%--            </div>--%>
        <%--        </div>--%>
        <%--    </div>--%>
        <div class="form-group m-form__group row">
            <label class="col-2 col-form-label"
                   style="text-align: left!important;padding-left: 50px;">Tiêu đề</label>
            <div class="col-3">
                <div>
                    <input class="form-control m-input" type="text" id="title" name="title"
                           placeholder="Đơn xin nghỉ phép ngày:" required>
                </div>
            </div>
            <div class="col-1">
            </div>
            <label class="col-2 col-form-label" style="text-align: left!important;padding-left: 50px;">Người phụ trách
                thay</label>
            <div class="col-3">

                <select id="personInCharge" name="personInCharge" class="form-control m-input" required>
                    <option class="form-control m-input" class="form-control m-input" disabled selected value="">Chọn
                        đồng nghiệp
                    </option>
                    <c:forEach var="users" items="${users}">
                        <option class="form-control m-input" value="${users.userId}">${users.fullName}</option>
                    </c:forEach>
                </select>


            </div>
        </div>
        <div class="form-group m-form__group row">
            <label for="description" class="col-2 col-form-label"
                   style="text-align: left!important;padding-left: 50px;">Lý do</label>
            <div class="col-9">
                <textarea class="form-control m-input" name="description" placeholder="Lý do xin nghỉ"
                          id="description" style="height: 150px" required></textarea>
            </div>
        </div>
        <div class="form-group m-form__group row">
            <label for="dayIn" class="col-2 col-form-label" style="text-align: left!important;padding-left: 50px;">Ngày
                bắt đầu</label>
            <div class="col-2">
                <input class="form-control m-input" onclick="enableEndDate()" type="date" id="dayIn" name="startDate"
                       min="2022-01-01"
                       required>
            </div>
            <div class="col-1">

            </div>
            <label for="dayOut" class="col-2 col-form-label" style="text-align: left!important;padding-left: 50px;">Ngày
                kết thúc</label>
            <div class="col-2">
                <input class="form-control m-input" type="date" onclick="validateEndDate()" id="dayOut" required
                       name="endDate" disabled>
            </div>
            <div class="col-2">
                <label id="dayOff" class="col-12 col-form-label" style="text-align: left"
                       style="text-align: left" hidden></label>
            </div>
        </div>
        <div class="form-group m-form__group row">
            <label for="dayIn" class="col-2 col-form-label"
                   style="text-align: left!important;padding-left: 50px;"></label>
            <div class="col-2">
                <select name="typeFromDate" id="fromDate" onclick="enableToDate()" class="form-control m-input"
                        required>
                    <option id="input1" class="form-control m-input" disabled selected value="">Chọn buổi</option>
                    <option class="form-control m-input" value="1">Buổi sáng</option>
                    <option class="form-control m-input" value="2">Buổi chiều</option>
                </select>
            </div>
            <div class="col-1">

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
        <div class="m-portlet__foot m-portlet__foot--fit">
            <div class="m-form__actions">
                <div class="row">
                    <div class="col-md-12">
                        <button id="submit" type="submit" class="btn btn-success">Gửi đơn</button>
                        <%--                        <button id="test123" type="button" class="btn btn-success">Gửi đơn test</button>--%>
                        <button type="reset" class="btn btn-secondary"><a href="/user">Trở về</a>
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <%--            <div class="form-group m-form__group row">--%>
        <%--                <label for="example-datetime-local-input1" class="col-2 col-form-label">Ngày kết thúc nghỉ</label>--%>
        <%--                <div class="col-4">--%>
        <%--                  --%>
        <%--                </div>--%>
        <%--            </div>--%>
    </form>

    </form>
</div>
<script>

    $(document).ready(function () {
        $("#toDate").click(function () {
            var toDate = document.getElementById("toDate").value
            if (toDate === '') {
                console.log("hello")
                return;
            } else {

                var absence = {
                    startDate: $("#dayIn").val(),
                    endDate: $("#dayOut").val(),
                    typeFromDate: $("#fromDate").val(),
                    typeToDate: $("#toDate").val()
                }
                $.ajax({

                    type: "POST",

                    url: '/api/getDay',

                    dataType: "json",

                    data: JSON.stringify(absence),

                    contentType: "application/json; charset=utf-8",

                    dataType: "json",

                    success: function (response) {
                        var x = response;
                        document.getElementById("dayOff").hidden = false;
                        document.getElementById("dayOff").innerHTML = "Số ngày :     " + x;
                    },


                });

            }
        });

    })

    $(document).ready(function () {
        $("#test123").click(function () {
            var absence = {
                startDate: $("#dayIn").val(),
                endDate: $("#dayOut").val(),
                typeFromDate: $("#fromDate").val(),
                typeToDate: $("#toDate").val(),
                title: $("#title").val(),
                description: $("#description").val(),
                personInCharge: $("#personInCharge").val(),
            }

            enableButton();

            var toDate = document.getElementById("toDate").value
            if (toDate === '' || absence.endDate === '' || absence.startDate === '' || absence.typeFromDate === '' || absence.description === '' || absence.personInCharge === '') {
                $("#response").html("<div class ='alert alert-warning' role='alert'>Xin hãy nhập đầy đủ thông tin</div>").fadeIn(300).fadeOut(3000)
                return;
            } else {


                $.ajax({

                    type: "POST",

                    url: '/api/createAbsence',

                    dataType: "json",

                    data: JSON.stringify(absence),

                    contentType: "application/json; charset=utf-8",

                    dataType: "json",

                    beforeSend: function () {
                        $("#response").html("<div class ='alert alert-info' role='alert'>Loading</div>").fadeIn(300).fadeOut(7000)

                    },

                    success: function (response) {
                        console.log(response)
                        $("#response").html("<div class ='alert alert-success' role='alert'>Success</div>")

                    },


                });

            }
        });

    })

    function ok() {
        alert("Thành công");
    }

    $(document).ready(function () {
        $("#submit").click(function () {
            var absence = {
                startDate: $("#dayIn").val(),
                endDate: $("#dayOut").val(),
                typeFromDate: $("#fromDate").val(),
                typeToDate: $("#toDate").val(),
                title: $("#title").val(),
                description: $("#description").val(),
                personInCharge: $("#personInCharge").val(),
            }

            var toDate = document.getElementById("toDate").value

            if (toDate === '' || absence.endDate === '' || absence.startDate === '' || absence.typeFromDate === '' || absence.description === '' || absence.personInCharge === '') {
                $("#response").html("<div class ='alert alert-warning' role='alert'>Xin hãy nhập đầy đủ thông tin</div>").fadeIn(300).fadeOut(3000)
                return;
            } else {
                enableButton();

            }
        });

    })

    function validateEndDate() {

        document.getElementById("toDate").disabled = true;
        document.getElementById("dayOff").hidden = true;

        var dayIn = document.getElementById("dayIn").value
        if (dayIn === '') {
            alert("Xin hãy nhập ngày bắt đầu nghỉ trước");
            document.getElementById("dayOut").disabled = true;
            return;
        }
        var d = new Date(dayIn);
        var month = d.getMonth() + 1;
        var year = d.getFullYear();
        var first_day = new Date(year, month - 1, 1);
        var f = new Date(year, month, 0);
        var localDate = f.toLocaleDateString('en-CA');

        document.getElementById("dayOut").setAttribute('max', localDate);
        document.getElementById("dayOut").setAttribute('min', dayIn);


    };

    function enableEndDate() {
        document.getElementById("dayOut").disabled = false;

    };

    function enableToDate() {
        document.getElementById("toDate").disabled = false;

    };

    function validateToDate() {
        var dayIn = document.getElementById("dayIn").value;
        var dayOut = document.getElementById("dayOut").value;
        var toDate = document.getElementById("fromDate").value;
        if (dayIn === dayOut && toDate == 2) {
            document.getElementById("toDateMidDay").hidden = true;
        } else document.getElementById("toDateMidDay").hidden = false;

    }

    function enableButton() {
        var timer2 = "02:00";

        var interval = setInterval(function () {
            var timer = timer2.split(':');
            //by parsing integer, I avoid all extra string processing
            var minutes = parseInt(timer[0], 10);
            var seconds = parseInt(timer[1], 10);
            --seconds;
            minutes = (seconds < 0) ? --minutes : minutes;
            if (minutes < 0) clearInterval(interval);
            seconds = (seconds < 0) ? 59 : seconds;
            //minutes = (minutes < 10) ?  minutes : minutes;
            $('.countdown').html(minutes + ':' + seconds);
            timer2 = minutes + ':' + seconds;
            if (minutes === 0 && seconds === 0) {
                clearInterval(interval);
                document.getElementById('submit').removeAttribute('disabled');
            } else {
                document.getElementById('submit').setAttribute('disabled', true);
            }

        }, 100);
    }
</script>
<script src="/app/js/my-script.js" type="text/javascript"></script>