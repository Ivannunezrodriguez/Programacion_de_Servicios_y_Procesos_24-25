import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProcesoLanzador procesoLanzador;
        procesoLanzador=new ProcesoLanzador();

// lanza el proceso 3 veces
        for (int i = 1; i <=3 ; i++) {

            procesoLanzador.lanzarProceso();
            System.out.println("Instancia numero "+i+" del proceso de lanzado");


        }

    }


}
