package com.computools.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class MultipartFileImpl implements MultipartFile {

    private FileInputStream inputStream;
    private File file;

    public MultipartFileImpl() {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            file = new File("testimg/lake.jpg");
            this.inputStream = new FileInputStream(file);
        }catch (NullPointerException e) {

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "lake.jpg";
    }

    @Override
    public String getOriginalFilename() { return "lake.jpg"; }

    @Override
    public String getContentType() {
        return "image/jpeg";
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return file.length();
    }

    @Override
    public byte[] getBytes() throws IOException {
        return "testimg/lake.jpg".getBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {

    }
}
