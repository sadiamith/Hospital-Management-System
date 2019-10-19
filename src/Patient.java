//Sadi Mohammad Mustafa
//sam774
//11257334

import java.util.LinkedList;

/**
 * This class is to model a patient in a hospital.  The class extends class Person
 * and has a bed in the ward with -1 value for no bed, and a list of doctors.
 */
public class Patient extends Person
{
    /**
     * The integer label of the bed occupied by the patient.
     * A value of -1 indicates no bed has been occupied.
     */
    private int bedLabel;

    /**
     * The list of the doctors of the patient.
     */
    private LinkedList<Doctor> doctors;

    /**
     * Initialize an instance with the given name and health number.
     * @param name		the name for the person
     * @param number	the health number for the person
     */
    public Patient(String name, int number)
    {
        super(name, number);
        bedLabel = -1;
        doctors = new LinkedList<Doctor>();

        if (name == null || name.equals("") && number > 0)
            throw new RuntimeException("Invalid constructor arguments");
    }

    /**
     * Return the integer label of the bed occupied by the patient.
     * @return  the integer label of the bed occupied by the patient
     */
    public int getBedLabel()
    {
        return bedLabel;
    }

    /**
     * Assign the patient to the bed whose label is passed in as a parameter.
     * @param bedLabel	the integer label of the bed for the patient
     */
    public void setBedLabel(int bedLabel)
    {
        this.bedLabel = bedLabel;
    }


    /**
     * Add another doctor to the list of doctors of this patient.
     * @param d	the new doctor to be added for this patient
     */
    public void addDoctor(Doctor d)
    {
        if (hasDoctor(d.getName()))
            throw new RuntimeException(d.getName()
                    + " is already a doctor for this patient");
        doctors.add(d);
    }

    /** Remove the doctor specified by the name parameter
     * from the doctors for this patient.
     * @param name	the name of the doctor to be removed from the doctors list
     */
    public void removeDoctor(String name)
    {
        if (!this.hasDoctor(name))
            throw new RuntimeException(name
                    + " is not a doctor for this patient");

        doctors.removeIf(d -> d.getName().equals(name));
    }

    /**
     * Does this patient have a doctor with the name specified by the parameter?
     * @param name	the name of the doctor to be tested for handling this patient
     * @return does this patient have a doctor with the name specified by the parameter?
     */
    public boolean hasDoctor(String name)
    {
        for (Doctor d : doctors) {
            if (d.getName().equals(name))
                return true;
        }
        return false;
    }

    /**
     * Return a string representation of the patient
     * @return a string representation of the patient
     */
    public String toString()
    {
        String result = super.toString();
        if (bedLabel != -1)
            result = result + "Bed: " + bedLabel + "\nDoctors: ";
        else
            result = result + "with doctors ";
        for (Doctor d: doctors)
            result = result + " " + d.getName() + ",";
        return result + "\n";
    }

    public static void main(String[ ] args)
    {
        int numErrors = 0;

        // testing all the methods on the instance of the class
        Patient p = new Patient("Bahar", 123456);
        if (p.getBedLabel() != -1)
        {
            System.out.println("constructor or getBedLabel failed: The bed label is " + p.getBedLabel()
                    + " when it should be -1");
            numErrors++;
        }
        if (p.doctors.size() != 0)
        {
            System.out.println("constructor failed: The patient should have no doctors, "
                    + "but already has " + p.doctors);
            numErrors++;
        }

        p.setBedLabel(205);
        if (p.getBedLabel() != 205)
        {
            System.out.println("getBedlabel or setBedLabel failed: The bed label is " + p.getBedLabel()
                    + " when it should be 205");
            numErrors++;
        }

        Doctor d = new Doctor("Joe");
        p.addDoctor(d);
        if (!p.hasDoctor("Joe"))
        {
            System.out.println("Either addDoctor or hasDoctor failed, "
                    + "as Pete does not have doctor Joe.");
            numErrors++;
        }
        d = new Doctor("Mary");
        p.addDoctor(d);
        if (!p.hasDoctor("Mary"))
        {
            System.out.println("Either addDoctor or hasDoctor failed, "
                    + "as Bahar does not have doctor Mary.");
            numErrors++;
        }
        if (p.doctors.size() != 2)
        {
            System.out.println("The patient should have two docotrs, "
                    + "but he has the patients " + p.doctors);
            numErrors++;
        }

        p.removeDoctor("Mary");
        if (p.hasDoctor("Mary"))
        {
            System.out.println("Either removeDoctor or hasDoctor failed, "
                    + "as Bahar still has doctor Mary.");
            numErrors++;
        }

        String expected = "\nName: Bahar\n" +
                "Health number: 123456\n" +
                "Bed: 205\n" +
                "Doctors:  Joe,\n";
        if(!p.toString().equals(expected)) {
            System.out.println("toString failed: " + p.toString());
            numErrors++;
        }
        System.out.println("The number of errors found is " + numErrors);
    }
}
