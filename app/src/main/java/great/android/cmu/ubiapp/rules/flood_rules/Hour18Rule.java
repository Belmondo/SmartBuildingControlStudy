package great.android.cmu.ubiapp.rules.flood_rules;

import android.content.Context;

import evaluators.Assignment;
import evaluators.EvaluationException;

import great.android.cmu.ubiapp.adaptations.Hour18Adapt;
import great.android.cmu.ubiapp.helpers.CalculateMetrics;
import great.android.cmu.ubiapp.rules.Filter;

public class Hour18Rule extends Filter {

    int currentHourIn24Format = 0;
    Hour18Adapt hour18Adapt;

    long timeOfRuleStart;
    long timeOfRuleEnd;

    long timeOfAdaptStart;
    long timeOfAdaptEnd;

    public Hour18Rule (Context context){
        hour18Adapt = new Hour18Adapt(context);
    }

    @Override
    public boolean filter(Assignment assignment) throws EvaluationException {
        return false;
    }

    public void setCurrentHourIn24Format(int currentHourIn24Format){
        this.currentHourIn24Format = currentHourIn24Format;
    }

    @Override
    public boolean evaluate() {
        timeOfRuleStart = System.currentTimeMillis();
        if (currentHourIn24Format==12){
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
        hour18Adapt.executar();

        timeOfAdaptEnd = System.currentTimeMillis();
        CalculateMetrics.setGeneralWatTimes((timeOfRuleEnd-timeOfRuleStart),(timeOfAdaptEnd-timeOfAdaptStart));
    }

    @Override
    public void setContext(int random){
        setCurrentHourIn24Format(random);
    }
}
