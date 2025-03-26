package com.example.MyBlog.services;

import com.example.MyBlog.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public interface UserService {




     User saveUser(String title, String content, String imageName, MultipartFile imageData, String author);
     List<User> getAllPost();
     Optional<User> userGetById(Long id);
     ResponseEntity<String> postDeleteById(Long id);
     User updateUserPost( Long id, User user);


//    Map<String, Object> gerateSummary(String blogTitle);

}
