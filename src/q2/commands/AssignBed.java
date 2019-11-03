package q2.commands;

import q2.containers.*;
import q2.entities.*;


/**
 * feature that assigns a bed to the patient
 */
public class AssignBed extends CommandStatus{

    /**
     * assigns the patient to the given bed number
     * @param healthNum patient name
     * @param bedNum bed number
     */

    public void assignBed(int healthNum, int bedNum) {
        successful = true;
        Patient p = PatientMapAccess.dictionary().get(healthNum);
        if(p == null){
            successful = false;
            errorMessage = "There is no patient with health number "
                    + healthNum;
        }
        if(p.getBedLabel() != -1){
            successful = false;
            errorMessage = " entities.Patient " + p
                    + " is already in a bed so cannot be assigned a new bed";
        }
        if (successful) {
            try {
                p.setBedLabel(bedNum);
                WardAccess.ward().assignPatientToBed(p, bedNum);
            } catch (RuntimeException e){
                successful = false;
                errorMessage = e.getMessage();
            }
        }
    }
}
