package com.uk.companieshouse.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestUtils {
    public static <E> E convertJsonToObject(String filePath, Class<E> name) {
        ClassLoader classLoader = name.getClassLoader();
        String response = stringFromFile(filePath, classLoader);

        E obj = null;
        try {
            obj = new ObjectMapper().readValue(response, name);
        } catch (Exception e) {
            Assert.fail("Wrong Conversion, response was : \n" + e.getLocalizedMessage());
        }
        return obj;
    }

    public static String stringFromFile(String filePath, ClassLoader classLoader) {
        File file = new File((classLoader.getResource(filePath).getFile()));
        return readJSON(file);
    }

    private static String readJSON(File file) {
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            return new String(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
