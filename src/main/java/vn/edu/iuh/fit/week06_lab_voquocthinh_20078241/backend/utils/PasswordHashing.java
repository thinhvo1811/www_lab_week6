package vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;

public class PasswordHashing {
    public static String toSHA1(String str) {
        String salt = "asjrlkmcoewj@tjle;oxqskjhdjksjf1jurVn";
        String result = null;

        str = str + salt;
        try {
            byte[] dataBytes = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(dataBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
