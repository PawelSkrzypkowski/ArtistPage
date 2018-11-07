package pl.pawelskrzypkowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.pawelskrzypkowski.entity.Blog;
import pl.pawelskrzypkowski.repository.BlogRepository;
import pl.pawelskrzypkowski.storage.StorageService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    @Qualifier("blogFileStorageService")
    StorageService blogStorageService;

    @GetMapping(value = {"", "/"})
    public String mainPage(Model model){
        return "admin/main";
    }

    @GetMapping("/blog")
    public String getBlogPage(Model model){
        List<Blog> blog = blogRepository.findAllByOrderByAddDateDesc();
        model.addAttribute("blog", blog);
        return "admin/blog";
    }

    @GetMapping(value = "/modal/addBlogPage")
    public String addBlogPageModal(){
        return "admin/modals::addBlogModal";
    }
}
