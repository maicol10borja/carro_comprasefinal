package repositories;

import java.sql.SQLException;
import java.util.List;

/*
* <T> Es un parámetro generico que permite que la intefaz sea utilizada
* como se desee o culquier tipo de objeto(entidad) que se desees manejar
* Esto hace que la interfaz sea flexible y reutilizable
* para cualquier tipo de dato*/
public interface Repository <T>{
    /*
    * El método listar retorna una lista de objetos de tipo generico T
    * Se usa para obtener todos los registros de una entidad desde la base de datos
    * */
    List<T> listar() throws SQLException;
    /*
    * El método porId recibe un identificador único y retorna un objeto de tipo T
    * correspondiente a ese identificador
    * Se usa para buscar un registro especifico por su id*/
    T porId(Long id) throws SQLException;
    /*Recibe un objeto de tipo T y lo guarda en la base de datos
    * este método puede ser utilizado para crear o actualizar un registri dependiendo
    * si el objeto ya existe en la base de datos*/
    void guardar(T t) throws SQLException;
    /*REcibe un identificador único y si exite el identificador lo borra de la base de datos
    * */
    void eliminar (Long id)throws SQLException;
}
