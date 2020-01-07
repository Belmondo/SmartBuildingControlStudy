package great.android.cmu.ubiapp.rules.flood_rules;

import android.content.Context;

import evaluators.Assignment;
import evaluators.EvaluationException;
import great.android.cmu.ubiapp.adaptations.Temp22Adapt;
import great.android.cmu.ubiapp.helpers.CalculateMetrics;
import great.android.cmu.ubiapp.rules.Filter;

public class Temp22Rule extends Filter {

    float detectedTemperature;
    Temp22Adapt temp22Adapt;

    long timeOfRuleStart;
    long timeOfRuleEnd;

    long timeOfAdaptStart;
    long timeOfAdaptEnd;

    public Temp22Rule (Context context){
        temp22Adapt = new Temp22Adapt(context);
    }

    @Override
    public boolean filter(Assignment assignment) throws EvaluationException {
        return false;
    }

    public void setDetectedTemperature(float detectedTemperature){
        this.detectedTemperature = detectedTemperature;
    }

    @Override
    public boolean evaluate() {
        timeOfRuleStart = System.currentTimeMillis();
        if (detectedTemperature==22.0){
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
        temp22Adapt.executar();
        timeOfAdaptEnd = System.currentTimeMillis();
        CalculateMetrics.setGeneralWatTimes((timeOfRuleEnd-timeOfRuleStart),(timeOfAdaptEnd-timeOfAdaptStart));
    }

    @Override
    public void setContext(int random){
        setDetectedTemperature((float)random);
    }
}
