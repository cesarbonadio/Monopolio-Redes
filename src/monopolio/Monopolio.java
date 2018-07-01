
package monopolio;
import com.fazecast.jSerialComm.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static monopolio.ConexionSerial.pasarByteAString;


public class Monopolio {


    public static void main(String[] args) {
               // TODO code application logic here
        ArrayList<String> puertos = ConexionSerial.listaPuertos();
        int i=0;
        byte[] recibo = new byte[4];
        System.out.println("Seleccione Puerto de  Entrada");
        for(String puerto: puertos){
            System.out.println(i + " "+ puerto);
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        int entrada = Integer.parseInt(scanner.nextLine());
        i=0;
        System.out.println("Seleccione Puerto de  Salida");
        Scanner scanner2 = new Scanner(System.in);
        int salida = Integer.parseInt(scanner2.nextLine());
        ConexionSerial conexion = new ConexionSerial();
        conexion.setPuertos(entrada, salida);
        System.out.println("1. Es el jugador uno");
        System.out.println("2. No es el jugador uno");
        Scanner scanner3 = new Scanner(System.in);
        int c = Integer.parseInt(scanner3.nextLine());
        if (c==1){
            conexion.EnviarMensaje("00000000", "10000000");
            recibo = conexion.RecibirMensaje();
            System.out.print("Se encontro el mensaje:\n");
            System.out.print(conexion.pasarByteAString(recibo[1])+" ");
            System.out.print(conexion.pasarByteAString(recibo[2])+" ");
        }
        if (c==2){
            recibo = conexion.RecibirMensaje();
            conexion.pasarByteAString(recibo[1]);
            conexion.pasarByteAString(recibo[2]);
            System.out.print("Se encontro el mensaje:\n");
            System.out.print(conexion.pasarByteAString(recibo[1])+" ");
            System.out.print(conexion.pasarByteAString(recibo[2])+" ");
        }
        
    }
    
}
