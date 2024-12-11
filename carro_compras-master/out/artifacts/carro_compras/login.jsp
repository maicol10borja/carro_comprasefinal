<%--
  Created by IntelliJ IDEA.
  User: ADMIN-ITQ
  Date: 25/11/2024
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formualrio de sesion </title>
</head>
<body>

<h1>Formulario de login</h1>
<form action="/carro_compras/login" method="post">
    <div>
        <label for="username">Username</label>
        <div>
            <input type="text" name="username" id="username">
        </div>
    </div>
    <div>
        <label for="username">Password</label>
        <div>
            <input type="password" name="password" id="paswword">
        </div>
    </div>
    <div>
        <input type="submit" value="Enviar">
    </div>
</form>

</body>
</html>
