package com.fintech.dao;

import com.fintech.ConnectionManager;
import com.fintech.entities.Movimentacao;
import com.fintech.entities.Usuario;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UsuarioDAO {
    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());

    public Usuario getUsuarioByEmail(String email) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String cpf = rs.getString("cpf");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    String senha = rs.getString("senha");

                    List<Movimentacao> movimentacoes = new ArrayList<>();
                    Array sqlArray = rs.getArray("movimentacoes");
                    if (sqlArray != null) {
                        Object[] arrayData = (Object[]) sqlArray.getArray();
                        for (Object obj : arrayData) {
                            if (obj instanceof Struct struct) {
                                Object[] attrs = struct.getAttributes();

                                String descricao = (String) attrs[0];
                                BigDecimal valor = (BigDecimal) attrs[1];

                                Movimentacao movimentacao = new Movimentacao(descricao, valor);
                                movimentacoes.add(movimentacao);
                            }
                        }
                    }

                    usuario = new Usuario(nome, cpf, email, senha, saldo, movimentacoes);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }

    public void cadastrarUsuario(String nome, String cpf, String email, String senha) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection connection = ConnectionManager.getConnection();

            String sqlUsuario = "INSERT INTO usuarios (nome, cpf, email, senha, saldo) VALUES (?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(sqlUsuario);

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, email);
            stmt.setString(4, senha);
            stmt.setBigDecimal(5, BigDecimal.ZERO);

            stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}