package oc.gui.dumpJSON;

public class CSVObject {
    
    String [] columnName;
    String [] csvValue;
    String [] line;

    public String[] getColumnName() {
        return columnName;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }

    public String[] getCsvValue() {
        return csvValue;
    }

    public void setCsvValue(String[] csvValue) {
        this.csvValue = csvValue;
    }

    public String[] getLine() {
        return line;
    }

    public void setLine(String[] line) {
        this.line = line;
    }

    public String [] convertStringToArrayString(String value){

        String[] val = value.split(",");

        return val;
    }


    public CSVObject(){}


}
