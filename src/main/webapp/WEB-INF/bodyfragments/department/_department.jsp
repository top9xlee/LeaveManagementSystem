<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@page pageEncoding="UTF-8" %><%--// đọc chữ tiếng việt--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}"/>
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}"/>
<div class="row">
    <div class="col-md-12" style="margin-top: 30px;margin: auto">
        <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
            <div class="m-portlet m-portlet--mobile "
                 style="margin: auto;width: 88%;margin-bottom: 10px;max-width: 1600px">

                <div class="m-portlet__head">
                    <div class="m-portlet__head-caption" style="margin: auto;">
                        <h2 class="m-portlet__head-text" style="color:black;font-size:2rem">
                            ${department.departmentName}
                        </h2>
                    </div>
                </div>

                <div class="m-portlet__body">

                    <c:choose>
                        <c:when test="${param.get('status').equals('success')}">
                            <div class='alert alert-success' role='alert' style="
    text-align: center;
    width: 25%;
    margin: auto;
    font-size: 1.1rem;
">Đồng ý đơn thành công
                            </div>
                        </c:when>
                        <c:when test="${param.get('error').equals('fail')}">
                            <div class='alert alert-info' role='alert' style="
    text-align: center;
    width: 25%;
    margin: auto;
    font-size: 1.1rem;
">Từ chối đơn thành công
                            </div>
                        </c:when>
                    </c:choose>

                    <div class="row">
                        <div class="col-md-9" style="padding-top: 15px">
                            <%--                            <div class="form-group m-form__group row">--%>
                            <%--                                <div class="text-center col-md-12">--%>
                            <%--                                    <a href = "/department/${department.departmentId}">--%>
                            <%--                                    <h4 class="m-form__section" style="color:#0C0C0C;">${department.departmentName}</h4>--%>
                            <%--                                    </a>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <div class="form-group m-form__group row">
                                <label class="col-4 col-form-label1">Tên trưởng phòng</label>
                                <div class="col-5">
                                    <input disabled class="form-control m-input" type="text"
                                           value="${department.headName}" }>
                                </div>

                                <div class="col-15" style="margin: auto"><a href="/user">
                                    <button class="btn btn-outline-primary" style="width: 100px; margin: 2px">Trở về
                                    </button>
                                </a></div>

                            </div>
                            <div class="form-group m-form__group row">
                                <label class="col-4 col-form-label1">Số nhân viên</label>
                                <div class="col-5">
                                    <input disabled class="form-control m-input" type="text"
                                           value="${department.numberOfUser}">
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<div class="col-md-12">


