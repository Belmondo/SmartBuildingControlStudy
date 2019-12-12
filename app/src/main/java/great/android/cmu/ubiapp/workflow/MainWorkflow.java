package great.android.cmu.ubiapp.workflow;


import task.Task2;


public class MainWorkflow {


    public static void executeOnMoment(Task2 adaptation, Object appContext){
        adaptation.evaluate(appContext);
    }
}
