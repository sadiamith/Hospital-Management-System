package entities;

import java.util.LinkedList;

/**
 * A ward of a hospital with a specified number of beds with consecutive labels.
 */
public class Ward
{
    /**
     * The name of this ward.
     */
    private String name;

    /**
     * The (external) label of the first bed of the ward.
     */
    private int minBedLabel;

    /**
     * An array to represent the beds of the ward.  Each bed is empty (null)
     * or else has a entities.Patient in it.
     */
    private Patient[] beds;

    /**
     * Initialize the ward with the name given, and with beds those labels are
     * the consecutive integers from minBedLabel to maxBedLabel.
     * @param wName    the name of the ward
     * @param wMinBedLabel the label of the first bed in the ward
     * @param wMaxBedLabel the label of the last bed in the ward
     * @precond wName != null && !wName.equals("") && wMinBedLabel >= 0 && wMaxBedLabel >= wMinBedLabel
     */
    public Ward(String wName, int wMinBedLabel, int wMaxBedLabel)
    {
        if (wName == null || wName.equals(""))
            throw new RuntimeException("The name of a ward cannot be null or empty.  "
                    + "It is " + wName);
        if (wMinBedLabel < 0 || wMaxBedLabel < wMinBedLabel)
            throw new RuntimeException("The bed labels " + wMinBedLabel + " and " + wMaxBedLabel
                    + " are invalid as they cannot be negative and must have at least one bed.");

        name = wName;
        minBedLabel = wMinBedLabel;
        beds = new Patient[wMaxBedLabel - wMinBedLabel + 1];
    }

    /**
     * Return the name of this ward.
     * @return  the name of this ward
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return the smallest label for a bed on the ward.
     * @return  the smallest Label for a bed on the ward
     */
    public int getMinBedLabel()
    {
        return minBedLabel;
    }

    /**
     * Return the largest label for a bed on the ward.
     * @return  the largest label for a bed on the ward
     */
    public int getMaxBedLabel()
    {
        return minBedLabel + beds.length - 1;
    }

    /**
     * Return the internal/array index of the bed corresponding to the external label.
     * @param bedLabel the label of a bed from the external/user perspective
     * @precond isValidLabel(bedLabel)
     * @return the internal/array index of the bed corresponding to the external label
     */
    private int externalToInternalIndex(int bedLabel)
    {
        if (! isValidLabel(bedLabel))
            throw new RuntimeException("The value " + bedLabel
                    + " is not a valid label for a bed in the ward.");

        return bedLabel - minBedLabel;
    }

    /**
     * Return the external/user label of the bed corresponding to the internal index.
     * @param arrayIndex the index of a location in the beds array
     * @precond 0 <= arrayIndex < beds.length
     * @return the external/user label of the bed corresponding to the internal index
     */
    private int internalToExternalLabel(int arrayIndex)
    {
        if (arrayIndex < 0 || arrayIndex >= beds.length)
            throw new RuntimeException("The value " + arrayIndex +
                    " is not a valid index for an array of length " + beds.length + ".");

        return arrayIndex + minBedLabel;
    }

    /**
     * Is the specified bed occupied?
     * @param bedLabel  the label of the bed to be tested for being occupied
     * @precond isValidLabel(bedLabel)
     * @return  is the specified bed occupied?
     */
    public boolean isOccupied(int bedLabel)
    {
        if (! isValidLabel(bedLabel))
            throw new RuntimeException("The value " + bedLabel
                    + " is not a valid label for a bed in the ward.");

        return beds[externalToInternalIndex(bedLabel)] != null;
    }