</div>
<div class="col-md-12" style="margin-top: 30px;margin: auto">
    <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">
        <div class="m-portlet m-portlet--mobile " style="margin: auto;width: 90%;max-width: 1600px">
            <div class="m-portlet__head">
                <div class="row" style="width: 100%;">
                    <div class="m-portlet__head-tools" style="width: 100%;">

                        <ul class="m-portlet__nav" style="width: 100%;">
                            <form action="/department1" method="GET" style="width: 100%">
                                <div class="row col-10">
                                    <label class="col-2 col-form-label" style="font-size: 16px;font-weight: bold">Sắp
                                        xếp
                                        theo:</label>
                                    <select name="sort" class="form-control m-input col-2">
                                        <option value="1" class="form-control m-input">Tất cả đơn</option>
                                        <option value="2" class="form-control m-input">Đơn Chưa duyệt</option>
                                        <option value="3" class="form-control m-input">Đơn Đã Duyệt</option>
                                        <option value="4" class="form-control m-input">Đơn Từ Chối</option>

                                    </select>
                                    <button class="btn btn-accent m-btn m-btn--custom m-btn--pill m-btn--icon m-btn--air"
                                            style="height: 30px;margin-left: 5%">
												<span>
													<i class="la la-search"></i>
												</span>
                                    </button>
                                </div>

                            </form>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="m-portlet__body">
                <!--begin: Datatable -->
                <table width="100%"
                       class="table table-striped- table-bordered table-hover table-checkable dataTable no-footer table"
                       style="overflow: scroll">
                    <thead>
                    <tr>
                        <th title="Field #1" data-field="Mã đơn">Số thứ tự</th>
                        <th title="Field #2" data-field="Owner">Tên nhân viên</th>
                        <th title="Field #3" data-field="Type">Tiêu đề</th>
                        <th title="Field #4" data-field="Contact">Nghỉ từ ngày</th>
                        <th title="Field #5" data-field="CarMake">Đến ngày</th>
                        <th title="Field #6" data-field="CarModel">Số ngày</th>
                        <th title="Field #7" data-field="Color">Ngày gửi đơn</th>
                        <th title="Field #8" data-field="DepositPaid">Tình trạng đơn</th>
                        <th title="Field #9" data-field="OrderDate">Chi tiết</th>


                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="absence" items="${absence}" varStatus="loopCounter">

                        <fmt:formatDate var="startDate" value="${absence.startDate}" pattern="yyyy-MM-dd"/>
                        <fmt:formatDate var="endDate" value="${absence.endDate}" pattern="yyyy-MM-dd"/>
                        <tr>
                            <td>${loopCounter.count}</td>
                            <td>${absence.userName}</td>
                            <td>${absence.title}</td>
                            <td>
                                <time><fmt:formatDate value="${absence.startDate}" pattern="dd-MM-yyyy"/></time>
                            </td>
                            <td>
                                <time><fmt:formatDate value="${absence.endDate}" pattern="dd-MM-yyyy "/></time>
                            </td>
                            <td><fmt:formatNumber value="${absence.dayOff}"></fmt:formatNumber></td>
                            <td>
                                <time><fmt:formatDate value="${absence.createDate}" pattern="dd-MM-yyyy "/></time>
                            </td>
                            <td><c:if test="${absence.status ==0}">
                                <span class=" m-badge  m-badge--danger m-badge--wide"
                                      style="margin: auto">Chưa duyệt</span>
                            </c:if>
                                <c:if test="${absence.status ==1}">
                                    <span class=" m-badge  m-badge--info m-badge--wide"
                                          style="margin: auto">Đã duyệt</span>
                                </c:if>
                                <c:if test="${absence.status ==2}">
                                    <span class=" m-badge  m-badge--brand m-badge--wide"
                                          style="margin: auto">Từ chối</span>
                                </c:if></td>
                            <td>
                                <c:if test="${absence.status ==0}">
                                    <a class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill">
                                        <i type="button" class=" la la-angle-down" data-toggle="modal"
                                           data-target="#exampleModal${loopCounter.count}"></i>
                                        <div class="modal fade" id="exampleModal${loopCounter.count}" tabindex="-1"
                                             role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document"
                                                 style="width: 100%;margin-left: 17%;padding-top:60px">
                                                <div class="modal-content" style="width: 200%">
                                                    <div class="modal-header" style="margin: auto; width: 100%">
                                                        <h5 class="modal-title" id="exampleModalLabel1"
                                                            style="text-align: center;width: 100%;font-size: 1.5rem">Chi
                                                            tiết đơn nghỉ</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body"
                                                         style="color:black;margin-top: -20px;text-align: left">

                                                            <%--        < class="m-portlet__body">--%>
                                                        <div class="form-group m-form__group row"
                                                             style="padding-top: 20px">

                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Tên người
                                                                gửi</label>
                                                            <div class="col-2">
                                                                <div>
                                                                    <label for="example-text-input"
                                                                           class="col-12 col-form-label">${absence.userName}</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-2">
                                                            </div>
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Tên công
                                                                việc</label>
                                                            <div class="col-2">
                                                                <div>
                                                                    <label for="example-text-input"
                                                                           class="col-12 col-form-label">${absence.jobName}</label>
                                                                </div>
                                                            </div>

                                                        </div>
                                                        <div class="form-group m-form__group row">
                                                            <label for="title" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Tiêu đề</label>
                                                            <div class="col-3">
                                                                <input disabled class="form-control m-input" type="text"
                                                                       value="${absence.title}" id="title" name="title">
                                                            </div>
                                                            <div class="col-1">

                                                            </div>
                                                            <label for="example-text-input"
                                                                   class="col-2 col-form-label">Người phụ trách</label>
                                                            <div class="col-2">
                                                                <div>
                                                                    <label class="col-12 col-form-label"
                                                                           style="text-align: left"
                                                                           style="text-align: left">${absence.namePersonInCharge}</label>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group m-form__group row"
                                                             style="color:black;">
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Lí do chi
                                                                tiết</label>
                                                            <div class="col-9">
                                                            <textarea disabled class="form-control m-input"
                                                                      name="description" placeholder="Lý do xin nghỉ"
                                                                      id="example-text-input"
                                                                      style="height: 150px">${absence.description} </textarea>
                                                            </div>
                                                        </div>
                                                        <div class="form-group m-form__group row"
                                                             style="color:black;">
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Ngày bắt đầu
                                                                nghỉ</label>
                                                            <div class="col-2">
                                                                <label for="example-text-input"
                                                                       class="col-12 col-form-label"
                                                                       style="text-align: left">
                                                                    <time><fmt:formatDate value="${absence.startDate}"
                                                                                          pattern="dd-MM-yyyy"/></time>
                                                                </label>
                                                            </div>
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Ngày kết thúc
                                                                nghỉ</label>
                                                            <div class="col-2">
                                                                <label for="example-text-input"
                                                                       class="col-12 col-form-label"
                                                                       style="text-align: left">
                                                                    <time><fmt:formatDate value="${absence.endDate}"
                                                                                          pattern="dd-MM-yyyy "/></time>
                                                                </label>
                                                            </div>
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Số ngày
                                                                nghỉ</label>
                                                            <div class="col-2">
                                                                <label for="example-text-input"
                                                                       class="col-12 col-form-label"
                                                                       style="text-align: left"> <fmt:formatNumber
                                                                        value=" ${absence.dayOff}"></fmt:formatNumber> </label>
                                                            </div>

                                                        </div>
                                                    </div>
                                                    <div class="m-portlet__foot m-portlet__foot--fit">
                                                        <form method="post" enctype="multipart/form-data"
                                                              action="/note">
                                                            <div class="modal-body">
                                                                <input hidden value="${absence.absenceId}"
                                                                       name="absenceId">
