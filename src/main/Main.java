package main;
import models.ExchangeResponse;
import client.APIclient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        APIclient api = new APIclient();

        boolean executar = true;
        while (executar){
            System.out.println("==============================");
            System.out.println("|1) Real => Peso argentino|");
            System.out.println("|2) Peso argentino => Real|");
            System.out.println("|3) Dolár => Real|");
            System.out.println("|4) Real => Dólar|");
            System.out.println("|5) Dólar => Peso colombiano|");
            System.out.println("|6) Peso colombiano => Dólar |");
            System.out.println("================================");
            System.out.println("Escolha uma opção: ");
            String opcao = scanner.nextLine().trim();

            if (opcao.equalsIgnoreCase("sair")){
                executar = false;
                System.out.println("Saindo do sistema...");
                break;
            }

            int escolha;
            try {
                escolha = Integer.parseInt(opcao);
            }catch (NumberFormatException e){
                System.out.println("O que voce digitou não corresponde ao que foi pedido, por favor, selecione úm número de 1 a 6");
                continue;
            }

            String moedaOrigem = null;
            String moedaDestino = null;

            switch (escolha){
                case 1:
                    moedaOrigem = "BRL";
                    moedaDestino = "ARS";
                    break;

                case 2:
                    moedaOrigem = "ARS";
                    moedaDestino = "BRL";
                    break;

                case 3:
                    moedaOrigem = "USD";
                    moedaDestino = "BRL";
                    break;

                case 4:
                    moedaOrigem = "BRL";
                    moedaDestino = "USD";
                    break;

                case 5:
                    moedaOrigem = "USD";
                    moedaDestino = "COP";
                    break;

                case 6:
                    moedaOrigem = "COP";
                    moedaDestino = "USD";
                    break;

                default:
                    System.out.println("Escolha uma opção válida");
                    continue;
            }

            double valor;
            System.out.println("Digite o valor a ser convertido: ");

            try {
                valor = Double.parseDouble(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Valor inválido.");
                continue;
            }

            try {
                ExchangeResponse response = api.getRates(moedaOrigem);
                Double taxa = response.getConversion_rates().get(moedaDestino);
                if(taxa == null){
                    System.out.println("moeda não encontrada");
                    continue;
                }
                double valorConvertido = taxa * valor;

                System.out.println("Resultado: " + valorConvertido + " " + moedaDestino);
            }catch (Exception e){
                System.out.println("Erro ao caçar dados na API");
            }

        }

        scanner.close();



    }
}