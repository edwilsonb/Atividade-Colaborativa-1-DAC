package br.com.atividade_colaborativa1.auth;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/*"})
public class FilterAuth extends HttpFilter implements Filter {

    private static final long serialVersionUID = 1L;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
       

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String requestURI = httpRequest.getRequestURI();
        
        if (requestURI.endsWith("/Login.html") || requestURI.endsWith("/login") || requestURI.endsWith("/logout")) {
            chain.doFilter(request, response);
            return;
        }
        
        if (requestURI.endsWith(".css")) {
            chain.doFilter(request, response);
            return;
        }
        
        String loginPage = httpRequest.getContextPath() + "/Login.html";
        if (requestURI.endsWith("/Login.html")) {
            chain.doFilter(request, response);
            return;
        }
        
        
        if (httpRequest.getSession().getAttribute("usuarioLogado") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/Login.html");
        } else {
            chain.doFilter(request, response);
        }
    }

}
