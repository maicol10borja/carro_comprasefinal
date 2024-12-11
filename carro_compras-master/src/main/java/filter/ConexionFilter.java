package filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import utils.Conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

//Implementamos una anotación
//Esta anotación me sireve para poder utilizar
// la conexión en cualquier parte de mi
//aplicación
@WebFilter("/*")

public class ConexionFilter implements Filter {
    /*
    * Una clase filter en Java es un objeto que realiza un
    * filtrado en las solicitudes, respuestas a un
    * recurs. Los filtros se pueden ejecutar en servidores
    * web compatibles con Jakarta EE
    * Los filtros interceptán solicitudes y respuestas
    * de manera dinámica para transformaformarlos
    * o utilizar la información qeu contiene. El filtro
    * se realiza en el método doFilter*/

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*
        * request: peticiones del cliente al servidor
        * response: respuesta del servidor
        * filterchain: Es una clase filtro que representa el flujo
        * de procesamiento llamando al método chain.doFilter(request
        * response), dentro de un filtro pasa la solicitud
        * al siguiente filtro o al recurso destino(servlet o un jsp)*/
        try(Connection conn = Conexion.getConnection()){
            //Verificamos si la conexión realiza un autocommit
            //(configuración automática para cada instrucción SQL)
            if(conn.getAutoCommit()){
                //Si esta activa desactivamos el autocommit
                conn.setAutoCommit(false);
            }
            try{
                //Agregamos la conexión como un atributo en la solicitud
                //esto permite que otros componentes(servlets o DAOS)
                //puedan accede a la conexión
                //desde el objeto request
                request.setAttribute("conn", conn);
                //Pasa la solicitud y la respuesta al siguinete filtro
                //del recurso o destino (servlet o jsp)
                chain.doFilter(request, response);
                //Si el procesamirnto se realizao correctamente sin
                //lanzr minguna excepción, se confirma la solicitud,
                //se aplica los cambio a la base de datos
                conn.commit();
                //Si ocurre alguna excepción se lanza dicha excepción
                //y no se cambia la base de datos
            }catch(SQLException e){
                //Se deshacen los cambios con rollback y de esa forma se mantien
                //la integridad de los datos
                conn.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
