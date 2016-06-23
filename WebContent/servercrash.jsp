<%-- 
    Document   : servercrash
    Created on : Apr 10, 2014, 10:30:23 AM
    Author     : Godwit-3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function callbackupserver()
            {
            	 alert("PathName  ="+ document.referrer);
            	 
//             	 var url=window.location.pathname;
//             	 window.open("http://www.google.com", "_top");
            	 
             alert("sorrryyyy Server Crash .....!!!! ....You redirect to backup server");
//             window.location.href="http://localhost:8080/Backup_CPABE/";
             //window.open("http://localhost:8080/CPABE/Show_owners_1.jsp","_self");
//             window.open("http://192.168.1.135:8080/reg/index.jsp","_blank");
            window.open("http://localhost:8080/test1.jsp","_blank");
//              window.open("http://http://env-6937392.j.layershift.co.uk/MONA_2/masterlogin.jsp","_self");
//             window.location.href="http://192.168.1.135:8080/reg/index.jsp";
//             alert("PathName  ="+ window.location.pathname);
            }
        </script>
    </head>
    <body onload="callbackupserver()">
        <h2>Sorrryyyy Server Crash .....!!!! ....You redirect to backup server!</h2>
        <%//response.sendRedirect("Show_owners.jsp");%>
        
    </body>
</html>
