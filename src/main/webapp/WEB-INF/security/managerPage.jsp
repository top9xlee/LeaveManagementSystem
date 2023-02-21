<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="_menu1.jsp" />

<h2>Manager Page</h2>


<h3>Welcome : ${pageContext.request.userPrincipal.name}</h3>

<b>Trang web này được bảo vệ</b>
</body>
</html>