<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Login Page</title>
    <link rel="stylesheet" href="assets/css/styles.css"/>
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e4bf80;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            margin-top: 0;
            color: #602033;
        }
        #login_form {
            display: flex;
            flex-direction: column;
        }
        label {
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
            background-color: #e4bf80;
            color: #602033;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #f1bf80;
        }
        .error {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Employee Login</h2>
        <div id="login_form">
            <%
                String error = request.getParameter("error");
                if (error != null && error.equals("1")) {
            %>
                <div class="error">Invalid username or password. Please try again.</div>
            <%
                }
            %>
            <form action="EmployeeLoginServlet" method="post">
                <label for="username">User Name :</label>
                <input type="text" name="username" value="" id="username" required></br>

                <label for="password">Password  :</label>
                <input type="password" name="password" value="" id="password" required></br>

                <input type="submit" name="login" value="Log In">
            </form>
        </div>
    </div>
</body>
</html>