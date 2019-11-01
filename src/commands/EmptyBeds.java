package commands;

import java.util.*;
import containers.WardAccess;

/**
 * feature to get the empty bed list
 */
public class EmptyBeds extends CommandStatus{
    //list of the empty beds of the ward
    private LinkedList<Integer> emptyBedList;

    public void findEmptyBedList(){
        emptyBedList = WardAccess.ward().availableBeds();
        successful = true;
    }

    public LinkedList<Integer> getEmptyBedList(){
        if(!wasSuccessful())
            throw new RuntimeException("the method obtainEmptyBed() must be invoked before"
            + "getEmptyBed()");
        return emptyBedList;
    }
}
