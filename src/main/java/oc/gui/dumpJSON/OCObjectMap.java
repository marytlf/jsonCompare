package oc.gui.dumpJSON;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jayway.jsonpath.WriteContext;
import com.opencsv.CSVWriter;

import oc.gui.ocguitools.jsonCompare;
import oc.gui.writeCSV.WriteCSV;

public class OCObjectMap {
    
    OCObject ocObj;
    ArrayList <Map> arrayAttributes = new ArrayList<Map>();
    //Map <String, OCObject> ocObj_mapa = new HashMap<String, OCObject>();
    Map <String, OCObject> ocObj_mapa = new LinkedHashMap<String, OCObject>();
    String header="";
    ArrayList <String> header_test= new ArrayList<String>();
    String[] lines;
    Integer headerNum = 0;

    public OCObjectMap(){}
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

    public void getColumnsName(String header){
        System.out.println("header >> "+header);
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

            ocObj_mapa.put(value, ocObj);
            
            //System.out.println("Type :: "+type);
            //System.out.println("value :: "+value);
            //System.out.println("KEY:: "+ocObj.getName());
            //System.out.println("Value:: "+ocObj.getValue());
            

            //arrayAttributes.add(ocObj_mapa);
            //System.out.println("Type:: "+ocObj.getType());
        }else{
            System.out.println("ELSE Type:: "+ocObj.getType());
        }
        

    }

    public Map <String, OCObject> injectJSONMap(JSONObject jsonObjReturn_1, JSONArray jsonArray_1){
       OCObject ocObj = new OCObject();
       WriteCSV writecsv = new WriteCSV();
       CSVObject csvObj = new CSVObject();
       List<String[]> data = new ArrayList<String[]>();
       //CSVWriter writerHandler = writecsv.writeCSV("output_csv.csv");
       FileWriter writerHandler = writecsv.writeCSV_FileWriter("output_csv.csv");

        for (String key :  jsonObjReturn_1.keySet()){
			
            csvObj.setColumnName(csvObj.convertStringToArrayString(key));
            boolean contains = data.stream().anyMatch(val -> val.toString().equals(key));
            if (!contains){
                data.add(csvObj.getColumnName());
            }
            
            
            if (jsonObjReturn_1.optJSONObject(key) != null){
                //System.out.println("File key :: "+key);
                //setOnMap(key, key, jsonObjReturn_1.optJSONObject(key).toString());
                if (this.isValid(jsonObjReturn_1.optJSONObject(key).toString())){
                    //System.out.println("ELSE TRUE >> " + jsonObjReturn_1.get(key));
                    this.injectJSONMap(jsonObjReturn_1.optJSONObject(key), jsonArray_1);
                    System.out.println("===============================:: ");
                }
            }else{
                //System.out.println("File key :: "+key);
                if (jsonObjReturn_1.optJSONArray(key) != null){
                    
                    if (this.isValid(jsonObjReturn_1.optJSONArray(key).toString())){
                        //System.out.println("ELSE TRUE >> " + jsonObjReturn_1.optJSONArray(key));
                        JSONArray aux = new JSONArray(jsonObjReturn_1.optJSONArray(key));
                        for (int i=0 ; i < aux.length(); i++){
                            this.injectJSONMap(aux.optJSONObject(i), jsonArray_1);
                            //System.out.println("ELSE TRUE >> " + aux.optJSONObject(i));
                            System.out.println("===============================:: ");
                        }
                        
                    }
                    //setOnMap(key, key, jsonObjReturn_1.optJSONArray(key).toString());
                }else{
                    //System.out.println("ELSE >> " + jsonObjReturn_1.get(key));
                    setOnMap(key, key, jsonObjReturn_1.get(key).toString());
                }
                
            }
        }

        //csvObj.setColumnName(csvObj.convertStringToArrayString(header));
        
        //writecsv.writeHeader(data, writerHandler);
        
        /*/ocObj_mapa.values().stream().forEachOrdered( val -> {try {
                writecsv.writeContent(val.getName(), val.getValue(), writerHandler);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }});
            //writecsv.writeHeaderOnce()
            //writecsv.writeContent(ocObj_mapa,writerHandler);
        /*/
        ocObj_mapa.values().stream().forEach( val -> {try {
                writecsv.writeContent(val.getName(), val.getValue(), writerHandler);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }});
            //writecsv.writeHeaderOnce()
            //writecsv.writeContent(ocObj_mapa,writerHandler);
        

        return ocObj_mapa;
    }

    public void test (String oi){
        System.out.println("oi = "+oi);
    }
    
}
