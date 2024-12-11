package service;

import models.Categoria;
import models.Productos;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Productos> listar();
    //implementamos el metodo para añadir
    Optional<Productos> agregarPorId(Long idProducto);
    //Implmentamos el método guardar , eliminar y listar de categoria
    void guarda(Productos producto);
    //método eliminar
    void eliminar(Long id);
    //Listarmos al Categoria
    List<Categoria> listarCategoria();
    Optional<Categoria> porIdCategoria(Long idCategoria);
}
