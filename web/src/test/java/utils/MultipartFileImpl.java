package utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class MultipartDataImpl implements MultipartFile {

    private FileInputStream inputStream;
    private File file;

    public MultipartDataImpl() {
        ClassLoader classLoader = getClass().getClassLoader();
        file = new File(classLoader.getResource("lake.jpg").getFile());
        try {
            this.inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "nature.jpeg";
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
        return "test_images/nature.jpeg".getBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {

    }
}
