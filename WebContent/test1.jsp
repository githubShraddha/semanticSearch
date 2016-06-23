<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>test1</title>
<SCRIPT type="text/javascript">
function callurl()
{
	 alert("PathName  ="+ window.location.href);
	 var url=window.location.href;
// 	 var url=document.referrer;
	 window.open("http://localhost:8080/SemanticSearch/test.jsp?url="+url,"_self");
// 	 window.open("http://www.google.com?url="+url,"_self");

}
</SCRIPT>
</head>
<body>

<h1>Welcome test1</h1>
<b> <a href="test.jsp">Click here</a> </b><br>
<b> <a href="servercrash.jsp">Crash here</a> </b>

<input type="button" onclick="callurl()" value="submit"/>
<b> <a href="#" onclick="callurl()">QwerCrash here</a> </b>
</body>
</html>