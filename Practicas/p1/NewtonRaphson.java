import java.util.InputMismatchException;
import java.util.Scanner;

public class NewtonRaphson {
    
    public static void main(String[] arg){
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Introduzca un entero para el numero de iteraciones\n");
            int itera = scan.nextInt();

            System.out.println("Introduzca un entero para la aproximacion de x sub 0\n");
            int xsub0 = scan.nextInt();

            Newton_Raphson(xsub0,itera);

        }catch(InputMismatchException ERROR){
            System.out.println("Se introdujo un valor erroneo\n");
        }
    }

    public static void Newton_Raphson(int x0,int iteraciones){
        double xn = x0;
        double xn1 = 0;
        
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Introduzca un entero seleccionar entre\n1- Math.cos(x) - Math.pow(x,3)\n2- Math.pow(x,2) - 5\n");
            int itera = scan.nextInt();
            
            switch (itera) {
                case 1:
                    for(int i=0; i<iteraciones; i++){
                        if(Fprima1(xn) != 0){
                            xn1 = xn - Funcion1(xn) / Fprima1(xn);
                            System.out.println("Iteraccion: "+ i +" Aproximacion: "+ xn1);
                            xn = xn1;
                        }
                    }
                    break;
                case 2:
                    for(int i=0; i<iteraciones; i++){
                        if(Fprima2(xn) != 0){
                            xn1 = xn - Funcion2(xn) / Fprima2(xn);
                            System.out.println("Iteraccion: "+ i +" Aproximacion: "+ xn1);
                            xn = xn1;
                        }
                    }
                    break;
                default:
                    break;
            }
        }catch(InputMismatchException ERROR){
            System.out.println("Se introdujo un valor erroneo\n");
        }

        System.out.println("El resultado es: "+ xn);
    }

    public static double Funcion1(double x){
        return Math.cos(x) - Math.pow(x,3);
    }

    public static double Fprima1(double x){
        return -(Math.sin(x)) - (3 * Math.pow(x, 2));
    }

    public static double Funcion2(double x){
        return Math.pow(x,2) - 5;
    }

    public static double Fprima2(double x){
        return 2 * Math.pow(x, 1);
    }
}

//OWNER: RAFAEL LEAL PARDO