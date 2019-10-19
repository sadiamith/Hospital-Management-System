import java.util.LinkedList;
import java.util.Iterator;

/**
 * A doctor with a unique name and a list of patients.
 */
public class Doctor extends BasicDoctor
{
    /**
     * The list of all patients of the doctor.
     */
    protected LinkedList<Patient> patients;

    /**
     * Initialize an instance with the given name.
     * @param name     the name for the doctor
     * @precond name != null && !name.equals("")
     */
    public Doctor(String name)
    {
        super(name);
        patients = new LinkedList<Patient>();

        if (name == null || name.equals(""))
            throw new RuntimeException("Invalid constructor argument");
    }

    /**
     * Add a patient to the list of patients for this doctor
     * @param p the patient to be added to the doctor's list
     * @precond ! hasPatient(p.getHealthNumber())
     */
    public void addPatient(Patient p)
    {
        if (hasPatient(p.getHealthNumber()))
            throw new RuntimeException("Patient " + p.getHealthNumber()
                    + " is already a patient of " + getName());
        patients.add(p);
    }

    /**
     * Remove the patient with the specified health number
     * from the list of those being treated by this doctor
     * @param healthNum  the health number of a Patient
     * @precond  hasPatient(healthNum)

     */
    public void removePatient(int healthNum)
    {
        if(!hasPatient(healthNum))
            throw new RuntimeException("Doctor " + getName()
                    + " does not have a patient with health number " + healthNum);

        Iterator<Patient> iter = patients.iterator();
        while (iter.hasNext())
        {
            Patient p = iter.next();
            if (p.getHealthNumber() == healthNum)
            {
                iter.remove();
                return;
            }
        }
    }

    /**
     * Is the Patient with the specified health number a patient of this doctor?
     * @param healthNum  the health number of the Patient to be tested
     *                   for being a patient of this doctor
     * @return is the Patient with the specified health number a patient of this doctor?
     */
    public boolean hasPatient(int healthNum)
    {
        Iterator<Patient> iter = patients.iterator();
        while (iter.hasNext())
        {
            Patient p = iter.next();
            if (p.getHealthNumber() == healthNum)
                return true;
        }
        return false;
    }


    /**
     * Return a string representation of the doctor
     * @return a string representation of the doctor
     */
    public String toString()
    {
        String result = super.toString() + "Patients: ";
        for (Patient p: patients)
            result = result + " " + p.getHealthNumber() + ",";
        return result + "\n";
    }

    /**
     * Carry out basic tests of this class
     */
    public static void main(String[ ] args)
    {
        int numErrors = 0;

        // testing all methods on one instance of the class
        Doctor d = new Doctor("Mary");
        if (!d.getName().equals("Mary"))
            System.out.println("Constructor failed: The doctor has name " + d.getName() + " rather than Mary");
        if (d.patients.size() != 0)
            System.out.println("Constructor failed: The doctor should have no patients, "
                    + "but already has the patients " + d.patients);

        Patient p = new Patient("Bill", 123);
        d.addPatient(p);
        if (!d.hasPatient(123))
            System.out.println("addPatient() or hasPatient() failed: The doctor should have a patient with health number 123, "
                    + "but the patient's list is " + d.patients);

        String expected = "\nName: Mary\nPatients:  123,\n";
        if(!d.toString().equals(expected)) {
            System.out.println("toString failed: " + d.toString());
            numErrors++;
        }

        d.removePatient(123);
        if (d.hasPatient(123))
            System.out.println("removePatient or hasPatient failed: The doctor should not have a patient with health number 123, "
                    + "but the patient's list is " + d.patients);


        // testing all methods on a second instance of the class
        d = new Doctor("Linda");
        if (!d.getName().equals("Linda"))
            System.out.println("Constructor failed: The doctor has name " + d.getName() + " rather than Linda");
        if (d.patients.size() != 0)
            System.out.println("Constructor failed: The doctor should have no patients, "
                    + "but already has the patients " + d.patients);

        p = new Patient("Bob", 569);
        d.addPatient(p);
        if (!d.hasPatient(569))
            System.out.println("addPatient() or hasPatient() failed: The doctor should have a patient with health number 569, "
                    + "but the patient's list is " + d.patients);

        expected = "\nName: Linda\nPatients:  569,\n";
        if(!d.toString().equals(expected)) {
            System.out.println("toString failed: " + d.toString());
            numErrors++;
        }

        d.removePatient(569);
        if (d.hasPatient(569))
            System.out.println("removePatient or hasPatient failed: The doctor should not have a patient with health number 123, "
                    + "but the patient's list is " + d.patients);

        System.out.println("The number of errors found is " + numErrors);

    }
}
