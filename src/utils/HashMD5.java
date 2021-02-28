package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashMD5 {

    public HashMD5(){

    }

    public String getMD5(String name){
        try{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(name.getBytes());
        byte[] md5 = md.digest();

        BigInteger numMd5 = new BigInteger(1, md5);

        return String.format("%022x", numMd5);
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
}
}
