<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>

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
                	<li class="active"><a href="index.jsp" class="selected">Login</a></li>
                    <li><a href="register.jsp">Register</a></li>
                   <!--   <li class="active">
                        <a href="#">Home</a>
                    </li>
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#">Products</a>
                    </li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Services <span class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="about-us">
							<li><a href="#">Engage</a></li>
							<li><a href="#">Pontificate</a></li>
							<li><a href="#">Synergize</a></li>
						</ul>
					</li> -->
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
                <h1>Login</h1>  
                <p >
                
                                    <form action="Login" method="post" style="color: black">
                    <table style="padding: 12px">
            			<tbody>
			                <tr>
			                    <td>Username &nbsp;</td>
			                    <td><input type="text" name="username" placeholder="UserName"></td>
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
			                    <td><input type="submit" value="Login"></td>
			                </tr>
            			</tbody>
        			</table>
        			</form>
        			</center>
        			<%
        			  String msg=(String)request.getAttribute("msg");
        			if(msg!=null){
        				System.out.println(" in indx :"+msg);
        			%>
        			<center><h5 style="color: red"><%=msg %> </h5></center>
        			<%} %>
        			<center><a href="register.jsp">New Registration!</a></center>
                
                </p>
            </div>
        </div>
        <!-- /.row -->

        <!-- Feature Row -->
      <!--  <div class="row">
            <article class="col-md-4 article-intro">
                <a href="#">
                    <img class="img-responsive img-rounded" src="holder.js/700x300" alt="">
                </a>
                <h3>
                    <a href="#">Efficiently Unleash</a>
                </h3>
                <p>Collaboratively administrate empowered markets via plug-and-play networks. Dynamically procrastinate B2C users after installed base benefits. Dramatically visualize customer directed convergence without revolutionary ROI.</p>
            </article>
            <article class="col-md-4 article-intro">
                <a href="#">
                    <img class="img-responsive img-rounded" src="holder.js/700x300" alt="">
                </a>
                <h3>
                    <a href="#">Completely Synergize</a>
                </h3>
                <p>Dramatically maintain clicks-and-mortar solutions without functional solutions. Efficiently unleash cross-media information without cross-media value. Quickly maximize timely deliverables for real-time schemas.</p>
            </article>

            <article class="col-md-4 article-intro">
                <a href="#">
                    <img class="img-responsive img-rounded" src="holder.js/700x300" alt="">
                </a>
                <h3>
                    <a href="#">Dynamically Procrastinate</a>
                </h3>
                <p>Professionally cultivate one-to-one customer service with robust ideas. Completely synergize resource taxing relationships via premier niche markets. Dynamically innovate resource-leveling customer service for state of the art customer service.</p>
            </article>
        </div> -->
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