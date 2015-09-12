package com.dgit.web;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
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
 
@MultipartConfig()
public class FileUploadServlet extends HttpServlet {
 
    private static final long serialVersionUID = 1L;
    int BUFFER_LENGTH = 4096;
    private static final String UPLOAD_DIR = "uploads";
    
    @Override
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        getServletContext().getRequestDispatcher("/WEB-INF/form.jsp").forward(request, response);
    }
 
    @Override
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	
    	String uploadPath = request.getServletContext().getRealPath("")+UPLOAD_DIR;
    	
    	// creates the save directory if it does not exists
        File fileSaveDir = new File(uploadPath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }    	
    	
        for (Part part : request.getParts()) {
        	
            InputStream is = request.getPart(part.getName()).getInputStream();                        
            String inputStreamString = new Scanner(is,"UTF-8").useDelimiter("\\A").next();            
            is.close();
            
            StringBuffer sb = new StringBuffer();
            out.println("<p> Testing the following contents </p> </br>");
            out.println(inputStreamString);
            out.println("<p> RESULTS::"+System.lineSeparator()+"</p>");
            
            WordCounter myWordCounter = new WordCounter();
            myWordCounter.processList(inputStreamString);
            
            out.println("<p> Number of words beginning with 'M' or 'm' :" + myWordCounter.getNumberOfWordStartingWithM() + "</br>");
            
            
            out.println(myWordCounter.getNamesStartingWithMm());
            out.println("</br> </br>"+System.lineSeparator());
            
            out.println(" Number of words with five more letters: " + myWordCounter.getNumberOfWordsLongerThan5Chars());
            out.println(" </br>"+System.lineSeparator());
            out.println(myWordCounter.getNamesLongerThan5Chars());
            out.println(" </p></br>"+System.lineSeparator());                      
           
            String fileName = getFileName(part);
            Path p = Paths.get(fileName);
            String file = p.getFileName().toString();
            
            FileOutputStream os = new FileOutputStream(uploadPath + file);
            os.write(inputStreamString.getBytes());            
            os.flush();
           
            os.close();
            out.println(fileName + " was uploaded to " +uploadPath + "\\"+ fileName);
        }
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
}