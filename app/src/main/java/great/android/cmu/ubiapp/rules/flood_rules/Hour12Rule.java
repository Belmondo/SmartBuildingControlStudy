package great.android.cmu.ubiapp.rules.flood_rules;

import android.content.Context;

import evaluators.Assignment;
import evaluators.EvaluationException;
import evaluators.Filter;
import great.android.cmu.ubiapp.adaptations.Hour12Adapt;
import great.android.cmu.ubiapp.helpers.CalculateMetrics;

public class Hour12Rule extends Filter {


    int currentHourIn24Format = 0;

    Hour12Adapt hour12Adapt;

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
        if (currentHourIn24Format==12){
            CalculateMetrics.setNumberOfRulesVerified();
            execute();
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void execute() {
        hour12Adapt.executar();
    }
}
