package oc.gui.ocguitools;

import org.json.JSONObject;
import org.json.JSONArray;
import oc.gui.dumpJSON.OCObject;

import java.io.BufferedReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;


public class JsonReader {

    
    public JsonReader(){};

    public void printMap(Map <String, OCObject> ocObjMapa){

        for (Object key: ocObjMapa.keySet()){
             OCObject oc = (OCObject) ocObjMapa.get(key.toString());
             //System.out.println("key value oc  >> "+oc.getValue());
        }

        //ocObjMapa.stream().forEach(val -> {;
            //System.out.println("value >> "+val.keySet());
        //    for (Object key : val.keySet()){
                //System.out.println("key value >> "+key.toString());
        //        OCObject oc = (OCObject) val.get(key.toString());
        //        System.out.println("key value oc  >> "+oc.getValue());
        //    }
        //});
    }

    public String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;
        
        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        return content.toString();
    }

    public JSONObject getJson(String fileName){
        
        //Path readerJsonQA = Paths.get(".", "src", "test", "resources", "tiers_qa.json");
        //Path readerJsonProd = Paths.get(".", "src", "test", "resources", "tiers_prod.json");
    
        try {
            Path readerJson = Paths.get(".", "src", "test", "resources", fileName);
            
            BufferedReader reader = Files.newBufferedReader(readerJson);

            String line = readAllLines(reader);
    
            JSONObject jsonObj = new JSONObject(line);
            System.out.println(">>"+readerJson.toAbsolutePath());
           
            return jsonObj;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("File not found.");
            System.err.println("Exception :: "+e.getMessage());
            return null;
        }
    }

    public JSONArray getJsonArray(String fileName){
        
        //Path readerJsonQA = Paths.get(".", "src", "test", "resources", "tiers_qa.json");
        //Path readerJsonProd = Paths.get(".", "src", "test", "resources", "tiers_prod.json");
    
        try {
            Path readerJson = Paths.get(".", "src", "test", "resources", fileName);
            
            BufferedReader reader = Files.newBufferedReader(readerJson);
            String line = readAllLines(reader);
            JSONArray jsArray = new JSONArray(line.toString());
            System.out.println(">>"+readerJson.toAbsolutePath());
            return jsArray;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("File not found.");
            System.err.println("Exception :: "+e.getMessage());
            return null;
        }
    }
    
}
