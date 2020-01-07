package great.android.cmu.ubiapp.rules.flood_rules;

import android.content.Context;

import evaluators.Assignment;
import evaluators.EvaluationException;
import great.android.cmu.ubiapp.adaptations.Batt50Adapt;
import great.android.cmu.ubiapp.helpers.CalculateMetrics;
import great.android.cmu.ubiapp.rules.Filter;

public class Batt50Rule extends Filter {

    Batt50Adapt batt50Adapt;

    static int batteryLevel = 0;

    long timeOfRuleStart;
    long timeOfRuleEnd;

    long timeOfAdaptStart;
    long timeOfAdaptEnd;

    public Batt50Rule (Context context){
        batt50Adapt  = new Batt50Adapt(context);
    }

    @Override
    public boolean filter(Assignment assignment) throws EvaluationException {
        return false;
    }

    public void setBatteryLevel(int batteryLevel){
        this.batteryLevel = batteryLevel;
    }

    @Override
    public boolean evaluate() {

        timeOfRuleStart = System.currentTimeMillis();

        System.out.println("Trs E"+ timeOfRuleStart);
        if (batteryLevel<=50){
            CalculateMetrics.setNumberOfRulesVerified();
            execute();
            timeOfRuleEnd = System.currentTimeMillis();
            return true;
        } else{
            timeOfRuleEnd = System.currentTimeMillis();
            return false;
        }



    }

    @Override
    public void execute() {
        timeOfAdaptStart = System.currentTimeMillis();
        batt50Adapt.executar();
        timeOfAdaptEnd = System.currentTimeMillis();

        System.out.println("ENTROU NO EXECUTE");
        System.out.println("TRE:" + timeOfRuleEnd + "TRS"+ timeOfRuleStart);

        CalculateMetrics.setGeneralWatTimes((timeOfRuleEnd-timeOfRuleStart),(timeOfAdaptEnd-timeOfAdaptStart));
    }

    @Override
    public void setContext(int random){
        setBatteryLevel(random);
    }
}
