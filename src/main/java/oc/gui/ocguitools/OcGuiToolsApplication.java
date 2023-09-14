package oc.gui.ocguitools;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class OcGuiToolsApplication {
	
	public static void main(String[] args) {
		//SpringApplication.run(OcGuiToolsApplication.class, args);

		JsonReader jsonObj = new JsonReader();
		jsonCompare jsonCmp = new jsonCompare();
		String idName = null;
		String idaux = null;

		//JSONObject jsonObjReturn_1 = jsonObj.getJson("all_condition_dev1.json");
		//JSONObject jsonObjReturn_1 = jsonObj.getJson("offers.json");
		JSONArray jsonArray_1 = jsonObj.getJsonArray("offers.json");


		//JSONObject jsonObjReturn_2 = jsonObj.getJson("tiers_prod.json");
		//JSONArray jsonArray_2 = jsonObjReturn_2.optJSONArray("content");

		//JSONObject jsonObjReturn_2 = jsonObj.getJson("offers_prod_sorted.json");
		JSONArray jsonArray_2 = jsonObj.getJsonArray("offers copy.json");
		
/*/
		JSONObject jsonObjReturn_1 = jsonObj.getJson("18052300104_threshold.json");
		JSONArray jsonArray_1 = null;
		JSONObject jsonObjReturn_2 = jsonObj.getJson("18052300104_threshold_copy.json");
		JSONArray jsonArray_2 = null;
	
		//JSONObject jsonObjReturn_1 = jsonObj.getJson("tiers_prod.json");
		if (jsonObjReturn_1.optString("content").equals("")){
			//System.out.println("Error :: "+jsonObjReturn_1.toString());
			//JSONArray jsonArray_1 = jsonObjReturn_1.optJSONArray("content");
			jsonArray_1 = new JSONArray();
			for (String key :  jsonObjReturn_1.keySet()){
				
				if(jsonCmp.isValid(jsonObjReturn_1.toString()) && jsonCmp.isValid(jsonObjReturn_1.get(key).toString()) ){
					
					if (jsonObjReturn_1.optJSONArray(key) != null){
						//equals does not work here, idk why
						jsonArray_1.put(jsonObjReturn_1.getJSONArray(key));
						System.out.println("File 1 1111 :: "+key);
					}else{
						System.out.println("File 1 2222 :: "+key);		
						jsonArray_1.put(jsonObjReturn_1.get(key));
					}
				}else{
					
					if (jsonObjReturn_1.opt(key) != null ){
						System.out.println("File 1 3333 :: "+key);
						jsonArray_1.put(jsonObjReturn_1.get(key));
						
					}

				}
			}
		}

		if (jsonObjReturn_2.optString("content").equals("")){
			//System.out.println("Error :: "+jsonObjReturn_1.toString());
			//JSONArray jsonArray_1 = jsonObjReturn_1.optJSONArray("content");
			jsonArray_2 = new JSONArray();
			for (String key :  jsonObjReturn_2.keySet()){
				
				if(jsonCmp.isValid(jsonObjReturn_2.toString()) && jsonCmp.isValid(jsonObjReturn_2.get(key).toString()) ){
					
					if (jsonObjReturn_2.optJSONArray(key) != null){
						//equals does not work here, idk why
						jsonArray_2.put(jsonObjReturn_2.getJSONArray(key));
						System.out.println("File 2 1111 :: "+key);
					}else{
						System.out.println("File 2 2222 :: "+key);		
						jsonArray_2.put(jsonObjReturn_2.get(key));
					}
				}else{
					
					if (jsonObjReturn_2.opt(key) != null ){
						System.out.println("File 2 3333 :: "+key);
						jsonArray_2.put(jsonObjReturn_2.get(key));
					}

				}
			}
		}
/*/	
		jsonCmp.compareSources(jsonArray_1, jsonArray_2);
		//jsonCmp.compareSources(jsonObjReturn_1, jsonObjReturn_2, "Id");
		//jsonCmp.compareSources(jsonObjReturn_1, jsonObjReturn_2);
		

		//jsonCmp.readJSONObject(jsonArray_1, jsonArray_1);
	}

	

}
