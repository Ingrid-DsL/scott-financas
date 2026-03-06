package com.fintech.servlets;

import com.fintech.dao.UsuarioDAO;
import com.fintech.entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.getUsuarioByEmail(email);

            if (usuario != null && usuario.getSenha().equals(senha)) {
                response.sendRedirect("dashboard.html");
            } else {
                response.sendRedirect("index.html?error=true");
            }
        } catch (IOException | SQLException e) {
            logger.info("Erro");
        }
    }
}