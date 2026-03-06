package com.fintech.servlets;

import com.fintech.dao.UsuarioDAO;
import com.fintech.entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/cadastrar")
public class CadastrarServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("cadastro.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");

            if (!nome.isBlank() && !cpf.isBlank() && !email.isBlank() && !senha.isBlank()) {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioDAO.cadastrarUsuario(nome, cpf, email, senha);
            }
                response.sendRedirect("dashboard.html");
        } catch (IOException e) {
            response.sendRedirect("dashboard.html");
        }
    }
}