package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Categoria;
import models.Productos;
import service.ProductoService;
import service.ProductoServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/productos/form")
public class ProductoFormControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Presentamos el formulario
        //necesitamos la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        req.setAttribute("categorias", service.listarCategoria());
        //Declaramos un varioable de tipo long para guardar el idProducto
        long id;
        try{
            //En la variable id guardamos lo que estamos mandando por el mpetodo get idProducto
            id= Long.parseLong(req.getParameter("idProducto"));
        }catch(NumberFormatException e){
            id=0L;
        }
        //Creamos un nuevo objeto vacio de tipo producto
        Productos productos=new Productos();
        //Seteamos la categoria
        productos.setCategoria(new Categoria());
        //Si el id >0
        if(id>0){
            //Creamos una variable de tipo optional para obtener el porducto por id
            Optional<Productos> o = service.agregarPorId(id);
            //Si la variable optional esta presente obtenemos todos los valores
            if(o.isPresent()){
                productos=o.get();
            }
        }
        //Seteamo los atributos en el alcance de request
        req.setAttribute("productos", productos);
        getServletContext().getRequestDispatcher("/formularioProducto.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn= (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        String nombre = req.getParameter("nombre");
        Double precio;
        try{
            precio=Double.valueOf(req.getParameter("precio"));
        }catch (NumberFormatException e){
            precio=0.0;
        }
        Long idCategoria;
        try{
            idCategoria=Long.valueOf(req.getParameter("categoria"));
        }catch (NumberFormatException e){
            idCategoria=0L;
        }
        //Voy a obtner el idproducto
        long idProducto;
        try{
            idProducto=Long.parseLong(req.getParameter("idProducto"));
        }catch (NumberFormatException e){
            idProducto=0L;
        }
        Productos productos= new Productos();
        productos.setIdProducto(idProducto);
        productos.setNombre(nombre);
        Categoria categoria= new Categoria();
        categoria.setIdCategoria(idCategoria);
        productos.setCategoria(categoria);
        productos.setPrecio(precio);
        service.guarda(productos);
        //REdireccional a un listado para que no se ejecute el método doPost
        //nuevamente y se guarde los datos duplicados
        resp.sendRedirect(req.getContextPath()+"/productos");
    }
}
