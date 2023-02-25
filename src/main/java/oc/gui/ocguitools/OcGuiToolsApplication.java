package oc.gui.ocguitools;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class OcGuiToolsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OcGuiToolsApplication.class, args);

		JsonReader jsonObj = new JsonReader();
		jsonCompare jsonCmp = new jsonCompare();
		String idName = null;
		String idaux = null;

		//JSONObject jsonObjReturn_1 = jsonObj.getJson("tiers_qa.json");
		//JSONObject jsonObjReturn_1 = jsonObj.getJson("tiers_prod.json");
		//JSONArray jsonArray_1 = jsonObjReturn_1.optJSONArray("content");


		//JSONObject jsonObjReturn_2 = jsonObj.getJson("tiers_prod.json");
		//JSONArray jsonArray_2 = jsonObjReturn_2.optJSONArray("content");

		

		//JSONObject jsonObjReturn_3 = jsonObj.getJson("18052300104_entitlements.json");
		//JSONObject jsonObjReturn_1 = jsonObj.getJson("tiers_prod.json");
		//JSONArray jsonArray_3 = jsonObjReturn_3.optJSONArray("content");


		//JSONObject jsonObjReturn_4 = jsonObj.getJson("18052300104_entitlements_copy.json");
		//JSONArray jsonArray_4 = jsonObjReturn_4.optJSONArray("content");
		

		JSONObject jsonObjReturn_3 = jsonObj.getJson("offer_100506.json");
		JSONArray jsonArray_3 = null;
		JSONObject jsonObjReturn_4 = jsonObj.getJson("offer_100506_copy.json");
		JSONArray jsonArray_4 = null;
		
		//JSONObject jsonObjReturn_1 = jsonObj.getJson("tiers_prod.json");
		if (jsonObjReturn_3.optString("content").equals("")){
			//System.out.println("Error :: "+jsonObjReturn_3.toString());
			//JSONArray jsonArray_3 = jsonObjReturn_3.optJSONArray("content");
			jsonArray_3 = new JSONArray();
			for (String key :  jsonObjReturn_3.keySet()){
				
				if(jsonCmp.isValid(jsonObjReturn_3.toString()) && jsonCmp.isValid(jsonObjReturn_3.get(key).toString()) ){
					
					if (jsonObjReturn_3.optJSONArray(key) != null){
						//equals does not work here, idk why
						jsonArray_3.put(jsonObjReturn_3.getJSONArray(key));
						System.out.println(" 1111 :: "+key);
					}else{
						System.out.println(" 2222 :: "+key);		
						jsonArray_3.put(jsonObjReturn_3.get(key));
					}
					


				}else{
					
					if (jsonObjReturn_3.opt(key) != null ){
						System.out.println(" 3333 :: "+key);
						jsonArray_3.put(jsonObjReturn_3.get(key));
						
					}

				}
				//JSONArray js = jsonObjReturn_3.getJSONArray(key);
				//JSONArray js_2 = jsonObjReturn_3.getJSONObject(key);
				//jsonArray_3.put(js);
				
				
				
				//System.out.println(">>> "+jsonArray_3.toString());
			}
		}

		//jsonCmp.compareSources(jsonArray_1, jsonArray_2, "Id");
		jsonCmp.compareSources(jsonArray_3, jsonArray_3, "Id");

	}

	

}
