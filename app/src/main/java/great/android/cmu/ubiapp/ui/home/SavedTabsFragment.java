package great.android.cmu.ubiapp.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.Fragment;

import great.android.cmu.ubiapp.CustomExpandableListAdapter;
import great.android.cmu.ubiapp.MainActivity;
import great.android.cmu.ubiapp.model.Device;
import great.android.cmu.ubiapp.ExpandableListDataPump;
import great.android.cmu.ubiapp.R;
import great.android.cmu.ubiapp.ui.notifications.NotificationsFragment;


public class SavedTabsFragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    ProgressBar pgsBar = null;
    Date lastRequest = null;
    ArrayList<Device> coapDevicesList = new ArrayList<Device>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, null);
        expandableListView = (ExpandableListView) v.findViewById(R.id.expandableListView);
        pgsBar = (ProgressBar) v.findViewById(R.id.progressBar);

        new UpdateListView().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                // Toast.makeText(getContext(), expandableListTitle.get(groupPosition) + " List Expanded.", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                // Toast.makeText(getContext(), expandableListTitle.get(groupPosition) + " List Collapsed.", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).toString().contains("light")) {
                    //AQUI DEVE FICAR O BROADCAST
                }
                return false;
            }
        });

        return v;
    }

    private class UpdateListView extends AsyncTask<Void, Void, Void> {

        private boolean done = false;

        @Override
        protected void onPostExecute(Void voids) {
            MainActivity main = (MainActivity) getParentFragment().getActivity();
            ExpandableListDataPump.setDevicesList(main.getDevices());
            expandableListDetail = ExpandableListDataPump.getData();
            expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
            expandableListAdapter = new CustomExpandableListAdapter(getContext(), expandableListTitle, expandableListDetail);
            expandableListView.setAdapter(expandableListAdapter);
            pgsBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            /**
             * Requests examples:
             * - coap://18.229.202.214:5683/.well-known/core
             * - coap://18.229.202.214:5683/devices?if=sensor
             * - coap://18.229.202.214:5683/devices?proximity=165,-3.7466212,-38.5769008
             * - coap://18.229.202.214:5683/devices?proximity=165,-3.7466212,-38.5769008&if=actuator
             * - coap://18.229.202.214:5683/devices?proximity=165,-3.7466212,-38.5769008&if=actuator&ct=10
             */
            MainActivity main = (MainActivity) getParentFragment().getActivity();
            boolean execute = false;
            if(main.getLastDeviceUpdade() == null){
                execute = true;
            }else{
                execute = ((new Date().getTime() - main.getLastDeviceUpdade().getTime())/60000) > 60;
            }

            if(execute){
                final CoapClient client = new CoapClient("coap://18.229.202.214:5683/devices");
                client.get(new CoapHandler() {
                    @Override
                    public void onLoad(CoapResponse response) {
                        if(response != null){
                            //System.out.println(jsonArray);
                            MainActivity main = (MainActivity) getParentFragment().getActivity();
                            String jsonArray = response.advanced().getPayloadString();
                            Type listType = new TypeToken<ArrayList<Device>>(){}.getType();
                            List<Device> devices = new Gson().fromJson(jsonArray, listType);
                            for (Device device : devices) {
                                coapDevicesList.add(device);
                            }
                            done = true;
                            main.setLastDeviceUpdade(new Date());
                            main.setDevices(coapDevicesList);
                            lastRequest = new Date();
                        }
                    }

                    @Override
                    public void onError() {
                        System.err.println("Exception on getDataFromCoapServer()");
                    }
                });
            }else{
                done = true;
            }
            while(!done){ /* waiting coap response */ }
            return null;
        }
    }
}