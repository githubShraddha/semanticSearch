<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Register</title>

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
                	<li><a href="index.jsp" >Login</a></li>
                    <li class="active"><a href="register.jsp" >Register</a></li>
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
               
               <center>
				
                	<h2>Registeration</h2>   
                    <p>
                    <form action="RegServ" method="post" style="color: black">
                    <table>
            			<tbody>
            			    <tr>
			                    <td>First Name:&nbsp;</td>
			                    <td><input type="text" name="fname" placeholder="First Name"></td>
			                </tr>
			                <tr><td><br></td></tr>
			                 <tr>
			                    <td>Last Name: &nbsp;</td>
			                    <td><input type="text" name="lname" placeholder="Last Name"></td>
			                </tr>
			                <tr><td><br></td></tr>
			                 <tr>
			                    <td>Email: &nbsp;</td>
			                    <td><input type="text" name="email" placeholder="Email"></td>
			                </tr>
			                <tr><td><br></td></tr>
			                
			                <tr>
			                    <td>Passowrd &nbsp;</td>
			                    <td><input type="password" name="password" placeholder="Password"></td>
			                </tr>
			                <tr><td><br></td></tr>
			                
			                <tr>
			                    <td>User Type &nbsp;</td>
			                    <td>
			                    	<select name="user_type">
			                    		<option>Select User Type</option>
			                    		<option>Data Owner</option>
			                    		<option>User</option>
			                    	</select>
			                    </td>
			                </tr>
			                <tr><td><br></td></tr>
			                <tr>
			                    <td></td>
			                    <td><input type="submit" value="Register"></td>
			                </tr>
            			</tbody>
        			</table>
        			</form></center>
                    </p>
                    <%
        			  String msg=(String)request.getAttribute("msg");
        			if(msg!=null){
//         				System.out.println(" in reg :"+msg);
        			%>
        			<center><h5 style="color: red"><%=msg %> </h5></center>
        			<%} %>
                    
                    </div>
        </div>
        <!-- /.row -->
					
  </div>
    <!-- /.container -->
                 
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
</body>
</html>