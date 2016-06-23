package com.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.org.apache.bcel.internal.generic.FNEG;

/**
 * Servlet implementation class Fileupload
 */
public class Fileupload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	private static final String UPLOAD_DIR = "C:/data";
	private static File storeFile;

	public Fileupload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
           PrintWriter out=response.getWriter();
           
		HttpSession session = request.getSession(true);
		// String username = session.getAttribute("username").toString();
		// System.out.println("USERNAME: " + username);
		if (request.getParameter("file") == null) {
			response.sendRedirect("fileup.jsp");
		}
		try {

			// PrintWriter out = response.getWriter();
			System.out.println("in upload servlet");

			if (!ServletFileUpload.isMultipartContent(request)) {
				// if not, we stop here
				PrintWriter writer = response.getWriter();
				writer.println("Error: Form must has enctype=multipart/form-data.");
//				writer.flush();
//				writer.close();
				return;
			}

			// configures upload settings
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// sets memory threshold - beyond which files are stored in disk
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			// sets temporary location to store files
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

			ServletFileUpload upload = new ServletFileUpload(factory);

			// sets maximum size of upload file
			upload.setFileSizeMax(MAX_FILE_SIZE);

			// sets maximum size of request (include file + form data)
			upload.setSizeMax(MAX_REQUEST_SIZE);

			// constructs the directory path to store upload file
			// this path is relative to application's directory
			// ServletContext context = getServletContext();
			// String uploadPath = context.getRealPath("/")+UPLOAD_DIR;
			String uploadPath = UPLOAD_DIR;

			String filetitle = "";
			String keyword = "";
			String uname = "";

			File fil = new File(uploadPath);
			if (!fil.exists()) {
				fil.mkdir();
			}
			System.out.println("uploadPath: " + uploadPath);
			// creates the directory if it does not exist
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			// parses the request's content to extract file data
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);
			String fileName = "";
			/*
			  if (formItems != null && formItems.size() > 0) { 
			  // iterates over form's fields 
			    for (FileItem item : formItems) { // processes only
			  fields that are not form fields 
			  if (!item.isFormField()) {
			  
			  fileName = new File(item.getName()).getName();
			  System.out.println("parameter: "+filetitle+" key: "+keyword+" filename: "+fileName);
			  String filePath = uploadPath + File.separator + fileName;
			  storeFile = new File(filePath); // saves the file on disk
			  item.write(storeFile); } } }
			 */
//			if(response.isCommitted()){
//				System.out.println("iscomts==**");
//			}
			Iterator itr = formItems.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();

					System.out.println("parameter: name= " + name + " Val: "
							+ value);
					if (name.equals("fname")) {

						filetitle = value;

					}
					if (name.equals("keyword")) {
						keyword = value;

					}
					if (name.equals("uname")) {
						uname = value;

					}
				
				}else{
				 fileName = new File(item.getName()).getName();
				 String filePath = uploadPath + File.separator + fileName;
				  storeFile = new File(filePath); // saves the file on disk
				  item.write(storeFile);
				 System.out.println("parameter: "+filetitle+" key: "+keyword+" filename: "+fileName+ " uname: " + uname);
			}
				
			}	
			System.out.println("parameter:filetitle= " + filetitle + " key: " + keyword
					+ " filename: " + fileName+ " uname: " + uname);
			
			
			///////////////////////////////////\\\\\\\\\\\\\\\\
			FileReader file = new FileReader(storeFile);

            String path = storeFile.getAbsolutePath();
            BufferedReader reader = new BufferedReader(file);
            String text = "";
            String line = reader.readLine();
            while (line != null) {
                text += line ;
                line = reader.readLine();
//                if(line.contains(str))
              //  out.println("line");
            }
            reader.close();
            System.out.println(text);

            FileUtil fu = new FileUtil();
            byte[] b1 = fu.createChecksum(path, "MD5");
            
            AESFile ae = new AESFile();

            ae.generateKey(b1, "AES");

            //  System.out.println("ke:"+key);
            String plainText = text; 
            System.out.println("plain : "+plainText);
            String encryptedText = AESFile.encrypt(plainText);
            System.out.println("encryptedText: "+encryptedText);
            
            String enc_file = storeFile.getAbsolutePath()+".enc";
            PrintWriter writer = new PrintWriter(enc_file, "UTF-8");
            writer.println(encryptedText);
            writer.close();
			/////////////////\\\\\\\\\\\\\\\\\\\\\\\\\
			
			
			// Class.forName("com.mysql.jdbc.Driver");
			// Connection con =
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/semanticsearch",
			// "root", "root");
			ServletContext sc = getServletContext();
			String url = sc.getInitParameter("url");
			String driver = sc.getInitParameter("driver");
			String password = sc.getInitParameter("password");
			String username = sc.getInitParameter("username");

			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username,
					password);


//			File enc_file = new File(storeFile.getAbsolutePath());
//			FileInputStream fis = new FileInputStream(enc_file);
			File save_enc_file = new File(enc_file);
			FileInputStream fis = new FileInputStream(save_enc_file);
//			

			String sql1 = "insert into fileupload(filename,filedata,file_title,keyword,dataowner,enc_key)"
					+ "values(?,?,?,?,?,?)";
			PreparedStatement psmt = con.prepareStatement(sql1);

			psmt.setString(1, fileName);
			psmt.setBinaryStream(2, fis, (int) save_enc_file.length());
			 psmt.setString(3, filetitle);
			 psmt.setString(4, keyword);
			 psmt.setString(5, uname);
			 psmt.setBytes(6, b1);
			System.out.println(psmt);
//			int s=1;
			 int s = psmt.executeUpdate();
			 System.out.println("S1: " + s);
//			session.setAttribute("filePath", fileName);
			/*if(!response.isCommitted()){
				System.out.println("iscomts=="+s);
			}*/
			if(s!=0){
				System.out.println("s=="+s);
				con.close();
			request.setAttribute("msg", "File uploaded successfully...");
//			 response.sendRedirect("fileup.jsp");
//			 request.getRequestDispatcher("fileup.jsp").forward(request, response);
//			RequestDispatcher rd11 = request.getRequestDispatcher("fileup.jsp");
//			rd11.forward(request, response);
			request.getRequestDispatcher("fileup.jsp").forward(request, response);
			return;
			}
			 // }
			//RequestDispatcher rd11 = request.getRequestDispatcher("uploadData.jsp");
//			rd11.forward(request, responce);
			} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}

}
