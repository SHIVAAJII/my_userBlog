package com.example.MyBlog.controllers;


import com.example.MyBlog.entities.User;
import com.example.MyBlog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;



    @PostMapping("/save")
    public ResponseEntity<User> saveUserPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("imageName") String imageName,
            @RequestParam("imageData") MultipartFile imageData,
            @RequestParam("author") String author
            ) throws IOException {



        User saveUser = userService.saveUser(title,content,imageName,imageData,author);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);


    }




    @GetMapping("/allPost")
    public List<User> allPosts()
    {
        return userService.getAllPost();
    }



    @GetMapping("/{id}")
    public ResponseEntity<User> postGetByIdf(@PathVariable Long id)
    {
        User user = userService.userGetById(id).orElse(null);

        if (user != null)
        {
            return ResponseEntity.ok().body(user);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<User> updatePost(@PathVariable Long id,

                                           @RequestParam("title") String title,
                                           @RequestParam("content") String content,
                                           @RequestParam("imageName") String imageName,
                                           @RequestParam("imageData") MultipartFile imageData,
                                           @RequestParam("author") String author)
    {

        User user = new User();
        user.setTitle(title);
        user.setContent(content);
        user.setImageName(imageName);
        user.setAuthor(author);
        if(imageData != null && !imageData.isEmpty())
        {
            try {
                user.setImageData(imageData.getBytes());  // MultipartFile को byte[] में Convert किया
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        User user1 = userService.updateUserPost(id, user);

        if (user1 != null)
        {
            return ResponseEntity.ok(user1);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id)
    {

         try {
             return userService.postDeleteById(id);
         }
         catch (Exception exception)
         {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + exception.getMessage());
         }
    }






// Genrate blog summary throw OpenAI

//
//    @PostMapping("/ai/genrateSummary")
//    public ResponseEntity<Map<String, Object>> blogSummary(@RequestParam String blogTitle)
//    {
//        Map<String, Object> result =  userService.gerateSummary(blogTitle);
//        return ResponseEntity.ok(result);
//    }


}
