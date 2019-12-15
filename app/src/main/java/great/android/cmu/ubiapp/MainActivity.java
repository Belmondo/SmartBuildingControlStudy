package great.android.cmu.ubiapp;


import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import great.android.cmu.ubiapp.adaptations.Batt50Adapt;
import great.android.cmu.ubiapp.model.Device;
import great.android.cmu.ubiapp.rules.flood_rules.Batt50Rule;
import great.android.cmu.ubiapp.rules.flood_rules.Hour12Rule;
import great.android.cmu.ubiapp.rules.flood_rules.Hour18Rule;
import great.android.cmu.ubiapp.rules.flood_rules.Temp14Rule;
import great.android.cmu.ubiapp.rules.flood_rules.Temp22Rule;
import great.android.cmu.ubiapp.rules.flood_rules.Temp30Rule;
import great.android.cmu.ubiapp.workflow.MainWorkflow;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Device> devices;
    private Date lastDeviceUpdade;


    Batt50Rule batt50Rule = new Batt50Rule(getApplicationContext());
    Hour12Rule hour12Rule = new Hour12Rule(getApplicationContext());
    Hour18Rule hour18Rule = new Hour18Rule(getApplicationContext());
    Temp14Rule temp14Rule = new Temp14Rule(getApplicationContext());
    Temp22Rule temp22Rule = new Temp22Rule(getApplicationContext());
    Temp30Rule temp30Rule = new Temp30Rule(getApplicationContext());







    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_settings)
                .build();//R.id.navigation_historic,
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        MainWorkflow.receive(batt50Rule);
        MainWorkflow.receive(hour12Rule);
        MainWorkflow.receive(hour18Rule);
        MainWorkflow.receive(temp14Rule);
        MainWorkflow.receive(temp22Rule);
        MainWorkflow.receive(temp30Rule);



    }

    public List<Device> getDevices(){
        return this.devices;
    }

    public void setDevices(ArrayList<Device> devices){
        this.devices = devices;
    }

    public Date getLastDeviceUpdade() {
        return lastDeviceUpdade;
    }

    public void setLastDeviceUpdade(Date lastDeviceUpdade) {
        this.lastDeviceUpdade = lastDeviceUpdade;
    }
}
