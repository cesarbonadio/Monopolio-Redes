/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puertoejemplo;

import gnu.io.*;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;

/**
 *
 * @author CesarAugusto
 */
public final class DetectorPuerto {
    
   
    Enumeration <?> puertos;

    
    public DetectorPuerto(){
      //obtener todos los puertos libres 
      this.puertos = CommPortIdentifier.getPortIdentifiers();
    }
    
    /**
     @return el identificador del puerto com
     @param nombre el nombre del puerto del que se desea obtener el identificador
     */
    
    public CommPortIdentifier getPuerto(String nombre){
      CommPortIdentifier puerto = null;
      while(this.puertos.hasMoreElements()){
          puerto = (CommPortIdentifier) puertos.nextElement();
                if (puerto.getName().equals(nombre))break;  
        }
      return puerto;
    }
    
    
    /**
     @return  el verdadero puerto serial
     @param puerto el identificador del puerto com
     @param nombre el nombre que se le desea asignar
     @param id el id que se le desea asignar
     @throws PortInUseException una excepcion cuando el puerto esta siendo usado
     */
    
    public SerialPort abrirPuerto(CommPortIdentifier puerto, String nombre, int id) throws PortInUseException{
        CommPort puertocom = puerto.open(nombre, id);
        SerialPort puertoserial = (SerialPort) puertocom;
      return puertoserial;
    }
    
    
    
    public void mandarAlgo(SerialPort puertoserial, String datos) throws IOException{
       PrintStream myPS;
       myPS = new PrintStream(puertoserial.getOutputStream());
       myPS.println(datos);
    }
    
    
}
