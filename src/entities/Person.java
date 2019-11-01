package entities;

/**
 * The model of a person who has a name and a health number
 * that cannot be changed.
 */
public class Person
{
    /**
     * The name of the person.
     */
    private String name;

    /**
     * The health number of the person.
     */
    private int healthNum;

    /**
     * Initialize an instance with the given name and health number.
     *
     * @param pName the person's name
     * @param pNumber the person's health number
     */
    public Person(String pName, int pNumber)
    {
        name = pName;
        healthNum = pNumber;
    }

    /**
     * Return the name of the person.
     *
     * @return the name of the person
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return the health number of the person.
     *
     * @return the health number of the person
     */
    public int getHealthNumber()
    {
        return healthNum;
    }

    /**
     * Change the name of the person.
     *
     * @param newName the name of the person
     */
    public void setName(String newName)
    {
        name = newName;
    }

    /**
     * Return a string representation of the person.
     *
     * @return a string representation of the person
     */
    public String toString()
    {
        return "\nName: " + name + "\nHealth number: " + healthNum + "\n";
    }

    /**
     * A method to test the entities.Person class.
     */
    public static void main(String[] args)
    {
        int numErrors = 0;

        // testing all the methods with one instance of the class
        Person p = new Person("Pete", 123456);

        if(! p.getName().equals("Pete")) {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        if(p.getHealthNumber() != 123456) {
            System.out.println("The constructor or getHealthNumber failed");
            numErrors++;
        }
        p.setName("Jim");
        if(! p.getName().equals("Jim")) {
            System.out.println("SetName failed");
            numErrors++;
        }
        String expected = "\nName: Jim\nHealth number: 123456\n";
        if(!p.toString().equals(expected)) {
            System.out.println("toString failed: " + p.toString());
            numErrors++;
        }

        // testing all the methods with a second instance of the class
        p = new Person("Mary", 987654);

        if(! p.getName().equals("Mary"))
        {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        if(p.getHealthNumber() != 987654)
        {
            System.out.println("The constructor or getHealthNumber failed");
            numErrors++;
        }
        p.setName("Sue");
        if(! p.getName().equals("Sue"))
        {
            System.out.println("setName failed");
            numErrors++;
        }
        expected = "\nName: Sue\nHealth number: 987654\n";
        if (!p.toString().equals(expected)) {
            System.out.println("toString failed: " + p.toString());
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
