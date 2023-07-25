package com.loncark.guitarapp.security;

import com.loncark.guitarapp.model.UserInfo;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SecureObjectInputStream extends ObjectInputStream {
    public SecureObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    /**
     * Only deserialize instances of our expected Bicycle class
     */
    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        Set<String> allowedClassNames = new HashSet<>();
        allowedClassNames.add(UserInfo.class.getName());
        allowedClassNames.add(String.class.getName());
        allowedClassNames.add(Long.class.getName());
        allowedClassNames.add(Number.class.getName());

        if (!allowedClassNames.contains(desc.getName())) {
            throw new InvalidClassException("Unauthorized deserialization attempt: " + desc.getName());
        }
        return super.resolveClass(desc);
    }
}
