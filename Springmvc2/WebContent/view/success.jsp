<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isErrorPage="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
                    
                     <br>
                     <!--  
                     <fmt:message key="resource.welcome"></fmt:message>
                     <fmt:message key="resource.exit"></fmt:message>-->
                     <c:forEach items="${requestScope.errors} " var="error">
                     				${error}
                     				<br/>
                     </c:forEach>
                     
                     <br>
                        ---------request-----------<br>
                     ${requestScope.Student1.id}-${requestScope.Student1.name}  <br>
                      ${requestScope.Student2.id}-${requestScope.Student2.name}<br>
                       ${requestScope.Student3.id}-${requestScope.Student3.name}<br>
                        ${requestScope.Student4.id}-${requestScope.Student4.name}<br>
                     ---------session-----------<br>   
                      
                     ${sessionScope.Student1.id}-${sessionScope.Student1.name}  <br>
                      ${sessionScope.Student2.id}-${sessionScope.Student2.name}<br>
                       ${sessionScope.Student3.id}-${sessionScope.Student3.name}<br>
                        ${sessionScope.Student4.id}-${sessionScope.Student4.name}<br>
                     
</body>
</html>