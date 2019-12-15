package great.android.cmu.ubiapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import great.android.cmu.ubiapp.model.Device;

public class ExpandableListDataPump {

    static List<Device> coapDevicesList = new ArrayList<>();

    public static void setDevicesList(Device device){
        coapDevicesList.add(device);
        //  System.out.println("device que entrou no expandable" + device);
        //  System.out.println("setou dados");
    }

    public static void setDevicesList(List<Device> listOfDevices){
        coapDevicesList.addAll(listOfDevices);
    }


    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        List<String> myDevices = new ArrayList<String>();
        ArrayList<String> environments = new ArrayList<>();
        for(int i = 0; i < coapDevicesList.size(); i++) {
            Device device = coapDevicesList.get(i);
            String env = device.getContext().get("env");
            String devIP = device.getIp();
            if(env != null && devIP != null){
                String devName = device.getResourceType().replaceAll("\"", "");
                myDevices.add(env + " - " + devName + " - " + devIP);
                if(!environments.contains(env)){
                    environments.add(env);
                }
            }
        }

        Collections.sort(myDevices);

        System.out.println(environments);
        System.out.println(myDevices);

        expandableListDetail.put("GENERAL DEVICES", myDevices);
        expandableListDetail.put("MY ENVIRONMENTS", environments);
        return expandableListDetail;
    }
}