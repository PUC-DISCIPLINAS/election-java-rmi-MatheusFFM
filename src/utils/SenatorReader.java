package utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SenatorReader {
    private String fileName;

    public SenatorReader(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, Integer> read(){
        Map<String, Integer> senators = new HashMap<String, Integer>();
        try {
            File file = new File(fileName);
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String line;
            bf.readLine();
            while ((line = bf.readLine()) != null) {
                String[] split = line.split(";");
                senators.put(split[0], 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return senators;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
