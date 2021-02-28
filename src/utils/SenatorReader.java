package utils;

import models.Senator;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SenatorReader {
    private String fileName;

    public SenatorReader(String fileName) {
        this.fileName = fileName;
    }

    public Map<Senator, Integer> read(){
        Map<Senator, Integer> senators = new HashMap<Senator, Integer>();
        try {
            File file = new File(fileName);
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bf.readLine()) != null) {
                String[] split = line.split(";");
                Senator senator = new Senator(split[1], split[0], split[2]);
                senators.put(senator, 0);
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
