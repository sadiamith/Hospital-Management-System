//Sadi Mohammad Mustafa
//sam774
//11257334
//CMPT270

package commands;


import containers.DoctorMapAccess;
import entities.Doctor;
import entities.Surgeon;

import javax.print.Doc;

/**
 * add a doctor to the system
 */
public class NewDoctor extends CommandStatus{
    /**
     * creates a doctor with the passed in argument as the name and adds it to the system.
     * makes the doctor a surgeon if needed
     * @param name the name of the doctor
     * @param isSurgeon is the doctor a surgeon?
     */
    public void addDoctor(String name, boolean isSurgeon){
        if(DoctorMapAccess.dictionary().containsKey(name)){
            successful = false;
            errorMessage = "entities.Doctor not added as there already " + "is a doctor with the name " + name;
        }else {
            Doctor d = null;
            try{
                if(isSurgeon)
                    d = new Surgeon(name);
                else
                    d = new Doctor(name);
            }
            catch (RuntimeException e){
                successful = false;
                errorMessage = e.getMessage();
                return;
            }
            Doctor sameDoctor = DoctorMapAccess.dictionary().put(name, d);
            if(sameDoctor != null){
                DoctorMapAccess.dictionary().put(name, sameDoctor);
                successful = false;
                errorMessage = "Name in the doctor dictionary even though "
                        + "containsKey failed.  Name "  + name + " not entered";
            }else
                successful = true;
        }
    }
}
