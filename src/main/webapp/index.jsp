<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e4bf80;
            margin: 0;
            padding: 0;
        }
        h1 {
            margin-top: 0;
            color: #602033;
        }
        #registerForm {
            display: flex;
            flex-direction: column;
        }
        td {
            margin-bottom: 5px;
            color: #602033;
        }
        input[type="text"], input[type="password"] {
            margin-bottom: 15px;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 3px;
            font-size: 14px;
        }
        input[type="submit"] {
            background-color: #602033;
            color: #e4bf80;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
    </style>
</head>
<body>
 <div id="registerForm" align="center">
  <h1>Employee Register Form</h1>
  <form action="RegisterServlet" method="post">
   <table style="with: 80%">
    <tr>
     <td>Full Name</td>
     <td><input type="text" name="fullname" /></td>
    </tr>
    <tr>
     <td>UserName</td>
     <td><input type="text" name="username" /></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="password" /></td>
    </tr>
    <tr>
     <td>Email</td>
     <td><input type="text" name="email" /></td>
    </tr>
    <tr>
     <td>Contact No</td>
     <td><input type="text" name="contact" /></td>
    </tr>
   </table>
   <input type="submit" value="Submit" />
  </form>
 </div>
</body>
</html>