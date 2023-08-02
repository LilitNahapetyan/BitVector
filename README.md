## BitVector Class

The `BitVector` class represents a collection of bits, where each bit can be either 0 or 1. It provides methods to set and reset individual bits, view the current state of the BitVector, and save/load the BitVector to/from a file.

### Constructors

- `BitVector(int numBits)`: Creates a new BitVector with the specified number of bits.
  - `numBits`: The total number of bits in the BitVector.
  - Throws an `IllegalArgumentException` if the number of bits is not a positive integer.

### Methods

- `byte[] getBitVector()`: Gets the underlying byte array representing the BitVector.
  - Returns the byte array representing the BitVector.

- `int getSize()`: Gets the total number of bits in the BitVector.
  - Returns the total number of bits in the BitVector.

- `void set(int position)`: Sets a specific bit at the given position to 1.
  - `position`: The position of the bit to set.
  - Throws an `IllegalArgumentException` if the position is out of range.

- `void reset(int position)`: Resets a specific bit at the given position to 0.
  - `position`: The position of the bit to reset.
  - Throws an `IllegalArgumentException` if the position is out of range.

- `String toString()`: Returns a string representation of the BitVector, showing the binary value of each bit. The bits are grouped into bytes, separated by underscores for better readability.
  - Returns a string representation of the BitVector.

- `void saveToFile(String fileName)`: Saves the BitVector to a file with the given filename.
  - `fileName`: The name of the file to save the BitVector to.
  - Throws an `IOException` if there is an error while writing to the file.

- `String readFromFile(String fileName)`: Reads the contents of a file and returns them as a single string.
  - `fileName`: The name of the file to read.
  - Returns a string containing the contents of the file.
  - Throws an `IOException` if an I/O error occurs while reading the file.

### Main Class

The `Main` class contains the main method for interacting with the `BitVector` class. It provides a simple console-based interface to set, reset, view, and save the BitVector.

When running the program, the user is prompted to choose one of the following options:

- 0: Reset a bit
- 1: Set a bit
- 2: See the current result
- 3: Save the data and end

The user can set or reset individual bits, view the current state of the BitVector, and choose to save the data to a file.

### Usage

1. Create a `BitVector` object by specifying the total number of bits.
2. Use the provided menu options to interact with the `BitVector` object.
3. Save the data to a file using the "Save the data and end" option if desired.

Please note that the BitVector class allows for setting and resetting bits based on their positions. The positions are 0-based, meaning the first bit is at position 0, the second bit at position 1, and so on. The menu provides an intuitive way to interact with the `BitVector` object and perform operations on its bits. The program will keep running until the user chooses to end the program.
