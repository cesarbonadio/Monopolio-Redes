import javax.swing.JOptionPane;
import com.fazecast.jSerialComm.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Tester {

	private static final int STARTING_MONEY = 1500;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<String> puertos = ConexionSerial.listaPuertos();
		int i=0;
		int current=0;
		byte[] recibo = new byte[4];
		for(String puerto: puertos){
			JOptionPane.showMessageDialog(null, i + " "+ puerto);
			i++;
		}
        int entrada = Integer.parseInt(JOptionPane.showInputDialog("Select input port"));
		//Scanner scanner = new Scanner(System.in);
		//int entrada = Integer.parseInt(scanner.nextLine());
		System.out.println("Seleccione Puerto de  Salida");
		//Scanner scanner2 = new Scanner(System.in);
		//int salida = Integer.parseInt(scanner2.nextLine());
        int salida = Integer.parseInt(JOptionPane.showInputDialog("Select output port"));
		ConexionSerial conexion = new ConexionSerial();
		conexion.setPuertos(entrada, salida);
		String player = JOptionPane.showInputDialog("Are you player one? Yes or no");
		if (player.equals("yes")){
			conexion.EnviarMensaje("00000000", "10000000");
			recibo = conexion.RecibirMensaje();
			System.out.print("Se encontro el mensaje:\n");
			System.out.print(ConexionSerial.pasarByteAString(recibo[1])+" ");
			System.out.print(ConexionSerial.pasarByteAString(recibo[2])+" ");
			conexion.EnviarMensaje("00000000", "100001"+ConexionSerial.pasarByteAString(recibo[2]).substring(6,8));
			current = 1;
		}else {
			/*while (true){
				recibo = conexion.RecibirMensaje();
				String origen;
				String destino;
				String mensaje;
				origen = conexion.pasarByteAString(recibo[1]).substring(0,2);
				destino = conexion.pasarByteAString(recibo[1]).substring(2,4);
				mensaje = conexion.pasarByteAString(recibo[1]).substring(4,8);
				System.out.print(origen + " ");
				System.out.print(destino + "\n");

			}*/
			recibo = conexion.RecibirMensaje();
			String primerOcteto = ConexionSerial.pasarByteAString(recibo[1]);
			String segundoOcteto = ConexionSerial.pasarByteAString(recibo[2]);
			System.out.print("Se encontro el mensaje:\n");
			System.out.print(primerOcteto + " ");
			System.out.print(segundoOcteto + "\n");

			if (segundoOcteto.equals("10000000")) {
				segundoOcteto = "10000001";
				current = 2;
			} else {
				if (segundoOcteto.equals("10000001")) {
					segundoOcteto = "10000010";
					current = 3;
				} else {
					segundoOcteto = "10000011";
					current = 4;
				}
			}
			System.out.print(current + " Es tu numero de jugador\n");
			conexion.EnviarMensaje(primerOcteto, segundoOcteto);
			recibo = conexion.RecibirMensaje();
		}



		String p1Name = JOptionPane.showInputDialog("Ingrese el nombre del primer jugador");
		Player p1 = new Player(STARTING_MONEY, p1Name);
		String p2Name = JOptionPane.showInputDialog("Ingrese el nombre del tercer jugador");
		Player p2 = new Player(STARTING_MONEY, p2Name);
		if (ConexionSerial.pasarByteAString(recibo[2]).substring(6,8).equals("11")){
			String p3Name = JOptionPane.showInputDialog("Ingrese el nombre del tercer jugador");
			Player p3 = new Player(STARTING_MONEY, p3Name);
			String p4Name = JOptionPane.showInputDialog("Ingrese el nombre del cuarto jugador");
			Player p4 = new Player(STARTING_MONEY, p4Name);
			MonopolyBoard gameBoard = new MonopolyBoard(p1Name, p2Name, p3Name, p4Name);
			Game myGame = new Game(gameBoard, p1, p2, current, conexion);
		}
		else if (ConexionSerial.pasarByteAString(recibo[2]).substring(6,8).equals("10")) {
				String p3Name = JOptionPane.showInputDialog("Ingrese el nombre del tercer jugador");
				Player p3 = new Player(STARTING_MONEY, p3Name);
				MonopolyBoard gameBoard = new MonopolyBoard(p1Name, p2Name, p3Name, null);
			Game myGame = new Game(gameBoard, p1, p2, current, conexion);
		}
		else{
				MonopolyBoard gameBoard = new MonopolyBoard(p1Name, p2Name, null, null);
				Game myGame = new Game(gameBoard, p1, p2, current, conexion);
			}




	}

}
