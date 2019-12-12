package great.android.cmu.ubiapp.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import great.android.cmu.ubiapp.R;
import great.android.cmu.ubiapp.helpers.DeviceActionMessage;
import great.android.cmu.ubiapp.helpers.Utils;

import static great.android.cmu.ubiapp.keywords.keywords.RAIO_DIGITADO;
import static great.android.cmu.ubiapp.keywords.keywords.SWITCH_STATE;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    Switch switchFiltros;

    TextView tvRaio;
    TextView tvMensagem;
    EditText etRaio;

    static int raioDigitado;

    DeviceActionMessage dvaMessage;


    boolean switchState = false;
    static SharedPreferences sharedPrefs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        switchFiltros = (Switch) root.findViewById(R.id.switchFiltro);
        tvRaio = (TextView) root.findViewById(R.id.textView2);
        tvMensagem = (TextView) root.findViewById(R.id.textView4);
        etRaio = (EditText) root.findViewById(R.id.editTextRaio);

        dvaMessage = new DeviceActionMessage();

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());



        if(sharedPrefs.contains("switchState")){
            switchFiltros.setChecked(sharedPrefs.getBoolean(SWITCH_STATE,false));
            switchState = sharedPrefs.getBoolean(SWITCH_STATE, false);
//
            if(switchState){
                tvRaio.setVisibility(View.VISIBLE);
                tvMensagem.setVisibility(View.VISIBLE);
                etRaio.setVisibility(View.VISIBLE);

                etRaio.setText(sharedPrefs.getString(RAIO_DIGITADO, " "));
            }
        }



        switchFiltros.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Toast.makeText(getActivity(), "The Switch is " + (isChecked ? "on" : "off"), Toast.LENGTH_SHORT).show();
                if(isChecked) {
                    //do stuff when Switch is ON
                    tvRaio.setVisibility(View.VISIBLE);
                    tvMensagem.setVisibility(View.VISIBLE);
                    etRaio.setVisibility(View.VISIBLE);
                    sharedPrefs.edit().putBoolean(SWITCH_STATE, true).commit();
                    new UpdateContextTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                } else {
                    //do stuff when Switch if OFF
                    tvRaio.setVisibility(View.INVISIBLE);
                    tvMensagem.setVisibility(View.INVISIBLE);
                    etRaio.setVisibility(View.INVISIBLE);
                    sharedPrefs.edit().putBoolean(SWITCH_STATE, false).commit();
                }
            }
        });

        etRaio.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {

//                Toast.makeText(getActivity(), "texto:" + mEdit.toString(), Toast.LENGTH_SHORT).show();

                sharedPrefs.edit().putString(RAIO_DIGITADO, mEdit.toString()).commit();

                if(mEdit.length() != 0){
                raioDigitado = Integer.parseInt(mEdit.toString());




                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });



//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    public static int getRaio(){
        return Integer.parseInt(sharedPrefs.getString(RAIO_DIGITADO, " "));
    }


    private class UpdateContextTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Utils.sendBroadcast(new ArrayList<String>());
            Long currentTime = System.currentTimeMillis();
            new UDP_Listener().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, currentTime);
            return null;
        }
    }

    private class UDP_Listener extends AsyncTask<Long, String, Long>{
        @Override
        protected Long doInBackground(Long... inputs) {
            int server_port = 6789;
            byte[] message = new byte[1024];
            try{
                DatagramPacket p = new DatagramPacket(message, message.length);
                DatagramSocket s = new DatagramSocket(server_port);
                s.receive(p);

                String clientMessage = new String(p.getData(), 0, p.getLength());
                String result = "IP " + p.getAddress().toString() + " sent: " + clientMessage;
                System.out.println(result);

                s.close();
                publishProgress(result);
            }catch(Exception e){
                Log.d("UDP_Listener","Error: " + e.toString());
            }
            return System.currentTimeMillis() - inputs[0];
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            Toast.makeText(getActivity(), progress[0], Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Long elapsedTime) {
            super.onPostExecute(elapsedTime);
            Toast.makeText(getActivity(), elapsedTime.toString(), Toast.LENGTH_LONG).show();
            System.out.println("Elapsed Time: " + elapsedTime);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //this.myContext = context;
    }



}