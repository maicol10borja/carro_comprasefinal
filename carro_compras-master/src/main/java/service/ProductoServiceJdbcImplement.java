package service;

import models.Categoria;
import models.Productos;
import repositories.CategoriaRepositoryJdbcImplement;
import repositories.ProductoRepositoryJdbcImplement;
import repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImplement implements ProductoService{
   //private ProductoRepositoryJdbcImplement repositoryJdbc;
    private Repository<Productos> repositoryJdbc;
    private Repository<Categoria> repositoryCategoriaJdbc;

    public ProductoServiceJdbcImplement(Connection connection) {
        this.repositoryJdbc=new ProductoRepositoryJdbcImplement(connection);
        this.repositoryCategoriaJdbc=new CategoriaRepositoryJdbcImplement(connection);
    }


    @Override
    public List<Productos> listar() {
        try{
            return repositoryJdbc.listar();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Productos> agregarPorId(Long idProducto) {
        try {
            return  Optional.ofNullable(repositoryJdbc.porId(idProducto));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guarda(Productos producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long idCategoria) {
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(idCategoria));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }
}
