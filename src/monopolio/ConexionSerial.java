/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopolio;
import com.fazecast.jSerialComm.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static monopolio.ConexionSerial.pasarByteAString;

/**
 *
 * @author Chu
 */
public class ConexionSerial {
    SerialPort puertoEntrada;
    SerialPort puertoSalida;
    
    public void ConexionSerial(){
        
    }
    
    public void setPuertos(int entrada, int salida){
        this.puertoEntrada = SerialPort.getCommPorts()[entrada];
        this.puertoEntrada.setComPortParameters(2400, 8, 0, 1);
        this.puertoSalida = SerialPort.getCommPorts()[salida];
        this.puertoSalida.setComPortParameters(2400, 8, 0, 1);      
    }
    
    
    public static ArrayList<String> listaPuertos(){
        ArrayList<String> puertos = new ArrayList<String>();
        SerialPort[] comPort = SerialPort.getCommPorts();
        for (int i=0; i<comPort.length; i++){
            puertos.add(comPort[i].getDescriptivePortName());
        }
        return puertos;
    }
    
    public static String pasarByteAString(byte b){
        String retorno = Integer.toBinaryString(b & 0xFF);
        //Para asegurar que sean 8 caracteres(llenar de ceros a la izquierda)
        while(retorno.length()<8) retorno = "0" + retorno;
        return retorno;
    }
    
     
    
    public void EnviarMensaje(String primero, String segundo){
        byte[] envio = new byte[4];
        this.puertoSalida.openPort();
        String flag = "01111110";
        envio[0] = (byte)Short.parseShort(flag, 2);
        envio[1] = (byte)Short.parseShort(primero, 2);
        envio[2] = (byte)Short.parseShort(segundo, 2);
        envio[3] = (byte)Short.parseShort(flag, 2);
        System.out.print("Mensaje enviado: "
                +" "+pasarByteAString(envio[0])
                +" "+pasarByteAString(envio[1])
                +" "+pasarByteAString(envio[2])
                +" "+pasarByteAString(envio[3])
                +"\n");
        puertoSalida.writeBytes(envio, envio.length);
        this.puertoSalida.closePort();
    }
    
    public byte[] RecibirMensaje(){
        byte[] readBuffer = null; // Bytes para almacenar la informacion
        this.puertoEntrada.openPort();
        //Espero a que algo llegue
        System.out.println("Esperando mensaje...");
        while (this.puertoEntrada.bytesAvailable() < 4) {
        }
            
        // Algo llego asi que lo almaceno en el buffer
        readBuffer = new byte[4];
        int numRead = puertoEntrada.readBytes(readBuffer, 4);
            
        //Comprobacion de que se envio
        System.out.print("Se encontro el mensaje:\n");
        for(int i=0; i<numRead;i++) System.out.println(" "+
                pasarByteAString(readBuffer[i]));
        this.puertoSalida.closePort();
        return readBuffer;
    }
    
       
    
    /*public void RecibirMensaje(SerialPort puertoEntrada){
        byte[] readBuffer = null; // Bytes para almacenar la informacion
        while (puertoEntrada.bytesAvailable() < 4) {
        }
            
        // Algo llego asi que lo almaceno en el buffer
        readBuffer = new byte[4];
        int numRead = puertoEntrada.readBytes(readBuffer, 4);
            
        //Comprobacion de que se envio
        System.out.print("Se encontro el mensaje:\n");
        for(int i=0; i<numRead;i++) System.out.println(" "+
                pasarByteAString(readBuffer[i]));
        
        String primerOcteto = pasarByteAString(readBuffer[2]);
        String segundoOcteto = pasarByteAString(readBuffer[3]);
        String destino = primerOcteto.substring(2,3);
        String origen = primerOcteto.substring(0,1);
        
        
    }*/
    
    
    
    /* public void RecibirTramaInicio(SerialPort puertoEntrada, SerialPort puertoSalida){
        byte[] readBuffer = null; // Bytes para almacenar la informacion
        while (puertoEntrada.bytesAvailable() < 4) {
        }
            
        // Algo llego asi que lo almaceno en el buffer
        readBuffer = new byte[4];
        int numRead = puertoEntrada.readBytes(readBuffer, 4);
            
        //Comprobacion de que se envio
        System.out.print("Se encontro el mensaje:\n");
        
        String primerOcteto = pasarByteAString(readBuffer[1]);
        String segundoOcteto = pasarByteAString(readBuffer[2]);
        System.out.print("Se encontro el mensaje:\n");
        System.out.print(primerOcteto + " ");
        System.out.print(segundoOcteto);

        int posicion;
        if (segundoOcteto.equals("10000000")){
            posicion = 2;
            segundoOcteto = "10000001";   
        }
        if (segundoOcteto.equals("10000001")){
            posicion = 3;
            segundoOcteto = "10000010";   
        }
        if (segundoOcteto.equals("10000010")){
            posicion = 4;
            segundoOcteto = "10000011";   
        }
        EnviarMensaje(puertoSalida, primerOcteto, segundoOcteto);     
    }*/
    
    /*public byte[] EnviarTrama(String flag){
        byte[] envio = new byte[4];
        this.puertoSalida.openPort();
        envio[0] = (byte)Short.parseShort(flag, 2);
        envio[1] = (byte)Short.parseShort("00000000", 2);
        envio[2] = (byte)Short.parseShort("10000001", 2);
        envio[3] = (byte)Short.parseShort(flag, 2);
        System.out.print("Mensaje enviado: "
                +" "+pasarByteAString(envio[0])
                +" "+pasarByteAString(envio[1])
                +" "+pasarByteAString(envio[2])
                +" "+pasarByteAString(envio[3])
                +"\n");
        puertoSalida.writeBytes(envio, envio.length);
        return envio;
    }*/
    
}
