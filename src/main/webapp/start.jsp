<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title> Web Java 24 - Hello, HBN!!!</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }
        h1 {
            color: #003366;
            margin-bottom: 20px;
        }
        form p {
            margin: 0;
            font-weight: bold;
            text-align: left;
            color: #333;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0 20px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #0099ff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #007acc;
        }
        .error-message {
            color: red;
            margin-top: 10px;
        }
        .timestamp {
            margin-top: 30px;
            color: #00509e;
        }
    </style>
</head>
<body>
<div style="text-align: center;">
    <h1><%= "Hello World with Hibernate v2!" %>
    </h1>
    <br/>
    <h3><a href="officeworkers">Show All Office Workers</a></h3>
    <br/>
    <br/>
    <jsp:useBean id="now" class="java.util.Date" />
    <h3><fmt:formatDate value="${now}" type="both" dateStyle="long" timeStyle="long" /></h3>
</div>
</body>
</html>
