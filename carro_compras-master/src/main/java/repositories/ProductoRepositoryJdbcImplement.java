package repositories;

import models.Categoria;
import models.Productos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImplement implements Repository<Productos>{
    /*Necesitanmos una conexión a la bbdd, la conexión se tiene que pasar al repository, luego
    * se lo pasa al Service y a su vez el sevlet lo obtiene del objeto request de los atributos
    * que se setearon por request vueleve a pasar al service y el service pasa al reposository*/
    private Connection conn;
    //Implementamos un constructor para inicializar la conexión


    public ProductoRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }
    //Sobreescribimos los métodos de la clase interfaz


    @Override
    public List<Productos> listar() throws SQLException {
        //Declaramos e inicializamos la lista de productos
        List<Productos> productos = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM producto as p "+
                    " inner join categoria as c ON (p.idcategoria = c.idcategoria) order by p.idproducto ASC")){
            while(rs.next()){
                Productos p = getProductos(rs);
                productos.add(p);
            }
        }
        return productos;
    }



    @Override
    public Productos porId(Long idProducto) throws SQLException {
       Productos productos=null;
       try(PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre as categoria FROM " +
               " producto as p inner join categoria as c ON(p.idcategoria=c.idcategoria)WHERE p.idproducto=? " )){
           stmt.setLong(1, idProducto);
           try(ResultSet rs=stmt.executeQuery()){
               if(rs.next()){
                   productos=getProductos(rs);
               }
           }

       }
       return productos;
    }

    @Override
    public void guardar(Productos productos) throws SQLException {
        String sql;
        if(productos.getIdProducto()!=null && productos.getIdProducto()>0){
            sql="update producto set idcategoria=?, nombre=?, precio=? where idproducto=?";
        }else{
            sql="INSERT INTO producto (idcategoria, nombre,precio) VALUES(?,?,?)";
        }
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setLong(1, productos.getCategoria().getIdCategoria());
            stmt.setString(2,productos.getNombre());
            stmt.setDouble(3,productos.getPrecio());
            if(productos.getIdProducto()!=null && productos.getIdProducto()>0){
                stmt.setLong(4,productos.getIdProducto());
            }
            stmt.executeUpdate();
        }

    }

    @Override
    public void eliminar(Long idProducto) throws SQLException {
        String sql="delete from produsctos where idproducto=?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, idProducto);
            stmt.executeUpdate();
        }

    }
    private static Productos getProductos(ResultSet rs) throws SQLException {
        Productos p= new Productos();
        p.setIdProducto(rs.getLong("idproducto"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getDouble("precio"));
        Categoria c=new Categoria();
        c.setIdCategoria(rs.getLong("idcategoria"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);
        return p;
    }
}
