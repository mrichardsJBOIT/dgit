package com.dgit.countwords.web;
 

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.dgit.countwords.controllers.WordCounter;

/**
 * When the HTTP GET Method is called the servelt forwards the request to a jsp that prompts the user for 
 * some text input or a local file to submit for processing
 * The HTTP POST method passes text data or a file with data to be processed
 * The servlet parses the input data and passes to the Word Counter for processing.
 * The servlet then calls the Word Counter to gain access to the results
 * The results are formated and displayed in the response to the user
*/
@MultipartConfig()
public class FileUploadServlet extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
    
    @Override
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        getServletContext().getRequestDispatcher("/WEB-INF/form.jsp").forward(request, response);
    }
 
    @Override
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
    	String inputData = request.getParameter("freeText");    	
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	WordCounter myWordCounter = new WordCounter();
    	
    	if (inputData == null || inputData.trim().isEmpty()) { 
    		Part filePart = request.getPart("myFile");    		
    		Scanner scanner = new Scanner(filePart.getInputStream());
    		StringBuffer fileData = new StringBuffer();    		 
    		while(scanner.hasNextLine()){
    			fileData.append(scanner.nextLine());
    		}	   		
    		scanner.close();
    		inputData = fileData.toString();
        } 	     
    	
	    out.println("<p> Testing the following contents </p> </br>");
	    out.println(inputData);
	    out.println("<p> RESULTS:: </p>");	
	    myWordCounter.processList(inputData);	        	            
	    out.println("<p> Number of words beginning with 'M' or 'm' : " + myWordCounter.getNumberOfWordStartingWithM() + " </p></br>");	    	    	        	        
	    out.println("Words with five more letters: </br>");
	    for (String longName : myWordCounter.getNamesLongerThan5CharsList()) {
	    	  out.println(longName+" </br>");			
		}	  	                            	      
    }            
}