<%--                                                                <input hidden name="type" value="2">--%>
                                                                <div class="form-group m-form__group row"
                                                                     style="color:black">
                                                                    <label for="example-text-input2"
                                                                           class="col-2 col-form-label"
                                                                           style="text-align: left!important;">Lí
                                                                        do</label>
                                                                    <div class="col-9">
                                                                                    <textarea
                                                                                            class="form-control m-input"
                                                                                            name="description"
                                                                                            placeholder="Nhập lí do"
                                                                                            id="example-text-input2"
                                                                                            style="height: 120px"
                                                                                            rows=11 cols=50
                                                                                            maxlength=250
                                                                                            required></textarea>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="submit" name="type" value="1"
                                                                        class="btn btn-success">Duyệt đơn
                                                                </button>
                                                                <button type="submit" name="type" value="2"
                                                                        class="btn btn-primary">Từ chối đơn
                                                                </button>

                                                            </div>
                                                        </form>
<%--                                                        <div class="m-form__actions">--%>
<%--                                                            <div class="row">--%>
<%--                                                                <div class="col-10 row" style="padding-top:10px;">--%>
<%--                                                                    <div style="padding-left:50%;padding-right:2%">--%>
<%--                                                                        <script> var a = 1</script>--%>
<%--                                                                        <button id="accept" class="btn btn-success"--%>
<%--                                                                                         type="submit" onclick="myFunction1()"--%>
<%--                                                                    >--%>
<%--                                                                        Duyệt--%>
<%--                                                                    </button>--%>

