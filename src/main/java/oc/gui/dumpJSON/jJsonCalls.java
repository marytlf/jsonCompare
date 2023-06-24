package oc.gui.dumpJSON;
import org.json.JSONArray;
import org.json.JSONObject;

import oc.gui.ocguitools.*;


public class jJsonCalls {
    public static void main(String[] args) {
        OCObjectMap ocMap = new OCObjectMap();
        JsonReader jsonObj = new JsonReader();
        JSONObject jsonObjReturn_1 = jsonObj.getJson("tiers_prod_2.json");
        JSONArray jsonArray_1 = jsonObjReturn_1.optJSONArray("content");

        OCObject ocObj;
        ocObj = ocMap.injectJSONMap(jsonObjReturn_1,jsonArray_1); 
    }
}
