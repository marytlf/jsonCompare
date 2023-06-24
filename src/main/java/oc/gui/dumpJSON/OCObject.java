package oc.gui.dumpJSON;

import java.util.ArrayList;

import org.json.JSONArray;

public class OCObject {
    
    String id, name, description;
    String type;
    
    String value;
    ArrayList<JSONArray> value_JArray;
    ArrayList<JSONArray> value_jObject;
    JSONArray jArray;

    public OCObject(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<JSONArray> getValue_JArray() {
        return value_JArray;
    }

    public void setValue_JArray(ArrayList<JSONArray> value_JArray) {
        this.value_JArray = value_JArray;
    }

    public ArrayList<JSONArray> getValue_jObject() {
        return value_jObject;
    }

    public void setValue_jObject(ArrayList<JSONArray> value_jObject) {
        this.value_jObject = value_jObject;
    }

    public JSONArray getjArray() {
        return jArray;
    }

    public void setjArray(JSONArray jArray) {
        this.jArray = jArray;
    }

}
