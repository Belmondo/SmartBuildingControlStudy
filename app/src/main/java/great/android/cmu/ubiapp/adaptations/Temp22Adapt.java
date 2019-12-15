package great.android.cmu.ubiapp.adaptations;

import android.content.Context;
import android.widget.Toast;

import task.Task2;

public class Temp22Adapt extends Task2 {

    Context received_context;

    public Temp22Adapt (Context context){
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
        Toast.makeText(received_context, "A temperatura está agradável", Toast.LENGTH_LONG).show();

    }

    @Override
    public Object retorno() {
        return null;
    }

    @Override
    public void evaluate(Object o) {

    }
}
