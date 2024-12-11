package service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSessionImplement implements LoginService{
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        //Obtengo al sesion
        HttpSession session = request.getSession();
        //Convierto el objeto de tipo sesion en String
        String username = (String) session.getAttribute("username");
        /*
         * Creo un acomdición en la cual valido
         * si al obtener el nombre del usuario es distinto de nulo
         * obtengo el username
         * Caso contrario devuelve la sesion vacia */
        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
