package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
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

   public CalculadoraGastos(GastosMensais var1) {
      this.dados = var1;
   }

   public void processar() {
      byte var1;
      var1 = switch (this.dados.mes) {
           case 1, 3, 5, 7, 8, 10, 12 -> 31;
           case 2 -> 28;
           case 4, 6, 9, 11 -> 30;
           default -> 30;
       };

      this.folgas = 0;

      for(int var2 = 1; var2 <= var1; ++var2) {
         LocalDate var3 = LocalDate.of(this.dados.ano, this.dados.mes, var2);
         DayOfWeek[] var4 = this.dados.diasFolga;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            DayOfWeek var7 = var4[var6];
            if (var3.getDayOfWeek() == var7) {
               ++this.folgas;
               break;
            }
         }
      }

      this.diasTrabalhados = var1 - this.folgas;
      this.custoTotalAlimentacao = this.dados.valorAlimentacao * (double)this.diasTrabalhados;
      this.custoTotalLocomocao = this.dados.valorPassagem * (double)this.diasTrabalhados * 2.0;
      LocalDate var11 = LocalDate.now();
      int var12 = 0;

      for(int var13 = 1; var13 <= var11.getDayOfMonth(); ++var13) {
         LocalDate var14 = LocalDate.of(this.dados.ano, this.dados.mes, var13);
         boolean var15 = false;
         DayOfWeek[] var16 = this.dados.diasFolga;
         int var8 = var16.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            DayOfWeek var10 = var16[var9];
            if (var14.getDayOfWeek() == var10) {
               var15 = true;
               break;
            }
         }

         if (!var15) {
            ++var12;
         }
      }

      this.diasRestantes = this.diasTrabalhados - var12;
      this.custoRestanteAlimentacao = this.dados.valorAlimentacao * (double)this.diasRestantes;
      this.custoRestanteLocomocao = this.dados.valorPassagem * (double)this.diasRestantes * 2.0;
   }

   public void exibirResultados() {

      System.out.println("\n****************************");

      System.out.println("Dias trabalhados no mês: " + this.diasTrabalhados);

      System.out.println("Folgas no mês: " + this.folgas);

      System.out.println("\nCUSTO TOTAL DO MÊS");

      System.out.println("Alimentação: R$ " + this.custoTotalAlimentacao);

      System.out.println("Locomoção: R$ " + this.custoTotalLocomocao);

      System.out.println("\nCUSTO RESTANTE DO MÊS");

      System.out.println("Dias restantes: " + this.diasRestantes);

      System.out.println("Alimentação restante: R$ " + this.custoRestanteAlimentacao);

      System.out.println("Locomoção restante: R$ " + this.custoRestanteLocomocao);
      
      System.out.println("\n****************************");
   }
}
