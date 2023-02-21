<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page pageEncoding="UTF-8" %><%--// đọc chữ tiếng việt--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8" />
    <title><tiles:getAsString name="title" /></title>
    <meta name="description" content="Latest updates and statistic charts">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
    <link href="/style.css" rel="stylesheet" type="text/css" />
    <link href="/image/sweetalert2/dist/sweetalert2.min.css" rel="stylesheet" >

    <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js" type="text/javascript"></script>

    <!--begin::Web font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet">

    <!--begin::Global Theme Styles -->
    <link href="/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />

<%--    <!--RTL version:<link href="vendors/base/vendors.bundle.rtl.css" rel="stylesheet" type="text/css" />-->--%>
    <link href="/demo/demo2/base/style.bundle.css" rel="stylesheet" type="text/css" />

    <!--RTL version:<link href="demo/demo2/base/style.bundle.rtl.css" rel="stylesheet" type="text/css" />-->

    <!--end::Global Theme Styles -->
    <link rel="shortcut icon" href="/demo/demo2/media/img/logo/favicon.ico" />
</head>


<tiles:insertAttribute name="header" />

<div style="background-color:#DCDCDC; height: 1000%">
<div style="margin-top: 10px;margin-bottom: 40px">
    <tiles:insertAttribute name="body" />
</div>
    <tiles:insertAttribute name="footer" />

</div>
<!--begin::Global Theme Bundle -->
<script src="/vendors/base/vendors.bundle.js" type="text/javascript"></script>
<script src="/demo/demo2/base/scripts.bundle.js" type="text/javascript"></script>

<script src="/image/sweetalert2/dist/sweetalert2.min.js"></script>

<!--end::Global Theme Bundle -->

<!--begin::Page Scripts -->
<script src="/app/js/dashboard.js" type="text/javascript"></script>

</html>