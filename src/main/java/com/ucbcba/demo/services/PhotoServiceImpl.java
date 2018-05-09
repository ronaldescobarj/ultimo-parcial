package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.Photo;
import com.ucbcba.demo.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Iterable<Photo> listAllPhotosById(Integer id) {
        List<Photo> res = new ArrayList<Photo>();
        List<Photo> lista = (List<Photo>) photoRepository.findAll();
        for(int i=0;i<lista.size();i++)
        {
            if(lista.get(i).getRestaurant().getId() == id)
            {
                res.add(lista.get(i));
            }
        }
        return (Iterable<Photo>) res;
    }

    @Override
    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
    }

    @Override
    public Photo getPhoto(Integer id) {
        return photoRepository.findById(id).get();
    }

    @Override
    public void deletePhoto(Integer id) {
        photoRepository.deleteById(id);
    }
    
}
