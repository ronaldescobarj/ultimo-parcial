package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.Photo;
import com.ucbcba.demo.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    private PhotoRepository photoRepository;

    @Autowired
    @Qualifier(value = "photoRepository")
    public void setPhotoRepository(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Iterable<Photo> listAllPhotos() {
        return photoRepository.findAll();
    }

    @Override
    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
    }

    @Override
    public Photo getPhoto(Integer id) {
        return photoRepository.findOne(id);
    }

    @Override
    public void deletePhoto(Integer id) {
        photoRepository.delete(id);
    }
    
}
