
package com.meuhotel.service;

import com.meuhotel.model.Hospede;
import com.meuhotel.model.Quarto;
import com.meuhotel.model.Reserva;
// Importações de exceções
import com.meuhotel.exceptions.QuartoOcupadoException;
import com.meuhotel.exceptions.QuartoNaoEncontradoException;
import com.meuhotel.exceptions.HospedeNaoEncontradoException;
import com.meuhotel.exceptions.ReservaNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Quarto> listaDeQuartos;
    private List<Reserva> listaDeReservas;
    private List<Hospede> listaDeHospedes;

    // Construtor: AGORA SEM PARÂMETROS, CRIANDO AS LISTAS VAZIAS.
    public Hotel() {
        this.listaDeQuartos = new ArrayList<>();
        this.listaDeReservas = new ArrayList<>();
        this.listaDeHospedes = new ArrayList<>();
    }

    public void adicionarQuarto(Quarto quarto){
        this.listaDeQuartos.add(quarto);
        System.out.println("Quarto " + quarto.getNumeroQuarto() + " adicionado ao hotel.");
    }

    public Quarto buscarQuarto(int numero){
        for (Quarto q : listaDeQuartos){
            if (q.getNumeroQuarto() == numero){
                return q;
            }
        }
        // Lançando exceção se o quarto não for encontrado
        throw new QuartoNaoEncontradoException("Quarto com número " + numero + " não encontrado.");
    }

    public void listarQuartosDisponiveis(String tipo){ // <-- Adicione 'String tipo' como parâmetro aqui
        boolean encontrou = false;
        System.out.println("--- Quartos disponíveis do tipo: " + tipo + " ---");
        for (Quarto quartoAtual : listaDeQuartos){
            if (!quartoAtual.isEstaOcupado() && quartoAtual.getTipoDoQuarto().equalsIgnoreCase(tipo)){
                quartoAtual.exibirInformacoesQuarto();
                System.out.println("-----------------------");
                encontrou = true;
            }
        }
        if (!encontrou){
            System.out.println("Nenhum quarto disponível do tipo \"" + tipo + "\" foi encontrado.");
        }
    }

    public void exibirTodosOsQuartos(){
        System.out.println("=== Lista de Todos os Quartos ===");
        if (listaDeQuartos.isEmpty()) {
            System.out.println("Nenhum quarto cadastrado no hotel.");
            return;
        }
        for (Quarto q : listaDeQuartos){
            q.exibirInformacoesQuarto();
            System.out.println("-----------------------------");
        }
        System.out.println("=== Fim da Lista ===");
    }

    public void adicionarHospede(Hospede hospede){
        listaDeHospedes.add(hospede);
        System.out.println("Hóspede " + hospede.getNomeCompleto() + " adicionado com sucesso.");
    }

    public Hospede buscarHospede(String cpf){
        for (Hospede h : listaDeHospedes){
            if (h.getCpf().equals(cpf)){
                return h;
            }
        }
        // Lançando exceção se o hóspede não for encontrado
        throw new HospedeNaoEncontradoException("Hóspede com CPF " + cpf + " não encontrado.");
    }

    public void fazerReserva(Hospede hospede, Quarto quarto, int diarias,  String checkIn, String checkOut){
        // verifica se o quarto está ocupado
        if (quarto.isEstaOcupado()){
            // Lançando QuartoOcupadoException e o método encerra aqui (não precisa de 'return;')
            throw new QuartoOcupadoException("Quarto " + quarto.getNumeroQuarto() + " já está ocupado. Não foi possível fazer a reserva.");
        }
        // Se chegar aqui, o quarto está livre, então a reserva pode ser criada
        Reserva novaReserva = new Reserva(hospede, quarto, diarias, checkIn, checkOut); // O construtor da Reserva já chama quarto.ocupar()
        listaDeReservas.add(novaReserva);
        System.out.println("Reserva criada com sucesso para o hóspede " + hospede.getNomeCompleto() + " no quarto " + quarto.getNumeroQuarto() + ".");
    }

    // Método fazerCheckOut corrigido para 'public void' e sem 'return false;'
    public boolean fazerCheckOut(int numeroQuarto){
        Reserva reservaParaRemover = null;
        for (Reserva r : listaDeReservas){
            if (r.getQuarto().getNumeroQuarto() == numeroQuarto){
                reservaParaRemover = r;
                break; // Encontrou a reserva, pode sair do loop
            }
        }

        if (reservaParaRemover != null){
            reservaParaRemover.getQuarto().liberar(); // Libera o quarto
            listaDeReservas.remove(reservaParaRemover); // Remove a reserva da lista
            System.out.println("Check-out do quarto " + numeroQuarto + " realizado com sucesso. Quarto liberado.");
        } else {
            // Lançando exceção se a reserva não for encontrada para o check-out
            throw new ReservaNaoEncontradaException("Não há reserva ativa para o quarto " + numeroQuarto + ".");
        }
        // REMOVIDO 'return false;'
        return false;
    }

    public void listarReservasAtivas(){
        System.out.println("\n=== Lista de Reservas Ativas ===");
        if (listaDeReservas.isEmpty()){
            System.out.println("Nenhuma reserva ativa no momento.");
            return;
        }
        for (Reserva r : listaDeReservas){
            // Corrigido para 'exibirDetalhesReserva()' (sem o 'Da')
            r.exibirDetalhesDaReserva();
            System.out.println("--------------------------------");
        }
        System.out.println("=== Fim das Reservas Ativas ===");
    }
}