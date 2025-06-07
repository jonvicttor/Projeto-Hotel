package com.meuhotel.app;

import com.meuhotel.exceptions.HospedeNaoEncontradoException;
import com.meuhotel.exceptions.QuartoNaoEncontradoException;
import com.meuhotel.exceptions.QuartoOcupadoException;
import com.meuhotel.model.Hospede;
import com.meuhotel.model.Quarto;
import com.meuhotel.service.Hotel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel meuHotel = new Hotel();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        // Dados iniciais
        meuHotel.adicionarQuarto(new Quarto(101, "Simples", 150.00));
        meuHotel.adicionarQuarto(new Quarto(102, "Luxo", 300.00));
        meuHotel.adicionarHospede(new Hospede("Alice", "111.222.333-44", "987654321"));


        do {
            System.out.println("\n--- MENU DO HOTEL ---");
            System.out.println("1. Adicionar Quarto");
            System.out.println("2. Adicionar Hóspede");
            System.out.println("3. Fazer Reserva");
            System.out.println("4. Fazer Check-out");
            System.out.println("5. Listar Quartos Disponíveis");
            System.out.println("6. Exibir Todos os Quartos");
            System.out.println("7. Listar Reservas Ativas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Número do quarto: ");
                    int numero = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Tipo do quarto: ");
                    String tipo = scanner.nextLine();
                    System.out.print("Preço por diária: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();
                    meuHotel.adicionarQuarto(new Quarto(numero, tipo, preco));
                    System.out.println("Quarto adicionado com sucesso!");
                    break;

                case 2:
                    System.out.print("Nome do hóspede: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine(); // <-- Declarada aqui
                    meuHotel.adicionarHospede(new Hospede(nome, cpf, telefone));
                    System.out.println("Hóspede adicionado com sucesso!");
                    break;

                case 3: // Fazer Reserva
                    try {
                        System.out.print("CPF do hóspede: ");
                        String cpfReserva = scanner.nextLine();
                        Hospede hospedeParaReserva = null;
                        try {
                            hospedeParaReserva = meuHotel.buscarHospede(cpfReserva);
                            System.out.println("Hóspede encontrado: " + hospedeParaReserva.getNomeCompleto());
                        } catch (HospedeNaoEncontradoException e) {
                            System.out.println("Hóspede com CPF " + cpfReserva + " não encontrado.");
                            System.out.print("Deseja cadastrar um novo hóspede com este CPF? (s/n): ");
                            String resposta = scanner.nextLine();
                            if (resposta.equalsIgnoreCase("s")) {
                                System.out.print("Nome do novo hóspede: ");
                                String nomeCompleto = scanner.nextLine();
                                System.out.print("Telefone do novo hóspede: ");
                                telefone = scanner.nextLine(); // <-- MUDANÇA AQUI: Removido 'String'
                                hospedeParaReserva = new Hospede(nomeCompleto, cpfReserva, telefone);
                                meuHotel.adicionarHospede(hospedeParaReserva);
                                System.out.println("Hóspede cadastrado com sucesso!");
                            } else {
                                System.out.println("Operação de reserva cancelada.");
                                break; // Sai do case 3
                            }
                        }
                        // ... (o restante do seu Case 3 para quarto, diárias, etc.)
                    } catch (QuartoOcupadoException | QuartoNaoEncontradoException | HospedeNaoEncontradoException e) {
                        System.err.println("Erro na reserva: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Número do quarto para check-out: ");
                    int numeroCheckout = scanner.nextInt();
                    scanner.nextLine();
                    boolean sucesso = meuHotel.fazerCheckOut(numeroCheckout);
                    if (sucesso) {
                        System.out.println("Check-out realizado com sucesso!");
                    } else {
                        System.out.println("Check-out falhou. Verifique o número do quarto.");
                    }
                    break;

                case 5:
                    meuHotel.listarQuartosDisponiveis("Simples");
                    meuHotel.listarQuartosDisponiveis("Luxo");
                    break;

                case 6:
                    System.out.println("--- Todos os Quartos ---");
                    meuHotel.exibirTodosOsQuartos();
                    break;

                case 7:
                    System.out.println("--- Reservas Ativas ---");
                    meuHotel.listarReservasAtivas();
                    break;

                case 0:
                    System.out.println("Saindo do programa. Obrigado!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
