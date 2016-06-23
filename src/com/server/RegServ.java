package com.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServ
 */
public class RegServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		String user_type= request.getParameter("user_type");
		try {
		
//			Class.forName("com.mysql.jdbc.Driver"); 
//		
//			String url ="jdbc:mysql://localhost:3306/semanticsearch";
//		
//			Connection con=DriverManager.getConnection(url,"root","root");
			
			 ServletContext sc=getServletContext();
	    		String url=sc.getInitParameter("url");
	    		String driver=sc.getInitParameter("driver");
	    		String password=sc.getInitParameter("password");
	    		String username=sc.getInitParameter("username");
	    		
	    		Class.forName(driver);
	    		Connection con=DriverManager.getConnection(url,username,password);
		 // out.println("Connected to the database");
		       Statement st;
			if(fname.length()!=0 && lname.length()!=0 && email.length()!=0 && pass.length()!=0 && !user_type.trim().equals("Select User Type") ){
				st = con.createStatement();
				
				 int i=st.executeUpdate("insert into registration(fname,lname,email,password,user_type)" +
				 		" values('"+fname+"','"+lname+"','"+email+"','"+pass+"', '"+user_type+"')");
			       System.out.println("Data is inserted successfully");
			       response.sendRedirect("index.jsp");
			       
			}else{
	        	
	        	request.setAttribute("msg", "Plz fill all fields!!!");
//	        	System.out.println("in else msg ");
	        	request.getRequestDispatcher("register.jsp").forward(request, response);
	        } 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
		      

	}

}
