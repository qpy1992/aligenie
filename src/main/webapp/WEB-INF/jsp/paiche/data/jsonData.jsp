﻿<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file = "GenJsonData.jsp" %>
<%
 String QuerySQL = "select * from "+request.getParameter("table")+" where "+request.getParameter("true");
 JSON_GenOneRecordset(response, QuerySQL);
%> 
