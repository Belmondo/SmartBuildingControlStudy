package great.android.cmu.ubiapp.adaptations;

import android.content.Context;

import great.android.cmu.ubiapp.external.External_Processment;
import task.Task2;

public class Hour12Adapt extends Task2 {


    Context received_context;

    public Hour12Adapt (Context context){
        received_context = context;
    }

    @Override
    public void recebeToken(Object o) {

    }

    @Override
    public Object retornaToken() {
        return null;
    }

    @Override
    public void executar() {
        External_Processment.turnOffTheLights(received_context);
    }

    @Override
    public Object retorno() {
        return null;
    }

    @Override
    public void evaluate(Object o) {

    }
}
