package oc.gui.writeCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.opencsv.CSVWriter;

import oc.gui.dumpJSON.OCObject;

public class WriteCSV {

    public WriteCSV(){
    }

    public CSVWriter writeCSV(String fileName){
        //Path readerJsonQA = Paths.get(".", "src", "test", "resources", "tiers_qa.json");
        //Path readerJsonProd = Paths.get(".", "src", "test", "resources", "tiers_prod.json");
        Path writerCSV;
        File file;
        FileWriter outputfile;
        CSVWriter writer;
        try {
            writerCSV = Paths.get(".", "src", "test", "resources","output", fileName);
            file = new File(writerCSV.toString());
            outputfile = new FileWriter(file);
            writer = new CSVWriter(outputfile);
            return writer;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("File not found.");
        }

        return null;
    }

    public FileWriter writeCSV_FileWriter(String fileName){
        //Path readerJsonQA = Paths.get(".", "src", "test", "resources", "tiers_qa.json");
        //Path readerJsonProd = Paths.get(".", "src", "test", "resources", "tiers_prod.json");
        Path writerCSV;
        File file;
        FileWriter outputfile;
        CSVWriter writer;
        try {
            writerCSV = Paths.get(".", "src", "test", "resources","output", fileName);
            file = new File(writerCSV.toString());
            outputfile = new FileWriter(file);
            //writer = new CSVWriter(outputfile);
            return outputfile;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("File not found.");
        }

        return null;
    }


    public void writeHeader(List <String[]> header, CSVWriter writerHandler) {
        //System.out.println("writeContent >> " + header.toString());

        header.stream().forEach(val -> System.out.println(">> "+val[0].toString()));
        writerHandler.writeAll(header, true);
        try {
            
            //writerHandler.close();
            
            writerHandler.flush();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //writerHandler.writeNext(header);
    }

    public void writeHeader(List <String[]> header, FileWriter writerHandler) {
    //System.out.println("writeContent >> " + header.toString());
    String data="";
    
    for (int i =0; i < header.size(); i++){
        System.out.println(">>>"+header.get(i)[0].toString());
        data = data + header.get(i)[0].toString()+ ",";
        
    }
    
    
    try {
        
        //writerHandler.close();
        writerHandler.append(data);    
        writerHandler.flush();
        
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    //writerHandler.writeNext(header);
    }


    public void writeHeaderOnce(String[] header, FileWriter writerHandler) {
        //System.out.println("writeContent >> " + header.toString());
        String data="";
        
        for (int i = 0 ; i < header.length; i ++){
            System.out.println("header i >> " + header[i]);
        }
        
        
        try {
            
            //writerHandler.close();
            writerHandler.append(data);    
            writerHandler.flush();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //writerHandler.writeNext(header);
    }


    public void writeContent(String column_type, String value, FileWriter writerHandler) throws IOException{
        
        
        try {
            //ocObj_mapa.values();

        //writerHandler.close();
        writerHandler.append(column_type+","+value+"\n");    
        writerHandler.flush();
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
