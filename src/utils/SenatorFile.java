package utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SenatorFile {

    public static Map<String, Integer> read() {
        Map<String, Integer> senators = new HashMap<String, Integer>();
        try {
            File file = new File(Config.FILE_CACHE_NAME);
            if(!file.exists()) {
                file.createNewFile();
            } else if (file.length() != 0){
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                senators = (Map<String, Integer>) objectInputStream.readObject();
            }

            File fileOrigin = new File(Config.FILE_NAME);
            BufferedReader bfOrigin = new BufferedReader(new FileReader(fileOrigin));
            String line;
            bfOrigin.readLine();
            while ((line = bfOrigin.readLine()) != null){
                String[] split = line.split(";");
                if(!senators.containsKey(split[0])) {
                    senators.put(split[0], 0);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return senators;
    }

    public static void write( Map<String, Integer> map) throws IOException {
        File file = new File(Config.FILE_CACHE_NAME);
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream outputStream = null;
        fileOutputStream = new FileOutputStream(file);
        outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(map);
    }
}
