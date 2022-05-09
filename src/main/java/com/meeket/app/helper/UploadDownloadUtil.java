package com.meeket.app.helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadDownloadUtil {
    private Path foundFile;
    public URI uriSave;

    public  String saveFile(long  userID,String fileName, MultipartFile multipartFile){

        String tmpDirsLocation = System.getProperty("java.io.tmpdir");
        File TEMP_DIRECTORY = new File(tmpDirsLocation);

        File newDirectory = new File(TEMP_DIRECTORY, "Meeket/" +userID +"/");

        if(newDirectory.exists()){
            System.out.println("Exist");
            System.out.println(System.getProperty("java.io.tmpdir"));
        }else{
            newDirectory.mkdir();
            System.out.println("Create folder");
            System.out.println(System.getProperty("java.io.tmpdir"));
        }

        uriSave= newDirectory.toURI();
        Path uploadFileDirectory = Paths.get(uriSave);

        String fileCode = RandomStringUtils.randomAlphabetic(8);
        try {
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadFileDirectory.resolve(fileCode +"_"+fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
        return  fileCode;
    }
    public Resource getFileAsResource(String fileCode){
        Path uploadDirectory = Paths.get("FileUpload");

        try {
            Files.list(uploadDirectory).forEach(file->{
                if (file.getFileName().toString().startsWith(fileCode)) {
                    foundFile = file;
                    return;

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(foundFile !=null){
            try {
                return  new UrlResource(foundFile.toUri());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }

    public URI getUriSave() {
        return uriSave;
    }

    public void setUriSave(URI uriSave) {
        this.uriSave = uriSave;
    }
}
