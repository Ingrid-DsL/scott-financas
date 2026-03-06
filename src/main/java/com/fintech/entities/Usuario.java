package com.fintech.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Usuario implements Serializable {

    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private BigDecimal saldo;
    private List<Movimentacao> movimentacoes;

    public Usuario() {}

    public Usuario(String nome, String cpf, String email, String senha, BigDecimal saldo) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
    }

    public Usuario (String nome, String cpf, String email, String senha, BigDecimal saldo, List<Movimentacao> movimentacoes) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.saldo = saldo;
        this.movimentacoes = movimentacoes;
    }

    public void adicionarMovimentacao(Movimentacao movimentacao) {
        if (movimentacao != null && movimentacao.valor() != null) {
            movimentacoes.add(movimentacao);
            this.saldo = this.saldo.subtract(movimentacao.valor());
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}