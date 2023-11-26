/*import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;*/

import java.util.concurrent.locks.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class E17 implements Runnable{
    private static AtomicInteger s = new AtomicInteger();
    private static ReentrantLock l = new ReentrantLock();
    private static int p = 2000;
    private static ArrayList<Condition> c = new ArrayList<Condition>();
    private int n;

    public E17(int n){
        this.n=n;
        c.add(n, l.newCondition());
    }

    public void run(){
        int j;

        for(int i=0; i<1000; i++);
        l.lock();

        try{ j=s.incrementAndGet();
            c.get(0).signalAll();
            try{c.get(n).await();
            }catch(InterruptedException e){}
        }finally{l.unlock();}
    }

    public static void main(String[] args){
        ExecutorService ept = Executors.newCachedThreadPool();

        for(int i=0; i<2000; i++)ept.submit(new E17(i));
        
        ept.shutdown();
        
        System.out.print(s);
    }
}












////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*private static int contador = 0;
    private static int contador2 = 0;
    private static final Object mutex = new Object();
    private static Semaphore sem = new Semaphore(1);

    public void run(){
        synchronized(mutex){
            for(int i=0;i<100;++i){
                ++contador2;
            }
        }
        try{
            sem.acquire();
            ++contador;System.out.println("El contador es: "+ contador);
        }catch(InterruptedException exc){System.out.println(exc);}
        sem.release();
    }

    public static void main(String[] args)throws Exception{
        Runnable r1 = new AnotacionesT1();Runnable r2 = new AnotacionesT1();Runnable r3 = new AnotacionesT1();
        Thread h1 = new Thread(r1);Thread h2 = new Thread(r2);Thread h3 = new Thread(r3);
        h1.start();h2.start();h3.start();
        h1.join();h2.join();h3.join();
        /*int nTareas = Runtime.getRuntime().availableProcessors();
        AnotacionesT1 ant[] = new AnotacionesT1[nTareas];
        
        ThreadPoolExecutor ejecutor = new ThreadPoolExecutor(nTareas, nTareas, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        for(int index=0; index<nTareas; ++index){
            ant[index] = new AnotacionesT1();
            ejecutor.execute(ant[index]);
        }*/
        //System.out.println("contador 2 es: "+ contador2);
        //ejecutor.shutdown();
    //}