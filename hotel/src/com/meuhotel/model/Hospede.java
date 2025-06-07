package com.meuhotel.model;

public class Hospede {
    private String nomeCompleto;
    private String cpf;
    private String telefone;

    public Hospede(String nomeCompleto, String cpf, String telefone) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void exibirInformacoesHospede() {
        System.out.println("Nome: " + nomeCompleto);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
    }
}
