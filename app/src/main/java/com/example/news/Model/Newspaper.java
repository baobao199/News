package com.example.news.Model;

import android.media.Image;

import java.io.Serializable;
import java.util.HashMap;

public class Newspaper implements Serializable {

     String name;
     String maincontent;
     String type;
     String image;
     String content;
     String author;

     public Newspaper() {
     }

     public Newspaper(String name, String maincontent, String type, String image, String content, String author) {
          this.name = name;
          this.maincontent = maincontent;
          this.type = type;
          this.image = image;
          this.content = content;
          this.author = author;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getMaincontent() {
          return maincontent;
     }

     public void setMaincontent(String maincontent) {
          this.maincontent = maincontent;
     }

     public String getType() {
          return type;
     }

     public void setType(String type) {
          this.type = type;
     }

     public String getImage() {
          return image;
     }

     public void setImage(String image) {
          this.image = image;
     }

     public String getContent() {
          return content;
     }

     public void setContent(String content) {
          this.content = content;
     }

     public String getAuthor() {
          return author;
     }

     public void setAuthor(String author) {
          this.author = author;
     }
}
