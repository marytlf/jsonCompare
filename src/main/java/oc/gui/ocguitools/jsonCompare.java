package oc.gui.ocguitools;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class jsonCompare {
    
	private static boolean flagFile;

	public JSONObject returnJSONOject(Object source_index_1){

		JSONObject jsObj_1 = null;
		JSONArray jsObj_2;
		try {
			if(isValid(source_index_1.toString())){
				jsObj_1 = new JSONObject(source_index_1.toString());
			}
			//System.out.println("alooo 1\n\n"+jsObj_1.toString());
		} catch (Exception e) {
			// TODO: handle exception
			if(isValid(source_index_1.toString())){
				jsObj_2 = new JSONArray(source_index_1.toString());
				//System.out.println("alooo 2\n\n"+jsObj_2.toString());
				//System.out.println("alooo 2\n"+jsObj_2.length());
				if (jsObj_2.length() > 0){
					//System.out.println("alooo 3\n"+jsObj_2.length());
					jsObj_1 = jsObj_2.getJSONObject(0);
					//System.out.println("alooo depois\n"+jsObj_1.toString());
					
				}
			}
		}

		return jsObj_1;
	}
	
    public jsonCompare(){
		flagFile = false;
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

	public String getIdName(Object source_index_1){
		JSONObject jsObj_1 = this.returnJSONOject(source_index_1);
		//System.out.println("object >> "+jsObj_1.toString());
		if(jsObj_1 != null){
			for (String id: jsObj_1.keySet()){
				//System.out.println("func getidname >> id :: "+id);
				String idAux = id.toLowerCase();
				if (idAux.contains("id")){
					//System.out.println("Id name exist. Id :: " +id);
					return id;
				}
			};	
		}
			
		return null;
	}

	public String getIdName(String source_index_1){
		//JSONObject jsObj_1 = this.returnJSONOject(source_index_1);
		System.out.println("string >> "+source_index_1.toString());
		String idAux = source_index_1;
		if (idAux.contains("Id")){
			//System.out.println("Id name exist. Id :: " +idAux);
			return idAux;
		}

		return null;
	}

	public String getIdValue(Object source_index_1){

		JSONObject jsObj_1 = this.returnJSONOject(source_index_1);

		if (jsObj_1 != null){
			for (String id: jsObj_1.keySet()){
				String idAux = id.toLowerCase();
				if (idAux.contains("id")){
					//System.out.println("Id name exist.");
					String value = jsObj_1.optString(id);
					//System.out.println("Id:: "+value);
					return value;
				}
			};
		}



		return null;
	}

	
    public boolean equalsValues(Object source_index_1, Object source_index_2, String attribute){

		JSONObject jsObj_1 = this.returnJSONOject(source_index_1);
		JSONObject jsObj_2 = this.returnJSONOject(source_index_2);

        //System.out.println(" attribute >> "+attribute);
		//System.out.println(" len1 >> "+jsObj_1.length());
		//System.out.println(" len2 >> "+jsObj_2.length());
		if (jsObj_1 != null && jsObj_2 != null){
			try {
				System.out.println("size1 :: "+jsObj_1.length());
				System.out.println("size2 :: "+jsObj_2.length());
				if (jsObj_1.optString(attribute) == jsObj_2.optString(attribute)){		
					System.out.println("att dentro if:: "+attribute);
					if (jsObj_1.get(attribute) == jsObj_2.get(attribute)){
						System.out.println("Equals Attribute :: "+attribute);
						System.out.println("Equals1 :: "+jsObj_1.get(attribute));
						System.out.println("Equals2 :: "+jsObj_2.get(attribute));
						return true;
					}
				}
			} catch (Exception e) {
				System.out.println("NOT Equals exception:: "+jsObj_1.toString());
				// TODO: handle exception
				return false;
			}
			
		}

        return false;
    }

	public void equalsAllValues(Object source_index_1, Object source_index_2){

        JSONObject jsObj_1 = this.returnJSONOject(source_index_1);
        JSONObject jsObj_2 = this.returnJSONOject(source_index_2);

        //System.out.println(" length_1 :: "+jsObj_1.keySet()+" length_2 :: "+jsObj_2.keySet());
		
		if (jsObj_1 != null && jsObj_2 != null){
			
			if (jsObj_1.keySet().size() != jsObj_2.keySet().size()){
				System.out.println("-------------------------------------------------------------------------------------");
				System.out.println("Missing JSON attribute.\n");
				
				System.out.println("Attributes from File 1: \n"+ jsObj_1.keySet() + "\nAttributes from File 2: \n"+jsObj_2.keySet());
				System.out.println("-------------------------------------------------------------------------------------");
				
				//return false;
			}
			
	
	
			for (String keys :  jsObj_1.keySet()){

				String aux_1 = jsObj_1.optString(keys);
				String aux_2 = jsObj_2.optString(keys);
	
				if(this.isValid(aux_1) && this.isValid(aux_2) ){
					this.equalsAllValues(jsObj_1.optString(keys), jsObj_2.optString(keys));
				}else if(this.isValid(aux_1) && !this.isValid(aux_2)) {
					System.out.println("Value [" +aux_1+ "] not found in the second file.");
					System.out.println("-------------------------------------------------------------------------------------");
				}else if(!this.isValid(aux_1) && this.isValid(aux_2)){
					System.out.println("Value [" +aux_2+ "] not found in the first file.");
					System.out.println("-------------------------------------------------------------------------------------");
				}
	
	
				//System.out.println("1 == Value " +aux_1+ " and " +aux_2);
				if (!aux_1.toString().equals(aux_2.toString())){
					
					/*/if (aux_1.toString().isEmpty()){
						System.out.println("Value [" +aux_2+ "] added.");
					}
					if (aux_2.toString().isEmpty()){
						System.out.println("Value [" +aux_1+ "] removed.");
					}/*/
					System.out.println("-------------------------------------------------------------------------------------");
	
					System.out.println("Value from file 1: [" +aux_1+ "]\nValue from file 2: [" +aux_2+ "]\nDifferent values.");
					
					System.out.println("-------------------------------------------------------------------------------------");
					
				}
	
			}
	
		}

    }


    public void compareSources(JSONArray source_1, JSONArray source_2, String searchIdValue){		
		
        source_1.forEach( (value_1) -> {
            //System.out.println("value :: "+searchValue+ " source_1 ::" + value_1);
			
			//String idName = getIdValue(value_1);
			//System.out.println("Attribute Id Name: "+value_1.toString());
			
			source_2.forEach( (value_2) -> {
				//System.out.println(" source_1 ::" + value_2);
				String idName = getIdName(value_1);
				String idNameAux = null;
				if (idName != null){
					idNameAux = idName;
				}else{
					idName = idNameAux;
				}
				if (idName != null){
					if(equalsValues(value_1, value_2, idName)){
						equalsAllValues(value_1, value_2);
						System.out.println(" value ::" + value_1);
						flagFile=true;
					}
				}
			});
			//System.out.println("Element with id :: "+getIdValue(value_1)+" not found in the second file.");				
		
			
        } );
		//if(flagFile){
		//	System.out.println("Different files");
		//}
    }
	
}
