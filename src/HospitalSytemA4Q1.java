import java.util.LinkedList;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Collection;

/**
 * A simple hospital system with only one ward.  Patients and doctors can be created,
 * and patients assigned to a doctor and/or placed in a bed of the ward.
 */
public class HospitalSytemA4Q1
{
    /**
     * The keyed dictionary of all patients.
     */
    private TreeMap<Integer, Patient> patients;

    /**
     * The keyed dictionary of all doctors.
     */
    private TreeMap<String, Doctor> doctors;

    /**
     * The ward to be handled.
     */
    private Ward ward;

    /**
     * Initialize an instance of the hospital ward
     * relies on user-input to get the relavent information
     */
    public HospitalSytemA4Q1() {

        patients = new TreeMap<Integer, Patient>();
        doctors = new TreeMap<String, Doctor>();

        // get the ward information
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Initializing the system...");
        System.out.println("Getting Ward information...");
        System.out.print("Enter the name of the Ward: ");
        String name = consoleIn.nextLine();
        System.out.print("Enter the integer label of the first bed: ");
        int firstBedNum = consoleIn.nextInt();
        consoleIn.nextLine();

        System.out.print("Enter the integer label of the last bed: ");
        int lastBedNum = consoleIn.nextInt();
        consoleIn.nextLine();
        ward = new Ward(name, firstBedNum, lastBedNum);
    }

    /**
     * Read the information for a new patient and then add the patient
     * to the dictionary of all patients.
     */
    public void addPatient()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting Patient information...");
        System.out.print("Enter the name of the patient: ");
        String name = consoleIn.nextLine();

