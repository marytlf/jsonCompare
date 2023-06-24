package oc.gui.dumpJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import oc.gui.ocguitools.jsonCompare;

public class OCObjectMap {
    
    OCObject ocObj;
    ArrayList <Map> arrayAttributes = new ArrayList<Map>();
    Map <String, OCObject> ocObj_mapa = new HashMap<String, OCObject>();

    public OCObjectMap(){
    }
    public boolean isValid(String json) {
		try {
			new JSONObject(json);
		} catch (JSONException e) {
			try {
				new JSONArray(json);
			} catch (JSONException ne) {
				return false;
			}
		}
		return true;
	}

    public void setOnMap(String type, String key, String value){

        //type = name of attribute (example entitlements, tiers, customAttributeValues)
        //key = name of attribute inside json object
        //value = value of the attribute inside json object
        ocObj = new OCObject();
        ocObj.setType(type);
        ocObj.setName(key);
        ocObj.setValue(value);

        if (!ocObj_mapa.containsValue(ocObj)){
            ocObj_mapa.put(type, ocObj);
            //System.out.println("Type :: "+type);
            //System.out.println("value :: "+value);
            arrayAttributes.add(ocObj_mapa);
            System.out.println("Mapa:: "+ocObj.getType());
        }
        

    }

    public OCObject injectJSONMap(JSONObject jsonObjReturn_1, JSONArray jsonArray_1){
       OCObject ocObj = new OCObject();

        if (jsonObjReturn_1.optString("content").equals("")){
			//System.out.println("Error :: "+jsonObjReturn_1.toString());
			//JSONArray jsonArray_1 = jsonObjReturn_1.optJSONArray("content");
			//jsonArray_1 = new JSONArray();
		}

        for (String key :  jsonObjReturn_1.keySet()){
				
            //System.out.println("File key :: "+key);
            //System.out.println("Length :: "+jsonObjReturn_1.optJSONObject(key)); //quando tem o objeto iniciado com {} nao []
            //System.out.println("Length :: "+jsonObjReturn_1.optJSONArray(key)); //quando tem o objeto iniciado com [] nao {}

            if (jsonObjReturn_1.optJSONObject(key) != null){
                //System.out.println("File key :: "+key);
                setOnMap(key, key, jsonObjReturn_1.optJSONObject(key).toString());
                if (this.isValid(jsonObjReturn_1.optJSONObject(key).toString())){
                    //System.out.println("ELSE TRUE >> " + jsonObjReturn_1.get(key));
                    this.injectJSONMap(jsonObjReturn_1.optJSONObject(key), jsonArray_1);
                }
            }else{
                if (jsonObjReturn_1.optJSONArray(key) != null){
                    setOnMap(key, key, jsonObjReturn_1.optJSONArray(key).toString());
                    if (this.isValid(jsonObjReturn_1.optJSONArray(key).toString())){
                        //System.out.println("ELSE TRUE >> " + jsonObjReturn_1.optJSONArray(key));
                        JSONArray aux = new JSONArray(jsonObjReturn_1.optJSONArray(key));
                        for (int i=0 ; i < aux.length(); i++){
                            this.injectJSONMap(aux.optJSONObject(i), jsonArray_1);
                            System.out.println("ELSE TRUE >> " + aux.optJSONObject(i));
                        }
                        
                    }
                }else{
                    //System.out.println("ELSE >> " + jsonObjReturn_1.get(key));
                    setOnMap(key, key, jsonObjReturn_1.get(key).toString());
                }
                
            }
        }
        return ocObj;
    }
}
