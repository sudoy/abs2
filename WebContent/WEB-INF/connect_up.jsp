<%@page import="java.sql.*,javax.naming.*,javax.sql.*"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>データベース接続</title>
</head>
<body>

<%
	Connection con = null;
	try{
	Context initContext = new InitialContext();
	Context envContext = (Context)initContext.lookup("java:/comp/env");
	DataSource ds = (DataSource)envContext.lookup("abs2");	
	con = ds.getConnection();
	}catch(Exception e){
		throw new ServletException(e);
	}finally{
		try{
			if(con != null){con.close();}
		}catch(Exception e){}
	}

%>
データベースの接続に成功

</body>
</html>