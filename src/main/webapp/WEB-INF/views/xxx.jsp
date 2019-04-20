<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<div class="action">
    <iframe  name=rightFrame style="WIDTH: 100%; HEIGHT: 100%" <%--src="../../welcome.jsp"--%>
             frameborder=0></iframe>
</div>
