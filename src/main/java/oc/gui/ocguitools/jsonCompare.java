package oc.gui.ocguitools;

import java.util.Random;

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
		//System.out.println("string >> "+source_index_1.toString());
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

	public boolean equalsSubAtt(Object source_index_1, Object source_index_2, String attribute){

		Boolean flagEquals = false;
		System.out.println("try attribute :: "+attribute);
		JSONObject jsObj_1 = this.returnJSONOject(source_index_1.toString());
		JSONObject jsObj_2 = this.returnJSONOject(source_index_2.toString());

		for (String key : jsObj_1.keySet()){
			if (jsObj_1.keySet().size() == jsObj_2.keySet().size()){
				if (!jsObj_1.get(key).equals(null) && !jsObj_2.get(key).equals(null)){
					if (jsObj_1.get(key).equals(jsObj_2.get(key))){	
						
						flagEquals = true;
						//return true;
					}else{
						System.out.println("length :: "+jsObj_2.getJSONArray(key).length());
						if (jsObj_2.getJSONArray(key).length() > 0 && jsObj_1.getJSONArray(key).length() > 0){

							//System.out.println("Key [ "+key+" ] - Different Value [ "+jsObj_1.get(key)+" ]");
							//System.out.println("Key [ "+key+" ] - Different Value [ "+jsObj_2.get(key)+" ]");
							System.out.println("dentro do if ");
							JSONArray jsonArray_1 = jsObj_1.getJSONArray(key);
							JSONArray jsonArray_2 = jsObj_2.getJSONArray(key);
							this.equalsSubAtt(jsonArray_1.toString(), jsonArray_2.toString(), key);							

						}
						
						System.out.println("Key [ "+key+" ] - fora Different Value [ "+jsObj_2.get(key)+" ]");
						//return flagEquals;
					}
				}
			}else{
				return flagEquals;
			}	
		}
		


		return flagEquals;
	}

	
    public boolean equalsValues(Object source_index_1, Object source_index_2, String attribute){

		JSONObject jsObj_1 = this.returnJSONOject(source_index_1);
		JSONObject jsObj_2 = this.returnJSONOject(source_index_2);

        //System.out.println(" attribute >> "+attribute);
		//System.out.println(" len1 >> "+jsObj_1.length());
		//System.out.println(" len2 >> "+jsObj_2.length());
		if (jsObj_1 != null && jsObj_2 != null){
			try {
				//System.out.println(" 1 >> "+attribute);
				if (jsObj_1.optString(attribute) == jsObj_2.optString(attribute)){		
					//System.out.println(" 2>> "+attribute);
					if (isValid(jsObj_1.toString()) && isValid(jsObj_2.toString()) ){
						return equalsSubAtt(jsObj_1.toString(),jsObj_2.toString(),attribute);
					}else{
						//System.out.println("try NOT EQUALS :: ");
						return false;
					}
					
				}else{
					//System.out.println("O QUE TEM AQUI " + jsObj_1.toString());
					//System.out.println("O QUE TEM AQUI " + jsObj_2.toString());

					if (jsObj_1.toString().equals(jsObj_2.toString())){
						//System.out.println("É IGUALLL " + jsObj_1.toString());
						//System.out.println("É IGUALLL " + jsObj_2.toString());
						return equalsSubAtt(jsObj_1.toString(),jsObj_2.toString(),attribute);
						
					}
				}
			} catch (Exception e) {
				//System.out.println("NOT Equals exception:: "+jsObj_1.toString());
				
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

	//check for the same ID
	public boolean checkIDExists(Object source_index_1, Object source_index_2, String idName){

		JSONObject jsObj_1 = this.returnJSONOject(source_index_1);
        JSONObject jsObj_2 = this.returnJSONOject(source_index_2);

		try {
			if (jsObj_1 != null && jsObj_2 != null){
				//System.out.println("idname >> "+idName);
				if (this.isValid(source_index_1.toString()) && this.isValid(source_index_2.toString())){
					
					if (!jsObj_1.get(idName).equals(null) && !jsObj_2.get(idName).equals(null)){
						System.out.println("idname >> "+idName);
						System.out.println("NAO VAZIO ID >> "+jsObj_1.get(idName));
						System.out.println("NAO VAZIO ID >> "+jsObj_2.get(idName));
						return true;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("TODO CATCH");
			return false;
			// TODO: handle exception
		}
		

		return false;
	}


	public boolean isBoolean(Object obj){

		try {
			if (obj.toString().equals(false)){
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	public boolean checkIfEquals(Object source_index_1, Object source_index_2, String idName){
		JSONObject jsObj_1 = this.returnJSONOject(source_index_1);
        JSONObject jsObj_2 = this.returnJSONOject(source_index_2);

		System.out.println("teste keys");

		System.out.println("key size1 :: "+jsObj_1.keySet().size());
		System.out.println("key size2 :: "+jsObj_2.keySet().size());
		

		return false;
	}

	//this check if keys in json is the same or if is missing a key
	public boolean checkEqualsKeys(Object source_index_1, Object source_index_2){

		//System.out.println("source index 1 "+source_index_1);
		//System.out.println("source index 2 "+source_index_2);

		if (!source_index_1.equals(null) && !source_index_2.equals(null)){
			JSONObject jsObj_1 = this.returnJSONOject(source_index_1);
        	JSONObject jsObj_2 = this.returnJSONOject(source_index_2);

			if (jsObj_1 == null || jsObj_2 == null){
				return false;
			}

			if (jsObj_1.keySet().size() != jsObj_2.keySet().size()){
				System.out.println("TESTE SEM FALSE" );
				//System.out.println("Different Key set1 "+jsObj_1.keySet());
				//System.out.println("Different Key set2 "+jsObj_2.keySet());
				//return false;
			}
			
			if (jsObj_1.keySet().equals(jsObj_2.keySet())){
				System.out.println("keySet 1 " + jsObj_1.keySet());
				System.out.println("keySet 2 " + jsObj_2.keySet());
				return true;
			}else{
				for (String key_1 : jsObj_1.keySet()){
					for (String key_2 : jsObj_2.keySet()){
						//System.out.println("File 1 - Key [ "+key_1+" ]");
						//System.out.println("File 2 - Key [ "+key_2+" ]");
						if (jsObj_2.keySet().contains(key_1)){
							//this if is necessary since it can give a false positive, since id contains in some words (entId, offerId, id)
							if (!key_1.equals(key_2)){
								System.out.println("NOT SAME key :: key1 "+key_1);
								System.out.println("NOT SAME key :: key2 "+key_2);
								return false;
							}else{
								System.out.println("Same key :: key1 "+key_1);
								System.out.println("Same key :: key2 "+key_2);
							}
						}
					}
				}
				return false;
			}

		}

		return false;
	}


    public void compareSources_2(JSONArray source_1, JSONArray source_2, String searchIdValue){		
		
        source_1.forEach( (value_1) -> {
            //System.out.println("value :: "+searchValue+ " source_1 ::" + value_1);
			
			//String idName = getIdValue(value_1);
			//System.out.println("Attribute Id Name: "+value_1.toString());
			String idName = getIdName(value_1);

			//JSONArray attName_1 = (JSONArray)value_1;
			
			source_2.forEach( (value_2) -> {
				
				checkEqualsKeys(value_1,value_2);
			});
			
        } );
		
    }

	public void compareSources(JSONObject source_1, JSONObject source_2, String searchIdValue){		
		
		//String idName = getIdName(searchIdValue);

		for (String key_1 : source_1.keySet()){
			for (String key_2 : source_2.keySet()){

				if (key_1.equals(key_2)){
					//System.out.println("keys --> "+key_1);
					//System.out.println("keys2 --> "+key_2);


					//System.out.println("opt source1 --> "+source_1.optJSONObject(key_1));
					//System.out.println("opt source2 --> "+source_2.optJSONObject(key_2));
					//NAO MEXE NESSE CODIGO
					//valor direto ou sub json na base
					if(source_1.optJSONObject(key_1) == null && source_2.optJSONObject(key_2) == null ){
						//System.out.println("opt source1 --> "+source_1.optJSONObject(key_1));
						//System.out.println("opt source2 --> "+source_2.optJSONObject(key_2));
						//valor direto
						if (source_1.optJSONArray(key_1) == null && source_2.optJSONArray(key_2) == null){
							//System.out.println("array1 -> "+source_1.get(key_1));
							//System.out.println("array2 -> "+source_2.get(key_2));
							if (source_1.get(key_1).equals(source_2.get(key_2))){
								System.out.println("Same value.\n[ "+key_1+" ]=[ " + source_1.get(key_1)+" ] match with [ "+key_2 +" ]=[ "+source_2.get(key_2)+"]");
								System.out.println("-----------------------------------------------------------------------------------------------------------------------");
							}else{
								System.out.println("Different value. \n[ "+key_1+" ]=[ " + source_1.get(key_1)+" ] not match with [ "+key_2 +" ]=[ "+source_2.get(key_2)+"]");
								System.out.println("-----------------------------------------------------------------------------------------------------------------------");
							}
						}
						/*/ 
						else{
							//objeto json dentro 
							//System.out.println("opt array1 -> "+source_1.optJSONArray(key_1));
							//System.out.println("opt array2 -> "+source_2.optJSONArray(key_2));

							
							System.out.println("else array1 -> "+source_1.get(key_1));
							System.out.println("else array2 -> "+source_2.get(key_2));
							 
							//System.out.println("else array1 SIZE -> "+jsObj_1.length());
							//System.out.println("else array2 SIZE -> "+jsObj_2.length());


							if (source_1.length() > 1 && source_2.length() > 1 ){
								//JSONObject jsonObj_1 = returnJSONOject(jsObj_1.get(key_1));
								//JSONObject jsonObj_2 = returnJSONOject(jsObj_1.get(key_2));
								JSONArray jsObj_aux_1 = source_1.names();
								JSONArray jsObj_aux_2 = source_2.names();
								
								System.out.println("else JSONarray1 SIZE -> "+jsObj_aux_1.length());
								System.out.println("else JSONarray2 SIZE -> "+jsObj_aux_2.length());

								for (int j= 0 ; j < source_1.length(); j++){
									JSONArray aux = (JSONArray) source_1.get(key_1);
									//System.out.println("posicao "+j+"=="+aux.get(j));
								}
								for (int i = 0; i < jsObj_aux_1.length() ; i++ ){
									String keys = jsObj_aux_1.getString(i);

									System.out.println("KEYSSSSSSSSSS -> "+keys);
									
									//System.out.println("else JSONarray1 VAL -> "+String.valueOf(jsObj_1.getString(keys).toString()));
									//System.out.println("else JSONarray2 VAL -> "+jsObj_2.getString(keys).toString());
								
									//this.compareSources(jsObj_aux_1.getJSONObject(i),jsObj_aux_2.getJSONObject(i), searchIdValue);
								}
							}else{
								//this.compareSources(jsObj_aux_1,jsObj_aux_2, searchIdValue);
							}
							
						}
						/*/
						//System.out.println("size1 --> "+source_1.get(key_1));
						//System.out.println("size2 --> "+source_2.get(key_2));
					}else{
						//vetor json na base
						//System.out.println("DIFERENT size1 --> "+source_1.get(key_1));
						//System.out.println("DIFERENT size2 --> "+source_2.get(key_2));	
						this.compareSources(source_1.getJSONObject(key_1),source_2.getJSONObject(key_2), searchIdValue);
						
					}

								
					//System.out.println("size1 --> "+source_1.get(key_1));
					//System.out.println("size2 --> "+source_2.get(key_2));

					
				}
			}

		}
    }

	public void readJSONObject(JSONArray source_1, JSONArray source_2){
		
		JsonWriter jsonWriter = new JsonWriter();
		source_1.forEach( (value_1) -> {
			Random rand = new Random(); 
			// Setting the upper bound to generate the
			// random numbers in specific range
			int upperbound = 600;
			
			String nameFIle;
            nameFIle = "Condition_"+rand.nextInt(upperbound);
			
			//System.out.println(">>>"+value_1.toString());
			jsonWriter.writeJSONFile((JSONObject)value_1,nameFIle);
			
        } );
	}

	
}
