package oc.gui.ocguitools;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONObject;



public class JsonWriter {

    public JsonWriter(){
    }

    public void writeJSONFile(JSONObject source_1, String path){
		//System.out.println(">>>"+source_1.toString());
        Path writerJson = (Path) Paths.get(".", "src", "test", "resources","output", path);

		try (PrintWriter out = new PrintWriter(new FileWriter(writerJson.toString()))) {
            out.write(source_1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
