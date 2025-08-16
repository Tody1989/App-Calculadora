package app;


import java.util.Scanner;
import model.GastosMensais;
import util.CalculadoraGastos;

public class Main {


   public Main() {
   }

   public static void main(String[] var0) {
       try (Scanner var1 = new Scanner(System.in)) {
           GastosMensais var2 = new GastosMensais();
           var2.coletarDados(var1);
           CalculadoraGastos var3 = new CalculadoraGastos(var2);
           var3.processar();
           var3.exibirResultados();
       }
   }
}
