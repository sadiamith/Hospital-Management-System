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
     * A method to test the Person class.
     */
    public static void main(String[] args)
    {
        int numErrors = 0;
        Person p = new Person("Pete", 123456);
        System.out.println("The person called Pete with number 123456 is " + p + "\n");
        if (! p.getName().equals("Pete"))
        {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        if (p.getHealthNumber() != 123456)
        {
            System.out.println("The constructor or getHealthNumber failed");
            numErrors++;
        }
        p.setName("Jim");
        if (! p.getName().equals("Jim"))
        {
            System.out.println("setName failed");
            numErrors++;
        }

        p = new Person("Mary", 987654);
        System.out.println("The person called Mary with number 987654 is " + p + "\n");
        if (! p.getName().equals("Mary"))
        {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        if (p.getHealthNumber() != 987654)
        {
            System.out.println("The constructor or getHealthNumber failed");
            numErrors++;
        }
        p.setName("Sue");
        if (! p.getName().equals("Sue"))
        {
            System.out.println("setName failed");
            numErrors++;
        }
        System.out.println("The number of errors found is " + numErrors);
    }
}