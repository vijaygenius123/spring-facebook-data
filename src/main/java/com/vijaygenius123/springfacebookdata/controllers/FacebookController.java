package com.vijaygenius123.springfacebookdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FacebookController {

    @Autowired
    private Facebook facebook;
    private ConnectionRepository connectionRepository;


    public FacebookController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    public String getAllFeeds(Model model){
        if(connectionRepository.getPrimaryConnection(Facebook.class)==null){
            return "redirect:/connect/facebook";
        }
        PagedList<Post> posts = facebook.feedOperations().getFeed();
        model.addAttribute("posts", posts);
        model.addAttribute("profileName", posts.get(0).getFrom().getName());
        return "profile";
    }
}
