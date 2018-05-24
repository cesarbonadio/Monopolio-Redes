package puertoejemplo;
import gnu.io.*;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CesarAugusto
 */
public class Puertoejemplo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, UnsupportedCommOperationException {
        try {
   
           
            CommPortIdentifier puerto = null;
            
            // el getPortIdentifiers detecta todos los puertos activo
            Enumeration <?>  puertos = CommPortIdentifier.getPortIdentifiers();
            int i=0;
            
            //este se supone que es un scan para leer datos por consola de java y mandarlos
            Scanner myScan;
            
            
            PrintStream myPS;
            
            
            while(puertos.hasMoreElements()){
                puerto = (CommPortIdentifier) puertos.nextElement();
                
                /*System.out.println("PUERTO "+i+" "+puerto.getName());
                ++i;*/    
               
                if (puerto.getName().equals("COM4"))break;
                //aqui colocar el puerto que se desee // obtener el puerto llamado COM4                
            }
            
            
            if (puerto!=null) // esto para saber que agarro el puerto bien
                System.out.println("Puerto :"+puerto.getName());
            
            
            
            
            // aqui abre el puerto asignandolo a un puerto COM, pues antes no se sabia que era un puerto de
            //tipo com
            CommPort pt = puerto.open("puerto 1",1000);
           
            
            
            
            // al puerto com pt se le hace un cast para saber que es un puerto serial
            SerialPort mipuerto = (SerialPort) pt;
            
            
            
            
            //RxtX no tiene documentacion por lo que supongo que los parametros son :
            // (id del puerto com serial, cantidad de bits de los datos, bit de parada, paridad)
            mipuerto.setSerialPortParams(19200, SerialPort.DATABITS_8,SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            
            
            
            myScan = new Scanner(mipuerto.getInputStream());
            myPS = new PrintStream(mipuerto.getOutputStream());
           
            
            while(!myScan.hasNextInt()){
                 myScan.close();
                 myScan = null;
                 myScan = new Scanner (mipuerto.getInputStream());
            }
            
            int valor = myScan.nextInt();
            System.out.println(valor);
            

           // aqui se supone que manda un hola
            myPS.println("Hola");
            
            
        } catch (PortInUseException ex) {
            //Una excepcion que arroja si el puerto esta siendo usado
            Logger.getLogger(Puertoejemplo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
