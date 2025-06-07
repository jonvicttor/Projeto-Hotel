package com.meuhotel.model;

public class Quarto {
    private int numeroQuarto;
    private String tipoDoQuarto;
    private double precoDiaria;
    private boolean estaOcupado; // AGORA É 'private' e declarado corretamente

    // CONSTRUTOR CORRIGIDO: Sem 'boolean estaOcupado' como parâmetro,
    // e com a ordem de parâmetros (int, String, double) que sua Main espera.
    // Todos os atributos são inicializados corretamente aqui dentro.
    public Quarto(int numeroQuarto, String tipoDoQuarto, double precoDiaria) {
        this.numeroQuarto = numeroQuarto;
        this.tipoDoQuarto = tipoDoQuarto;
        this.precoDiaria = precoDiaria;
        this.estaOcupado = false; // Um quarto novo sempre começa como "livre"
    }

    // Métodos Getters
    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public String getTipoDoQuarto() {
        return tipoDoQuarto;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public boolean isEstaOcupado() {
        return estaOcupado;
    }

    // Métodos de Ação
    public void ocupar() {
        this.estaOcupado = true;
    }

    public void liberar() {
        this.estaOcupado = false;
    }

    // Método para exibir informações do quarto.
    public void exibirInformacoesQuarto() {
        System.out.println("Número do Quarto: " + numeroQuarto);
        System.out.println("Tipo do Quarto: " + tipoDoQuarto);
        System.out.println("Preço da Diária: R$" + String.format("%.2f", precoDiaria));
        String status = this.estaOcupado ? "Ocupado" : "Livre";
        System.out.println("Status: " + status);
    }
}