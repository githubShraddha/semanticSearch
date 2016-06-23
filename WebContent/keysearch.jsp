<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>KeySearch</title>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->


    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS: You can use this stylesheet to override any Bootstrap styles and/or apply your own styles -->
    <link href="css/custom.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript">

function valid(){
	var ss=document.keysrch.keyword.value;
	if(ss==""){
	
		alert("Plz fill Keyword!!");
		return false;
	}
}

</script>
</head>
<body>

<!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Logo and responsive toggle -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                	<span class="glyphicon glyphicon-fire"></span> 
                	
                </a>
            </div>
            <!-- Navbar links -->
            <div class="collapse navbar-collapse" id="navbar">
                <ul class="nav navbar-nav">
                	
                	<%
                	
//                 	request.getSession(false);
                	if(request.getSession().getAttribute("username")==null){
                		response.sendRedirect("index.jsp");
                	}else{
                		String ss=request.getSession().getAttribute("usertype").toString();
                		String uname=request.getSession().getAttribute("username").toString();
                		
                		System.out.println("in fl down "+ss);
                	if(ss.equals("Data Owner")){
                		%>
                	<li><a href="ownerhome.jsp" >Home</a></li>	
                    <li><a href="fileup.jsp">File Upload</a></li>
                    <li class="active"><a href="filedownload.jsp" class="selected">Download</a></li>
                    <%} else { %>
                     <li><a href="home.jsp" >Home</a></li>
                     <li class="active"><a href="keysearch.jsp" class="selected">KeySearch</a></li>
                     <li><a href="usrdownload.jsp" class="selected">Download</a></li>
                     
                    <%} %>
                     <li><a href="logout.jsp" >Logout</a></li>
<!--                     <li><a href="#services">Services</a></li> -->
<!--                     <li><a href="#gallery">Gallery</a></li> -->
<!--                     <li><a href="#contact" class="last">Contact</a></li> -->
                </ul>

    
       <!-- Search -->
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control">
					</div>
					<button type="submit" class="btn btn-default">Search</button>
				</form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
                

			<div class="jumbotron feature">
		<div class="container">
			<h1><span class="glyphicon glyphicon-equalizer"></span> SemanticSearch</h1>
		<!--	<p>Objectively innovate empowered manufactured products whereas parallel platforms.</p>
			<p><a class="btn btn-default" href="#">Engage Now</a></p> -->
		</div>
	</div>
		
     <!-- Content -->
 <div class="container">

        <!-- Heading -->
        <div class="row">
            <div class="col-lg-12">
              <!--  <h1 class="page-header">Superior Collaboration
                    <small>Visualize Quality</small>
                </h1> -->
               
               <center style="color: black">
               
                	<h2>KeySearch</h2>   
                	 <p>
                    <form action="KeywordSearchServlet" name="keysrch" method="post">
                    <table>
            			<tbody>
            			 <tr>
			            
            			    <tr><td><br></td></tr>
			                <tr>
			                    <td>Keyword: &nbsp;</td>
			                    <td><input type="text" class="form-control" name="keyword" placeholder="Keyword"></td>
			                </tr>
			                <tr><td><br></td></tr>
			               
			                <tr>
		                <td></td>
<!-- 			                    <td><input class="btn btn-colr" type="button" value="Search" onclick="valid()" src="images/upld.png"/> <img src="images/upld.png" height="25" width="25"/></td> -->
<td><button class="btn btn-colr" type="submit" onclick="valid()">Search&nbsp;<img alt="btn" style="height: 30px;width: 30px"  src="images/magnifier13.png"></button> 

			                </tr>
            			</tbody>
        			</table>
        			</form>
        			<br/><br/>
        			<table border="1" width="30%">
                    <thead>
                    <th>Filename</th>
                    <th>Download</th>
                    </thead>
            			<tbody>
        			<%
        			  String msg=(String)request.getAttribute("msg");
        			String list=(String)session.getAttribute("list");
        			String dolist=(String)session.getAttribute("dlist");
        			if(list!=null && dolist!=null){
        				String s=request.getParameter("keyword");
//         				System.out.println(" in indx :"+list);
        				
        				String fillist=list.substring(1, (list.length()-1));
        				String dwlist=dolist.substring(1, (dolist.length()-1));
//         				System.out.println(" in indx :"+fillist);
        				String[] flist=fillist.split(",");
        				String[] dlist=dwlist.split(",");
//         				System.out.println(" in dindx :"+dwlist);
        			%>
<%--         			<center><h5 style="color: red"><%=fillist%> </h5></center> --%>
        			<% for(int i=0;i<flist.length;i++){ 
        				System.out.println(" in flist["+i+"]indx :"+flist[i]);
        				System.out.println(" in dlist["+i+"]indx :"+dlist[i]);
        			%>
        			<tr>
			                    <td><%=flist[i]%></td>
			                    <td><a href="Keydatarequest?filename=<%=flist[i].trim()%>&downer=<%=dlist[i].trim()%>&user=<%=uname%>">Download Request</a></td>
			                </tr>
<!-- 			               <img src="images/dwnicon.png"  height="20" width="20"/> -->
            		
        			<%
        			}
        			  session.removeAttribute("list");
        			  session.removeAttribute("dlist");
        			
        			} %>
        			
        				</tbody>
        			</table>
                    </p>
 </div>
        </div>
        <!-- /.row -->
					
  </div>
    <!-- /.container -->
                 <br/><br/>
           <!-- footer -->      
  <footer>
		<div class="footer-blurb">
			<div class="container">
		<!--		<div class="row">
					<div class="col-sm-4 footer-blurb-item">
						<h3><span class="glyphicon glyphicon-fire"></span> Dynamically Procrastinate</h3>
						<p>Collaboratively administrate empowered markets via plug-and-play networks. Dynamically procrastinate B2C users after installed base benefits. Dramatically visualize customer directed convergence without revolutionary ROI.</p>
						<p><a class="btn btn-default" href="#">Procrastinate</a></p>
					</div>
					<div class="col-sm-4 footer-blurb-item">
						<h3><span class="glyphicon glyphicon-cloud-upload"></span> Efficiently Unleash</h3>
						<p>Dramatically maintain clicks-and-mortar solutions without functional solutions. Efficiently unleash cross-media information without cross-media value. Quickly maximize timely deliverables for real-time schemas. </p>
						<p><a class="btn btn-default" href="#">Unleash</a></p>
					</div>
					<div class="col-sm-4 footer-blurb-item">
						<h3><span class="glyphicon glyphicon-leaf"></span> Completely Synergize</h3>
						<p>Professionally cultivate one-to-one customer service with robust ideas. Completely synergize resource taxing relationships via premier niche markets. Dynamically innovate resource-leveling customer service for state of the art customer service.</p>
						<p><a class="btn btn-default" href="#">Synergize</a></p>
					</div>

				</div> -->
				<!-- /.row -->	
			</div> 
        </div>
        
        <div class="small-print">
        	<div class="container">
        		<p><a href="#">Terms &amp; Conditions</a> | <a href="#">Privacy Policy</a> | <a href="#">Contact</a></p>
        		<p>Copyright &copy; Example.com 2015 </p>
        	</div>
        </div>
	</footer>   
	
	<!-- /.footer -->
<%} %>
</body>
</html>