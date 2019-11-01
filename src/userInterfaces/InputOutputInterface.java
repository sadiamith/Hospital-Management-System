package userInterfaces;

/**
 * An interface for input and output methods to read a String, read an int, output a message, and
 * display a list of choices from which the user must select the index of a choice.
 */
public interface InputOutputInterface {
    /**
     * Display a prompt and read the string entered.
     * 
     * @param prompt the string to be displayed as a prompt
     * @return the String read
     */
    String readString(String prompt);

    /**
     * Display a prompt and read the int entered.
     * 
     * @param prompt the string to be displayed as a prompt
     * @return the int read
     */
    int readInt(String prompt);

    /**
     * Display the list of options and read an int that is the index of one of the options. The
     * first option is the default.
     * 
     * @param options an array with the options that are presented to the user
     * @return the int specifying the array index for the option selected by the user
     */
    int readChoice(String[] options);

    /**
     * Output the String parameter.
     * 
     * @param outString the string whose value is to be displayed
     */
    void outputString(String outString);
}
