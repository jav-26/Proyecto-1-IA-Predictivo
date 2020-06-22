package Main;

import Formularios.FormularioPrincipal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import AlgoritmosBusqueda.Nodo;

/**
 *
 * @author javie
 */
public class Main{
    public static void main(String[] args) {
        FormularioPrincipal form = new FormularioPrincipal();
        form.Inicializacion();
    }
    
    public void llenar(){
        Nodo nodo;
        String ubicacion = "C:\\Users\\javie\\Documents\\GitHub\\TextoPredictivoIA\\TextoPredictivoIA\\palabras\\Palabras_con_M.txt";
        String ubicacion2 = "C:\\Users\\javie\\Documents\\GitHub\\TextoPredictivoIA\\TextoPredictivoIA\\palabras\\costo.txt";
        File archivo = new File(ubicacion);
        File archivo2 = new File(ubicacion2);
        
        try{
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            BufferedReader entrada2 = new BufferedReader(new FileReader(archivo2));
            
            String lectura = entrada.readLine();
            Integer lectura2 = new Integer(entrada.readLine());
           
            while(lectura != null && lectura2 != null){
                int cont = 0;
                if(cont == 0){
                    nodo = new Nodo(lectura, lectura2);
                }
                else{
                    
                }
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace(System.out);
        }
    }
}
