package q2.commands;

import q2.containers.*;
import q2.entities.*;


/**
 * releases a patient from the bed
 */
public class ReleasePatient extends CommandStatus{

    /**
     * releases a patient from the system
     * @param healthNum of the patient who will get released
     */

    public void releasePatient(int healthNum) {
        Patient p = PatientMapAccess.dictionary().get(healthNum);
        if(p == null){
            successful = false;
            errorMessage = "There is no patient with the health number" + healthNum;
        }else
            successful = true;
        if(p.getBedLabel() == 1){
            successful = false;
            errorMessage = "The patient is not assigned to any bed";
        }else
            successful = true;
        WardAccess.ward().freeBed(p.getBedLabel());
        p.setBedLabel(-1);
    }
}
