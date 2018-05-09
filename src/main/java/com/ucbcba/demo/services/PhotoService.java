package com.ucbcba.demo.services;

import com.ucbcba.demo.entities.Photo;

public interface PhotoService {

    Iterable<Photo> listAllPhotos();

    Iterable<Photo>listAllPhotosById(Integer id);

    void savePhoto(Photo photo);

    Photo getPhoto(Integer id);

    void deletePhoto(Integer id);

}
