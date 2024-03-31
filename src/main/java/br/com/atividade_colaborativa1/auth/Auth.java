package br.com.atividade_colaborativa1.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/Auth","/login","/logout"})
public class Auth extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        if (action.equals("/login")) {
            String user =  "admin";
            String pass =  "123";
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            if (user.equals(username) && pass.equals(password)) {
                request.getSession().setAttribute("usuarioLogado", username);
                response.sendRedirect(request.getContextPath() + "/index.html");
            } else {
                response.sendRedirect(request.getContextPath() + "/Login.html");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        
        if (action.equals("/logout")) {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/Login.html");
        }
    }
}
