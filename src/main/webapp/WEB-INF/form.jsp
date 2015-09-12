<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head></head>
    <body>
        
        <form action="upload" enctype="multipart/form-data" method="POST">
         	<p>Enter free text to process</p>
         	<textarea cols="40" rows="5" name="freeText"></textarea>
        	<!--  <input type="text" name="freeText" style="width: 300px;" >  -->
        	
        	<p>Select file to process </p>
            <input type="file" name="myFile"><br>
           <p> </p>
           <p>If text area is populated, the file will be ignored</p>
            <input type="Submit" value="Submit"><br>
        </form>
    </body>
</html>