<%--
  Created by IntelliJ IDEA.
  User: ADMIN-ITQ
  Date: 25/11/2024
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" import="models.*" %>
<%
    Carro carro = (Carro) session.getAttribute("carro");
%>
<html>
<head>
    <title>Carro de compras</title>
</head>
<body>
<h1>Carro de compras</h1>
<%
    if (carro == null || carro.getItems().isEmpty()) {

%>
<p>Lo sentimos no hay productos ern el carro de compras!</p>


<% } else {%>
<table>
    <tr>
        <th>ID PRODUCTO</th>
        <th>NOMBRE</th>
        <th>PRECIO</th>
        <th>CANTIDAD</th>
        <th>TOTAL</th>
    </tr>

    <%
        for (ItemCarro item : carro.getItems()) {%>
    <tr>
        <td><%=item.getProductos().getIdProducto()%>
        </td>
        <td><%=item.getProductos().getNombre()%>
        </td>
        <td><%=item.getProductos().getPrecio()%>
        </td>
        <td><%=item.getCantidad()%>
        </td>
        <td><%=item.getSbtotal()%>
        </td>
    </tr>
    <% }%>
    <tr>
        <td colspan="4" style="text-align: right">Total</td>
        <td><%=carro.getTotal()%>
        </td>

    </tr>
</table>
<%}%>
<p><a href="<%=request.getContextPath()%>/productos">seguir comprando</a></p>
<p><a href="<%=request.getContextPath()%>/index.html">Ir al inicio </a></p>

</body>
</html>
