import java.util.*;
import java.io.*;
import java.lang.*;

public class FungusQuest
{
	public static void main (String [] args) throws IOException, InterruptedException
	{
		// Initialize Global Variables
		int screenX = 80;
		int screenY = 29;
		boolean gameOver = false;
		boolean devMode = false;
		Scanner keyboard = new Scanner(System.in);

		while (!gameOver) {
			// Initialize Variables
			int invSlots = 10;
			int invAddCount = invSlots;
			List<Integer> inv = new ArrayList<Integer>();
			while (invAddCount>0) {
				inv.add(0);
				invAddCount = invAddCount-1;
			}
			// Ask for screen size
			boolean screenSizeFound = false;
			while (!screenSizeFound) {
				int iY = 0;
				System.out.println("Use WASD+enter to resize the screen and y to finish. This line should be visible.");
				while (iY < screenY-1) {
					int iX = 0;
					while (iX < screenX) {
						System.out.print("|");
						iX++;
					}
					System.out.println();
					iY++;
				}
				char screenSizeChange = keyboard.next().charAt(0);
				if (screenSizeChange == 'w' && screenY > 1) {
				  screenY = screenY-1;
				} else if (screenSizeChange == 'a' && screenX > 2) {
				  screenX = screenX-2;
				} else if (screenSizeChange == 's') {
				  screenY = screenY+1;
				} else if (screenSizeChange == 'd') {
				  screenX = screenX+2;
				} else if (screenSizeChange == ']') {
				  devMode = true;
				} else if (screenSizeChange == 'y') {
					screenSizeFound = true;
				}
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			}

			// Title Screen Calculations
			int itemW = 38;
			int itemH = 9;
			int screenRemX = screenX-itemW;
			int screenRemY = screenY-itemH;
			int xBordL = 0;
			int xBordR = 0;
			int yBordT = 0;
			int yBordB = 0;

			if (screenRemX%2 == 0) {
				xBordL = screenRemX/2;
				xBordR = screenRemX/2;
			} else {
				xBordL = screenRemX/2;
				xBordR = screenRemX/2+1;
			}
			if (screenRemY%2 == 0) {
				yBordT = screenRemY/2;
				yBordB = screenRemY/2;
			} else {
				yBordT = screenRemY/2;
				yBordB = screenRemY/2+1;
			}
			// Title Screen Display
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			int dispIY = 1;
			int dispIX = 1;
			while (dispIY <= screenY) {
				if (dispIY <= yBordT || dispIY >= yBordT+itemH) {
					while (dispIX < screenX) {
						System.out.print(" ");
						dispIX = dispIX+1;
					}
					System.out.println();
					dispIX = 0;
					dispIY++;
				} else {
					dispIX = 0;
					while (dispIX <= xBordL) {
						System.out.print(" ");
						dispIX++;
					}
					switch (dispIY-yBordT) {
						case 1:
							System.out.print(" ____  __  __  _  _  ___  __  __  ___ ");
							break;
						case 2:
							System.out.print("( ___)(  )(  )( \\( )/ __)(  )(  )/ __)");
							break;
						case 3:
							System.out.print(" )__)  )(__)(  )  (( (_-. )(__)( \\__ \\");
							break;
						case 4:
							System.out.print("(__)  (______)(_)\\_)\\___/(______)(___/");
							break;
						case 5:
							System.out.print("    _____  __  __  ____  ___  ____    ");
							break;
						case 6:
							System.out.print("   (  _  )(  )(  )( ___)/ __)(_  _)   ");
							break;
						case 7:
							System.out.print("    )(_)(  )(__)(  )__) \\__ \\  )(     ");
							break;
						case 8:
							System.out.print("   (___/\\\\(______)(____)(___/ (__)    ");
							break;
						case 9:
							System.out.print("");
							break;
					}
					System.out.println();
					dispIY++;
				}
			}

			String testForInput = keyboard.next();

			// Data Import Screen
			itemW = 19;
			itemH = 3;
			screenRemX = screenX-itemW;
			screenRemY = screenY-itemH;

			if (screenRemX%2 == 0) {
				xBordL = screenRemX/2;
				xBordR = screenRemX/2;
			} else {
				xBordL = screenRemX/2;
				xBordR = screenRemX/2+1;
			}
			if (screenRemY%2 == 0) {
				yBordT = screenRemY/2;
				yBordB = screenRemY/2;
			} else {
				yBordT = screenRemY/2;
				yBordB = screenRemY/2+1;
			}
			// Import Screen Display
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			dispIY = 1;
			dispIX = 1;
			while (dispIY <= screenY) {
				if (dispIY <= yBordT || dispIY > yBordT+itemH) {
					while (dispIX < screenX) {
						System.out.print(" ");
						dispIX = dispIX+1;
					}
					System.out.println();
					dispIX = 0;
					dispIY++;
				} else {
					dispIX = 0;
					while (dispIX <= xBordL) {
						System.out.print(" ");
						dispIX++;
					}
					if (dispIY-yBordT == 1) {
						System.out.print(" --------------");
					}
					if (dispIY-yBordT == 2) {
						System.out.print("Loading Files...");
					}
					if (dispIY-yBordT == 3) {
						System.out.print(" --------------");
					}
					System.out.println();
					dispIY++;
				}
			}

			// Get data
			FileReader fr = new FileReader("levelDat.txt");
			BufferedReader levelDat = new BufferedReader(fr);
			String s="";
            s=levelDat.readLine();
            System.out.println(s);

            //TextBox() initialize
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            String[] textIn;
            textIn = new String[3];
            textIn[0] = String.valueOf(screenX);
            textIn[1] = String.valueOf(screenY);
            	//Puts text of "hi" into textIn and runs TextBox
            textIn[2] = "hi";
            TextBox(textIn);

			// Ask if over
			System.out.println("Are you done?");
			gameOver = keyboard.nextBoolean();

			// Clear screen
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
	}
	public static void TextBox (String [] args)
	{
		int screenX = Integer.parseInt(args[0]);
		int screenY = Integer.parseInt(args[1]);
		String text = args[2];
		screenX *= 2;
		int tWidth = screenX-4;
		int lines = (text.length()/tWidth)+1;
	}
}

/*
 ____  __  __  _  _  ___  __  __  ___
( ___)(  )(  )( \( )/ __)(  )(  )/ __)
 )__)  )(__)(  )  (( (_-. )(__)( \__ \
(__)  (______)(_)\_)\___/(______)(___/
    _____  __  __  ____  ___  ____
   (  _  )(  )(  )( ___)/ __)(_  _)
    )(_)(  )(__)(  )__) \__ \  )(
   (___/\\(______)(____)(___/ (__)

*/