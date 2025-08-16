package model;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Scanner;

public class GastosMensais {
    public int ano;
    public int mes;
    public List<DayOfWeek> diasFolga;
    public double valorAlimentacao;
    public double valorPassagem;
    public String nomeUsuario;

    public void coletarDados(Scanner sc) {
        System.out.print("Digite seu nome: ");
        nomeUsuario = sc.nextLine();

        System.out.print("Digite o ano: ");
        ano = sc.nextInt();

        do {
            System.out.print("Digite o mês (1 a 12): ");
            mes = sc.nextInt();
        } while (mes < 1 || mes > 12);

        sc.nextLine(); 

        System.out.print("Digite os dias de folga (1=Seg, 2=Ter, ... 7=Dom) separados por espaço: ");
        String[] entrada = sc.nextLine().split(" ");
        diasFolga = new java.util.ArrayList<>();
        for (String s : entrada) {
            int d = Integer.parseInt(s);
            diasFolga.add(DayOfWeek.of(d));
        }

        System.out.print("Digite valor diário de alimentação: ");
        valorAlimentacao = sc.nextDouble();

        System.out.print("Digite valor diário de passagem: ");
        valorPassagem = sc.nextDouble();
    }
}
