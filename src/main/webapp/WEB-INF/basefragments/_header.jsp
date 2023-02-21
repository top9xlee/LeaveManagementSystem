<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<header id="m_header" class="m-grid__item m-header " m-minimize="minimize" m-minimize-offset="200"
        m-minimize-mobile-offset="200">
    <div class="m-header__bottom" style=" height: 80px">
        <div class="m-container m-container--responsive m-container--xxl m-container--full-height m-page__container">
            <div class="m-stack m-stack--ver m-stack--desktop">

                <!-- begin::Horizontal Menu -->
                <div class="m-stack__item m-stack__item--middle m-stack__item--fluid">
                    <button class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-light "
                            id="m_aside_header_menu_mobile_close_btn " style="font-size: 17px;"><i
                            class="la la-close"></i></button>
                    <div id="m_header_menu"
                         class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-dark m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-light m-aside-header-menu-mobile--submenu-skin-light ">
                        <ul class="m-menu__nav  m-menu__nav--submenu-arrow ">
                            <li class="m-menu__item  m-menu__item--active " aria-haspopup="true">
                                <a href="" class="m-menu__link ">
                                    <div class="l9i9rfs">
                                        <a class="l1gk4jyx" href="/user" style="justify-content: unset;">
                                            <picture>
                                                <source type="image/webp"
                                                        srcset="/image/logo.png">
                                                <img class="m-header-menu m-aside-header-menu-mobile"
                                                     src="/image/logo.png"
                                                     alt="Chợ Tốt"
                                                     style="height: 70px;width: 90px; object-fit: contain;">
                                            </picture>
                                        </a>
                                    </div>
                                </a>
                            </li>
                            <li class="m-menu__item  m-menu__item--active " aria-haspopup="true">
                                <a href="" class="m-menu__link ">
                                    <div class="l9i9rfs">
                                        <a class="l1gk4jyx" href="/user" style="justify-content: unset;">
                                            <span class="m-menu__item-here"></span><span
                                                class="m-menu__link-text"
                                                style="color: #C0C0C0;font-size: 19px;font-weight: 400">Dashboard</span>
                                        </a>
                                    </div>
                                </a>
                            </li>


                            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                                <%--                            <li class="m-menu__item  m-menu__item--submenu m-menu__item--rel" m-menu-submenu-toggle="click" aria-haspopup="true"><a href="javascript:;" class="m-menu__link m-menu__toggle" title="Chức năng quản trị">--%>
                                <%--                                <i class = "m-menu__link-icon flaticon-userEntity"></i>--%>
                                <%--                                <span class="m-menu__item-here"></span><span--%>
                                <%--                                    class="m-menu__link-text"style="color: #C0C0C0;font-size: 19px">Tài khoản</span>--%>
                                <%--                                <i class="m-menu__hor-arrow la la-angle-down">--%>

                                <%--                                </i><i class="m-menu__ver-arrow la la-angle-right"></i></a>--%>
                                <%--                                <div class="m-menu__submenu m-menu__submenu--classic m-menu__submenu--left"><span class="m-menu__arrow m-menu__arrow--adjust"></span>--%>
                                <%--                                    <ul class="m-menu__subnav ">--%>

                                <%--&lt;%&ndash;                                        <li class="m-menu__item " m-menu-link-redirect="1" aria-haspopup="true"><a href="/changepw" class="m-menu__link "><i class="m-menu__link-icon flaticon-userEntity-settings"></i><span class="m-menu__link-text">Chỉnh sửa thông tin</span></a></li>&ndash;%&gt;--%>
                                <%--                                    </ul>--%>
                                <%--                                </div>--%>
                                <%--                            </li>--%>

                                <li class="m-menu__item  m-menu__item--active " aria-haspopup="true">
                                    <a href="" class="m-menu__link ">
                                        <div class="l9i9rfs">
                                            <a class="l1gk4jyx" href="/getAllUserEntityEnable"
                                               style="justify-content: unset;">
                                                <span class="m-menu__item-here"></span><span
                                                    class="m-menu__link-text"
                                                    style="color: #C0C0C0;font-size: 19px;font-weight: 400">Quản lí tài khoản</span>
                                            </a>
                                        </div>
                                    </a>
                                </li>

                                <li class="m-menu__item  m-menu__item--submenu m-menu__item--rel"
                                    m-menu-submenu-toggle="click" m-menu-link-redirect="1" aria-haspopup="true"><a
                                        href="javascript:;" class="m-menu__link m-menu__toggle"
                                        title="Chức năng người dùng">
                                        <%--                                <span class="m-menu__item-here"></span><a href="/login" class="m-menu__link "><i class="m-menu__link-icon flaticon-laptop"></i><span class="m-menu__link-text" style="color: #C0C0C0;font-size: 19px">Các chức năng</span></a>--%>
                                    <span class="m-menu__item-here"></span><span class="m-menu__link-text"
                                                                                 style="color: #C0C0C0;font-size: 19px">Các Chức Năng</span><i
                                        class="m-menu__hor-arrow la la-angle-down"></i><i
                                        class="m-menu__ver-arrow la la-angle-right"></i></a>
                                    <div class="m-menu__submenu  m-menu__submenu--fixed m-menu__submenu--left"
                                         style="width:300px"><span class="m-menu__arrow m-menu__arrow--adjust"></span>
                                        <div class="m-menu__subnav">
                                            <ul class="m-menu__content">
                                                <li class="m-menu__item">
                                                    <h3 class="m-menu__heading m-menu__toggle"><span
                                                            class="m-menu__link-text">Chức năng</span><i
                                                            class="m-menu__ver-arrow la la-angle-right"></i></h3>
                                                    <ul class="m-menu__inner">
                                                        <li class="m-menu__item " m-menu-link-redirect="1"
                                                            aria-haspopup="true"><a href="/register"
                                                                                    class="m-menu__link "><i
                                                                class="m-menu__link-icon flaticon-users-1"></i><span
                                                                class="m-menu__link-text">Đăng kí nhân viên</span></a>
                                                        </li>
                                                        <li class="m-menu__item " m-menu-link-redirect="1"
                                                            aria-haspopup="true"><a href="/create-department"
                                                                                    class="m-menu__link "><i
                                                                class="m-menu__link-icon flaticon-map"></i><span
                                                                class="m-menu__link-text">Quản lí phòng ban</span></a>
                                                        </li>
                                                        <li class="m-menu__item " m-menu-link-redirect="1"
                                                            aria-haspopup="true"><a href="/create-holiday"
                                                                                    class="m-menu__link "><i
                                                                class="m-menu__link-icon flaticon-calendar"></i><span
                                                                class="m-menu__link-text">Quản lí ngày nghỉ</span></a>
                                                        </li>
                                                        <li class="m-menu__item " m-menu-link-redirect="1"
                                                            aria-haspopup="true"><a href="/telegram"
                                                                                    class="m-menu__link "><i
                                                                class="m-menu__link-icon flaticon-chat-1"></i><span
                                                                class="m-menu__link-text">Quản lí Group</span></a>
                                                        </li>
                                                        <li class="m-menu__item " m-menu-link-redirect="1"
                                                            aria-haspopup="true"><a href="/export"
                                                                                    class="m-menu__link "><i
                                                                class="m-menu__link-icon flaticon-graphic-2"></i><span
                                                                class="m-menu__link-text">Tạo báo cáo</span></a></li>
                                                    </ul>
                                                </li>

                                        </div>
                                    </div>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>

                <!-- end::Horizontal Menu -->
                <div class="m-stack__item m-stack__item--fluid m-header-head" id="m_header_nav">
                    <div id="m_header_topbar" class="m-topbar  m-stack m-stack--ver m-stack--general">
                        <div class="m-stack__item m-topbar__nav-wrapper">
                            <ul class="m-topbar__nav m-nav m-nav--inline">
                                <li class="m-nav__item m-topbar__user-profile m-topbar__user-profile--img  m-dropdown m-dropdown--medium m-dropdown--arrow m-dropdown--header-bg-fill m-dropdown--align-right m-dropdown--mobile-full-width m-dropdown--skin-light"
                                    m-dropdown-toggle="click">
                                    <a href="#" class="m-nav__link m-dropdown__toggle">
													<span class="m-topbar__userpic m--hide">
													</span>
                                        <span class="m-topbar__welcome" style="color: #DCDCDC">Hello, </span>
                                        <span class="m-topbar__username"
                                              style="font-size: 19px"> ${pageContext.request.userPrincipal.name}</span>

                                        <%--                                            <c:if test="${not empty pageContext.request.userPrincipal}">--%>

                                        <%--                                                <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">--%>

                                        <%--                                                    User ${pageContext.request.userPrincipal.name} in ADMIN Group--%>

                                        <%--                                                </c:if>--%>

                                        <%--                                            </c:if>--%>

                                        </span>
                                    </a>

                                    <div class="m-dropdown__wrapper">
                                        <span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
                                        <div class="m-dropdown__inner">
                                            <div class="m-dropdown__header m--align-center"
                                                 style="background: #0a6aa1 ;background-size: cover;">
                                                <div class="m-card-userEntity m-card-userEntity--skin-dark">
                                                    <div class="m-card-user__pic">

                                                    </div>
                                                    <div class="m-card-user__details">
                                                        <span class="m-card-user__name m--font-weight-500"
                                                              style="color: #DCDCDC">${pageContext.request.userPrincipal.name}</span>
                                                        o<%--                                                        <a href="" class="m-card-user__email m--font-weight-300 m-link"style="color: #DCDCDC">mark.andre@gmail.com</a>--%>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="m-dropdown__body">
                                                <div class="m-dropdown__content">
                                                    <ul class="m-nav m-nav--skin-light">
                                                        <li class="m-nav__section m--hide">
                                                            <span class="m-nav__section-text">Section</span>
                                                        </li>
                                                        <li class="m-nav__item">
                                                            <a href="/changepw" class="m-nav__link">
                                                                <i class="m-nav__link-icon flaticon-profile-1"></i>
                                                                <span class="m-nav__link-title">
																				<span class="m-nav__link-wrap">
																					<span class="m-nav__link-text"
                                                                                          style="color: #333">Thay đổi mật khẩu</span>
																				</span>
																			</span>
                                                            </a>
                                                        </li>
                                                        <li class="m-nav__separator m-nav__separator--fit">
                                                        </li>
                                                        <li class="m-nav__item">
                                                            <%--                                                            <a href="${pageContext.request.contextPath}/logout" class="btn m-btn--pill btn-secondary m-btn m-btn--custom m-btn--label-brand m-btn--bolder">Logout</a>--%>
                                                            <a href="/logout"
                                                               class="btn m-btn--pill btn-secondary m-btn m-btn--custom m-btn--label-brand m-btn--bolder">Logout</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <%--                                <li class="m-nav__item m-topbar__notifications m-topbar__notifications--img m-dropdown m-dropdown--large m-dropdown--header-bg-fill m-dropdown--arrow m-dropdown--align-center 	m-dropdown--mobile-full-width" m-dropdown-toggle="click"--%>
                                <%--                                    m-dropdown-persistent="1">--%>
                                <%--                                    <a href="#" class="m-nav__link m-dropdown__toggle" id="m_topbar_notification_icon">--%>
                                <%--                                        <span class="m-nav__link-badge m-badge m-badge--dot m-badge--dot-small m-badge--danger"></span>--%>
                                <%--                                        <span class="m-nav__link-icon">--%>
                                <%--														<span class="m-nav__link-icon-wrapper">--%>
                                <%--															<i class="flaticon-alarm"></i>--%>
                                <%--														</span>--%>
                                <%--													</span>--%>
                                <%--                                    </a>--%>
                                <%--                                    <div class="m-dropdown__wrapper">--%>
                                <%--                                        <span class="m-dropdown__arrow m-dropdown__arrow--center"></span>--%>
                                <%--                                        <div class="m-dropdown__inner">--%>
                                <%--                                            <div class="m-dropdown__header m--align-center" style="background: url(assets/app/media/img/misc/notification_bg.jpg); background-size: cover;">--%>
                                <%--                                                <span class="m-dropdown__header-title">9 New</span>--%>
                                <%--                                                <span class="m-dropdown__header-subtitle">UserEntity Notifications</span>--%>
                                <%--                                            </div>--%>
                                <%--                                            <div class="m-dropdown__body">--%>
                                <%--                                                <div class="m-dropdown__content">--%>
                                <%--                                                    <ul class="nav nav-tabs m-tabs m-tabs-line m-tabs-line--brand" role="tablist">--%>
                                <%--                                                        <li class="nav-item m-tabs__item">--%>
                                <%--                                                            <a class="nav-link m-tabs__link active" data-toggle="tab" href="#topbar_notifications_notifications" role="tab">--%>
                                <%--                                                                Alerts--%>
                                <%--                                                            </a>--%>
                                <%--                                                        </li>--%>
                                <%--                                                        <li class="nav-item m-tabs__item">--%>
                                <%--                                                            <a class="nav-link m-tabs__link" data-toggle="tab" href="#topbar_notifications_events" role="tab">Events</a>--%>
                                <%--                                                        </li>--%>
                                <%--                                                        <li class="nav-item m-tabs__item">--%>
                                <%--                                                            <a class="nav-link m-tabs__link" data-toggle="tab" href="#topbar_notifications_logs" role="tab">Logs</a>--%>
                                <%--                                                        </li>--%>
                                <%--                                                    </ul>--%>
                                <%--                                                    <div class="tab-content">--%>
                                <%--                                                        <div class="tab-pane active" id="topbar_notifications_notifications" role="tabpanel">--%>
                                <%--                                                            <div class="m-scrollable" data-scrollable="true" data-height="250" data-mobile-height="200">--%>
                                <%--                                                                <div class="m-list-timeline m-list-timeline--skin-light">--%>
                                <%--                                                                    <div class="m-list-timeline__items">--%>
                                <%--                                                                        <div class="m-list-timeline__item">--%>
                                <%--                                                                            <span class="m-list-timeline__badge -m-list-timeline__badge--state-success"></span>--%>
                                <%--                                                                            <span class="m-list-timeline__text">12 new userEntities registered</span>--%>
                                <%--                                                                            <span class="m-list-timeline__time">Just now</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                        <div class="m-list-timeline__item">--%>
                                <%--                                                                            <span class="m-list-timeline__badge"></span>--%>
                                <%--                                                                            <span class="m-list-timeline__text">System shutdown <span class="m-badge m-badge--success m-badge--wide">pending</span></span>--%>
                                <%--                                                                            <span class="m-list-timeline__time">14 mins</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                        <div class="m-list-timeline__item">--%>
                                <%--                                                                            <span class="m-list-timeline__badge"></span>--%>
                                <%--                                                                            <span class="m-list-timeline__text">New invoice received</span>--%>
                                <%--                                                                            <span class="m-list-timeline__time">20 mins</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                        <div class="m-list-timeline__item">--%>
                                <%--                                                                            <span class="m-list-timeline__badge"></span>--%>
                                <%--                                                                            <span class="m-list-timeline__text">DB overloaded 80% <span class="m-badge m-badge--info m-badge--wide">settled</span></span>--%>
                                <%--                                                                            <span class="m-list-timeline__time">1 hr</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                        <div class="m-list-timeline__item">--%>
                                <%--                                                                            <span class="m-list-timeline__badge"></span>--%>
                                <%--                                                                            <span class="m-list-timeline__text">System error - <a href="#" class="m-link">Check</a></span>--%>
                                <%--                                                                            <span class="m-list-timeline__time">2 hrs</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                        <div class="m-list-timeline__item m-list-timeline__item--read">--%>
                                <%--                                                                            <span class="m-list-timeline__badge"></span>--%>
                                <%--                                                                            <span href="" class="m-list-timeline__text">New order received <span class="m-badge m-badge--danger m-badge--wide">urgent</span></span>--%>
                                <%--                                                                            <span class="m-list-timeline__time">7 hrs</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                        <div class="m-list-timeline__item m-list-timeline__item--read">--%>
                                <%--                                                                            <span class="m-list-timeline__badge"></span>--%>
                                <%--                                                                            <span class="m-list-timeline__text">Production server down</span>--%>
                                <%--                                                                            <span class="m-list-timeline__time">3 hrs</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                        <div class="m-list-timeline__item">--%>
                                <%--                                                                            <span class="m-list-timeline__badge"></span>--%>
                                <%--                                                                            <span class="m-list-timeline__text">Production server up</span>--%>
                                <%--                                                                            <span class="m-list-timeline__time">5 hrs</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                    </div>--%>
                                <%--                                                                </div>--%>
                                <%--                                                            </div>--%>
                                <%--                                                        </div>--%>
                                <%--                                                        <div class="tab-pane" id="topbar_notifications_events" role="tabpanel">--%>
                                <%--                                                            <div class="m-scrollable" data-scrollable="true" data-height="250" data-mobile-height="200">--%>
                                <%--                                                                <div class="m-list-timeline m-list-timeline--skin-light">--%>
                                <%--                                                                    <div class="m-list-timeline__items">--%>
                                <%--                                                                        <div class="m-list-timeline__item">--%>
                                <%--                                                                            <span class="m-list-timeline__badge m-list-timeline__badge--state1-success"></span>--%>
                                <%--                                                                            <a href="" class="m-list-timeline__text">New order received</a>--%>
                                <%--                                                                            <span class="m-list-timeline__time">Just now</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                        <div class="m-list-timeline__item">--%>
                                <%--                                                                            <span class="m-list-timeline__badge m-list-timeline__badge--state1-danger"></span>--%>
                                <%--                                                                            <a href="" class="m-list-timeline__text">New invoice received</a>--%>
                                <%--                                                                            <span class="m-list-timeline__time">20 mins</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                        <div class="m-list-timeline__item">--%>
                                <%--                                                                            <span class="m-list-timeline__badge m-list-timeline__badge--state1-success"></span>--%>
                                <%--                                                                            <a href="" class="m-list-timeline__text">Production server up</a>--%>
                                <%--                                                                            <span class="m-list-timeline__time">5 hrs</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                        <div class="m-list-timeline__item">--%>
                                <%--                                                                            <span class="m-list-timeline__badge m-list-timeline__badge--state1-info"></span>--%>
                                <%--                                                                            <a href="" class="m-list-timeline__text">New order received</a>--%>
                                <%--                                                                            <span class="m-list-timeline__time">7 hrs</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                        <div class="m-list-timeline__item">--%>
                                <%--                                                                            <span class="m-list-timeline__badge m-list-timeline__badge--state1-info"></span>--%>
                                <%--                                                                            <a href="" class="m-list-timeline__text">System shutdown</a>--%>
                                <%--                                                                            <span class="m-list-timeline__time">11 mins</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                        <div class="m-list-timeline__item">--%>
                                <%--                                                                            <span class="m-list-timeline__badge m-list-timeline__badge--state1-info"></span>--%>
                                <%--                                                                            <a href="" class="m-list-timeline__text">Production server down</a>--%>
                                <%--                                                                            <span class="m-list-timeline__time">3 hrs</span>--%>
                                <%--                                                                        </div>--%>
                                <%--                                                                    </div>--%>
                                <%--                                                                </div>--%>
                                <%--                                                            </div>--%>
                                <%--                                                        </div>--%>
                                <%--                                                        <div class="tab-pane" id="topbar_notifications_logs" role="tabpanel">--%>
                                <%--                                                            <div class="m-stack m-stack--ver m-stack--general" style="min-height: 180px;">--%>
                                <%--                                                                <div class="m-stack__item m-stack__item--center m-stack__item--middle">--%>
                                <%--                                                                    <span class="">All caught up!<br>No new logs.</span>--%>
                                <%--                                                                </div>--%>
                                <%--                                                            </div>--%>
                                <%--                                                        </div>--%>
                                <%--                                                    </div>--%>
                                <%--                                                </div>--%>
                                <%--                                            </div>--%>
                                <%--                                        </div>--%>
                                <%--                                    </div>--%>
                                <%--                                </li>--%>
                                <%--                                <li class="m-nav__item m-topbar__quick-actions m-topbar__quick-actions--img m-dropdown m-dropdown--large m-dropdown--header-bg-fill m-dropdown--arrow m-dropdown--align-right m-dropdown--align-push m-dropdown--mobile-full-width m-dropdown--skin-light"--%>
                                <%--                                    m-dropdown-toggle="click">--%>
                                <%--                                    <a href="#" class="m-nav__link m-dropdown__toggle">--%>
                                <%--                                        <span class="m-nav__link-badge m-badge m-badge--dot m-badge--info m--hide"></span>--%>
                                <%--                                        <span class="m-nav__link-icon">--%>
                                <%--														<span class="m-nav__link-icon-wrapper">--%>
                                <%--															<i class="flaticon-share"></i>--%>
                                <%--														</span>--%>
                                <%--													</span>--%>
                                <%--                                    </a>--%>
                                <%--                                    <div class="m-dropdown__wrapper">--%>
                                <%--                                        <span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>--%>
                                <%--                                        <div class="m-dropdown__inner">--%>
                                <%--                                            <div class="m-dropdown__header m--align-center" style="background: url(/app/media/img/misc/quick_actions_bg.jpg); background-size: cover;">--%>
                                <%--                                                <span class="m-dropdown__header-title">Quick Actions</span>--%>
                                <%--                                                <span class="m-dropdown__header-subtitle">Shortcuts</span>--%>
                                <%--                                            </div>--%>
                                <%--                                            <div class="m-dropdown__body m-dropdown__body--paddingless">--%>
                                <%--                                                <div class="m-dropdown__content">--%>
                                <%--                                                    <div class="m-scrollable" data-scrollable="false" data-height="380" data-mobile-height="200">--%>
                                <%--                                                        <div class="m-nav-grid m-nav-grid--skin-light">--%>
                                <%--                                                            <div class="m-nav-grid__row">--%>
                                <%--                                                                <a href="#" class="m-nav-grid__item">--%>
                                <%--                                                                    <i class="m-nav-grid__icon flaticon-file"></i>--%>
                                <%--                                                                    <span class="m-nav-grid__text">Generate Report</span>--%>
                                <%--                                                                </a>--%>
                                <%--                                                                <a href="#" class="m-nav-grid__item">--%>
                                <%--                                                                    <i class="m-nav-grid__icon flaticon-time"></i>--%>
                                <%--                                                                    <span class="m-nav-grid__text">Add New Event</span>--%>
                                <%--                                                                </a>--%>
                                <%--                                                            </div>--%>
                                <%--                                                            <div class="m-nav-grid__row">--%>
                                <%--                                                                <a href="#" class="m-nav-grid__item">--%>
                                <%--                                                                    <i class="m-nav-grid__icon flaticon-folder"></i>--%>
                                <%--                                                                    <span class="m-nav-grid__text">Create New Task</span>--%>
                                <%--                                                                </a>--%>
                                <%--                                                                <a href="#" class="m-nav-grid__item">--%>
                                <%--                                                                    <i class="m-nav-grid__icon flaticon-clipboard"></i>--%>
                                <%--                                                                    <span class="m-nav-grid__text">Completed Tasks</span>--%>
                                <%--                                                                </a>--%>
                                <%--                                                            </div>--%>
                                <%--                                                        </div>--%>
                                <%--                                                    </div>--%>
                                <%--                                                </div>--%>
                                <%--                                            </div>--%>
                                <%--                                        </div>--%>
                                <%--                                    </div>--%>
                                <%--                                </li>--%>
                                <%--                                <li id="m_quick_sidebar_toggle" class="m-nav__item">--%>
                                <%--                                    <a href="#" class="m-nav__link m-dropdown__toggle">--%>
                                <%--													<span class="m-nav__link-icon m-nav__link-icon--aside-toggle">--%>
                                <%--														<span class="m-nav__link-icon-wrapper"><i class="flaticon-grid-menu"></i></span>--%>
                                <%--													</span>--%>
                                <%--                                    </a>--%>
                                <%--                                </li>--%>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</header>

<script src="/style.css" type="text/css"></script>