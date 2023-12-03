<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--
<%! %>: Khai bao bien
<% %>: The xu ly logic code
<%= %>: Xuat Gia tri cua bien ra HTML



 --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>



<body>
<%! int count=0;%>
<% count++; %>
	Hello Servlet Cuong
	<%
	if(count%2==0){
	%>
	<h1 style="color: red"><%=count %></h1>
	<% }else{%>
	<h1 style="color: blue"><%=count %></h1><%} %>
	
	
</body>
</html>