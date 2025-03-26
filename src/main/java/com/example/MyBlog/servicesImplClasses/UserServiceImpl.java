package com.example.MyBlog.servicesImplClasses;

import com.example.MyBlog.entities.User;
import com.example.MyBlog.repositories.UserRepository;
import com.example.MyBlog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User saveUser(String title, String content, String imageName, MultipartFile imageData, String author) {
        User user = new User();
        user.setTitle(title);
        user.setContent(content);
        user.setImageName(imageName);
        try {
            user.setImageData(imageData.getBytes());  // MultipartFile को byte[] में Convert किया
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        user.setAuthor(author);


        return userRepository.save(user);

    }

    @Override
    public List<User> getAllPost() {

        return userRepository.findAll();
    }

    @Override
    public Optional<User> userGetById(Long id) {
        return userRepository.findById(id);
    }




    @Override
    public User updateUserPost(Long id, User newUser) {


        User userData = userRepository.findById(id).orElse(null);



        if(userData != null && userData.getId() != null)
        {
            userData.setTitle(newUser.getTitle());
            userData.setContent(newUser.getContent());
            userData.setImageName(newUser.getImageName());
            userData.setImageData(newUser.getImageData());
            userData.setAuthor(newUser.getAuthor());
            return userRepository.save(userData);
        }
        else
        {
            throw new RuntimeException("User not found exception! " +id);
        }


    }



    @Override
    public ResponseEntity<String > postDeleteById(Long id) {

        if (!userRepository.existsById(id))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found with this id "+ id);

        }
        else
        {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User post deleted successfully! ");
        }

    }








// service for genrate summary throw OpenAI
//
//    @Override
//    public Map<String, Object> gerateSummary(String blogTitle) {
//        return Map.of();
//    }


}
