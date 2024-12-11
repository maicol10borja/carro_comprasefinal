package repositories;

import models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryJdbcImplement implements Repository<Categoria> {
    private Connection conn;

    public CategoriaRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }


    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from categoria")){
            while (rs.next()){
                Categoria c = getCategoria(rs);
                categorias.add(c);
            }
        }
        return categorias;
    }



    @Override
    public Categoria porId(Long idCategoria) throws SQLException {
        Categoria categoria=null;
        try(PreparedStatement stmt = conn.prepareStatement(
                "select *from categoria where idcateria =?")){
            stmt.setLong(1,idCategoria);
            try(ResultSet rs=stmt.executeQuery()){
                categoria=getCategoria(rs);
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setNombre(rs.getString("nombre"));
        c.setEstado(rs.getInt("estado"));
        c.setIdCategoria(rs.getLong("idcategoria"));
        return c;
    }
}
