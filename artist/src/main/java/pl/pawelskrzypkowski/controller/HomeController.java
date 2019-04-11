package pl.pawelskrzypkowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawelskrzypkowski.entity.Blog;
import pl.pawelskrzypkowski.repository.BlogRepository;
import pl.pawelskrzypkowski.storage.StorageService;

import java.util.List;

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
    StorageService blogStorageService;

    @RequestMapping("/")
    public String home(Model model){
        List<Blog> latestBlogs = blogRepository.findTop3ByOrderByAddDateDesc();
        model.addAttribute("blog", latestBlogs);
        return "index";
    }

    @GetMapping(value = "/modal/mailing")
    public String deleteBlogPageModal(){
        return "modals::mailingModal";
    }
}
