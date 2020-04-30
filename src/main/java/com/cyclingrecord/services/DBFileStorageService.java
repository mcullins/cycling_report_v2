package com.cyclingrecord.services;

import com.cyclingrecord.data.DBFileRepository;
import com.cyclingrecord.exceptions.FileStorageException;
import com.cyclingrecord.models.DBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeImage(MultipartFile image) {
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());

        try{
            if(imageName.contains("..")){
                throw new FileStorageException("Sorry! Image name contains invalid path sequence " + imageName);
            }

            DBFile dbFile = new DBFile(imageName, image.getBytes());
            return dbFileRepository.save(dbFile);
        } catch (IOException ex){
            throw new FileStorageException("Could not store image " + imageName + " . Please try again!", ex);
        }
    }

    public ArrayList<DBFile> getImage(){
        return (ArrayList<DBFile>) dbFileRepository.findAll();
    }

}
