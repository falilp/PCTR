import java.util.Random;
/**
 * Esta clase contiene los atributos y metodos de resImagen
 * @author Rafael Leal Pardo
 * @version 2022
*/
public class resImagen{
    public int nTamanio;
    public int matriz[][];

    /**
     * Metodo Constructor parametrizado
     * @param tam Entero que es asignado al numero de filas y columnas
     */
    public resImagen(int tam){
        this.nTamanio = tam;
        matriz = new int[nTamanio][nTamanio];
        Random ran = new Random();

        for(int i=0; i<nTamanio; i++){
            for(int j=0; j<nTamanio;j++){
                matriz[i][j] = ran.nextInt()%255;
            }
        }
    }

    /**
     * Metodo que realiza el tratamiento de imagenes
     */
    public void RealizarRes(){
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz[i].length; j++){
                matriz[i][j] = 4*matriz[i][j];

                if(i+1 < matriz.length){matriz[i][j] -= matriz[i+1][j];}
				if(j+1 < matriz[i].length){matriz[i][j] -= matriz[i][j+1];}
				if(i-1 >= 0){matriz[i][j] -= matriz[i-1][j];} 
				if(j-1 >= 0){matriz[i][j] -= matriz[i][j-1];} 
				
				matriz[i][j] /= 8;
            }
        }
    }

    /**
     * Metodo main el cual realizará las llamadas, operaciones y cronometra los tiempos.
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args)throws InterruptedException{
        resImagen imagen = new resImagen(1000);
        long inicioCrono = System.currentTimeMillis();

        imagen.RealizarRes();

        long finalizoCrono = System.currentTimeMillis();
        System.out.println("Tiempo en realizar: "+ (finalizoCrono-inicioCrono) +" milisegundos");
    }

}