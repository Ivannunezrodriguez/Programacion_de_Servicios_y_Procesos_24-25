import java.io.IOException;

public class ProcesoLanzador {
    public void lanzarProceso(){

        ProcessBuilder processBuilder=new ProcessBuilder("src/triangulo5.txt" );
        try{
            processBuilder.start().waitFor();
            System.out.println("Proceso lanzado");
        }catch (IOException e){
            System.out.println("Error al lanzar el proceso"+e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("El proceso se ha interrumpido"+e.getMessage());
        }
    }
}
