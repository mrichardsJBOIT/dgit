package com.dgit.web;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dgit.controllers.WordCounter;

import java.io.InputStream;
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
    int BUFFER_LENGTH = 4096;
    private static final String UPLOAD_DIR = "uploads";
    String uploadPath;
    
    @Override
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        getServletContext().getRequestDispatcher("/WEB-INF/form.jsp").forward(request, response);
    }
 
    @Override
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
    	String fileName = new SimpleDateFormat("yyyyMMddmmss").format(new Date())+".txt";
    	String inputData = request.getParameter("freeText");
    	uploadPath = request.getServletContext().getRealPath("")+UPLOAD_DIR;
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	WordCounter myWordCounter = new WordCounter();
    	
    	 if (inputData == null || inputData.trim().isEmpty()) { 
    		 Part filePart = request.getPart("myFile");
    		 //InputStream fileData = filePart.getInputStream();
    		 fileName = getFileName(filePart);

    		 Scanner scanner = new Scanner(filePart.getInputStream());
    		 StringBuffer fileData = new StringBuffer();
    		 
    		 while(scanner.hasNextLine()){
    			 fileData.append(scanner.nextLine());
    		 }	   		
    		 scanner.close();
    		 inputData = fileData.toString();
         } 
	     
    	 StringBuffer sb = new StringBuffer();
	     out.println("<p> Testing the following contents </p> </br>");
	     out.println(inputData);
	     out.println("<p> RESULTS:: </p>");
	
	     myWordCounter.processList(inputData);
	        	            
	     out.println("<p> Number of words beginning with 'M' or 'm' :" + myWordCounter.getNumberOfWordStartingWithM() + "</br>");
	     out.println(myWordCounter.getNamesStartingWithMm());
	     out.println("</br> </br>");
	        	            
	     out.println(" Number of words with five more letters: " + myWordCounter.getNumberOfWordsLongerThan5Chars());
	     out.println(" </br>");
	     out.println(myWordCounter.getNamesLongerThan5Chars());
	     out.println(" </p></br>");                                 
	        
	     saveData(inputData,fileName);
	        	            
	     out.println("Data was uploaded to " +uploadPath + File.separator+ fileName);
    }
              
    private String getFileName(Part part) {
        String partHeader = part.getHeader("content-disposition");
        for (String cd : partHeader.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
    
    private void saveData(String inputData,String fileName){
    	// creates the save directory if it does not exists
    	Path p = Paths.get(fileName);
    	String file = p.getFileName().toString();
    	File fileSaveDir = new File(uploadPath);
    	if (!fileSaveDir.exists()) {
    		fileSaveDir.mkdirs();
    	 }    
    	try {
    		 FileOutputStream os = new FileOutputStream(uploadPath +File.separator+ fileName);
        	 os.write(inputData.getBytes());            
        	 os.flush();           
        	 os.close();        	
    	} catch(IOException ex){
    		//Add some logging code here
    	}
    }
}