package com.fintech.servlets;

import com.fintech.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class FinTech {
    public static void main(String[] args) {
        try (Connection conn = ConnectionManager.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexão bem-sucedida com o banco de dados Oracle!");
            }
        } catch (SQLException e) {
            System.out.println("Falha na conexão com o banco de dados.");
        }
    }
}
