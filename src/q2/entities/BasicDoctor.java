package q2.entities;

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
     * Initialize an instance of entities.BasicDoctor with the given name.
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
     * Change the name of the doctor.
     *
     * @param newName the name of the doctor
     */
    public void setName(String newName) {
        name = newName;
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
     * A method to test the entities.BasicDoctor class.
     */
    public static void main(String[] args)
    {
        int numErrors = 0;

        // testing all the methods with one instance of the class
        BasicDoctor d = new BasicDoctor("Joe");
        if(! d.getName().equals("Joe")) {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        d.setName("Dr. Joe");
        if(! d.getName().equals("Dr. Joe")) {
            System.out.println("The constructor or setName failed");
            numErrors++;
        }
        String expected = "\nName: Dr. Joe\n";
        if(!d.toString().equals(expected)) {
            System.out.println("The constructor or toString failed");
            numErrors++;
        }

        // testing all the methods with a second instance of the class
        d = new BasicDoctor("Mary");
        if(! d.getName().equals("Mary")) {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        d.setName("Dr. Mary");
        if(! d.getName().equals("Dr. Mary")) {
            System.out.println("The constructor or setName failed");
            numErrors++;
        }
        expected = "\nName: Dr. Mary\n";
        if(!d.toString().equals(expected)) {
            System.out.println("The constructor or toString failed");
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
