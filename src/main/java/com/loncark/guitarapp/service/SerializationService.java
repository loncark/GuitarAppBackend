package com.loncark.guitarapp.service;

import com.loncark.guitarapp.model.MaliciousUserInfo;
import com.loncark.guitarapp.model.UserInfo;
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

    public String deserializeUserInfo() {
        try {
            FileInputStream fileIn = new FileInputStream("john.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            UserInfo deserializedUserInfo = (UserInfo) in.readObject();
            in.close();
            fileIn.close();

            System.out.println("Deserialized UserInfo object:");
            System.out.println(deserializedUserInfo);

            return deserializedUserInfo.toString() + "\nMessage: " + deserializedUserInfo.printMessage();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Deserialization unsuccessful";
        }
    }
}
