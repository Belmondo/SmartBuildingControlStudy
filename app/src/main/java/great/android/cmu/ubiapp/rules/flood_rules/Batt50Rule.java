package great.android.cmu.ubiapp.rules.flood_rules;

import android.content.Context;

import evaluators.Assignment;
import evaluators.EvaluationException;
import evaluators.Filter;
import great.android.cmu.ubiapp.adaptations.Batt50Adapt;
import great.android.cmu.ubiapp.helpers.CalculateMetrics;

public class Batt50Rule extends Filter {

    Batt50Adapt batt50Adapt;

    static int batteryLevel = 0;

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
        if (batteryLevel<=50){
            CalculateMetrics.setNumberOfRulesVerified();
            execute();
            return true;
        } else{
            return false;
        }

    }

    @Override
    public void execute() {
        batt50Adapt.executar();
    }

}
