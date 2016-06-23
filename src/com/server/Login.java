package com.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		String uname= request.getParameter("username");
		String pass= request.getParameter("password");
		String user_type= request.getParameter("user_type");
		//out.println(uname);
		//out.println(pass);
		
	
	try {
		
		HttpSession session=request.getSession();
//		Class.forName("com.mysql.jdbc.Driver"); 
//		
//		String url ="jdbc:mysql://localhost:3306/semanticsearch";
//	
//		Connection con=DriverManager.getConnection(url,"root","root");
		
		ServletContext sc=getServletContext();
		String url=sc.getInitParameter("url");
		String driver=sc.getInitParameter("driver");
		String password=sc.getInitParameter("password");
		String username=sc.getInitParameter("username");
		
		Class.forName(driver);
		Connection con=DriverManager.getConnection(url,username,password);
		
		Statement st = con.createStatement();
		 String sql = "select * from registration where email='"+uname+"' AND password='"+pass+"' and user_type='"+user_type+"'";
//	        System.out.println("SQL :"+sql);
	        ResultSet rst = st.executeQuery(sql);
	        String usertype;
	        if(rst.next()){
	        	
	        		    usertype=rst.getString("user_type").trim();
	        	if(usertype.equals("Data Owner")){
			        	session.setAttribute("username", uname);
			        	session.setAttribute("usertype", usertype);
			        	System.out.println("in if uname "+usertype);
			          response.sendRedirect("ownerhome.jsp");
	          
	        } else {
	        	System.out.println("in else uname "+usertype);
	        	session.setAttribute("username", uname);
	        	session.setAttribute("usertype", usertype);
	        	response.sendRedirect("home.jsp");
	        }
	        
	        }else{
	        	
	        	request.setAttribute("msg", "Username or Password incorrect!!!");
//	        	System.out.println("in else msg ");
	        	request.getRequestDispatcher("index.jsp").forward(request, response);
//	        	response.sendRedirect("index.jsp");
	        }
//	        else if (rst.next()) 
//	        {
//	        	session.setAttribute("username", uname);
//	        	response.sendRedirect("home.jsp");
//	        }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
//       response.sendRedirect("index.jsp");

	}

}
