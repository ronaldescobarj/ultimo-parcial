package com.ucbcba.demo.controllers;

import com.ucbcba.demo.entities.City;
import com.ucbcba.demo.entities.Photo;
import com.ucbcba.demo.entities.Restaurant;
import com.ucbcba.demo.services.CategoryService;
import com.ucbcba.demo.services.CityService;
import com.ucbcba.demo.services.PhotoService;
import com.ucbcba.demo.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.List;

@Controller
public class RestaurantController {

    private RestaurantService restaurantService;
    private CategoryService categoryService;
    private CityService cityService;
    private PhotoService photoService;

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    @RequestMapping(value = "admin/restaurants", method = RequestMethod.GET)
    public String listAllRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.listAllRestaurants());
        return "restaurants";
    }

    @RequestMapping(value = "admin/restaurant/new")
    public String newRestaurant(Model model) {
        model.addAttribute("restaurantCategories", categoryService.listAllCategories());
        model.addAttribute("cities", cityService.listAllCities());
        model.addAttribute("restaurant", new Restaurant());
        return "newRestaurant";
    }

    @RequestMapping(value = "admin/restaurant/save", method = RequestMethod.POST)
    public String saveRestaurant(Restaurant restaurant, @RequestParam("file") MultipartFile file) {
        restaurantService.saveRestaurant(restaurant);
//        byte[] pixel = file.getBytes();
//        Photo photo = new Photo();
//        photo.setRestaurant(restaurant);
//        photo.setPhoto(pixel);
//        photoService.savePhoto(photo);
        return "redirect:/admin/restaurant/new";
    }

    @RequestMapping("admin/restaurant/{id}")
    String showRestaurant(@PathVariable Integer id, Model model) {
        model.addAttribute("restaurant", restaurantService.getRestaurant(id));
        model.addAttribute("photos", photoService.listAllPhotosById(id));
        return "ShowRestaurant";
    }

    @RequestMapping(value = "admin/restaurant/edit/{id}")
    String editRestaurant(@PathVariable Integer id, Model model) {
        model.addAttribute("restaurant", restaurantService.getRestaurant(id));
        model.addAttribute("restaurantCategories", categoryService.listAllCategories());
        model.addAttribute("cities", cityService.listAllCities());
        return "restaurantForm";
    }

    @RequestMapping(value = "admin/restaurant/delete/{id}")
    String delete(@PathVariable Integer id, Model model) {
        restaurantService.deleteRestaurant(id);
        return "redirect:/admin/restaurants";
    }

    /*@RequestMapping(value = "/home")
    public ModelAndView home()  throws IOException {
        ModelAndView view = new ModelAndView("index");
        view.addObject("image_id", 15);
        return view;
    }*/
    @RequestMapping(value = "admin/image/{image_id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_id") Long imageId) throws IOException {
        //  model.addAttribute("restaurant",restaurantService.getRestaurant(id));
        Iterable<Photo> i = photoService.listAllPhotosById(9);
        List<Photo> x = (List<Photo>) i;
        byte[] imageContent = x.get(0).getPhoto();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.ALL);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "admin/savePhotos")
    public void savePhotos(Photo photo) {
        photoService.savePhoto(photo);
    }

}
