package great.android.cmu.ubiapp.adaptations;

import android.content.Context;

import great.android.cmu.ubiapp.external.External_Processment;
import task.Task2;

public class Temp14Adapt extends Task2 {

    Context received_context;

    public Temp14Adapt (Context context){
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
        External_Processment.turnOffTheAir(received_context);

    }

    @Override
    public Object retorno() {
        return null;
    }

    @Override
    public void evaluate(Object o) {

    }
}
