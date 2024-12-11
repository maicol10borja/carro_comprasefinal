package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Productos;
import service.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtenemos la conexión desde los atributos del request
        Connection conn = (Connection) req.getAttribute("conn");

        // Creamos una instancia del servicio de productos
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        
        // Listamos los productos
        List<Productos> productos = service.listar();

        // Obtenemos el username autenticado
        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional = auth.getUsername(req);

        // Seteamos los atributos de productos y username
        req.setAttribute("productos", productos);
        req.setAttribute("username", usernameOptional);

        // Redireccionamos a la vista listar.jsp
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtenemos la conexión desde los atributos del request
        Connection conn = (Connection) req.getAttribute("conn");

        // Creamos una instancia del servicio de productos
        ProductoService service = new ProductoServiceJdbcImplement(conn);

        // Obtenemos el parámetro "accion" para determinar si es una eliminación
        String accion = req.getParameter("accion");

        if ("eliminar".equals(accion)) {
            try {
                // Obtenemos el ID del producto a eliminar
                Long idProducto = Long.parseLong(req.getParameter("idProducto"));

                // Llamamos al servicio para eliminar el producto
                service.eliminar(idProducto);

                // Redireccionamos nuevamente a la lista de productos
                resp.sendRedirect(req.getContextPath() + "/productos");
            } catch (Exception e) {
                // Manejo de errores: mostramos un mensaje o redirigimos a una página de error
                req.setAttribute("error", "No se pudo eliminar el producto: " + e.getMessage());
                getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        }
    }
}