    /**
     * Return the patient in the specified bed.
     * @param bedLabel  the label of the bed that has the patient to be retrieved
     * @precond isValidLabel(bedLabel) && isOccupied(bedLabel)
     * @return  the patient in the specified bed
     */
    public Patient getPatient(int bedLabel)
    {
        if (! isValidLabel(bedLabel))
            throw new RuntimeException("The value " + bedLabel
                    + " is not a valid label for a bed in the ward.");

        if (! isOccupied(bedLabel))
            throw new RuntimeException("Bed " + bedLabel + " is not currently occupied"
                    + " so cannot get its patient");
        return beds[externalToInternalIndex(bedLabel)];
    }

    /**
     * Assign the specified patient to the specified bed.
     * @param p  the patient to be assigned a bed
     * @param bedLabel  the label of the bed that the patient is to be assigned
     * @precond  isValidLabel(bedLabel) && !isOccupied(bedLabel)
     */
    public void assignPatientToBed(Patient p, int bedLabel)
    {
        if (! isValidLabel(bedLabel))
            throw new RuntimeException("The value " + bedLabel
                    + " is not a valid label for a bed in the ward.");

        if (isOccupied(bedLabel))
            throw new RuntimeException("Bed " + bedLabel + " is currently occupied by "
                    + beds[externalToInternalIndex(bedLabel)]
                    + " so cannot be assigned to another patient");

        beds[externalToInternalIndex(bedLabel)] = p;
    }

    /**
     * Return a String representation of the properties of the ward.
     * @return a String representation of the properties of the ward
     */
    public String toString()
    {
        String result = "\nentities.Ward " + name + " with capacity " + beds.length
                + " has the following patients: ";
        for (int i = 0; i < beds.length; i++)
        {
            result = result + "\nbed " + internalToExternalLabel(i) + ": ";
            if (beds[i] != null)
                result = result + beds[i].getName();
        }
        return result + "\n";
    }

    /**
     * Return a list of the (bed labels of the) empty beds of the ward.
     * @return a list of the (bed labels of the) empty beds of the ward
     */
    public LinkedList<Integer> availableBeds()
    {
        LinkedList<Integer> bedList = new LinkedList<Integer>();
        for (int i = minBedLabel; i <= getMaxBedLabel(); i++)
            if (! isOccupied(i))
                bedList.addLast(i);
        return bedList;
    }

    /**
     * Remove the patient from a specific bed.
     * @param bedLabel  the label of the bed from which the patient is to be released
     * @precond isValidLabel(bedLabel) && isOccupied(bedLabel)
     */
    public void freeBed(int bedLabel)
    {
        if (! isValidLabel(bedLabel))
            throw new RuntimeException("The value " + bedLabel
                    + " is not a valid label for a bed in the ward.");

        if (! isOccupied(bedLabel))
            throw new RuntimeException("Bed " + bedLabel + " is not currently occupied"
                    + " so it cannot be freed");
        beds[externalToInternalIndex(bedLabel)] = null;
    }

    /**
     * Is bedLabel a valid external label for a bed?
     * @param bedLabel  an int to be tested to determined whether it is a valid label
     *             for a bed (from the external/user perspective)
     * @return  is bedLabel a valid external label for a bed?
     *
     * This is a helper method for testing pre-conditions students were not required to implement it
     */
    public boolean isValidLabel(int bedLabel)
    {
        return bedLabel >= minBedLabel && bedLabel <= minBedLabel + beds.length - 1;
    }


