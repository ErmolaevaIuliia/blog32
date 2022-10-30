package com.example.blog32.controller;

import com.example.blog32.entity.ArticleEntity;
import com.example.blog32.repository.ArticleRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleRepo articleRepo;

    @PostMapping("/addArticle")
    public String addArticle(@ModelAttribute ArticleEntity article) {
        String html = article.getContent();
        Document document = Jsoup.parse(html);
        Elements images = document.select("img");
        //дз: циклом перебрать картинки
        Element img = images.first();
        String src = img.attr("src");
        String base64 = src.split(",")[1];
        byte[]image = Base64.getDecoder().decode(base64);
        try {
            FileOutputStream fos = new FileOutputStream("/Users/iuliiaermolaeva/Desktop/site");
            fos.write(image);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //articleRepo.save(article);
        return "OK";
    }

    @GetMapping("/getArticles")
    public ResponseEntity getArticles(){
        ArrayList <ArticleEntity> articles = (ArrayList<ArticleEntity>) articleRepo.findAll();
        return ResponseEntity.ok(articles);
    }

    @PostMapping("/getArticleById")
    public ResponseEntity getArticleById(@ModelAttribute ArticleEntity article){
        return ResponseEntity.ok(articleRepo.findById(article.getId()));
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
