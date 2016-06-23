package com.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Request
 */
public class Request extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Request() {
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
  		
  		
  		String file_name = request.getParameter("filename");
		String user = request.getParameter("user");
		String dataowner = request.getParameter("downer");
		String state = request.getParameter("ac");
		
  		Connection con=null;
  		try {
			Class.forName(driver);
			 con=DriverManager.getConnection(url,username,password);
			 
			 if(state.equalsIgnoreCase("accept")){
			 String sql1 = "Update downrequest SET status='true' where filename=? AND user=?";
// 					+ "values(?,?)";
 			PreparedStatement psmt = con.prepareStatement(sql1);

 			psmt.setString(1, file_name);
 			 psmt.setString(2, user);
// 			 psmt.setString(3, dataowner);

 			System.out.println(psmt);
// 			int s=1;
 			 int v = psmt.executeUpdate();
 			 System.out.println("S1: " + v);
			 }else{
				 
				 String id = request.getParameter("id");
				 String sql1 = "Delete from downrequest where filename=? AND id=?";
//					+ "values(?,?)";
			PreparedStatement psmt = con.prepareStatement(sql1);

			psmt.setString(1, file_name);
			psmt.setInt(2, Integer.parseInt(id));
//			 psmt.setString(3, dataowner);
			 int v = psmt.executeUpdate();
			 System.out.println("delete S1: " + v);
				 
			 }
 			request.getRequestDispatcher("datarequest.jsp").forward(request, response);
			 
  		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

}