<%--                                                                    </div>--%>
<%--                                                                    <button id="reject" class="btn btn-primary"--%>
<%--                                                                            type="submit"--%>
<%--                                                                            style="height: 40px;width:95px "--%>
<%--                                                                            onclick="myFunction2()">Từ chối--%>
<%--                                                                    </button>--%>
<%--                                                                </div>--%>
<%--                                                            </div>--%>
<%--                                                            <div>--%>
<%--                                                                <div style="display: none"--%>
<%--                                                                     class="multiCollapseExample2">--%>
<%--                                                                    <form method="post" enctype="multipart/form-data"--%>
<%--                                                                          action="/note">--%>
<%--                                                                        <div class="modal-body">--%>
<%--                                                                            <input hidden value="${absence.absenceId}"--%>
<%--                                                                                   name="absenceId">--%>
<%--                                                                            <input hidden name="type" value="2">--%>
<%--                                                                            <div class="form-group m-form__group row"--%>
<%--                                                                                 style="color:black">--%>
<%--                                                                                <label for="example-text-input2"--%>
<%--                                                                                       class="col-2 col-form-label"--%>
<%--                                                                                       style="text-align: left!important;">Lí--%>
<%--                                                                                    do</label>--%>
<%--                                                                                <div class="col-9">--%>
<%--                                                                                    <textarea--%>
<%--                                                                                            class="form-control m-input"--%>
<%--                                                                                            name="description"--%>
<%--                                                                                            placeholder="Lý do từ chối"--%>
<%--                                                                                            id="example-text-input2"--%>
<%--                                                                                            style="height: 120px"--%>
<%--                                                                                            rows=11 cols=50--%>
<%--                                                                                            maxlength=250--%>
<%--                                                                                            required></textarea>--%>
<%--                                                                                </div>--%>
<%--                                                                            </div>--%>
<%--                                                                        </div>--%>
<%--                                                                        <div class="modal-footer">--%>
<%--                                                                            <button type="reset"--%>
<%--                                                                                    class="btn btn-primary">Xóa--%>
<%--                                                                            </button>--%>
<%--                                                                            <button type="submit" onclick="send()"--%>
<%--                                                                                    class="btn btn-success">Từ chối đơn--%>
<%--                                                                            </button>--%>
<%--                                                                        </div>--%>
<%--                                                                    </form>--%>
<%--                                                                </div>--%>
<%--                                                            </div>--%>
<%--                                                            <div>--%>
<%--                                                                <div style="display: none"--%>
<%--                                                                     class="multiCollapseExample1">--%>
<%--                                                                    <form method="post" enctype="multipart/form-data"--%>
<%--                                                                          action="/note">--%>
<%--                                                                        <div class="modal-body">--%>
<%--                                                                            <input hidden value="${absence.absenceId}"--%>
<%--                                                                                   name="absenceId">--%>
<%--                                                                            <input hidden name="type" value="1">--%>
<%--                                                                            <div class="form-group m-form__group row"--%>
<%--                                                                                 style="color:black">--%>
<%--                                                                                <label for="example-text-input2"--%>
<%--                                                                                       class="col-2 col-form-label"--%>
<%--                                                                                       style="text-align: left!important;">Lí--%>
<%--                                                                                    do</label>--%>
<%--                                                                                <div class="col-9">--%>
<%--                                                                                    <textarea--%>
<%--                                                                                            class="form-control m-input"--%>
<%--                                                                                            name="description"--%>
<%--                                                                                            value="Không có ý kiến gì"--%>
<%--                                                                                            placeholder="Lý do duyệt đơn"--%>
<%--                                                                                            id="example-text-input1"--%>
<%--                                                                                            style="height: 120px"--%>
<%--                                                                                            rows=11 cols=50--%>
<%--                                                                                            maxlength=250 value="Đồng ý"--%>
<%--                                                                                            required>Đồng ý</textarea>--%>
<%--                                                                                </div>--%>
<%--                                                                            </div>--%>
<%--                                                                        </div>--%>
<%--                                                                        <div class="modal-footer">--%>
<%--                                                                            <button type="reset"--%>
<%--                                                                                    class="btn btn-primary">Xóa--%>
<%--                                                                            </button>--%>
<%--                                                                            <button type="submit" onclick="send()"--%>
<%--                                                                                    class="btn btn-success">Duyệt đơn--%>
<%--                                                                            </button>--%>
<%--                                                                        </div>--%>
<%--                                                                    </form>--%>
<%--                                                                </div>--%>
<%--                                                            </div>--%>
<%--                                                        </div>--%>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </a>
                                </c:if>
                                <c:if test="${absence.status ==1}">
                                    <a class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill">
                                        <i type="button" class=" la la-angle-down" data-toggle="modal"
                                           data-target="#exampleModal${loopCounter.count}"></i>
                                        <div class="modal fade" id="exampleModal${loopCounter.count}" tabindex="-1"
                                             role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document"
                                                 style="width: 100%;margin-left: 17%;padding-top:60px">
                                                <div class="modal-content" style="width: 200%">
                                                    <div class="modal-header" style="margin: auto; width: 100%">
                                                        <h5 class="modal-title" id="exampleModalLabel2"
                                                            style="text-align: center;width: 100%;font-size: 1.5rem">Chi
                                                            tiết đơn nghỉ</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body" style="color:black;margin-top: -20px">

                                                            <%--        < class="m-portlet__body">--%>

                                                        <div class="form-group m-form__group row"
                                                             style="padding-top: 20px">

                                                            <label for="example-text-input"
                                                                   class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Tên người
                                                                gửi</label>
                                                            <div class="col-2">
                                                                <div>
                                                                    <label for="example-text-input"
                                                                           class="col-12 col-form-label">${absence.userName}</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-2">
                                                            </div>
                                                            <label for="example-text-input"
                                                                   class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Tên công
                                                                việc</label>
                                                            <div class="col-2">
                                                                <div>
                                                                    <label for="example-text-input"
                                                                           class="col-12 col-form-label">${absence.jobName}</label>
                                                                </div>
                                                            </div>

                                                        </div>

                                                        <div class="form-group m-form__group row">
                                                            <label for="title" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Tiêu
                                                                đề</label>
                                                            <div class="col-3">
                                                                <input disabled class="form-control m-input" type="text"
                                                                       value="${absence.title}" id="title1"
                                                                       name="title">
                                                            </div>
                                                            <div class="col-1">
                                                            </div>
                                                            <label for="example-text-input"
                                                                   class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Người phụ
                                                                trách</label>
                                                            <div class="col-2">
                                                                <label class="col-12 col-form-label"
                                                                       style="text-align: left"
                                                                       style="text-align: left">${absence.namePersonInCharge}</label>
                                                            </div>

                                                        </div>
                                                        <div class="form-group m-form__group row" style="color:black">
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Lí
                                                                do chi tiết</label>
                                                            <div class="col-9">
                                                            <textarea disabled class="form-control m-input"
                                                                      name="description" placeholder="Lý do xin nghỉ"
                                                                      style="height: 150px">${absence.description} </textarea>
                                                            </div>
                                                        </div>
                                                        <div class="form-group m-form__group row" style="color:black">
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Ngày
                                                                bắt đầu nghỉ</label>
                                                            <div class="col-2">
                                                                <label for="example-text-input"
                                                                       class="col-12 col-form-label"
                                                                       style="text-align: left">
                                                                    <time><fmt:formatDate value="${absence.startDate}"
                                                                                          pattern="dd-MM-yyyy"/></time>
                                                                </label>
                                                            </div>
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Ngày
                                                                kết thúc nghỉ</label>
                                                            <div class="col-2">
                                                                <label for="example-text-input"
                                                                       class="col-12 col-form-label"
                                                                       style="text-align: left">
                                                                    <time><fmt:formatDate value="${absence.endDate}"
                                                                                          pattern="dd-MM-yyyy "/></time>
                                                                </label>
                                                            </div>
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Số
                                                                ngày nghỉ</label>
                                                            <div class="col-2">
                                                                <label for="example-text-input"
                                                                       class="col-12 col-form-label"
                                                                       style="text-align: left"> <fmt:formatNumber
                                                                        value=" ${absence.dayOff}"></fmt:formatNumber></label>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </a>
                                </c:if>
                                <c:if test="${absence.status ==2}">
                                    <a class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill">
                                        <i type="button" class=" la la-angle-down" data-toggle="modal"
                                           data-target="#exampleModal${loopCounter.count}"></i>
                                        <div class="modal fade" id="exampleModal${loopCounter.count}" tabindex="-1"
                                             role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document"
                                                 style="width: 100%;margin-left: 17%;padding-top:30px">
                                                <div class="modal-content" style="width: 200%">
                                                    <div class="modal-header" style="margin: auto; width: 100%">
                                                        <h5 class="modal-title" id="exampleModalLabel3"
                                                            style="text-align: center;width: 100%;font-size: 1.5rem">Chi
                                                            tiết đơn nghỉ</h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body" style="color:black;margin-top: -20px">

                                                            <%--        < class="m-portlet__body">--%>

                                                        <div class="form-group m-form__group row"
                                                             style="padding-top: 20px">

                                                            <label for="example-text-input"
                                                                   class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Tên người
                                                                gửi</label>
                                                            <div class="col-2">
                                                                <div>
                                                                    <label for="example-text-input"
                                                                           class="col-12 col-form-label">${absence.userName}</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-2">
                                                            </div>
                                                            <label for="example-text-input"
                                                                   class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Tên công
                                                                việc</label>
                                                            <div class="col-2">
                                                                <div>
                                                                    <label for="example-text-input"
                                                                           class="col-12 col-form-label">${absence.jobName}</label>
                                                                </div>
                                                            </div>

                                                        </div>

                                                        <div class="form-group m-form__group row">
                                                            <label for="title" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Tiêu
                                                                đề</label>
                                                            <div class="col-3">
                                                                <input disabled class="form-control m-input" type="text"
                                                                       value="${absence.title}"
                                                                       name="title">
                                                            </div>
                                                            <div class="col-1">
                                                            </div>
                                                            <label for="example-text-input"
                                                                   class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Người phụ
                                                                trách</label>
                                                            <div class="col-2">
                                                                <label class="col-12 col-form-label"
                                                                       style="text-align: left"
                                                                       style="text-align: left">${absence.namePersonInCharge}</label>
                                                            </div>

                                                        </div>
                                                        <div class="form-group m-form__group row" style="color:black">
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Lí
                                                                do chi tiết</label>
                                                            <div class="col-9">
                                                            <textarea disabled class="form-control m-input"
                                                                      name="description" placeholder="Lý do xin nghỉ"
                                                                      style="height: 150px">${absence.description} </textarea>
                                                            </div>
                                                        </div>
                                                        <div class="form-group m-form__group row" style="color:black">
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Ngày
                                                                bắt đầu nghỉ</label>
                                                            <div class="col-2">
                                                                <label for="example-text-input"
                                                                       class="col-12 col-form-label"
                                                                       style="text-align: left">
                                                                    <time><fmt:formatDate value="${absence.startDate}"
                                                                                          pattern="dd-MM-yyyy"/></time>
                                                                </label>
                                                            </div>
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Ngày
                                                                kết thúc nghỉ</label>
                                                            <div class="col-2">
                                                                <label for="example-text-input"
                                                                       class="col-12 col-form-label"
                                                                       style="text-align: left">
                                                                    <time><fmt:formatDate value="${absence.endDate}"
                                                                                          pattern="dd-MM-yyyy "/></time>
                                                                </label>
                                                            </div>
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Số
                                                                ngày nghỉ</label>
                                                            <div class="col-2">
                                                                <label for="example-text-input"
                                                                       class="col-12 col-form-label"
                                                                       style="text-align: left"> <fmt:formatNumber
                                                                        value=" ${absence.dayOff}"></fmt:formatNumber></label>
                                                            </div>

                                                        </div>
                                                        <div class="form-group m-form__group row" style="color:black">
                                                            <label for="example-text-input" class="col-2 col-form-label"
                                                                   style="text-align: left!important;">Lí
                                                                do từ chối</label>

                                                            <div class="col-9">
                                                            <textarea disabled class="form-control m-input"
                                                                      name="description" placeholder="Lý do từ chối"
                                                                      style="height: 150px">${absence.note} </textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </a>
                                </c:if></td>

                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example">
                    <ul class="pagination" style="padding-left: 45%">
                        <li class="page-item">
                            <c:if test="${param.sort != null}">
                                <a class="page-link"
                                   href="${requestScope['javax.servlet.forward.request_uri']}?page=1&sort=${param.sort}">first</a>
                            </c:if>
                            <c:if test="${param.sort == null}">
                                <a class="page-link" href="${requestScope['javax.servlet.forward.request_uri']}?page=1">first</a>
                            </c:if>
                        </li>
                        <li class="page-item">
                            <c:if test="${param.sort != null}">
                                <a class="page-link"
                                   href="${requestScope['javax.servlet.forward.request_uri']}?page=${newOutput.previousPage}&sort=${param.sort}">previous</a>
                            </c:if>
                            <c:if test="${param.sort == null}">
                                <a class="page-link"
                                   href="${requestScope['javax.servlet.forward.request_uri']}?page=${newOutput.previousPage}">previous</a>
                            </c:if>
                        </li>
                        <li class="page-item">
                            <c:if test="${param.sort != null}">
                                <a class="page-link"
                                   href="${requestScope['javax.servlet.forward.request_uri']}?page=${newOutput.nextPage}&sort=${param.sort}">next</a>
                            </c:if>
                            <c:if test="${param.sort == null}">
                                <a class="page-link"
                                   href="${requestScope['javax.servlet.forward.request_uri']}?page=${newOutput.nextPage}">next</a>
                            </c:if>
                        </li>
                        <li class="page-item">
                            <c:if test="${param.sort != null}">
                                <a class="page-link"
                                   href="${requestScope['javax.servlet.forward.request_uri']}?page=${newOutput.totalPage}&sort=${param.sort}">last</a>
                            </c:if>
                            <c:if test="${param.sort == null}">
                                <a class="page-link"
                                   href="${requestScope['javax.servlet.forward.request_uri']}?page=${newOutput.totalPage}">last</a>
                            </c:if>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
