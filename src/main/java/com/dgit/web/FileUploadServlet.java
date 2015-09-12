package com.dgit.web;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dgit.controllers.NamesValidationController;

import java.io.InputStream;
 
//@WebServlet("/upload")
//@MultipartConfig(location = "/var/lib/openshift/55f2dcbd2d5271d54a00013f/app-root/data")
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
    	NamesValidationController namesTester = new NamesValidationController();
    	String uploadPath = System.getenv("OPENSHIFT_DATA_DIR");
    	if (null == uploadPath){
    		uploadPath = request.getServletContext().getRealPath("")+UPLOAD_DIR;
    	}
    	// creates the save directory if it does not exists
        File fileSaveDir = new File(uploadPath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
    	
    	
        for (Part part : request.getParts()) {
        	
            InputStream is = request.getPart(part.getName()).getInputStream();            
            
            String inputStreamString = new Scanner(is,"UTF-8").useDelimiter("\\A").next();
            
            StringBuffer sb = new StringBuffer();
            sb.append("<p>");
            sb.append("Testing the following contents");
            sb.append("</p> </br>");
            out.println(sb.toString());
            out.println(inputStreamString);
            
            sb = new StringBuffer();
            sb.append("<p>");
            sb.append("RESULTS::");
            sb.append("</p> </br>"); 
            out.println(sb.toString());
            
            namesTester.doValidation(inputStreamString);
            out.println(namesTester.getNamesStartingWithMm());
            
            sb = new StringBuffer();
            sb.append("<p>");
            sb.append("HAPPY>::");
            sb.append("</p> </br>"); 
            
            String fileName = getFileName(part);
            FileOutputStream os = new FileOutputStream(uploadPath + fileName);
            byte[] bytes = new byte[BUFFER_LENGTH];
            int read = 0;
            while ((read = is.read(bytes, 0, BUFFER_LENGTH)) != -1) {
                os.write(bytes, 0, read);
                
            }
            os.flush();
            is.close();
            os.close();
            out.println(fileName + " was uploaded to " +uploadPath);
        }
        
        
 
//        Collection<Part> parts = request.getParts();
//        out.write("<!DOCTYPE html PUBLIC \''>");
//        out.write("<h2> Total parts : " + parts.size() + "</h2>");
 
//        for (Part part : parts) {
//            printEachPart(part, out);
//            part.write(getFileName(part));
//            InputStream fileContent =  request.getPart(part.getName()).getInputStream();
//            String fileName = getFileName(part);
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            int read = 0;
//            final byte[] bytes = new byte[1024];
//
//            while ((read = fileContent.read(bytes)) != -1) {
//            	 os.write(bytes, 0, read);
//            	 
//            }
//            out.write(new String(os.toByteArray(),"UTF-8"));
//            os.flush();
//            fileContent.close();
//            os.close();
//            out.println(getFileName(part) + " was uploaded to " + System.getenv("OPENSHIFT_DATA_DIR"));
//         }         
    }
 
    private void printEachPart(Part part, PrintWriter pw) {
    	
    	
    	 
    	 
        StringBuffer sb = new StringBuffer();
        sb.append("<p>");
        sb.append("Name : " + part.getName());
        sb.append("<br>");
        sb.append("Content Type : " + part.getContentType());
        sb.append("<br>");
        sb.append("Size : " + part.getSize());
        sb.append("<br>");
        for (String header : part.getHeaderNames()) {
            sb.append(header + " : " + part.getHeader(header));
            sb.append("<br>");
        }
        sb.append("</p>");
        pw.write(sb.toString());
 
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