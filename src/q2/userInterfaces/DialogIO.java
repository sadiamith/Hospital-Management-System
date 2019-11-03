package q2.userInterfaces;

import javax.swing.*;

public class DialogIO extends AbstractDialogIO {

    /**
     * reads in a string from a prompt
     * @param prompt the string that will be prompted to the user
     * @return the string returned from the user
     */
    public String readString(String prompt){
        return JOptionPane.showInputDialog(null, prompt);
    }

    /**
     * reads in a int from a prompt
     * @param prompt the string that will be prompted to the user
     * @return the int returned from the user
     */

    public int readInt(String prompt){
        String valueAsString = JOptionPane.showInputDialog(null, prompt);
        int value;
        if(valueAsString != null && valueAsString.length() > 0){
            try{
                value = Integer.parseInt(valueAsString);
            }catch (NumberFormatException e){
                outputString("You entered " + valueAsString + "Which is not a String."
                + "\nPlease Try Again");
                value = readInt(prompt);
            }
        }else{
            outputString("You must enter a value in the input field");
            value = readInt(prompt);
        }
        return value;
    }

    /**
     * displays the String in a dialog box.
     *
     * @param outString the string whose value is to be displayed
     */
    public void outputString(String outString) {
        JOptionPane.showMessageDialog(null, outString);
    }


}
