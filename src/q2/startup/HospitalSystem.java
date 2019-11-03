package q2.startup;

import commands.*;
import containers.*;
import entities.Doctor;
import entities.*;
import q2.commands.AddPatient;
import q2.commands.AssignBed;
import q2.commands.CurrentState;
import q2.commands.ReleasePatient;
import q2.userInterfaces.*;

import java.util.*;

/**
 * A simple hospital system with only one ward.  Patients and doctors can be created,
 * and patients assigned to a doctor and/or placed in a bed of the ward.
 */
public class HospitalSystem
{
    /**
     * The interface to be used to read input from the user and output results to the user.
     */
    private InputOutputInterface ioInterface;

    /**
     * Initialize the system by creating the dictionaries, ward, and interface for I/O.
     */
    public void initialize() {
        ioInterface = new DialogIO();
        String option = ioInterface.readString("Should dialog boxes be used for I/O? (Y/N) ");
        if (option != null)
            if (option.charAt(0) == 'N' || option.charAt(0) == 'n')
                ioInterface = new ConsoleIO();
        createWard();
    }

    /**
     * Create the ward after reading the information to initialize it.
     */
    public void createWard() {
        String name = ioInterface.readString("Enter the name of the ward: ");
        int firstBedLabel = ioInterface.readInt("Enter the integer label of the first bed: ");
        int lastBedLabel = ioInterface.readInt("Enter the integer label of the last bed: ");
    }

    /**
     * Initialize an instance of the hospital ward
     * relies on user-input to get the relavent information
     */
    public HospitalSystem() {
        initialize();
    }

    /**
     * Read the information for a new patient and then add the patient
     * to the dictionary of all patients.
     */
    public void addPatient()
    {
        String name = ioInterface.readString("Enter the name of the patient: ");
        int healthNum = ioInterface.readInt("Enter the health number of the patient: ");
        AddPatient cmd = new AddPatient();
        cmd.addPatient(name, healthNum);
        if (!cmd.wasSuccessful())
            throw new RuntimeException(cmd.getErrorMessage() + "\n");
    }

    /**
     * Read the information for a new doctor and then add the doctor
     * to the dictionary of all doctors.
     */
    public void addDoctor()
    {
        String name = ioInterface.readString("Enter the name of the doctor: ");
        String response = ioInterface.readString("Is the doctor a surgeon? (yes or no)");
        NewDoctor cmd = new NewDoctor();
        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
            cmd.addDoctor(name, true);
        else
            cmd.addDoctor(name, false);
        if (!cmd.wasSuccessful())
            throw new RuntimeException(cmd.getErrorMessage() + "\n");

    }

    /**
     * Assign a doctor to take care of a patient.
     */
    public void assignDoctorToPatient()
    {
        int healthNumber = ioInterface.readInt("Enter the health number of the patient: ");
        String name = ioInterface.readString("Enter the name of the doctor: ");

        AssignDoctor cmd = new AssignDoctor();
        cmd.assignDoctor(name, healthNumber);
        if(!cmd.wasSuccessful()) {
            throw new RuntimeException(cmd.getErrorMessage());
        }

    }

    /**
     * Assign a patient to a specific bed.
     */
    public void assignBed()
    {
        int healthNum = ioInterface.readInt("Enter the health number of the patient: ");
        int bedNum = ioInterface.readInt("Enter the bed number for the patient: ");

        AssignBed cmd = new AssignBed();
        cmd.assignBed(healthNum, bedNum);
        if (!cmd.wasSuccessful())
            throw new RuntimeException(cmd.getErrorMessage() + "\n");
    }

    /**
     * Drop the association between a doctor and a patient.
     */
    public void dropAssociation()
    {
        int healthNumber = ioInterface.readInt("Enter the health number of the patient: ");

        Patient p = PatientMapAccess.dictionary().get(healthNumber);

        String name = ioInterface.readString("Enter the name of the doctor: ");

        Doctor d = DoctorMapAccess.dictionary().get(name);
        int pHealthNumber = p.getHealthNumber();

        DropDoctor cmd = new DropDoctor();
        cmd.dropAssociation(name, pHealthNumber);
        if(!cmd.wasSuccessful()){
            throw new RuntimeException(cmd.getErrorMessage());
        }
    }

    /**
     * Displays the system state
     */
    
    public void systemState()
    {
        System.out.println(this.toString());
    }

    /**
     * Return a string representation of the startup.HospitalSystem
     * @return a string representation of the startup.HospitalSystem
     */
    public String toString() {
        String result = "\nThe patients in the system are \n";
        Collection<Patient> patientsColl = PatientMapAccess.dictionary().values();
        for (Patient p: patientsColl)
            result = result + p;
        result = result + "\nThe doctors in the system are \n";
        Collection<Doctor> doctorsColl = DoctorMapAccess.dictionary().values();
        for (Doctor d: doctorsColl)
            result = result + d;
        result = result + "\nThe ward is " + WardAccess.ward();
        return result;
    }

    /**
     * Display the empty beds for the ward.
     */
    public void displayEmptyBeds()
    {
        EmptyBeds cmd = new EmptyBeds();
        cmd.findEmptyBedList();
        LinkedList<Integer> emptyBedsList = cmd.getEmptyBedList();

        if(cmd.wasSuccessful()) {
            ioInterface.outputString("The empty beds of the hospital are:  " + emptyBedsList + "\n");
        } else {
            throw new RuntimeException(cmd.getErrorMessage());
        }
    }


    /**
     * Release a patient from the ward.
     */
    public void releasePatient()
    {
        int healthNum = ioInterface.readInt("Enter the health number of the patient: ");

        ReleasePatient cmd = new ReleasePatient();
        cmd.releasePatient(healthNum);
        if (!cmd.wasSuccessful())
            ioInterface.outputString(cmd.getErrorMessage() + "\n");
    }

    /**
     * Output the prompt that lists the possible tasks, and read the selection chosen by the user.
     *
     * @return the int corresponding to the task selected
     */
    public int readOpId() {
        String[] taskChoices =
                new String[] {"quit", "add a new patient", "add a new doctor",
                        "assign a doctor to a patient", "display the empty beds of the ward",
                        "assign a patient a bed", "release a patient",
                        "drop doctor-patient association", "display current system state"};

        return ioInterface.readChoice(taskChoices);
    }

    /**
     * Run the hospital system.
     * @param args not used
     */
    public static void main(String[] args)
    {
        Scanner consoleIn = new Scanner(System.in);
        int task = -1;

        HospitalSystem sys = new HospitalSystem();

        try{
            while(task != 0) {
                task = sys.readOpId();

                if (task == 0)
                    sys.systemState();
                else if (task == 1)
                    sys.addPatient();
                else if (task == 2)
                    sys.addDoctor();
                else if (task == 3)
                    sys.assignDoctorToPatient();
                else if (task == 4)
                    sys.displayEmptyBeds();
                else if (task == 5)
                    sys.assignBed();
                else if (task == 6)
                    sys.releasePatient();
                else if (task == 7)
                    sys.dropAssociation();
                else if (task == 8)
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