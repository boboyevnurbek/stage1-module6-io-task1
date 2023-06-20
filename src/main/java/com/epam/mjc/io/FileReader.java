package com.epam.mjc.io;

import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();

        try (java.io.FileReader reader = new java.io.FileReader(file)) {
            int r = -1;
            StringBuilder sb = new StringBuilder();

            while ((r = reader.read()) != -1) {
                char c = (char) r;

                if (c == '\r') {
                    String line = sb.toString();
                    if (line.startsWith("Name: ")) {
                        profile.setName(line.substring(6));
                    } else if (line.startsWith("Age: ")) {
                        profile.setAge(Integer.parseInt(line.substring(5)));
                    } else if (line.startsWith("Email: ")) {
                        profile.setEmail(line.substring(7));
                    } else if (line.startsWith("Phone: ")) {
                        profile.setPhone(Long.parseLong(line.substring(7)));
                    }
                    reader.read();
                    sb.setLength(0);
                } else {
                    sb.append(c);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return profile;
    }
}
