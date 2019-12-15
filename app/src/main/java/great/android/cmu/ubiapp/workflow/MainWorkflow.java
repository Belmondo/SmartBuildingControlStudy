package great.android.cmu.ubiapp.workflow;


import java.util.ArrayList;
import java.util.List;

import evaluators.Filter;
import task.Task2;


public class MainWorkflow {

    public static List<Filter> listaFiltros = new ArrayList<Filter>();

    public MainWorkflow (){}

    public static void receive(Filter filter) {
        listaFiltros.add(filter);

    }

    public static void executeOnMoment(Task2 adaptation, Object appContext){
        adaptation.evaluate(appContext);
    }

    public static void executeBasedOnFilters() {
        for(Filter filter:listaFiltros) {
            filter.evaluate();
        }
    }
}
