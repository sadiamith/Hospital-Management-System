//Sadi Mohammad Mustafa
//sam774
//11257334
//CMPT270

package q2.commands;

import q2.containers.DoctorMapAccess;
import q2.containers.PatientMapAccess;
import q2.entities.Doctor;
import q2.entities.Patient;

/**
 * feature that assigns a doctor to a patient
 */
public class AssignDoctor extends CommandStatus {
    /**
     * assign the given name doctor to a given health number patient
     * @param name the name of the doctor
     * @param patientNum the health number of the patient
     */

    public void assignDoctor(String name, int patientNum){
        successful = true;
        Patient p = PatientMapAccess.dictionary().get(patientNum);
        if(p == null){
            successful = false;
            errorMessage = "There is no patient with health number" + patientNum;
        }
        Doctor d = DoctorMapAccess.dictionary().get(name);
        if(d == null){
            if(successful){
                successful = false;
                errorMessage = "There is no Doctor with name" + name;
            }else
                errorMessage = errorMessage + "and no doctor with name" + name;
        }
        if(successful){
            try{
                p.addDoctor(d);
                d.addPatient(p);
            } catch (RuntimeException e){
                successful = false;
                errorMessage = e.getMessage();
            }
        }
    }
}
