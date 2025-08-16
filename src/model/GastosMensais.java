package model;

import java.time.DayOfWeek;
import java.util.Scanner;

public class GastosMensais{
   public int ano;
   public int mes;
   public DayOfWeek[] diasFolga;
   public double valorAlimentacao;
   public double valorPassagem;

   public GastosMensais() {
   }

   /**
    * @param var1
    */
   public void coletarDados(Scanner var1) {
      System.out.print("\nDigite o ano: ");
      this.ano = var1.nextInt();

      while(true) {
         System.out.print("\nDigite o mês (1 a 12): ");
         this.mes = var1.nextInt();
         if (this.mes >= 1 && this.mes <= 12) {
            var1.nextLine();

            while(true) {
               System.out.print("\nDigite os dias de folga (1=Seg...7=Dom): ");
               String[] var2 = var1.nextLine().split(" ");
               boolean var3 = true;
               this.diasFolga = new DayOfWeek[var2.length];

               for(int var4 = 0; var4 < var2.length; ++var4) {
                  try {
                     int var5 = Integer.parseInt(var2[var4]);
                     if (var5 < 1 || var5 > 7) {
                        var3 = false;
                        break;
                     }

                     this.diasFolga[var4] = DayOfWeek.of(var5);
                  } catch (NumberFormatException var6) {
                     var3 = false;
                     break;
                  }
               }

               if (var3) {
                  System.out.print("\nDigite o valor da alimentação diária: ");
                  this.valorAlimentacao = var1.nextDouble();
                  System.out.print("\nDigite o valor da passagem diária: ");
                  this.valorPassagem = var1.nextDouble();
                  return;
               }

               System.out.println("Entrada inválida!");
            }
         }

         System.out.println("Mês inválido!");
      }
}

}
