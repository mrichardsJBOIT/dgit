<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head></head>
    <body>
        <p>Select Name File To Upload</p>
        <form action="upload" enctype="multipart/form-data" method="POST">
            <input type="file" name="myFile"><br>
            <input type="Submit" value="Upload File"><br>
        </form>
    </body>
</html>