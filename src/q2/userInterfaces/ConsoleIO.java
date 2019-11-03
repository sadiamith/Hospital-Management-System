package q2.userInterfaces;


import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * a class with the input and output method which can read a string, read an int, output a message
 * and display a list of options of operations from which the user must choose one of the oprations
 * that needs to be done
 */
public class ConsoleIO implements InputOutputInterface {

    private Scanner in = new Scanner(System.in);


    /**
     * Displays a prompt and reads the string entered
     * @param prompt the string that will get displayed on the screen
     * @return returns the input which is received from the console
     */

    public String readString(String prompt){
        System.out.println(prompt);
        return in.nextLine();
    }

    /**
     * Displays the prompt and reads the int from the user
     * @param prompt list of options
     * @return int from the user
     */

    public int readInt(String prompt){
        int result = 0;
        boolean successful;
        do {
            successful = true;
            try{
                System.out.println(prompt);
                result = in.nextInt();
            } catch (InputMismatchException e) {
                successful = false;
                String wrongInput = in.nextLine();
                System.out.println("you entered " + wrongInput + "which is not an int, please try again");
            }
        } while(!successful);
        in.nextLine(); //discarding remainder of the line
        return result;
    }

    /**
     * displays all the options and receives an int as the index of the options,
     * takes the first option as the default
     * @param options an array with all the options that is going to be displayed
     * @return the int that refers to the selected option from the user
     */

    public int readChoice(String[] options){
        String prompt = "\n Please select an option: ";
        for(int i = 0; i<options.length; i++){
            prompt = prompt + "\n" + i + ":" + options[i];
        }
        prompt = prompt + "\n Enter the number of your selection: ";
        int result = readInt(prompt);
        if(result < 0 || result >= options.length){
            outputString("you entered an option"+ result +"which is not between" + (options.length - 1)
            + "\nPlease try again.");
            return readChoice(options);
        }else
            return result;
    }

    /**
     * outputs the string parameter to the screen
     * @param outPutString the string that will get displayed
     */

    public void outputString(String outPutString){
        System.out.println(outPutString);
    }



















}
