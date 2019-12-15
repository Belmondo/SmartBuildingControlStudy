package great.android.cmu.ubiapp.rules.flood_rules;

import evaluators.Assignment;
import evaluators.EvaluationException;
import evaluators.Filter;
import great.android.cmu.ubiapp.adaptations.Temp30Adapt;
import great.android.cmu.ubiapp.helpers.CalculateMetrics;

public class Temp30Rule extends Filter {

    float detectedTemperature;
    Temp30Adapt temp30Adapt = new Temp30Adapt();
    @Override
    public boolean filter(Assignment assignment) throws EvaluationException {
        return false;
    }

    public void setDetectedTemperature(float detectedTemperature){
        this.detectedTemperature = detectedTemperature;
    }

    @Override
    public boolean evaluate() {
        if (detectedTemperature>=30.0){
            CalculateMetrics.setNumberOfRulesVerified();
            execute();
            return true;
        } else{
            return false;
        }

    }

    @Override
    public void execute() {
        temp30Adapt.executar();
    }
}

