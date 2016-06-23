package com.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
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
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// size of byte buffer to send file
	private static final int BUFFER_SIZE = 4096;

	// database connection settings
	private String dbURL = "jdbc:mysql://localhost:3306/semanticsearch";
	private String dbUser = "root";
	private String dbPass = "root";

	public DownloadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
		String file_name = request.getParameter("filename");
		String id = request.getParameter("id");
		Connection conn = null; // connection to the database

		try {
			// connects to the database

//			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//			conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

			ServletContext sc=getServletContext();
			String url=sc.getInitParameter("url");
			String driver=sc.getInitParameter("driver");
			String password=sc.getInitParameter("password");
			String username=sc.getInitParameter("username");
			
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 conn=DriverManager.getConnection(url,username,password);
			
			// queries the database
			String sql = "SELECT * FROM fileupload WHERE id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			System.out.println("statement: " + statement);
			statement.setInt(1, Integer.parseInt(id));

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				// gets file name and file blob data
//				String file_data = result.getString("fi");
//				String encrypted_text = result.getString("encrypted_data");
//				session.setAttribute("file_data", file_data);
//				session.setAttribute("encrypted_text", encrypted_text);
				Blob hash_data;
                byte[] db_enc_data=null;
				hash_data=result.getBlob("enc_key");
                db_enc_data = hash_data.getBytes(1,(int)hash_data.length());
                
				String fileName = result.getString("filename");
				Blob blob = result.getBlob("filedata");
				byte[] bdata = blob.getBytes(1, (int) blob.length());
				String s = new String(bdata);
				
				 AESFile ae = new AESFile();

                 ae.generateKey(db_enc_data, "AES");

                 //  System.out.println("ke:"+key);
//                 String plainText = text;
                 String plainText = s;
                 System.out.println("plain txt: "+plainText);
                 String decryptedText = AESFile.decrypt(plainText.trim());
                 byte requestBytes[] = decryptedText.getBytes();
                 System.out.println("Decplain txt: "+decryptedText);
//				String s1 = AESFile.decrypt(s);
//				session.setAttribute("decrypted_text", s1);
//				 InputStream inputStream = blob.getBinaryStream();
                 ByteArrayInputStream inputStream = new ByteArrayInputStream(requestBytes);
//				System.out.println("S1: " + s1);
//				byte[] bytes = s1.getBytes();
//				InputStream inputStream = new ByteArrayInputStream(inputStream);
				int fileLength = inputStream.available();

				System.out.println("fileLength = " + fileLength);

				ServletContext context = getServletContext();

				// sets MIME type for the file download
				String mimeType = context.getMimeType(fileName);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}

				// set content properties and header attributes for the response
				response.setContentType(mimeType);
				response.setContentLength(fileLength);
				String headerKey = "Content-Disposition";
				String headerValue = String.format(
						"attachment; filename=\"%s\"", fileName);
				response.setHeader(headerKey, headerValue);

				// writes the file to the client
				OutputStream outStream = response.getOutputStream();

				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}

				inputStream.close();
				outStream.close();
			} else {
				// no file found
				response.getWriter().print("File not found");
			}
		} catch (SQLException ex) {
			response.getWriter().print("SQL Error: " + ex.getMessage());
		} catch (IOException ex) {
			response.getWriter().print("IO Error: " + ex.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				// closes the database connection
				try {
					conn.close();
				} catch (SQLException ex) {

				}
			}
		}
		request.getRequestDispatcher("cloud_result.jsp").include(request,
				response);
	}

}
