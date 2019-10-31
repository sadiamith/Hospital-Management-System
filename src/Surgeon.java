/**
 * A doctor with the Surgeon specialty.
 */
public class Surgeon extends Doctor
{
    /**
     * Initialize the surgeon with the specified name
     * @param name  the name for the new Surgeon object
     * @precond name != null && !name.equals("")
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

        // testing all the methods on one instance of the class
        Surgeon s = new Surgeon("Peter");
        String expected = "\nSurgeon\nName: Peter\nPatients: \n";
        if(!s.toString().equals(expected)) {
            System.out.println("toString failed: " + s.toString());
            numErrors++;
        }


        // testing all the methods on a second instance of the class
        s = new Surgeon("Billie Jean");
        expected = "\nSurgeon\nName: Billie Jean\nPatients: \n";
        if(!s.toString().equals(expected)) {
            System.out.println("toString failed: " + s.toString());
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
