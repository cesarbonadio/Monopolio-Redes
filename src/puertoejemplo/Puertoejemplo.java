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
    public static void main(String[] args) {
       
        
        try{
            
            DetectorPuerto d = new DetectorPuerto();
            
            
            CommPortIdentifier s = d.getPuerto("COM10");
            SerialPort serial = d.abrirPuerto(s, "puerto 1", 1000);
            
            serial.setSerialPortParams(9600, 8, 1, 0);
            
            d.mandarAlgo(serial, "hello port");
            
            
           
        } catch (PortInUseException ex) {
            System.out.println("Puerto en uso");
        } catch (IOException ex) {
            Logger.getLogger(Puertoejemplo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(Puertoejemplo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
