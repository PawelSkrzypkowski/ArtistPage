package pl.pawelskrzypkowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawelskrzypkowski.entity.Blog;
import pl.pawelskrzypkowski.repository.BlogRepository;
import pl.pawelskrzypkowski.storage.StorageService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Paweł Skrzypkowski
 * Wojskowa Akademia Techniczna im. Jarosława Dąbrowskiego, Warszawa 2018.
 */

@Controller
public class HomeController {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    @Qualifier("blogFileStorageService")
    StorageService storageService;

    @RequestMapping("/")
    public String home(Model model){
        List<Blog> latestBlogs = blogRepository.findTop3ByOrderByAddDateDesc();
        List<Resource> blogImages = latestBlogs.stream().map(b->storageService.loadAsResource(b.getId().toString())).
                collect(Collectors.toList());
        model.addAttribute("blog", latestBlogs);
        model.addAttribute("blogImages", blogImages);
        return "index";
    }
}
