package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import opennlp.uima.tokenize.Tokenizer;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class KeywordSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource dataSource;
	private Connection connection;
	private Statement statement;
	private ArrayList<String> flist = new ArrayList<>();
	private ArrayList<String> downerlist = new ArrayList<>();
	private HashSet<String> hflist = new HashSet<>();
	
	private HashMap<String, Integer> cmap;
	private HashMap<String, Double> tfmap;
	private ArrayList<Integer> sub=null;
	long tgtime;
	
	
	int dkeywrdcnt=0;
	
	public KeywordSearchServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/testdb");
		} catch (NamingException e) {

		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ResultSet resultSet = null;
		try {
			// Get Connection and Statement
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			HttpSession session = request.getSession(true);
			String attr = (String) session.getAttribute("list");

			PrintWriter out = response.getWriter();
			String keyword = request.getParameter("keyword");

			System.out.println("keyword: " + keyword);
			System.out.println("attr: " + attr);

			RemoveStopwords stop = new RemoveStopwords();

			ArrayList<String> stopwordRemoveList = stop.remove_stopwords(keyword);

			Synonyms synonyms = new Synonyms();
			flist = new ArrayList<>();
			downerlist = new ArrayList<>();
			hflist = new HashSet<>();
			cmap=new HashMap<>();
			tfmap=new HashMap<>();
			sub = new ArrayList<>();
			int flag=0;
			int synsiz=0;
			int wscount=0;
			String fileName=""; 
			
			
			
			String sqld="delete from tggraph";
			int dg=statement.executeUpdate(sqld);
//			long strttime=0;
//			long endtime=0;
//			ArrayList countlist = new ArrayList<>();
			// Iterator<String> itr = stopwordRemoveList.iterator();
			long stime=System.currentTimeMillis();
			// while (itr.hasNext()) {
			for (String s1 : stopwordRemoveList) {

				long strttime=System.currentTimeMillis();
				String[] synonymsList = synonyms.main(s1);
				long endtime=System.currentTimeMillis();
				dkeywrdcnt=synonymsList.length;
//				int word = 0;
				tgtime=endtime-strttime;
				flag=0;
				synsiz=synonymsList.length;
				String datatext="";
				if (synonymsList.length != 0) {
					
					synonymsList[synonymsList.length - 1] = s1;
					for (int i = 0; i < synonymsList.length; i++) {
						
						System.out.println("synonyms: " + synonymsList[i]);
						String query = "SELECT * FROM fileupload";
						// String query =
						// "SELECT * FROM fileupload WHERE filename LIKE '"+synonymsList[i]+"%' or keyword LIKE '"+synonymsList[i]+"%'";
						System.out.println("synonyms query: " + query);
						resultSet = statement.executeQuery(query);
						String ss = null;
						int word = 0;
						while (resultSet.next()) {

							String filename = resultSet.getString("filename");
							String key_word = resultSet.getString("keyword");
							// String filedata=resultSet.getString("filedata");

							// System.out.println("k-w:"+key_word);
							// if(filename.contains(synonymsList[i]) ||
							// key_word.contains(synonymsList[i]) ){
							// System.out.println("synonymsList wrd: "+synonymsList[i]);
							// flist.add(filename);
							// System.out.println("kw:"+key_word.contains(synonymsList[i]));
							// // ss=resultSet.getString(1) + "\n" +
							// resultSet.getString(2) + "\n" +
							// resultSet.getString(3);
							//
							// // System.out.println(resultSet.getString(1) +
							// "\n" + resultSet.getString(2) + "\n" +
							// resultSet.getString(3));
							//
							// }

							Blob hash_data;
							byte[] db_enc_data = null;
							hash_data = resultSet.getBlob("enc_key");
							db_enc_data = hash_data.getBytes(1,
									(int) hash_data.length());

//							String fileName = resultSet.getString("filename");
							 fileName = resultSet.getString("filename");
							Blob blob = resultSet.getBlob("filedata");
							byte[] bdata = blob
									.getBytes(1, (int) blob.length());
							String s = new String(bdata);

							AESFile ae = new AESFile();

							ae.generateKey(db_enc_data, "AES");

							String plainText = s;
							// System.out.println("plain txt: "+plainText);
							String decryptedText = AESFile.decrypt(plainText
									.trim());
//							System.out.println("Decplain txt: " + decryptedText);
							System.out.println("String S1: " + s1);
						
//							int word = 0;
							Pattern p = Pattern.compile(s1);
							Matcher m = p.matcher(decryptedText);
							while (m.find()) {
								word++;

							}

							

							if (filename.contains(synonymsList[i])
									|| key_word.contains(synonymsList[i])
									|| decryptedText.contains(s1)) {
								System.out.println("synonymsList wrd: "
										+ synonymsList[i]);
								
//								flist.add(filename);
								
								datatext=decryptedText;
								flag=1;
								cmap.put(s1, word);
								System.out.println("above tfd: "+word);
//								double tfd=word/(float)countw(decryptedText,word);
								sub.add(word);
								wscount=counterm(decryptedText,word);
//								tfmap.put(fileName, tfd);
								hflist.add(fileName);
//								wscount=wscount+countword(s1,fileName);								
//								wscount=countword(s1,fileName);
//								System.out.println("wscount : "+wscount);
								System.out.println("kw:"
										+ key_word.contains(synonymsList[i]));
							}

							// if(decryptedText.contains(s1)){
							//
							// countlist.add("cnt: "+i);
							// }
							

						}
						
						
					}
					
					
					
				} else {

					System.out.println("in else non: " + s1);
					strttime=System.currentTimeMillis();
					
					dkeywrdcnt=synonymsList.length;
					String query1 = "SELECT * FROM fileupload WHERE filename LIKE '"+ s1 + "%' or keyword LIKE '" + keyword + "%'";
					Statement st = connection.createStatement();
					// System.out.println("sqlq: " + query);
					resultSet = st.executeQuery(query1);
					String ss = null;

					int flag1 = 0;

					while (resultSet.next()) {

						String fileName1 = resultSet.getString("filename");
						flist.add(fileName1);
						hflist.add(fileName1);
						flag1 = 1;
					}
					
					
					
					
					resultSet.close();
					st.close();

					if (flag1 == 0) {
					String	query = "SELECT * FROM fileupload ";
					
//					 Statement st1=connection.createStatement();
					st=connection.createStatement();
					// System.out.println("sqlq: " + query);
					resultSet = st.executeQuery(query);
					
//					System.out.println("sqlq: " + query);
					
					int word = 0;
					
					while (resultSet.next()) {

					Blob hash_data;
					byte[] db_enc_data = null;
					hash_data = resultSet.getBlob("enc_key");
					db_enc_data = hash_data.getBytes(1,
							(int) hash_data.length());

//					String fileName = resultSet.getString("filename");
					 fileName = resultSet.getString("filename");
					Blob blob = resultSet.getBlob("filedata");
					byte[] bdata = blob.getBytes(1, (int) blob.length());
					String s = new String(bdata);

					AESFile.generateKey(db_enc_data, "AES");

					String plainText = s;
					// System.out.println("plain txt: "+plainText);
					String decryptedText = AESFile.decrypt(plainText.trim());
					System.out.println("Decplain txt: " + decryptedText);
					System.out.println("String S1: " + s1);
//					int word = 0;
					Pattern p = Pattern.compile(s1);
					Matcher m = p.matcher(decryptedText);
					while (m.find()) {
						word++;

					}
					
					endtime=System.currentTimeMillis();
					tgtime=endtime-strttime;
						if (decryptedText.contains(s1)) {
//							flag=1;
							datatext=decryptedText;
							cmap.put(s1, word);
							hflist.add(fileName);
							wscount=counterm(decryptedText,word);
//							wscount=wscount+countword(s1,fileName);
							
						}
					}
				}
					
			}
				
				
				if(dkeywrdcnt==0){

					dkeywrdcnt=stopwordRemoveList.size();
				}
				
				tgtime=FileUtil.tg(tgtime);
				String isql="insert into tggraph(time,cnt) values(?,?)";
				
				PreparedStatement ps=connection.prepareStatement(isql);
				ps.setDouble(1,Double.parseDouble(String.valueOf(tgtime)));
				ps.setInt(2, dkeywrdcnt);
				
				int in=ps.executeUpdate();
//				System.out.println(" isql  : "+ps);
//				int wscount=countword(datatext);
//				System.out.println(" words in "+s1+" : "+wscount);
				
			/*	if(flag==1){
//					wscount=wscount+countword(s1,fileName);
//					wscount=countword(s1,fileName);
//					wscount=countword(datatext);
//					System.out.println(" words in "+s1+" : "+wscount+"  flag: "+flag);
					
				}*/
				
		 }
			
			long etime=System.currentTimeMillis();
			
			long qgt=etime-stime;
			
			String isql1="insert into qgraph(time,cnt) values(?,?)";
			
			PreparedStatement ps1=connection.prepareStatement(isql1);
			ps1.setDouble(1,Double.parseDouble(String.valueOf(qgt)));
			ps1.setInt(2, dkeywrdcnt);
			int in=ps1.executeUpdate();
			System.out.println(" isql  : "+ps1);
			//to get total data collection
			Statement st1 = connection.createStatement();
			int tfile_count=0;
			String sql1 = "select * from fileupload ";
			
			ResultSet rs1 = st1.executeQuery(sql1);
			while (rs1.next()) {
			  tfile_count++;	
			}
			
//			System.out.println("tfile_count: "+tfile_count);
//			// System.out.println("Flist: "+flist);(String) cmap.get(kw).toString()
//			System.out.println("hFlist: " + hflist.toString());
//
//			System.out.println("Count Map: " + cmap.toString());
			System.out.println("TfMap: " + tfmap.toString());
			int no_occ=0;
			long scr=0;
			for(String kw: stopwordRemoveList){
				no_occ=cmap.get(kw);
				double tf=(no_occ/(float)wscount);
//				System.out.println("TF: "+tf);
				System.out.println("kw no_occ: "+no_occ+" / "+wscount+"  = TF: "+tf);
				System.out.println("TF of the term t: "+no_occ+"\n\n number of documents containing the term t: "+hflist.size()+"\n\nnumber of the whole document collection :"+tfile_count);
				scr=(score(tf, tfile_count, hflist.size())+score(tf, tfile_count, synsiz));
			
			}
			
			double dv=tfile_count/hflist.size();
			System.out.println("IDF: "+(Math.log(dv)));
			System.out.println("SCR : "+(scr));
			
			
			if (!hflist.isEmpty()) {

//				for (int i = 0; i < hflist.size(); i++) {
				for(String hs:hflist){
					Statement st = connection.createStatement();
//					String sql = "select * from fileupload where filename='"
//							+ flist.get(i).toString().trim() + "'";
					String sql = "select * from fileupload where filename='"
							+ hs.trim() + "'";
					// System.out.println("sql: "+sql);
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {

						String downer = rs.getString("dataowner");
						String fname = rs.getString("filename");
						downerlist.add(downer);
					}
				}

			/*if (!flist.isEmpty()) {

				for (int i = 0; i < flist.size(); i++) {
					Statement st = connection.createStatement();
					String sql = "select * from fileupload where filename='"
							+ flist.get(i).toString().trim() + "'";
					// System.out.println("sql: "+sql);
					ResultSet rs = st.executeQuery(sql);
					while (rs.next()) {

						String downer = rs.getString("dataowner");
						String fname = rs.getString("filename");
						downerlist.add(downer);
					}
				}*/
				// session.setAttribute("list", flist.toString());
				session.setAttribute("list", hflist.toString());
				session.setAttribute("dlist", downerlist.toString());
			}

//			System.out.println("dlist: " + downerlist);
			// request.setAttribute("msg", "key word search!!!");
			// System.out.println("in else msg ");
			request.getRequestDispatcher("keysearch.jsp").forward(request,
					response);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != resultSet)
					resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (null != statement)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (null != connection)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	
		// String[] s = keyword.split("\\s+");
		//
		// for (int i = 0; i < s.length; i++) {
		// System.out.println(s[i]);
		// }
	}
	
	private int counterm(String s,int w) throws Exception {
//		System.out.println("in cnout sub:"+ sub);
		System.out.println("in cnout w:"+ w);
		int cnt=0;
		int mk=0;
		double tfd;
		StringTokenizer	stk = new StringTokenizer(s);
		cnt	= stk.countTokens();
		if(!sub.isEmpty()){
		if(sub.get(0)==w ){
			mk=w;
			tfd=(w)/(float)cnt;
		}else{
			mk=w-sub.get(0);
		 tfd=(mk)/(float)cnt;
		}
		System.out.println("no_occ"+ (w-sub.get(0))+" / "+cnt+"  = TF: "+tfd);
		}
//		tfmap.put(String.valueOf(w),(double) cnt);
		tfmap.put(String.valueOf(w),(double) cnt);
		return cnt;
	}
	
	private long score(double dt,double n,int siz){
		
		long scr=0;
		double tmp=0;
		for(int i=0;i<siz;i++){
			tmp=+(Math.log(1+dt)*Math.log(1+(n/dt)))*(0.5);
		}
		scr=(long) ((Math.log(1+dt)*Math.log(1+(n/dt)))/tmp);
		
		return scr;
	}
	
	private int countword(String s,String fn) throws Exception {
		
		
		Statement st2 = connection.createStatement();
//		String sql = "select * from fileupload where filename='"
//				+ flist.get(i).toString().trim() + "'";
		String sql = "select * from fileupload where filename='"+fn+"'";
		 System.out.println("sql: "+sql);
		ResultSet resultSet = st2.executeQuery(sql);
		StringTokenizer stk = null;
		int csum=0;
		while(resultSet.next()) {
			
			Blob hash_data;
			byte[] db_enc_data = null;
			hash_data = resultSet.getBlob("enc_key");
			db_enc_data = hash_data.getBytes(1,
					(int) hash_data.length());

			String fileName = resultSet.getString("filename");
			Blob blob = resultSet.getBlob("filedata");
			byte[] bdata = blob.getBytes(1, (int) blob.length());
			String s111 = new String(bdata);

			AESFile.generateKey(db_enc_data, "AES");

			String plainText = s111;
			// System.out.println("plain txt: "+plainText);
			String decryptedText = AESFile.decrypt(plainText.trim());
			
			 
			if (decryptedText.contains(s)) {
				
				stk = new StringTokenizer(decryptedText);
				System.out.println("countword Decplain txt: " + decryptedText);
			    csum=csum+stk.countTokens();
			}
		}
//		if(csum==0){
//			csum=1;
//		}
//		StringTokenizer 
		return csum;
	}

}
