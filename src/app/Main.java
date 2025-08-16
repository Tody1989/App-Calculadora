/*
 * 
 * Programa para calcular gastos mensais 
 */


package app;

import java.util.Scanner;
import model.GastosMensais;
import util.CalculadoraGastos;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            GastosMensais dados = new GastosMensais();
            dados.coletarDados(sc);

            CalculadoraGastos calc = new CalculadoraGastos(dados);
            calc.processar();
            calc.exibirResultados();
        }
    }
}