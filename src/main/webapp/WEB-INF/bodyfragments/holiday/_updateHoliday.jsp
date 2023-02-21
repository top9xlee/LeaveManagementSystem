<%@page pageEncoding="UTF-8" %><%--// đọc chữ tiếng việt--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div class = "col-md-5" style="margin-left:80px">
    <div class="m-portlet m-portlet--tab middle">
        <div class="m-portlet__head">
            <div class="m-portlet__head-caption">
                <div class="m-portlet__head-title">
												<span class="m-portlet__head-icon m--hide">
													<i class="la la-gear"></i>
												</span>
                    <h3 class="m-portlet__head-text">
                        Cập nhật ngày nghỉ <fmt:formatDate value="${holi.holiday}" pattern="dd-MM-yyyy" />
                    </h3>
                </div>
            </div>
        </div>
        <div class="col-2"> <button type="button" class="class=btn btn-primary m-btn--wide">Tạo ngày lễ</button></div>


        <!--begin::Form-->
        <form action="update-holiday" method="post" id="form-create"  class="m-form m-form--fit m-form--label-align-right" enctype="multipart/form-data">
            <div class="m-portlet__body">
                <c:choose>
                    <c:when test="${param.get('status').equals('successfull')}">
                        <p class="message-register" style="color: rgb(22, 161, 255); font-size: 16px;text-align: center">Tạo ngày làm  thành công.</p>
                    </c:when>
                    <c:when test="${param.get('error').equals('fail1')}">
                        <p class="message-register" style="color: rgb(20, 157, 252); font-size: 16px;text-align: center">Xóa ngày làm thành công.</p>
                    </c:when>
                </c:choose>

                <div class="form-group m-form__group row">
                    <label for="DepartmentName" class="col-5 col-form-label">Ngày nghỉ</label>
                    <div class="col-7">
                        <input class="form-control m-input" type="date"  id="DepartmentName" name = "holiday" value="${holiday.holiday}">
                    </div>
                </div>
                <div class="form-group m-form__group row">
                    <label for="HeadId" class="col-5 col-form-label">Tên ngày nghỉ</label>
                    <div class="col-7">
                        <input class="form-control m-input" type="text" id="HeadId" name = "description" value="${holiday.description}">
                    </div>
                </div>

            </div>

            <div class="m-portlet__foot m-portlet__foot--fit">
                <div class="m-form__actions">
                    <c:if test="${status == 1}">
                    <div class="row">
                        <div class="col-2">
                        </div>
                        <div class="col-10">
                            <button type="submit" class="btn btn-success">Submit</button>
                            <button type="reset" class="btn btn-secondary">Cancel</button>
                        </div>
                    </div>
                    </c:if>
                </div>
            </div>

        </form>
    </div>
</div>
    <div class="col-md-6" style="margin-left: 10px">
    <div class="m-portlet m-portlet--full-height ">
        <div class="m-portlet__head">
            <div class="m-portlet__head-caption">
                <div class="m-portlet__head-title">
                    <h3 class="m-portlet__head-text">
                        Danh sách Ngày nghỉ lễ
                    </h3>
                </div>
            </div>
            <div class="m-portlet__head-tools">
                <ul class="m-portlet__nav">
                    <li class="m-portlet__nav-item m-dropdown m-dropdown--inline m-dropdown--arrow m-dropdown--align-right m-dropdown--align-push" m-dropdown-toggle="hover" aria-expanded="true">
                        <a href="#" class="m-portlet__nav-link m-portlet__nav-link--icon m-portlet__nav-link--icon-xl">
                            <i class="fa fa-genderless m--font-brand"></i>
                        </a>
                        <div class="m-dropdown__wrapper">
                            <span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust" style="left: auto; right: 17px;"></span>
                            <div class="m-dropdown__inner">
                                <div class="m-dropdown__body">
                                    <div class="m-dropdown__content">
                                        <ul class="m-nav">
                                            <li class="m-nav__item">
                                                <a href="" class="m-nav__link">
                                                    <i class="m-nav__link-icon flaticon-share"></i>
                                                    <span class="m-nav__link-text">Activity</span>
                                                </a>
                                            </li>
                                            <li class="m-nav__item">
                                                <a href="" class="m-nav__link">
                                                    <i class="m-nav__link-icon flaticon-chat-1"></i>
                                                    <span class="m-nav__link-text">Messages</span>
                                                </a>
                                            </li>
                                            <li class="m-nav__item">
                                                <a href="" class="m-nav__link">
                                                    <i class="m-nav__link-icon flaticon-info"></i>
                                                    <span class="m-nav__link-text">FAQ</span>
                                                </a>
                                            </li>
                                            <li class="m-nav__item">
                                                <a href="" class="m-nav__link">
                                                    <i class="m-nav__link-icon flaticon-lifebuoy"></i>
                                                    <span class="m-nav__link-text">Support</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

        <div class="m-portlet__body" style="color: #3f4047">
            <div class="m-widget6">
                <div class="m-widget6__head">
                    <div class="m-widget6__item">
													<span class="m-widget6__caption">
														Số thứ tự
													</span>
                        <span class="m-widget6__caption">
														Ngày nghỉ
													</span>
                        <span class="m-widget6__caption ">
                                                            Mô tả ngày nghỉ
													</span>
                        <span class="m-widget6__caption">Sửa</span>
                        <span class="m-widget6__caption m--align-right">Xóa</span>
                    </div>
                </div>
                <div class="m-widget6__body">
                    <c:forEach var="holi" items="${holiday}" varStatus="loopCounter">
                        <div class="m-widget6__item">
                            <span class="m-widget6__text">

                                    ${loopCounter.count}
                            </span>
                            <span class="m-widget6__text">
                                  <time><fmt:formatDate value="${holi.holiday}" pattern="dd-MM-yyyy" /></time>
                            </span>
                            <span class="m-widget6__text">
                                    ${holi.description}
                            </span>
                            <span class="m-widget6__text ">
                                <a href="/update-holiday/${holi.holidayId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Sửa">
                                    <i class="la la-edit"></i></a>
                            </span>
                            . <span class="m-widget6__text m--align-right m--font-boldest m--font-brand">
                                <a href="/delete-holiday/${holi.holidayId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Xóa"
                                   onclick="return confirm('Bạn có muốn xóa ngày nghỉ này không?');">
                                    <i class="la la-remove"></i></a>
                            </span>

                        </div>
                    </c:forEach>

                </div>

            </div>
        </div>
    </div>
    </div>
