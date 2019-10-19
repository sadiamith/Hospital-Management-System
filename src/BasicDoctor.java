/**
 * A very simple model of a doctor who has a name.
 */
public class BasicDoctor
{
    /**
     * The name of the doctor.
     */
    private String name;

    /**
     * Initialize an instance of BasicDoctor with the given name.
     *
     * @param name the name of the doctor
     */
    public BasicDoctor(String name)
    {
        this.name = name;
    }

    /**
     * Return  the name of the doctor
     *
     * @return the name of the doctor
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return a string representation of the doctor
     *
     * @return a string representation of the doctor
     */
    public String toString()
    {
        return "\nName: " + name + "\n";
    }

    /**
     * A method to test the BasicDoctor class.
     */
    public static void main(String[] args)
    {
        int numErrors = 0;
        BasicDoctor d = new BasicDoctor("Joe");
        System.out.println("BasicDoctor Joe is " + d + "\n");
        if (! d.getName().equals("Joe"))
        {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }

        d = new BasicDoctor("Mary");
        System.out.println("BasicDoctor Mary is " + d + "\n");
        if (! d.getName().equals("Mary"))
        {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}