/**
 * This class has static methods be
 * @author kshah
 *
 */
public class BoardSpace {
	
	/*
	 * The 0 column is for ownership. -1 if the property cannot be owned, 0 of unowned
	 * 		and the player number if owned by a player.
	 * The 1 column is the cost of the property.
	 * The 2 column is the rent of the property. If the property cannot be owned, then the 2 column
	 * 		represents the payout or charge of the space.
	 * The 3 column is the mortgage of the property.

	 */
	private static int properties[][] = {
		// 0    1     2    3 
        { -1,   0,  200,   0},    //Go                            0				1111
        {  0,  60,    2,  30},    //Mediterranean Avenue       	  1
        { -1,   0,   -1,   0},    //Community Chest               2
        {  0,  60,    4,  30},    //Baltic Avenue                 3
        { -1,   0, -200,   0},    //Income Tax                    4				1111
        {  0, 200,   50, 100},    //Reading Railroad              5
        {  0, 100,    6,  50},    //Oriental Avenue               6
        { -1,   0,   -1,   0},    //Chance                        7
        {  0, 100,    6,  50},    //Vermont Avenue                8
        {  0, 120,    8,  60},    //Connecticut Avenue            9
        { -1,   0,    0,   0},    //Jail                         10
        {  0, 140,   10,  70},    //St. Charles Place            11
        {  0, 150,    4,  75},    //Electric Company             12
        {  0, 140,   10,  70},    //States Avenue                13
        {  0, 160,   12,  80},    //Virginia Avenue              14
        {  0, 200,   50, 100},    //Pennsylvania Railroad        15
        {  0, 180,   14,  90},    //St. James Place              16
        { -1,   0,   -1,   0},    //Community Chest              17
        {  0, 180,   14,  90},    //Tennessee Avenue             18
        {  0, 200,   16, 100},    //New York Avenue              19
        { -1,   0,  500,   0},    //Free Parking                 20				1111
        {  0, 220,   18, 110},    //Kentucky Avenue              21
        { -1,   0,   -1,   0},    //Chance                       22
        {  0, 220,   18, 110},    //Indiana Avenue               23
        {  0, 240,   20, 120},    //Illinois Avenue              24
        {  0, 200,   50, 100},    //B & O RailRoad               25
        {  0, 260,   22, 130},    //Atlantic Avenue              26
        {  0, 260,   22, 130},    //Ventnor Avenue               27
        {  0, 150,    4,  75},    //Water Works                  28
        {  0, 280,   24, 140},    //Marvin Gardens               29
        { -1,   0,  -50,   0},    //Go to Jail                   30				1111
        {  0, 300,   26, 150},    //Pacific Avenue               31
        {  0, 300,   26, 150},    //North Carolina Avenue        32
        { -1,   0,   -1,   0},    //Community Chest              33
        {  0, 320,   28, 160},    //Pennsylvania Avenue          34
        {  0, 200,   50, 100},    //Short Line Railroad          35
        { -1,   0,   -1,   0},    //Chance                       36
        {  0, 350,   35, 175},    //Park Place                   37
        { -1,  0,   -75,   0},    //Luxury Tax                   38				1111
        {  0, 400,   50, 200}     //Boardwalk                    39
        };
	
	private static String names[] =  {
			"Go",
			"Mediterranean Avenue",
			"Community Chest",
			"Baltic Avenue",
			"Income Tax",
			"Reading Railroad",
			"Oriental Avenue",
			"Chance",
			"Vermont Avenue",
			"Connecticut Avenue",
			"Jail",
			"St. Charles Place",
			"Electric Company",
			"States Avenue",
			"Virginia Avenue",
			"Pennsylvania Railroad",
			"St. James Place",
			"Community Chest",
			"Tennessee Avenue",
			"New York Avenue",
			"Free Parking",
			"Kentucky Avenue",
			"Chance",
			"Indiana Avenue",
			"Illinois Avenue",
			"B & O RailRoad",
			"Atlantic Avenue",
			"Ventnor Avenue",
			"Water Works",
			"Marvin Gardens",
			"Go to Jail",
			"Pacific Avenue",
			"North Carolina Avenue",
			"Community Chest",
			"Pennsylvania Avenue",
			"Short Line Railroad",
			"Chance",
			"Park Place",
			"Luxury Tax",
			"Boardwalk"
		};
	
	//unimplemented: altered payment methods for railroad and utiliy, "Get out of jail free"

    
	public static int getSpaceAttribute(int space, int column) {
		return properties[space][column];
	}
	
	public static void setSpaceAttribute(int space, int column, int value) {
		properties[space][column] = value;
	}
	public static String getName(int space) {
		return names[space];
	}

    private static String[] chanceNames = {
        "Advance to Go (Collect $200)",
            "Advance to Illinois Ave",
            "Advance token to nearest Utility.",
            "Advance token to nearest Railroad.",
            "Advance to St. Charles Place",
            "Bank pays you dividend of $50.",
            "Go back three spaces.",
            "Go directly to Jail - do not pass Go, do not collect $200.",
            "Pay poor tax of $15.",
            "Take a trip on the Reading Railroad - if you pass Go, collect $200.",
            "Take a walk on the Boardwalk - advance token to Boardwalk.",
            "You have been elected chairman of the board - pay $50.",
            "Your building loan matures - collect $150.",
            "You have won a crossword competition - collect $100."
    };
    
	 /*
     * Column 1 is a boolean for if the chance card requires relocation
     * Column 2 is the space number for the space relocation, will be reevaluated afterwards
     * Column 3 is the payout of the chance card
     */
    private static int chanceCards[][] = {
        {1,  0, 200},
        {1,  24,  0},
        {1,  28,  0},
        {1,  11,  0},
        {0, -1,  50},
        {1,  0,   0},
        {1,  30,  0},
        {0, -1, -15},
        {1,  5,   0},
        {1,  39,  0},
        {0, -1, -50},
        {0, -1, 150},
        {0, -1, 100}
    };
	public static int getChanceCardAttribute(int cardToPick, int i) {
		// TODO Auto-generated method stub
		return chanceCards[cardToPick][i];
	}

	public static String getChanceCardName(int cardToPick) {
		// TODO Auto-generated method stub
		return chanceNames[cardToPick];
	}
    
   
}
