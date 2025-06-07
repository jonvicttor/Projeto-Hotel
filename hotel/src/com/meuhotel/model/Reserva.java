package com.meuhotel.model;

public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private int numeroDeDiarias;
    private String dataCheckIn;
    private String dataCheckOut;

    public Reserva(Hospede hospede, Quarto quarto, int numeroDeDiarias, String checkIn, String checkOut) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.numeroDeDiarias = numeroDeDiarias;
        this.dataCheckIn = checkIn;
        this.dataCheckOut = checkOut;

        this.quarto.ocupar();
    }

    public void exibirDetalhesDaReserva() {
        System.out.println("Hóspede: " + hospede.getNomeCompleto());
        System.out.println("CPF: " + hospede.getCpf());
        System.out.println("Quarto Nº: " + quarto.getNumeroQuarto());
        System.out.println("Tipo do Quarto: " + quarto.getTipoDoQuarto());
        System.out.println("Check-In: " + dataCheckIn);
        System.out.println("Check-Out: " + dataCheckOut);
        System.out.println("Diárias: " + numeroDeDiarias);
        System.out.println("Total: R$" + (numeroDeDiarias * quarto.getPrecoDiaria()));
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public Hospede getHospede() {
        return hospede;
    }

}