        System.out.print("Enter the health number of the patient: ");
        int healthNum = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line
        if (patients.containsKey(healthNum))
        {
            throw new RuntimeException("Patient with the health number " + healthNum + " already exsists");
        }
        else
        {
            Patient p = new Patient(name, healthNum);
            Patient result = patients.put(healthNum, p);

            // checking to make sure the the key was unique
            if (result != null)
            {
                patients.put(healthNum, result);  // put the original patient back
                throw new RuntimeException("Health number in the patient dictionary even "
                        + "though containsKey failed.  Number " + healthNum + " not entered.");
            }
        }
    }

    /**
     * Read the information for a new doctor and then add the doctor
     * to the dictionary of all doctors.
     */
    public void addDoctor()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Getting Patient information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();
        if (doctors.containsKey(name))
            throw new RuntimeException("Doctor not added as there already "
                    + "is a doctor with the name " + name);

        System.out.print("Is the doctor a surgeon? (yes or no)");
        String response = consoleIn.nextLine();
        Doctor d;
        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
            d = new Surgeon(name);
        else
            d = new Doctor(name);

        // check to make sure the doctor name doesn't already exsist
        Doctor sameNumberDoctor = doctors.put(name, d);
        if (sameNumberDoctor != null)
        {
            doctors.put(name, sameNumberDoctor); // put the original doctor back
            throw new RuntimeException("Name in the doctor dictionary even though "
                    + "containsKey failed.  Name "  + name + " not entered.");
        }
    }

    /**
     * Assign a doctor to take care of a patient.
     */
    public void assignDoctorToPatient()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Assigning a new Doctor-Patient Association...");
        System.out.println("Getting Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        System.out.println("Getting Doctor information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();
        Doctor d = doctors.get(name);
        if (d == null)
            throw new RuntimeException("There is no doctor with name " + name);
        else
        {
            p.addDoctor(d);
            d.addPatient(p);
        }
    }

    /**
     * Assign a patient to a specific bed.
     */
    public void assignBed()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Assigning a Patient to a Bed...");
        System.out.println("Getting Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        if (p.getBedLabel() != -1)
            throw new RuntimeException(" Patient " + p
                    + " is already in a bed so cannot be assigned a new bed");

        System.out.print("Enter the bed number for the patient: ");
        int bedNum = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line
        if (bedNum < ward.getMinBedLabel() || bedNum > ward.getMaxBedLabel())
            throw new RuntimeException("Bed label " + bedNum + " is not valid, as "
                    + "the value must be between " + ward.getMinBedLabel()
                    + " and " + ward.getMaxBedLabel());

        p.setBedLabel(bedNum);
        ward.assignPatientToBed(p, bedNum);
    }

    /**
     * Drop the association between a doctor and a patient.
     */
    public void dropAssociation()
    {
        Scanner consoleIn = new Scanner(System.in);

        System.out.println("Dropping a new Doctor-Patient Association...");
        System.out.println("Getting Patient information...");
        System.out.print("Enter the health number of the patient: ");
        int healthNumber = consoleIn.nextInt();
        consoleIn.nextLine();  // discard the remainder of the line

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        System.out.println("Getting Doctor information...");
        System.out.print("Enter the name of the doctor: ");
        String name = consoleIn.nextLine();

        Doctor d = doctors.get(name);
        if (d == null)
            throw new RuntimeException("There is no doctor with name " + name);

        int pHealthNumber = p.getHealthNumber();
        if (!d.hasPatient(pHealthNumber))
            throw new RuntimeException("This doctor is not associated with this patient.");
        if (!p.hasDoctor(name))
            throw new RuntimeException("This doctor and this patient are incorrectly "
                    + "associated.  The doctor has the patient, "
                    + "but the patient does not have the doctor");

        p.removeDoctor(name);
        d.removePatient(healthNumber);
    }

    /**
     * Displays the system state
     */
    public void systemState()
    {
        System.out.println(this.toString());
    }

    /**
     * Return a string representation of the HospitalSystem
     * @return a string representation of the HospitalSystem
     */
    public String toString() {
        String result = "\nThe patients in the system are \n";
        Collection<Patient> patientsColl = patients.values();
        for (Patient p: patientsColl)
            result = result + p;
        result = result + "\nThe doctors in the system are \n";
        Collection<Doctor> doctorsColl = doctors.values();
        for (Doctor d: doctorsColl)
            result = result + d;
        result = result + "\nThe ward is " + ward;
        return result;
    }

    /**
     * Display the empty beds for the ward.
     * Method is just a stub, needs to be implemented
     */
    public void displayEmptyBeds()
    {
        LinkedList<Integer> emptyBedList = ward.availableBeds();
        System.out.println("The empty beds are" + emptyBedList);
    }


    /**
     * Release a patient from the ward.
     * Method is just a stub, needs to be implemented
     */
    public void releasePatient()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the health number of the patient: ");

        int patientHealthNum = in.nextInt();
        in.nextLine();

        if(!patients.containsKey(patientHealthNum)){
            throw new RuntimeException("There is no patient with the health number" + patientHealthNum);
        }

        Patient p = patients.get(patientHealthNum);
        if(p.getBedLabel() == -1){
            throw new RuntimeException("The patient is not assigned to any bed");
        }
        int bedLabel = p.getBedLabel();
        if(ward.getPatient(bedLabel) != p){
            throw new RuntimeException("The bed" + bedLabel + "does not contain the patient it was supposed to"
            + "Instead it contains" + ward.getPatient(bedLabel) + "Patient");
        }
        ward.freeBed(bedLabel);
        p.setBedLabel(-1);
    }

    /**
     * Run the hospital system.
     * @param args not used
     */
    public static void main(String[] args)
    {
        Scanner consoleIn = new Scanner(System.in);
        int task = -1;

        HospitalSytemA4Q1 sys = new HospitalSytemA4Q1();

        try{
            while(task != 1) {
                System.out.print("Please select an operation to do"
                        + "\n1: quit"
                        + "\n2: add a new patient"
                        + "\n3: add a new doctor"
                        + "\n4: assign a doctor to a patient"
                        + "\n5: display the empty beds of the ward"
                        + "\n6: assign a patient a bed"
                        + "\n7: release a patient"
                        + "\n8: drop doctor-patient association"
                        + "\n9: display current system state"
                        + "\nEnter the number of your selection: ");

                task = consoleIn.nextInt();
                consoleIn.nextLine();

                if (task == 1)
                    sys.systemState();
                else if (task == 2)
                    sys.addPatient();
                else if (task == 3)
                    sys.addDoctor();
                else if (task == 4)
                    sys.assignDoctorToPatient();
                else if (task == 5)
                    sys.displayEmptyBeds();
                else if (task == 6)
                    sys.assignBed();
                else if (task == 7)
                    sys.releasePatient();
                else if (task == 8)
                    sys.dropAssociation();
                else if (task == 9)
                    sys.systemState();
                else
                    System.out.println("Invalid option, try again.");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            consoleIn.close();
        }
    }
}