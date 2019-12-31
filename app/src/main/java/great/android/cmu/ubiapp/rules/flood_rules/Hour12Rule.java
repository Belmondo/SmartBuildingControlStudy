package great.android.cmu.ubiapp.rules.flood_rules;

import android.content.Context;

import evaluators.Assignment;
import evaluators.EvaluationException;

import great.android.cmu.ubiapp.adaptations.Hour12Adapt;
import great.android.cmu.ubiapp.helpers.CalculateMetrics;
import great.android.cmu.ubiapp.rules.Filter;

public class Hour12Rule extends Filter {


    int currentHourIn24Format = 0;

    Hour12Adapt hour12Adapt;

    long timeOfRuleStart;
    long timeOfRuleEnd;

    long timeOfAdaptStart;
    long timeOfAdaptEnd;

    public Hour12Rule (Context context){

        hour12Adapt = new Hour12Adapt(context);

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
        hour12Adapt.executar();

        timeOfAdaptEnd = System.currentTimeMillis();

        CalculateMetrics.setGeneralWatTimes((timeOfRuleEnd-timeOfRuleStart),(timeOfAdaptEnd-timeOfAdaptStart));
    }

    @Override
    public void setContext(int random){
        setCurrentHourIn24Format(random);
    }
}
