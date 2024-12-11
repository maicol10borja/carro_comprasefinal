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
import java.util.Optional;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional=auth.getUsername(req);
        if(usernameOptional.isPresent()) {
            HttpSession session = req.getSession();
            //Este método elimina la sesión
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath()+"/login.html");
    }
}
