<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<html>
    
    <head>
        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <title>Packetcomm Result</title>
        <script src="js/highcharts.js"></script>

<script>
    <%         
		    ServletContext sc=getServletContext();
			String url=sc.getInitParameter("url");
			String driver=sc.getInitParameter("driver");
			String password=sc.getInitParameter("password");
			String username=sc.getInitParameter("username");
			
			Class.forName(driver);
			Connection con=DriverManager.getConnection(url,username,password);
			Statement st = con.createStatement();
         
             ArrayList arr1=new ArrayList();
             ArrayList arr2=new ArrayList();
             
             ArrayList arr3=new ArrayList();
             ArrayList arr4=new ArrayList();

	
// 	 System.out.println("No:"+no);
	 
		  String sql1="SELECT * FROM tggraph";
		  Statement st1=con.createStatement();
         ResultSet rss=st1.executeQuery(sql1);
         while(rss.next())
         {
             arr1.add(rss.getString("time"));
             arr2.add(rss.getString("cnt"));
         }
	
	 
// 	 System.out.println("Time"+arr1);
//      System.out.println("keyno"+arr2);
        
         
        String  arr11=arr1.toString().replace("[", "").replace("]", "")
            .replace(", ", ",");
         String  arr22=arr2.toString().replace("[", "").replace("]", "")
            .replace(", ", ",");
         
         System.out.println("arr11"+arr11);
         System.out.println("arr22 "+arr22);
          
//         String  arr33=arr3.toString().replace("[", "").replace("]", "")
//             .replace(", ", ",");
//          String  arr44=arr4.toString().replace("[", "").replace("]", "")
//             .replace(", ", ",");
             
                 
    %>
    

$(function () {
    $('#container').highcharts({
        title: {
            text: 'Time cost to build trie',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            
        title: {
                text: 'No Of distinct keywords'
            },
            categories: [<%= arr22 %>]
        },
        yAxis: {
            title: {
                text: 'Time (ms)'
            },
            plotLines: [{
                value: 0,
                width: 0.5,
                color: '#808080'
            }]
        },
//         tooltip: {
//             valueSuffix: '°ms'
//         },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '',
            data: [<%=arr11%>]
        },
//         {
//             name: 'Callee packets',
<%--             data: [] --%>
//         }
        ]
    });
});

</script>
  </head> 
<body>
        
        
    
<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>

</body></html>