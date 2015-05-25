<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
      <title>Image Upload</title>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
    <form action="FileUploadServlet1" method="post" enctype="multipart/form-data">
    <table width="400px" align="center" border=0>
       <tr>
           <td align="center" colspan=2>
            Image Details</td>
       </tr>
       <tr>
           <td>First Name </td>
           <td>
               <input type="input" name="firstname">
           </td>
       </tr>
       <tr>
           <td>Last Name </td>
           <td>
               <input type="input" name="lastname">
           </td>
       </tr>
       <tr>
           <td>Image Link: </td>
           <td>
               <input type="file" name="file">
           </td>
       </tr>
       <tr>
           <td></td>
           <td>
              <input type="submit" name="submit" value="Submit"></td>
       </tr>
   </table>
</form>
</body>
</html>