    /**
     * A method to test the class.
     * @param args  not used
     */
    public static void main(String[] args)
    {
        int numErrors = 0;

        // testing all the methods with one instance of a entities.Ward
        Ward w = new Ward("surgery", 200, 210);

        if(!w.getName().equals("surgery")) {
            System.out.println("The constructor or getName failed.");
            numErrors++;
        }

        if(w.getMinBedLabel() != 200) {
            System.out.println("The constructor or getMinBedLabel failed.");
            numErrors++;
        }

        if(w.getMaxBedLabel() != 210) {
            System.out.println("The constructor or getMaxBedLabel failed.");
            numErrors++;
        }


        if(!w.isValidLabel(200) || !w.isValidLabel(201)
                || !w.isValidLabel(210) || !w.isValidLabel(209)
                ||  w.isValidLabel(199)
                ||  w.isValidLabel(211)) {
            System.out.println("isValidLabel failed.");
            numErrors++;
        }

        if(w.internalToExternalLabel(w.externalToInternalIndex(200)) != 200
                || w.internalToExternalLabel(w.externalToInternalIndex(210)) != 210
                || w.internalToExternalLabel(w.externalToInternalIndex(205)) != 205) {
            System.out.println("internalToExternalLabel failed.");
            numErrors++;
        }

        if(w.externalToInternalIndex(w.internalToExternalLabel(0)) != 0
                || w.externalToInternalIndex(w.internalToExternalLabel(w.beds.length-1)) != w.beds.length-1
                || w.externalToInternalIndex(w.internalToExternalLabel(w.beds.length/2)) != w.beds.length/2) {
            System.out.println("externalToInternalIndex failed.");
            numErrors++;
        }

        if (w.externalToInternalIndex(200) != 0) {
            System.out.println("Minimum external label was not converted to 0.");
            numErrors++;
        }
        if (w.externalToInternalIndex(210) != w.beds.length-1) {
            System.out.println("Maximum external label was not converted "
                    + "to last location of the array.");
            numErrors++;
        }
        if (w.internalToExternalLabel(0) != 200) {
            System.out.println("Minimum internal index was not converted to first bed label.");
            numErrors++;
        }
        if (w.internalToExternalLabel(w.beds.length-1) != 210) {
            System.out.println("Maximum internal index was not converted "
                    + "to last bed label.");
            numErrors++;
        }

        if (w.isOccupied(205)) {
            System.out.println("Function isOccupied incorrectly returns that bed 205 is occupied.");
            numErrors++;
        }

        Patient p = new Patient("Pete", 123456);
        w.assignPatientToBed(p, 205);
        if (! w.isOccupied(205)) {
            System.out.println("assignPatientToBed() or isOccupied() failed: isOccupied incorrectly returns that bed 205 is not occupied.");
            numErrors++;
        }
        if (w.getPatient(205) != p) {
            System.out.println("getPatient() or isOccupied() failed: entities.Person Pete should be in bed 205, but " + w.getPatient(205) + " is.");
            numErrors++;
        }
        if (! w.getPatient(205).getName().equals("Pete")) {
            System.out.println("getPatient() or isOccupied() failed: Pete should be in bed 205, but " + w.getPatient(205) + " is.");
            numErrors++;
        }

        LinkedList<Integer> expectedAvaiableBeds = new LinkedList<Integer>();
        expectedAvaiableBeds.add(200);
        expectedAvaiableBeds.add(201);
        expectedAvaiableBeds.add(202);
        expectedAvaiableBeds.add(203);
        expectedAvaiableBeds.add(204);
        expectedAvaiableBeds.add(206);
        expectedAvaiableBeds.add(207);
        expectedAvaiableBeds.add(208);
        expectedAvaiableBeds.add(209);
        expectedAvaiableBeds.add(210);
        if(!w.availableBeds().equals(expectedAvaiableBeds)) {
            System.out.println("avaiableBeds failed: method returned: " + w.availableBeds().toString() + " when " + expectedAvaiableBeds.toString() + " was expected");
            numErrors++;
        }

        String expected = "\nentities.Ward surgery with capacity 11 has the following patients: \n" +
                "bed 200: \n" +
                "bed 201: \n" +
                "bed 202: \n" +
                "bed 203: \n" +
                "bed 204: \n" +
                "bed 205: Pete\n" +
                "bed 206: \n" +
                "bed 207: \n" +
                "bed 208: \n" +
                "bed 209: \n" +
                "bed 210: \n";
        if(!w.toString().equals(expected)) {
            System.out.println("Error in toString() method. Expected: " + expected + "But returned: " + w);
            numErrors++;
        }

        w.freeBed(205);
        if(w.isOccupied(205)) {
            System.out.println("Error in freeBed() method. Bed 205 should be empty but isn'");
            numErrors++;
        }

        // retest all the methods on a second instance of the class
        w = new Ward("ER", 1, 3);

        if(!w.getName().equals("ER")) {
            System.out.println("The constructor or getName failed.");
            numErrors++;
        }

        if(w.getMinBedLabel() != 1) {
            System.out.println("The constructor or getMinBedLabel failed.");
            numErrors++;
        }

        if(w.getMaxBedLabel() != 3) {
            System.out.println("The constructor or getMaxBedLabel failed.");
            numErrors++;
        }


        if(!w.isValidLabel(1) || !w.isValidLabel(2)
                || !w.isValidLabel(3)
                ||  w.isValidLabel(0)
                ||  w.isValidLabel(4)) {
            System.out.println("isValidLabel failed.");
            numErrors++;
        }

        if(w.internalToExternalLabel(w.externalToInternalIndex(1)) != 1
                || w.internalToExternalLabel(w.externalToInternalIndex(2)) != 2
                || w.internalToExternalLabel(w.externalToInternalIndex(3)) != 3) {
            System.out.println("internalToExternalLabel failed.");
            numErrors++;
        }

        if(w.externalToInternalIndex(w.internalToExternalLabel(0)) != 0
                || w.externalToInternalIndex(w.internalToExternalLabel(w.beds.length-1)) != w.beds.length-1
                || w.externalToInternalIndex(w.internalToExternalLabel(w.beds.length/2)) != w.beds.length/2) {
            System.out.println("externalToInternalIndex failed.");
            numErrors++;
        }

        if (w.externalToInternalIndex(1) != 0) {
            System.out.println("Minimum external label was not converted to 0.");
            numErrors++;
        }
        if (w.externalToInternalIndex(3) != w.beds.length-1) {
            System.out.println("Maximum external label was not converted "
                    + "to last location of the array.");
            numErrors++;
        }
        if (w.internalToExternalLabel(0) != 1) {
            System.out.println("Minimum internal index was not converted to first bed label.");
            numErrors++;
        }
        if (w.internalToExternalLabel(w.beds.length-1) != 3) {
            System.out.println("Maximum internal index was not converted "
                    + "to last bed label.");
            numErrors++;
        }

        if (w.isOccupied(2)) {
            System.out.println("Function isOccupied incorrectly returns that bed 2 is occupied.");
            numErrors++;
        }

        p = new Patient("Dan", 789456);
        w.assignPatientToBed(p, 1);
        if (! w.isOccupied(1)) {
            System.out.println("assignPatientToBed() or isOccupied() failed: isOccupied incorrectly returns that bed 1 is not occupied.");
            numErrors++;
        }
        if (w.getPatient(1) != p) {
            System.out.println("getPatient() or isOccupied() failed: entities.Person Dan should be in bed 1, but " + w.getPatient(1) + " is.");
            numErrors++;
        }

        expectedAvaiableBeds = new LinkedList<Integer>();
        expectedAvaiableBeds.add(2);
        expectedAvaiableBeds.add(3);
        if(!w.availableBeds().equals(expectedAvaiableBeds)) {
            System.out.println("avaiableBeds failed: method returned: " + w.availableBeds().toString() + " when " + expectedAvaiableBeds.toString() + " was expected");
            numErrors++;
        }

        expected = "\nentities.Ward ER with capacity 3 has the following patients: \n" +
                "bed 1: Dan\n" +
                "bed 2: \n" +
                "bed 3: \n";
        if(!w.toString().equals(expected)) {
            System.out.println("Error in toString() method. Expected: " + expected + "But returned: " + w);
            numErrors++;
        }

        w.freeBed(1);
        if(w.isOccupied(1)) {
            System.out.println("Error in freeBed() method. Bed 1 should be empty but isn'");
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
