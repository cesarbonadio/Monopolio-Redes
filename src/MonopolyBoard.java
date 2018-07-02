import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * This class will create the Monopoly Board. It is the essential GUI
 * 
 * @author Vanshil Shah
 * 
 */
@SuppressWarnings("serial")
public class MonopolyBoard extends JFrame{
	
	private BoardPanel boardPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JSplitPane split;
	private JLayeredPane lp;	
	private JLabel topInfo;
	private JButton buyButton;
	private JButton diceButton;
	private JButton endButton;
	private JButton myPropertiesButton;
	private JTextArea outputArea;
	private JScrollPane outputPane;
	private PlayerToken token1;
	private PlayerToken token2;
	private DiceIcon dice1;
	private DiceIcon dice2;
	private JLabel p1Token;
	private JLabel p2Token;
	private JLabel die1;
	private JLabel die2;
	private String[] dice;
	private String p1Name, p2Name;
	private ArrayList<OwnerToken> redTokens;
	private ArrayList<JLabel> redLabels;
	private ArrayList<OwnerToken> blueTokens;
	private ArrayList<JLabel> blueLabels;
	
	
	public MonopolyBoard(String p1Name, String p2Name){
		
		this.p1Name = p1Name;
		this.p2Name = p2Name;
		
		//Instantiates the components
		instantiate();
		//Initializes the tokens
		initTokens();
		
		//Generate and add stuff to the panels
		topPanel.add(topInfo);
		bottomPanel.add(buyButton);
		bottomPanel.add(myPropertiesButton);
		bottomPanel.add(diceButton);
		bottomPanel.add(endButton);
		
		
		//Add all of the components to the frame
		setSize(1200, 850);
		setTitle("Monopoly");
		setLayout(new BorderLayout());
		add(topPanel, BorderLayout.NORTH);
		add(split, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);	
		//Sets a minimum size for the the board, which 
		//is the size of the image of the board.
		Dimension d = new Dimension(756,756);
		boardPanel.setMinimumSize(d);
		//Sets a minimum size for the output area,
		//based on the board.
		Dimension e = new Dimension(214, 800);
		outputPane.setMinimumSize(e);
		
		//This creates the strings for each of the dice.
		createDice();
		
		//Back end stuff for the frame.
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buyButton.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e)
            {
                //ActionListener for buy
            	Game.buy();
            }
        });      
		
		myPropertiesButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				Game.myProperties();
			}	
		});
		
		diceButton.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e)
            {
                //ActionListener for buy
            	Game.dice();
            }
        }); 
		
		endButton.addActionListener(new ActionListener() 
		{
            public void actionPerformed(ActionEvent e)
            {
                //ActionListener for buy
            	Game.endTurn();
            }
        }); 
	}

	/**
	 * Initializes the player tokens with their specific locations.
	 * Also initializes the dice at their correct positions.
	 */
	private void initTokens() {
		token1 = new PlayerToken("DogToken.gif", 700,740,1);
		token2 = new PlayerToken("ThimbleToken.gif", 700, 700,2);
		redTokens = new ArrayList<OwnerToken>();
		redLabels = new ArrayList<JLabel>();
		blueTokens = new ArrayList<OwnerToken>();
		blueLabels = new ArrayList<JLabel>();
		dice1 = new DiceIcon("d0.gif", 500, 150);
		dice2 = new DiceIcon("d0.gif", 200, 450);
		p1Token = new JLabel(token1);
		p2Token = new JLabel(token2);
		die1 = new JLabel(dice1);
		die2 = new JLabel(dice2);
		lp.add(p1Token);
		lp.add(p2Token);
		lp.add(die1);
		lp.add(die2);
		p1Token.setBounds(0, 0, 800, 800);
		p2Token.setBounds(0, 0, 800, 800);
		die1.setBounds(0, 0, 800, 800);
		die2.setBounds(0, 0, 800, 800);
	}


	/**
	 *Instantiates all of the buttons and shit. 
	 */
	private void instantiate() {
		boardPanel = new BoardPanel("MonopolyBoard.gif");
		topPanel = new JPanel(new FlowLayout());
		bottomPanel = new JPanel(new FlowLayout());
		lp = getLayeredPane();
		topInfo = new JLabel("Game Information      " + p1Name + ": 1500" + "      " + p2Name + ": 1500");
		buyButton = new JButton("Buy");
		myPropertiesButton = new JButton("My Properties");
		diceButton = new JButton("Throw Dice");
		endButton = new JButton("End Turn");
		outputArea = new JTextArea("Welcome to Monopoly\n");
		outputArea.setEditable(false);
		outputArea.setLineWrap(true);
		outputPane = new JScrollPane(outputArea);
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, boardPanel, outputPane);
	}
	
	/**
	 * This method uses the names of each of the dice files to create
	 * an array for the dice names.
	 */
	private void createDice() {
		dice = new String[7];
		dice[0] = "d0.gif";
		dice[1] = "d1.gif";
		dice[2] = "d2.gif";
		dice[3] = "d3.gif";
		dice[4] = "d4.gif";
		dice[5] = "d5.gif";
		dice[6] = "d6.gif";
	}

	public void changeDice(int d1, int d2) {
		dice1.changeDice(dice[d1]);
		dice2.changeDice(dice[d2]);
		repaint();
	}

	public void moveToken(int space, int player) {
		if(player == 1) {
			token1.translate(space);
		}
		else
			token2.translate(space);
		repaint();
	}
	
	public void changeTextArea(String text) {
		outputArea.append(text);
		outputArea.setCaretPosition(outputArea.getDocument().getLength());
	}

	public void updateInformation(int p1Money, int p2Money) {
		topInfo.setText("Game Information      " + p1Name + ": " + p1Money + "      " + p2Name + ": " + p2Money);
	}

	public void placeOwnerToken(int space, int player) {
		if(player == 1) {
			OwnerToken tempRed = new OwnerToken("red.gif",space);
			redTokens.add(tempRed);
			JLabel tempRedLabel = new JLabel(tempRed);
			redLabels.add(tempRedLabel);
			lp.add(tempRedLabel);
			tempRedLabel.setBounds(0, 0, 800, 800);
		}
		else {
			OwnerToken tempBlue = new OwnerToken("blue.gif",space);
			blueTokens.add(tempBlue);
			JLabel tempBlueLabel = new JLabel(tempBlue);
			blueLabels.add(tempBlueLabel);
			lp.add(tempBlueLabel);
			tempBlueLabel.setBounds(0, 0, 800, 800);
		}
	}

}

@SuppressWarnings("serial")
class BoardPanel extends JPanel{
	private Image image;
	public BoardPanel(String img) {
		image = new ImageIcon(img).getImage();
	}
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}