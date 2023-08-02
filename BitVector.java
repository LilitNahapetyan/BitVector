import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class BitVector {

  private int numBits; // Total number of bits in the BitVector
  private byte[] bitArray; // Array used to store the bits
  private static final byte BIT_SIZE = 8; // Number of bits in each array element


  /**
   * Creates a new BitVector with the specified number of bits.
   *
   * @param numBits The total number of bits in the BitVector.
   * @throws IllegalArgumentException if the number of bits is not a positive integer.
   */
  public BitVector(int numBits) {
    if (numBits <= 0) {
      throw new IllegalArgumentException(
        "Number of bits must be a positive integer."
      );
    }
    this.numBits = numBits;
    bitArray = new byte[(numBits + BIT_SIZE - 1) / BIT_SIZE]; // Creating the bitArray based on the number of bits
  }


   /**
   * Gets the underlying byte array representing the BitVector.
   *
   * @return The byte array representing the BitVector.
   */
  public byte[] getBitVector() {
    return bitArray;
  }

  /**
   * Gets the total number of bits in the BitVector.
   *
   * @return The total number of bits in the BitVector.
   */
  public int getSize() {
    return numBits;
  }

  /**
   * Set a specific bit at the given position to 1.
   *
   * @param position The position of the bit to set.
   * @throws IllegalArgumentException if the position is out of range.
   */
  public void set(int position) {
    checkIndex(position);
    int wordIndex = position / BIT_SIZE; // Index of the word containing the target bit
    int bitIndex = position % BIT_SIZE; // Bit index within the word

    int mask = 1 << bitIndex; // Create a mask with only the target bit set to 1
    bitArray[wordIndex] |= mask; // Perform bitwise OR to set the target bit to 1
  }

  /**
   * Reset a specific bit at the given position to 0.
   *
   * @param position The position of the bit to reset.
   * @throws IllegalArgumentException if the position is out of range.
   */

  public void reset(int position) {
    checkIndex(position);

    int wordIndex = position / BIT_SIZE; // Index of the word containing the target bit
    int bitIndex = position % BIT_SIZE; // Bit index within the word

    int mask = ~(1 << bitIndex); // Create a mask with only the target bit set to 0
    bitArray[wordIndex] &= mask; // Perform bitwise AND to reset the target bit to 0
  }

   /**
   * Returns a string representation of the BitVector, showing the binary value of each bit.
   * The bits are grouped into bytes, separated by underscores for better readability.
   *
   * @return A string representation of the BitVector.
   */
  @Override
  public String toString() {
    StringBuilder binaryStringBuilder = new StringBuilder();

    for (int wordIndex = bitArray.length - 1; wordIndex >= 0; wordIndex--) {
      for (int bitIndex = BIT_SIZE - 1; bitIndex >= 0; bitIndex--) {
        int bit = (bitArray[wordIndex] >> bitIndex) & 1;
        binaryStringBuilder.append(bit);
      }
      if (wordIndex != 0) {
        binaryStringBuilder.append("_");
      }
    }

    return binaryStringBuilder.toString();
  }


   /**
   * Checks whether the given position is within the valid range of the BitVector.
   *
   * @param position The position of the bit to check.
   * @throws IllegalArgumentException if the position is out of range.
   */
  private void checkIndex(int position) {
    if (position < 0 || position >= numBits) {
      throw new IllegalArgumentException(
        "Invalid input position. Position should be within 0 to " +
        (numBits - 1)
      );
    }
  }

  /**
   * Saves the BitVector to a file with the given filename.
   *
   * @param fileName The name of the file to save the BitVector to.
   * @throws IOException if there is an error while writing to the file.
   */
  public void saveToFile(String fileName) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      writer.write(this.toString());
    } catch (IOException e) {
      throw new IOException("Error saving data to the file: " + e.getMessage());
    }
  }

  /**
   * Reads the contents of a file and returns them as a single string.
   *
   * @param fileName The name of the file to read.
   * @return A string containing the contents of the file.
   * @throws IOException If an I/O error occurs while reading the file.
   */
  public String readFromFile(String fileName) throws IOException {
    StringBuilder result = new StringBuilder();
    String currentLine;
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      while ((currentLine = reader.readLine()) != null) {
        result.append(currentLine).append("\n");
      }
    } catch (IOException e) {
      throw new IOException("Error reading the file: " + e.getMessage());
    }
    return result.toString();
  }
}
