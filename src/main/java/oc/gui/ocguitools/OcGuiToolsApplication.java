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

		JSONObject jsonObjReturn_1 = jsonObj.getJson("tiers_qa.json");
		//JSONObject jsonObjReturn_1 = jsonObj.getJson("tiers_prod.json");
		JSONArray jsonArray_1 = jsonObjReturn_1.optJSONArray("content");


		JSONObject jsonObjReturn_2 = jsonObj.getJson("tiers_prod.json");
		JSONArray jsonArray_2 = jsonObjReturn_2.optJSONArray("content");


		//System.out.println("tier >> "+ jsonArray_1);
		jsonCmp.compareValues(jsonArray_1, jsonArray_2, "Id");


	}

	

}
