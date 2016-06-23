package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Keydatarequest
 */
public class Keydatarequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Keydatarequest() {
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
		
		ServletContext sc=getServletContext();
  		String url=sc.getInitParameter("url");
  		String driver=sc.getInitParameter("driver");
  		String password=sc.getInitParameter("password");
  		String username=sc.getInitParameter("username");
  		
  		PrintWriter out=response.getWriter();
  		String file_name = request.getParameter("filename");
		String user = request.getParameter("user");
		String dataowner = request.getParameter("downer");
		
  		Connection con=null;
  		try {
			 
  			 Class.forName(driver);
			 con=DriverManager.getConnection(url,username,password);
			 
			 String sql = "select * from downrequest where filename=? AND user=? AND dataowner=?";
	 		 PreparedStatement psmt1 = con.prepareStatement(sql);

	 			psmt1.setString(1, file_name);
	 			 psmt1.setString(2, user);
	 			 psmt1.setString(3, dataowner);

//	 			System.out.println(psmt);
//	 			int s=1;
	 			 ResultSet rs = psmt1.executeQuery();
//	 			 System.out.println("S1: " + v);
			 if(rs.next()){
				 
				  request.setAttribute("msg", "File request already sent!!!");
		        	System.out.println("in if datarequest msg: "+rs.getString("filename"));
		        	request.getRequestDispatcher("usrdownload.jsp").forward(request, response);
		        	return;
			 }
			 
			 String sql1 = "insert into downrequest(filename,user,dataowner)"
 					+ "values(?,?,?)";
 			PreparedStatement psmt = con.prepareStatement(sql1);

 			psmt.setString(1, file_name);
 			 psmt.setString(2, user);
 			 psmt.setString(3, dataowner);

// 			System.out.println(psmt);
// 			int s=1;
 			 int v = psmt.executeUpdate();
 			 System.out.println("S1: " + v);
   			
 			request.getRequestDispatcher("usrdownload.jsp").forward(request, response);
			 
  		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
  		
	}

}