</div>
<script>

    function myFunction1() {
        var x = document.getElementsByClassName('multiCollapseExample1');
        console.log(x);
        var y = document.getElementsByClassName('multiCollapseExample2');
        console.log(a);
        for(var i=1; i <= x.length; i++){
            if(a === i) {
                if (y[i].) {
                    y[i].style.display = 'none';
                }
                if (x[i].style.display === 'none') {
                    x[i].style.display = 'block';
                } else {
                    x[i].style.display = 'none';
                }
            }
        }
        // if (y.style.display !== 'none') {
        //     y.style.display = 'none';
        // }
        // if (x.style.display === 'none') {
        //     x.style.display = 'block';
        // } else {
        //     x.style.display = 'none';
        // }
    }
    // $( "#exampleModalLabel1" ).on('shown', function(){
    //     alert("I want this to appear after the modal has opened!");
    // });

    // function myFunction2() {
    //     var x = document.getElementsByClassName('multiCollapseExample1')
    //     var y = document.getElementsByClassName('multiCollapseExample2')
    //     for(var i=1; i<= x.length; i++){
    //         if(a === i) {
    //             if (x[i]..style.display !== 'none') {
    //                 x[i].style.display = 'none';
    //             }
    //             if (y[i].style.display === 'none') {
    //                 y[i].style.display = 'block';
    //             } else {
    //                 y[i].style.display = 'none';
    //             }
    //         }
    //     }
    // }
    // $(document).ready(function () {
    //     $("#accept").click(function () {
    //         var x = document.getElementById('multiCollapseExample1');
    //         var y = document.getElementById('multiCollapseExample2');
    //         if (y.style.display !== 'none') {
    //             y.style.display = 'none';
    //         }
    //         if (x.style.display === 'none') {
    //             x.style.display = 'block';
    //         } else {
    //             x.style.display = 'none';
    //         }
    //     });
    // })

</script>
<script src="/app/js/my-script.js" type="text/javascript"></script>