package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Productos;
import service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*ProductoService servicios = new ProductoServiceImplement();
        List<Productos> productos = servicios.listar();*/

        //Creamos la conexión u obtenemos la conexión
        Connection conn = (Connection)req.getAttribute("conn");
        //Creamos el nuevo objeto
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        List<Productos> productos=service.listar();


        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional=auth.getUsername(req);

        //Seteamos lo atributos de producto y el username
        req.setAttribute("productos", productos);
        req.setAttribute("username", usernameOptional);

        // redireccionamos a la vista indicada listar.jsp
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);
    }
}
