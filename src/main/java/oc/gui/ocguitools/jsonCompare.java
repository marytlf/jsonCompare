package oc.gui.ocguitools;

import org.json.JSONArray;
import org.json.JSONObject;


public class jsonCompare {
    
	private static boolean flagFile;

	
    public jsonCompare(){
		flagFile = false;
	}

	public String getIdName(Object source_index_1){

		JSONObject jsObj_1 = new JSONObject(source_index_1.toString());

		for (String id: jsObj_1.keySet()){
			String idAux = id.toLowerCase();
			if (idAux.contains("id")){
				//System.out.println("Id name exist.");
				return id;
			}
		};


		return null;
	}


	public String getIdValue(Object source_index_1){

		JSONObject jsObj_1 = new JSONObject(source_index_1.toString());

		for (String id: jsObj_1.keySet()){
			String idAux = id.toLowerCase();
			if (idAux.contains("id")){
				//System.out.println("Id name exist.");
				String value = jsObj_1.optString(id);
				//System.out.println("Id:: "+value);
				return value;
			}
		};


		return null;
	}

    public boolean equalsValues(Object source_index_1, Object source_index_2, String attribute){

        JSONObject jsObj_1 = new JSONObject(source_index_1.toString());
        JSONObject jsObj_2 = new JSONObject(source_index_2.toString());
		

        //System.out.println(" attribute >> "+attribute+ " value_1 :: "+jsObj_1.get(attribute)+" value_2 :: "+jsObj_2.get(attribute));

        if (jsObj_1.get(attribute).equals(jsObj_2.get(attribute))){
        	return true;
        }
        return false;
    }

	public boolean equalsAllValues(Object source_index_1, Object source_index_2){

        JSONObject jsObj_1 = new JSONObject(source_index_1.toString());
        JSONObject jsObj_2 = new JSONObject(source_index_2.toString());

        //System.out.println(" length_1 :: "+jsObj_1.keySet()+" length_2 :: "+jsObj_2.keySet());

		if (jsObj_1.keySet().size() != jsObj_2.keySet().size()){
			System.out.println("Missing attribute");
			System.out.println("File 1 have: \n"+ jsObj_1.keySet() + "\nFile 2 have: \n"+jsObj_2.keySet());
			return false;
		}

		for (String keys :  jsObj_1.keySet()){

			//System.out.println("Keys >> "+keys) ;
			String aux_1 = jsObj_1.optString(keys);
			String aux_2 = jsObj_2.optString(keys);

			//System.out.println("1 == Value " +aux_1+ " and " +aux_2);
			if (!aux_1.toString().equals(aux_2.toString())){
				
				if (aux_1.toString().isEmpty()){
					System.out.println("Value " +aux_1+ "don't exist in the second file.");
				}
				if (aux_2.toString().isEmpty()){
					System.out.println("Value " +aux_2+ "don't exist in the first file.");
				}
				System.out.println("Value " +aux_1+ " and " +aux_2+ " is not the same in both files.");
				
			}else{
				System.out.println("Value " +aux_1+ " and " +aux_2+ " is the same in both files.");
			}
		}


        return false;
    }


    public void compareValues(JSONArray source_1, JSONArray source_2, String searchValue){		
		
        source_1.forEach( (value_1) -> {
            //System.out.println("value :: "+searchValue+ " source_1 ::" + value);
			String idName = getIdName(value_1);
			
			//System.out.println("Attribute Id Name: "+idName);
			if (!idName.equals(null)){
				source_2.forEach( (value_2) -> {
					//System.out.println(" source_1 ::" + value_2);
					if(equalsValues(value_1, value_2, idName)){
						System.out.println(" value ::" + getIdValue(value_1));
						equalsAllValues(value_1, value_2);
					}else{
						flagFile=true;	
						return;
					}
				});
				
				System.out.println("Element with id :: "+getIdValue(value_1)+" not found in the second file.");
			
				
			}
			
        } );
		if(flagFile){
			System.out.println("Different files");
		}
    }
}
