package great.android.cmu.ubiapp.helpers;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CalculateMetrics {

    static ArrayList<Long> TaTimes = new ArrayList();
    static ArrayList<Long> GeneralWatTimes = new ArrayList();

    static int numberOfRulesVerified = 0;


    public static void calculateSingleWat(String adaptationName, Long WorkingTime, Long AdaptivityTime){
        System.out.println("Name of Adaptation: " + adaptationName + " the WAT calculus = " + (WorkingTime/AdaptivityTime));
        Log.d("WAT", "Name of Adaptation: " + adaptationName + " the WAT calculus = " + (WorkingTime/AdaptivityTime));

    }

    public static void calculateSingleWat(Context context,String adaptationName, Long WorkingTime, Long AdaptivityTime){

        Toast.makeText(context,"Name of Adaptation: " + adaptationName + " the WAT calculus = " + (WorkingTime/AdaptivityTime), Toast.LENGTH_LONG).show();
        System.out.println("Name of Adaptation: " + adaptationName + " the WAT calculus = " + (WorkingTime/AdaptivityTime));
        Log.d("WAT", "Name of Adaptation: " + adaptationName + " the WAT calculus = " + (WorkingTime/AdaptivityTime));



    }




    public static void calculateGeneralWat(){

        Long GeneralWATTimeVariable = null;

        for(int i=0; i< GeneralWatTimes.size(); i++){
            GeneralWATTimeVariable +=GeneralWatTimes.get(i);


        }

        System.out.println("General Wat Calculated: " + GeneralWATTimeVariable );
        Log.d("GENERAL WAT", "General Wat Calculated: " + GeneralWATTimeVariable );

    }


    public static void calculateGeneralWat(Context context){

        Long GeneralWATTimeVariable = null;

        for(int i=0; i< GeneralWatTimes.size(); i++){
            GeneralWATTimeVariable +=GeneralWatTimes.get(i);


        }

        Toast.makeText(context,"General Wat Calculated: " + GeneralWATTimeVariable, Toast.LENGTH_LONG).show();
        System.out.println("General Wat Calculated: " + GeneralWATTimeVariable );
        Log.d("GENERAL WAT", "General Wat Calculated: " + GeneralWATTimeVariable );

    }

    public static void setGeneralWatTimes(Long WorkingTime, Long AdaptivityTime){
        GeneralWatTimes.add((WorkingTime/AdaptivityTime));
    }



    public static void setTATimes(Long taTimeOnThisMoment){

        TaTimes.add(taTimeOnThisMoment);
    }

    public static void calculateTA(){
        Long GeneralTAVariable = null;

        for(int i=0; i< TaTimes.size(); i++){
            GeneralTAVariable +=GeneralWatTimes.get(i);


        }

        System.out.println("General TA Calculated: " + GeneralTAVariable );
        Log.d("GENERAL TA", "General TA Calculated: " + GeneralTAVariable );

    }

    public static void calculateTA(Context context){
        Long GeneralTAVariable = null;

        for(int i=0; i< TaTimes.size(); i++){
            GeneralTAVariable +=GeneralWatTimes.get(i);


        }

        Toast.makeText(context,"General TA Calculated: " + GeneralTAVariable, Toast.LENGTH_LONG).show();
        System.out.println("General TA Calculated: " + GeneralTAVariable );
        Log.d("GENERAL TA", "General TA Calculated: " + GeneralTAVariable );

    }

    public static void setNumberOfRulesVerified(){
        numberOfRulesVerified ++;
    }

    public int getNumberOfRulesVerified(){
        return this.numberOfRulesVerified;
    }


}
