package great.android.cmu.ubiapp.rules.flood_rules;

import android.content.Context;

import evaluators.Assignment;
import evaluators.EvaluationException;
import evaluators.Filter;
import great.android.cmu.ubiapp.adaptations.Hour18Adapt;
import great.android.cmu.ubiapp.helpers.CalculateMetrics;

public class Hour18Rule extends Filter {

    int currentHourIn24Format = 0;
    Hour18Adapt hour18Adapt;

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
        hour18Adapt.executar();
    }

}
