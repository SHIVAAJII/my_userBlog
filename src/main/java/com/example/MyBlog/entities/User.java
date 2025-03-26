package com.example.MyBlog.entities;


import jakarta.persistence.*;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;

@Entity
@Table(name = "blog")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "LONGTEXT")  // यह Database को बताता है कि ये Data बहुत बड़ा हो सकता है
    @Lob
    private String content;

    @Column
    private String imageName;

    @Lob  // to store large object
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @Column
    private String author;

    @Column
    private LocalDateTime uploadDateTime;


    public User() {
    }

    public User(Long id, LocalDateTime uploadDateTime, String author, byte[] imageData, String imageName, String content, String title) {
        this.id = id;
        this.uploadDateTime = uploadDateTime;
        this.author = author;
        this.imageData = imageData;
        this.imageName = imageName;
        this.content = content;
        this.title = title;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getUploadDateTime() {
        return uploadDateTime;
    }

    @PrePersist
    public void setUploadDateTime() {
        this.uploadDateTime = LocalDateTime.now();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
