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
		String player = JOptionPane.showInputDialog("Are you player one? Yes or no");
		if (player.equals("yes")){
			conexion.EnviarMensaje("00000000", "10000000");
			recibo = conexion.RecibirMensaje();
			System.out.print("Se encontro el mensaje:\n");
			System.out.print(ConexionSerial.pasarByteAString(recibo[1])+" ");
			System.out.print(ConexionSerial.pasarByteAString(recibo[2])+" ");
			conexion.EnviarMensaje("00000000", "10000111");
			current = 1;
		}else {
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
					if (segundoOcteto.equals("10000010")) {
						segundoOcteto = "10000011";
						current = 4;
					}
				}
			}
			conexion.EnviarMensaje(primerOcteto, segundoOcteto);
		}



		String p1Name = JOptionPane.showInputDialog("Please input a name for player 1");
		Player p1 = new Player(STARTING_MONEY, p1Name);
		String p2Name = JOptionPane.showInputDialog("Please input a name for player 2");
		Player p2 = new Player(STARTING_MONEY, p2Name);
		MonopolyBoard gameBoard = new MonopolyBoard(p1Name, p2Name);
		Game myGame = new Game(gameBoard, p1, p2, current, conexion);
	}

}
