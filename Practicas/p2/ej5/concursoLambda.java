/**
 * Esta clase contiene los atributos y metodos de concursoLambda
 * @author Rafael Leal Pardo
 * @version 2022
*/
public class concursoLambda{
    public static int contador = 0;

    /**
     * Metodo main que realizara la condición de concurso
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Runnable run1 = () -> {for(int i=0;i<10000;i++){contador++;}};
        Runnable run2 = () -> {for(int i=0;i<10000;i++){contador--;}};

        Thread h1 = new Thread(run1);
        Thread h2 = new Thread(run2);

        h1.start();h2.start();
        h1.join();h2.join();

        System.out.println("El resultado de la variable compartida por las expresiones lambda es: "+ contador);
    }
}