<%--
  Created by IntelliJ IDEA.
  User: ADMIN-ITQ
  Date: 4/12/2024
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*,models.*" %>
<%
    List<Productos> productos = (List<Productos>) request.getAttribute("productos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<html>
<head>
    <title>Lista de Productos</title>
</head>
<body>
<h1>Listado de productos</h1>
<%if(username.isPresent()){%>
        <div style="color: blue;">Hola <%=username.get()%>, bienvenido a la aplicación!</div>
        <div><p><a href="${pageContext.request.contextPath}/productos/form">Ingrese el producto</a></p></div>
<%}%>
<table>
    <tr>
        <th>ID PRODUCTO</th>
        <th>NOMBRE PRODUCTO</th>
        <th>CATEGORIA</th>
        <% if(username.isPresent()){%>
        <th>PRECIO</th>
        <th>OPCIONES</th>
        <%}%>
    </tr>
    <%
        for (Productos p: productos) {%>
    <tr>
        <td><%=p.getIdProducto()%></td>
        <td><%=p.getNombre()%></td>
        <td><%=p.getCategoria().getNombre()%></td>
        <%if(username.isPresent()){%>
        <td><%=p.getPrecio()%></td>
        <td>
            <a href="<%=request.getContextPath()%>/agregar-carro?idProducto=<%=p.getIdProducto()%>">Agregar</a>
            |
            <a href="<%=request.getContextPath()%>/productos/form?idProducto=<%=p.getIdProducto()%>">Editar</a>
            |
            <form method="post" action="<%=request.getContextPath()%>/productos" style="display:inline;">
                <input type="hidden" name="accion" value="eliminar">
                <input type="hidden" name="idProducto" value="<%=p.getIdProducto()%>">
                <button type="submit" onclick="return confirm('Esta seguro de eliminar este producto')">Eliminar</button>
            </form>
        </td>
        <% } %>
    </tr>
    <%}%>
</table>

</body>
</html>

