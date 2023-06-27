package oc.gui.dumpJSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import oc.gui.ocguitools.*;


public class jJsonCalls {

    
    public static void main(String[] args) {
        OCObjectMap ocMap = new OCObjectMap();
        JsonReader jsonObj = new JsonReader();
        JSONObject jsonObjReturn_1 = jsonObj.getJson("offer_100506_copy.json");
        JSONArray jsonArray_1 = jsonObjReturn_1.optJSONArray("content");

        Map <String, OCObject> ocObjArray;
        ocObjArray = ocMap.injectJSONMap(jsonObjReturn_1,jsonArray_1); 

        //jsonObj.printMap(ocObjArray);
        
        
    }
}
