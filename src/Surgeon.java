//Sadi Mohammad Mustafa
//sam774
//11257334


/**
 * A doctor with the Surgeon specialty.
 */
public class Surgeon extends Doctor
{
    /**
     * Initialize the surgeon with the specified name
     * @param name  the name for the new Surgeon object
     */
    public Surgeon(String name)
    {
        super(name);

        if (name == null || name.equals(""))
            throw new RuntimeException("Invalid constructor argument");
    }

    /**
     * Return a string representation of the Surgeon.
     * @return a string representation of the Surgeon
     */
    public String toString()
    {
        return "\nSurgeon" + super.toString();
    }

    /**
     * Carry out basic tests of the class.
     */
    public static void main(String[ ] args)
    {
        int numErrors = 0;

        // testing all the methods on the instance of the class
        Surgeon s = new Surgeon("Bahar");
        String expected = "\nSurgeon\nName: Bahar\nPatients: \n";
        if(!s.toString().equals(expected)) {
            System.out.println("toString failed: " + s.toString());
            numErrors++;
        }
        System.out.println("The number of errors found is " + numErrors);
    }
}
