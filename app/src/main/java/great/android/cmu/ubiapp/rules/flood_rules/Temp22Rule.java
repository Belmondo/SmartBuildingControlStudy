package great.android.cmu.ubiapp.rules.flood_rules;

import evaluators.Assignment;
import evaluators.EvaluationException;
import evaluators.Filter;
import great.android.cmu.ubiapp.adaptations.Temp22Adapt;
import great.android.cmu.ubiapp.helpers.CalculateMetrics;

public class Temp22Rule extends Filter {

    float detectedTemperature;
    Temp22Adapt temp22Adapt = new Temp22Adapt();


    @Override
    public boolean filter(Assignment assignment) throws EvaluationException {
        return false;
    }

    public void setDetectedTemperature(float detectedTemperature){
        this.detectedTemperature = detectedTemperature;
    }

    @Override
    public boolean evaluate() {
        if (detectedTemperature==22.0){
            CalculateMetrics.setNumberOfRulesVerified();
            execute();
            return true;
        } else{
            return false;
        }
    }

    @Override
    public void execute() {
        temp22Adapt.executar();
    }
}
