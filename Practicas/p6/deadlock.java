/**
 * Esta clase contiene los atributos y metodos de deadlock
 * @author Rafael Leal Pardo
 * @version 2022
*/

public class deadlock extends Thread{

    /**
     * Metodo main que iniciara los valores, threads con runnable para luego ejecutarlos.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception{
        final Object A = new Object();
        final Object B = new Object();
        final Object C = new Object();

        Thread hilo_c = new Thread(new Runnable() {
            public void run(){
                synchronized(A){for(int i=0; i<10000;i++);
                    synchronized(B){
                        synchronized(C){
                            System.out.println("hola C");
                        }
                    }
                }
            }
        });

        Thread hilo_b = new Thread(new Runnable() {
            public void run(){
                synchronized(C){for(int i=0; i<10000;i++);
                    synchronized(A){
                        synchronized(B){
                            System.out.println("hola B");
                        }
                    }
                }
            }
        });

        Thread hilo_a = new Thread(new Runnable() {
            public void run(){
                synchronized(C){for(int i=0; i<10000;i++);
                    synchronized(B){
                        synchronized(A){
                            System.out.println("hola A");
                        }
                    }
                }
            }
        });

        hilo_c.start();
        hilo_b.start();
        hilo_a.start();
    }
}