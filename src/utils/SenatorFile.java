package utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SenatorFile {

    Map<String, Integer> senators = new HashMap<String, Integer>();
    Map<String, String> userVotes = new HashMap<String, String>();

    public void read() {
        try {
            File file = new File(Config.FILE_CACHE_NAME);
            if(!file.exists()) {
                file.createNewFile();
            } else if (file.length() != 0){
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                senators = (Map<String, Integer>) objectInputStream.readObject();
            }

            File fileUsers = new File(Config.FILE_VOTES_CACHE);
            if(!fileUsers.exists()) {
                fileUsers.createNewFile();
            } else if (fileUsers.length() != 0){
                FileInputStream fileInputStreamUsers = new FileInputStream(fileUsers);
                ObjectInputStream objectInputStreamUsers = new ObjectInputStream(fileInputStreamUsers);
                userVotes = (Map<String, String>) objectInputStreamUsers.readObject();
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
    }

    public static void write( Map map, String fileName) throws IOException {
        File file = new File(fileName);
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream outputStream = null;
        fileOutputStream = new FileOutputStream(file);
        outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(map);
    }

    public Map<String, Integer> getSenators() {
        return senators;
    }

    public Map<String, String> getUserVotes() {
        return userVotes;
    }
}
