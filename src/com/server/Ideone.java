package com.server;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone {
	public static void main (String[] args) throws java.lang.Exception
    {
//    	String in ="i have a male cat. the color of male cat is Black";
    	
    	String in="Information system, an integrated set of components for collecting, storing, and processing data and for delivering information, knowledge, and digital products. Business firms and other organizations rely on information systems to carry out and manage their operations, interact with their customers and suppliers, and compete in the marketplace. For instance, corporations use information systems to reach their potential customers with targeted messages over the Web, to process financial accounts, and to manage their human resources. Governments deploy information systems to provide services cost-effectively to citizens. Digital goods, such as electronic books and software, and online services, such as auctions and social networking,"+
    				"are delivered with information systems. Individuals rely on information systems, generally Internet-based, for conducting much of their personal lives: for socializing, study, shopping, banking, and entertainment."+
    					"As major new technologies for recording and processing information have been invented over the millennia, new capabilities have appeared. The invention of the printing press by Johannes Gutenberg in the mid-15th century and the invention of a mechanical calculator by Blaise Pascal in the 17th century are but two examples. These inventions led to a profound revolution in the ability to record, process, and disseminate information and knowledge. The first large-scale mechanical information system was Herman Hollerith’s census tabulator. Invented in time to process the 1890 U.S. census, Hollerith’s machine represented a major step in automation, as well as an inspiration to develop computerized information systems."
      +"WAV files must be 8000hz, 16bit, mono in PCM format (no compression).Uses an embedded database server which will automatically create itself the first time you "+
"I would like to read line-by-line and store each word into different variables. For example : When I read first line \"1 1111 47\" I would like store the first word \"1\" into var_1,\"1111\" into var_2, and \"47\" into var_3. Then, when it goes to next line, the values should be stored into same var_1, var_2 and var_3 variables respectively. Recommendation  systems  are  intended  to  increase  developer  productivity  by  recommending  files  to  edit.  These systems  mine  association rules in  software revision histories. However,  mining coarse-grained rules  using only  edit histories produces  recommendations with low accuracy,  and can  only  produce recommendations  after a  developer  edits a file.  In this work,  we  explore  the  use  of  finer-grained  association  rules,  based  on  the  insight  that  view  histories  help  characterize  the contexts  of  files  to  edit.";  


    	
    	int i=0;
        Pattern p = Pattern.compile("software");
        Matcher m = p.matcher(in);
        while (m.find()) {
        	i++;
            
        }

        System.out.println(i);
        
//        double tf=(1/133f);
//		System.out.println("TF: "+tf);
		
	}
	

}