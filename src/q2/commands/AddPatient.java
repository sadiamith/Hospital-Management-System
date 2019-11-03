package q2.commands;

import q2.containers.PatientMapAccess;
import q2.entities.Patient;


/**
 * adds a patient to the system
 */

public class AddPatient extends CommandStatus{

    /**
     * Creates a patient with the passed in argument as the name
     * and health number and adds it to the system
     * @param name of the patient
     * @param healthNum of the patient
     */
    public void addPatient(String name, int healthNum) {
        if(PatientMapAccess.dictionary().containsKey(healthNum)){
            successful = false;
            errorMessage = "entities.Patient with the health number " + healthNum + " already exsists";
        }else {
            Patient p = null;
            Patient samePatient = PatientMapAccess.dictionary().put(healthNum, p);
            if(samePatient != null){
                PatientMapAccess.dictionary().put(healthNum, samePatient);
                successful = false;
                errorMessage = "Health number in the patient dictionary even "
                        + "though containsKey failed.  Number " + healthNum + " not entered.";
            }else
                successful = true;
        }
    }
}
