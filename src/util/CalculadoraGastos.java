package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import model.GastosMensais;

public class CalculadoraGastos {
    private final GastosMensais dados;
    private int diasTrabalhados;
    private int folgas;
    private double custoTotalAlimentacao;
    private double custoTotalLocomocao;
    private int diasRestantes;
    private double custoRestanteAlimentacao;
    private double custoRestanteLocomocao;

    public CalculadoraGastos(GastosMensais dados) {
        this.dados = dados;
    }

    public void processar() {

        // Dias do mês (considera bissexto automaticamente)

        int diasMes = YearMonth.of(dados.ano, dados.mes).lengthOfMonth();



       DayOfWeek[] folgasArr = (dados.diasFolga != null)
        ? dados.diasFolga.toArray(DayOfWeek[]::new)
        : new DayOfWeek[0];

     for (int dia = 1; dia <= diasMes; dia++) {
       LocalDate data = LocalDate.of(dados.ano, dados.mes, dia);
      for (DayOfWeek f : folgasArr) {
        if (data.getDayOfWeek() == f) {
            this.folgas++;
            break;
                }
            }
        }

        // Calculo de dias trabalhados e custos totais

        diasTrabalhados = diasMes - folgas;
        custoTotalAlimentacao = dados.valorAlimentacao * diasTrabalhados;
        custoTotalLocomocao = dados.valorPassagem * diasTrabalhados * 2.0;

        // Dias passados somente se o mês/ano do input for o mês/ano atuais

        LocalDate hoje = LocalDate.now();
        int diasPassados = 0;
        if (dados.ano == hoje.getYear() && dados.mes == hoje.getMonthValue()) {
            int limite = Math.min(hoje.getDayOfMonth(), diasMes);
            for (int dia = 1; dia <= limite; dia++) {
                LocalDate data = LocalDate.of(dados.ano, dados.mes, dia);
                boolean ehFolga = false;
                for (DayOfWeek f : folgasArr) {
                    if (data.getDayOfWeek() == f) {
                        ehFolga = true;
                        break;
                    }
                }
                if (!ehFolga) diasPassados++;
            }
        }

        // Restantes
        diasRestantes = diasTrabalhados - diasPassados;
        custoRestanteAlimentacao = dados.valorAlimentacao * diasRestantes;
        custoRestanteLocomocao = dados.valorPassagem * diasRestantes * 2.0;
    }



    /*
     * Saida do resultado 
     * 
     */
    public void exibirResultados() {
        
        Month nomeMes = Month.of(dados.mes);
        String nomeMesExtenso = nomeMes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));

        System.out.println("\n===================================");
        System.out.println("BEM-VINDO !  " + dados.nomeUsuario);
        System.out.println("===================================\n");
        System.out.println("MÊS DE " + nomeMesExtenso + "\n");
        System.out.println("Dias trabalhados => " + diasTrabalhados);
        System.out.println("Folgas =>  " + folgas + "\n");
        System.out.println("-----------------------------------\n");
        System.out.println("GASTO MENSAL\n ");
        System.out.printf("%-20s R$ %-10.2f\n", "Alimentação:", custoTotalAlimentacao);
        System.out.printf("%-20s R$ %-10.2f\n", "Locomoção:", custoTotalLocomocao);
        System.out.println("\n-----------------------------------");
        System.out.println("\nGASTOS PARCIAL\n");
        System.out.printf("%-20s %d\n", "Dias restantes:", diasRestantes);
        System.out.printf("%-20s R$ %-10.2f\n", "Alimentação:", custoRestanteAlimentacao);
        System.out.printf("%-20s R$ %-10.2f\n", "Locomoção:", custoRestanteLocomocao);
        System.out.println("\n===================================\n");
    }
}

