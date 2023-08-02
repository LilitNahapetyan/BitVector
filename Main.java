import java.io.IOException;
import java.util.Scanner;

public class Main {

  private static final int SET_BIT = 1;
  private static final int RESET_BIT = 0;
  private static final int SHOW_DATA = 2;
  private static final int SAVE_DATA = 3;

  public static int getPositionFromUser(Scanner sc, int sizeVector) {
    int position = -1;

    System.out.print( "\nPlease enter the bit POSITION (starting from 0 to " + ( sizeVector - 1 ) + ") - " );
    while (position < 0 || position >= sizeVector) {
      try {
        position = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {}
      System.out.println(
        "Enter a valid position: (0 to " + (sizeVector - 1) + ")"
      );
    }

    return position;
  }

  public static int getValidSizeFromUser(Scanner sc) {
    int size = 0;
    System.out.print("Please input the array size: ");
    while (size <= 0) {
      try {
        size = Integer.parseInt(sc.nextLine());
        if (size <= 0) {
          System.out.println("Invalid input! Please enter a positive size.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input! Please enter a valid integer.");
      }
    }
    return size;
  }

  public static int getCommand(Scanner sc) {
    int command;
    while (true) {
      System.out.println("\nWhat do you want to do?");
      System.out.println("0. Reset a bit");
      System.out.println("1. Set a bit");
      System.out.println("2. See the current result");
      System.out.println("3. Save the data and end");

      try {
        command = Integer.parseInt(sc.nextLine());
      } catch (NumberFormatException e) {
        command = -1;
      }
      if (command >= 0 && command < 4) {
        return command;
      }
      System.out.println("Enter a valid command!");
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    BitVector bitVector = new BitVector(getValidSizeFromUser(sc));

    while (true) {
      int userInput = getCommand(sc);

      switch (userInput) {
        case SET_BIT:
          int setBit = getPositionFromUser(sc, bitVector.getSize());
          bitVector.set(setBit);
          System.out.println("Bit set successfully!");
          break;
        case RESET_BIT:
          int resetBit = getPositionFromUser(sc, bitVector.getSize());
          bitVector.reset(resetBit);
          System.out.println("Bit reset successfully!");
          break;
        case SHOW_DATA:
          System.out.println(
            "Current BitVector state: " + bitVector.toString()
          );
          break;
        case SAVE_DATA:
          System.out.println("Do you want to save your data? (Y/N)");
          String saveChoice = sc.next().trim().toLowerCase();
          if (saveChoice.equals("y")) {
            System.out.print("Please enter the file name to save: ");
            sc.nextLine();
            String fileName = sc.nextLine().trim(); // Use nextLine() to get the filename with spaces
            try {
              bitVector.saveToFile(fileName);
              System.out.println(
                "Data saved successfully to file: " + fileName
              );
            } catch (IOException e) {
              System.out.println("Something went wrong. Data was not saved!");
            }
          }
          System.out.println("Ending the program.");
          sc.close();
          return;
      }
    }
  }
}
