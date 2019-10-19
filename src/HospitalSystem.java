 //Sadi Mohammad Mustafa
 //sam774
 //11257334

import java.util.TreeMap;
import java.util.Scanner;
import java.util.Collection;

/**
 * A simple hospital system with only one ward.  Patients and doctors can be created,
 * and patients assigned to a doctor and placed in a bed of the ward.
 */
public class HospitalSystem
{
    /**
     * The TreeMap of all patients.
     */
    private TreeMap<Integer, Patient> patients;

    /**
     * The TreeMap of all doctors.
     */
    private TreeMap<String, Doctor> doctors;

    /**
     * The ward that we are going to use.
     */
    private Ward ward;

    /**
     * Initializing an instance of the hospital ward
     * relies on user-input to get the required information
     */
    private HospitalSystem() {

        patients = new TreeMap<Integer, Patient>();
        doctors = new TreeMap<String, Doctor>();

        // getting the ward information
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the name of the Ward: ");
        String name = in.nextLine();
        System.out.print("Enter the number for the first bed: ");
        int firstBedNum = in.nextInt();
        in.nextLine();

        System.out.print("Enter the number for the last bed: ");
        int lastBedNum = in.nextInt();
        in.nextLine();
        ward = new Ward(name, firstBedNum, lastBedNum);
    }

    /**
     * getting the information for a new patient and then adding the patient
     * to the dictionary of all patients.
     */
    private void addPatient()
    {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the name of the patient: ");
        String name = in.nextLine();

        System.out.print("Enter the health number of the patient: ");
        int healthNum = in.nextInt();
        if (patients.containsKey(healthNum))
        {
            throw new RuntimeException("Patient with the health number " + healthNum + " already exists");
        }
        else
        {
            Patient p = new Patient(name, healthNum);
            Patient result = patients.put(healthNum, p);

            // checking to make sure the the key was unique
            if (result != null)
            {
                patients.put(healthNum, result);  // putting the original patient back
                throw new RuntimeException("Health number in the patient dictionary even "
                        + "though containsKey failed. " + healthNum + " not entered.");
            }
        }
    }

    /**
     * Reading the information for a new doctor and then adds the doctor
     * to the dictionary of all doctors.
     */
    private void addDoctor()
    {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the name of the doctor: ");
        String name = in.nextLine();
        if (doctors.containsKey(name))
            throw new RuntimeException("Doctor not added as there already "
                    + "is a doctor with the name " + name);

        System.out.print("Is the doctor a surgeon? (yes or no)");
        String response = in.nextLine();
        Doctor d;
        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
            d = new Surgeon(name);
        else
            d = new Doctor(name);

        // checking to make sure the doctor name doesn't already exists
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
    private void assignDoctorToPatient()
    {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the health number of the patient: ");
        int healthNumber = in.nextInt();
        in.nextLine();  // discard the remainder of the line

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        System.out.println("Getting Doctor information...");
        System.out.print("Enter the name of the doctor: ");
        String name = in.nextLine();
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
    private void assignBed()
    {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the health number of the patient: ");
        int healthNumber = in.nextInt();
        in.nextLine();  // discard the remainder of the line

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        if (p.getBedLabel() != -1)
            throw new RuntimeException(" Patient " + p
                    + " is already in a bed so cannot be assigned a new bed");

        System.out.print("Enter the bed number for the patient: ");
        int bedNum = in.nextInt();
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
    private void dropAssociation()
    {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the health number of the patient: ");
        int healthNumber = in.nextInt();

        Patient p = patients.get(healthNumber);
        if (p == null)
            throw new RuntimeException("There is no patient with health number "
                    + healthNumber);

        System.out.print("Enter the name of the doctor: ");
        String name = in.nextLine();

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
    private void systemState()
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
    private void displayEmptyBeds()
    {
        // TODO: implement stub
        System.out.println("TODO: method not complete");
    }


    /**
     * Release a patient from the ward.
     * Method is just a stub, needs to be implemented
     */
    private void releasePatient()
    {
        // TODO: implement stub
        System.out.println("TODO: method not complete");

    }

    /**
     * Run the hospital system.
     * @param args not used
     */
    public static void main(String[] args)
    {

        try (Scanner in = new Scanner(System.in)) {
            int task = -1;
            HospitalSystem sys = new HospitalSystem();
            while (task != 1) {
                System.out.print("Please select an Function to do"
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

                task = in.nextInt();
                in.nextLine();

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
        }
    }
}