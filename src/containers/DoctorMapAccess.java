//Sadi Mohammad Mustafa
//sam774
//11257334
//CMPT270


package containers;
import java.util.*;
import entities.Doctor;

/**
 * singleton class to access the doctors dictionary
 */

public class DoctorMapAccess {
    private DoctorMapAccess(){};
    private static TreeMap<String, Doctor> dictionary;


    /**
     * returns the dictionary that maps the names to the doctors
     * @return dictionary that maps names to the doctors
     */
    public static TreeMap<String, Doctor> dictionary(){
        if(dictionary == null) {
            dictionary = new TreeMap<String, Doctor>();
        }
        return dictionary;
    }
}
