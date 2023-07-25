package com.loncark.guitarapp.service;

import com.loncark.guitarapp.model.MaliciousUserInfo;
import com.loncark.guitarapp.model.UserInfo;
import com.loncark.guitarapp.security.SecureObjectInputStream;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class SerializationService {
    public String serializeUserInfo() {
        UserInfo userInfo = new UserInfo(1L, "John", "johnpassword", "ROLE_ADMIN");

        try {
            FileOutputStream fileOut = new FileOutputStream("john.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(userInfo);
            out.close();
            fileOut.close();
            return "Serialization of user John successful";

        } catch (IOException e) {
            e.printStackTrace();
            return "Serialization unsuccessful";
        }
    }

    public String serializeMaliciousUserInfo() {
        MaliciousUserInfo userInfo = new MaliciousUserInfo(1L, "MaliciousJohn", "johnpassword", "ROLE_ADMIN,ROLE_USER");

        try {
            FileOutputStream fileOut = new FileOutputStream("john.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(userInfo);
            out.close();
            fileOut.close();
            return "Serialization of MaliciousJohn successful";

        } catch (IOException e) {
            e.printStackTrace();
            return "Serialization unsuccessful";
        }
    }

    public String deserializeUserInfoSafely() throws IOException {
        FileInputStream fin = new FileInputStream("john.ser");
        SecureObjectInputStream sois = new SecureObjectInputStream(fin);

        return deserializeUserInfo(sois, fin);
    }

    public String deserializeUserInfoUnsafely() throws IOException {
        FileInputStream fin = new FileInputStream("john.ser");
        ObjectInputStream ois = new ObjectInputStream(fin);

        return deserializeUserInfo(ois, fin);
    }

    public String deserializeUserInfo(ObjectInputStream ois, FileInputStream fin) {
        try {
            UserInfo deserializedUserInfo = (UserInfo) ois.readObject();
            ois.close();
            fin.close();

            System.out.println("Deserialized UserInfo object:");
            System.out.println(deserializedUserInfo);

            return deserializedUserInfo.toString() + "\nMessage: " + deserializedUserInfo.printMessage();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Deserialization unsuccessful";
        }
    }
}
