package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.LoginService;
import service.LoginServiceSessionImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];

        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                //Convertimos de cookie a string
                .map(Cookie::getValue)
                .findAny();*/
        //esta sección es para la cookie
        /*LoginService auth=new LoginServiceImplement();
        Optional<String> cookieOptional=auth.getUsername(req);*/
        //Implementamos objeto de la sesion
        LoginService auth=new LoginServiceSessionImplement();
        //Creamos una variable optional donde guardamos
        //el nombre del usuario obteniendolo del método
        //getUsername
        Optional<String> usernameOptional=auth.getUsername(req);
        //Si el username esta presente quiero que me muestre
        //la información de login exitoso
        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                //Creo la plantilla html
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Hola " + usernameOptional.get() + "</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hola " + usernameOptional.get() + " ya has iniciado sesión anteriormente</h1>");
                out.println("<p><a href='"+req.getContextPath()+"/index.html'>Volver al inicio</a></p>");
                out.println("<p><a href='"+req.getContextPath()+"/logout'>Cerrar sesion</a></p>");
                out.println("</body>");
                out.println("</html>");
            }
            /*Caso contrario me muestre un error
             * de me devuleve al login  */
        }else{
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {

            //Implementamos la Cookie
            //Cookie usernameCookie = new Cookie("username", username);
            //  resp.addCookie(usernameCookie);
            // resp.setContentType("text/html;charset=UTF-8");
            /*try (PrintWriter out = resp.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Login correcto</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Login correcto!</h1>");
                out.println("        <h3>Hola " + username + " has iniciado sesión con éxito!</h3>");
                out.println("<p><a href='"+req.getContextPath()+"/index.html'>Volver al inicio</a></p>");
                out.println("    </body>");
                out.println("</html>");
            }*/
            //no poner la siguinet linea de código antes de usar el metodo get

            //Creo la sesion
            HttpSession session=req.getSession();
            session.setAttribute("username", username);

            resp.sendRedirect(req.getContextPath()+"/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }
    }
}