</div>
<div class="row">
    <div class = "col-md-5" style="margin-left:80px">
        <div class="m-portlet m-portlet--tab middle">
            <div class="m-portlet__head">
                <div class="m-portlet__head-caption">
                    <div class="m-portlet__head-title">
												<span class="m-portlet__head-icon m--hide">
													<i class="la la-gear"></i>
												</span>
                        <h3 class="m-portlet__head-text">
                            Cập nhật ngày làm việc
                        </h3>
                    </div>
                </div>
            </div>

            <!--begin::Form-->
            <form action="/update-workday" method="post" id="form-create1"  class="m-form m-form--fit m-form--label-align-right" enctype="multipart/form-data">
                <div class="m-portlet__body">
                    <c:choose>
                        <c:when test="${param.get('status').equals('successfull1')}">
                            <p class="message-register" style="color: rgb(22, 161, 255); font-size: 16px;text-align: center">Tạo ngày làm  thành công.</p>
                        </c:when>
                        <c:when test="${param.get('error').equals('fail1')}">
                            <p class="message-register" style="color: rgb(20, 157, 252); font-size: 16px;text-align: center">Xóa ngày làm thành công.</p>
                        </c:when>
                    </c:choose>

                    <div class="form-group m-form__group row">
                        <label for="WorkDayName" class="col-5 col-form-label">Ngày làm việc</label>
                        <div class="col-7">
                            <input class="form-control m-input" type="date"  id="WorkDayName" name = "workDay">
                        </div>
                    </div>
                    <div class="form-group m-form__group row">
                        <label for="WorkDayDescription" class="col-5 col-form-label">Nội dung ngày làm việc</label>
                        <div class="col-7">
                            <input class="form-control m-input" type="text" id="WorkDayDescription" name = "description">
                        </div>
                    </div>

                </div>
                <div class="m-portlet__foot m-portlet__foot--fit">
                    <div class="m-form__actions">
                        <c:if test="${status == 0}">
                        <div class="row">

                            <div class="col-2">
                            </div>
                            <div class="col-10">

                                    <button type="submit" class="btn btn-success">Submit</button>



                                <button type="reset" class="btn btn-secondary">Cancel</button>
                            </div>
                        </div>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="col-md-6" style="margin-left: 10px">
        <div class="m-portlet m-portlet--full-height ">
            <div class="m-portlet__head">
                <div class="m-portlet__head-caption">
                    <div class="m-portlet__head-title">
                        <h3 class="m-portlet__head-text">
                            Danh sách ngày làm linh hoạt
                        </h3>
                    </div>
                </div>

            </div>

            <div class="m-portlet__body">
                <div class="m-widget6">
                    <div class="m-widget6__head">

                        <div class="m-widget6__item">
													<span class="m-widget6__caption">
														Số thứ tự
													</span>
                            <span class="m-widget6__caption">
														Ngày làm
													</span>
                            <span class="m-widget6__caption ">
                                                            Mô tả ngày làm
													</span>
                            <span class="m-widget6__caption">Sửa</span>
                            <span class="m-widget6__caption m--align-right">Xóa</span>
                        </div>
                    </div>
                    <div class="m-widget6__body" style="position: relative; overflow: auto; width: 100%; max-height: 50vh;">
                        <c:forEach var="woda" items="${workingDays}" varStatus="loopCounter">
                            <div class="m-widget6__item">
                            <span class="m-widget6__text">
                                    ${loopCounter.count}
                            </span>
                                <span class="m-widget6__text">
                                    <time><fmt:formatDate value="${woda.workDay}" pattern="dd-MM-yyyy" /></time>
                            </span>
                                <span class="m-widget6__text">
                                        ${woda.description}
                                </span>
                                <span class="m-widget6__text ">
                                <a href="/update-workday/${woda.workDayId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Sửa">
                                    <i class="la la-edit"></i></a>
                            </span>
                                <span class="m-widget6__text m--align-right m--font-boldest m--font-brand">
                                <a href="/delete-workday/${woda.workDayId}" class="m-portlet__nav-link btn m-btn m-btn--hover-brand m-btn--icon m-btn--icon-only m-btn--pill" title="Xóa"
                                   onclick="return confirm('Bạn có muốn xóa ngày làm việc này không?');">
                                    <i class="la la-remove"></i></a>
                            </span>
                            </div>
                        </c:forEach>

                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<!--end:: Widgets/Sales States-->->