import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class intDefinidaMonteCarlo{
    
    public static void main(String[] arg){
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Introduzca el numero de veces a comparar:\n");
            int n = scan.nextInt();

            MetodoMontecarlo(n);
        }catch (InputMismatchException ERROR){
            System.out.println("No se seleccionó una opcion valida");
        
        }
    }

    public static void MetodoMontecarlo(int n){
        int contadorExitosRandom=0;
        int contadorExitosAleatorio=0;
        double cordenadaXRandom = 0;
        double cordenadaYRandom = 0;
        double cordenadaXAleatorio = 0;
        double cordenadaYAleatorio = 0;
        
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Seleccione la opción deseada \n1- f(x)\n2- seno(x)\n");
            int op = scan.nextInt();

            switch(op){
                case 1:
                    for(int i=0;i<n;i++){
                        cordenadaXAleatorio = aleatorio(0,1);
                        cordenadaYAleatorio = aleatorio(0,1);
                        cordenadaXRandom = random(0,1);
                        cordenadaYRandom = random(0,1);
                        
                        if(cordenadaYAleatorio <= Funcion(cordenadaXAleatorio)){
                            contadorExitosAleatorio++;
                        }
                        if(cordenadaYRandom <= Funcion(cordenadaXRandom)){
                            contadorExitosRandom++;
                        }
                    }
                    System.out.println("Contador de exitos con Aleatorio: " + (float)contadorExitosRandom/n +"\nContador de exitos con Random: "+ (float)contadorExitosAleatorio/n);
                    break;
                case 2:
                    for(int i=0;i<n;i++){
                        cordenadaXAleatorio = aleatorio(0,1);
                        cordenadaYAleatorio = aleatorio(0,1);
                        cordenadaXRandom = random(0,1);
                        cordenadaYRandom = random(0,1);
                        
                        if(cordenadaYAleatorio <= Funcion(cordenadaXAleatorio)){
                            contadorExitosAleatorio++;
                        }
                        if(cordenadaYRandom <= seno(cordenadaXRandom)){
                            contadorExitosRandom++;
                        }
                    }
                    System.out.println("Contador de exitos con Aleatorio: " + (float)contadorExitosRandom/n +"\nContador de exitos con Random: "+ (float)contadorExitosAleatorio/n);
                    break;
                default:
                    System.out.println("No se seleccionó una opcion valida");
                    break;
            }
        }catch (InputMismatchException ERROR){
            System.out.println("No se seleccionó una opcion valida");
        }
    }

    public static double Funcion(double x){
        return x;
    }

    public static double seno(double x){
        return Math.sin(x);
    }

    //metodo con math
    public static double aleatorio(double op1,double op2){
        return Math.random()*op2+op1; //para math.random()*op2+op1 se debe colocar el el numero mayor del intervalo primero y el menor despues (*mayor+menor).
    }

    //metodo con java.util
    public static double random(double op1,double op2){
        Random ran = new Random();
        return ran.nextDouble(op1, op2); //java.util  basta con pasar por valor el menor valor primero y el mayor segundo.
    }
}

//OWNER: RAFAEL LEAL PARDO