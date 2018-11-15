package pl.pawelskrzypkowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pl.pawelskrzypkowski.entity.Blog;
import pl.pawelskrzypkowski.repository.BlogRepository;
import pl.pawelskrzypkowski.storage.StorageService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

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

    @GetMapping(value = "/modal/editBlogPage/{id}")
    public String editBlogPageModal(@PathVariable("id") Long id, Model model){
        model.addAttribute("blogPage", blogRepository.getOne(id));
        return "admin/modals::addBlogModal";
    }

    @GetMapping(value = "/modal/deleteBlogPage/{id}")
    public String deleteBlogPageModal(@PathVariable("id") Long id, Model model){
        model.addAttribute("id", id);
        return "admin/modals::deleteBlogModal";
    }

    @PostMapping("/blog/add")
    @ResponseBody
    public Blog addBlogPage(Blog blog){
        return blogRepository.save(blog);
    }

    @PostMapping("/blog/edit")
    @ResponseBody
    public Blog editBlogPage(Blog blog){
        return blogRepository.save(blog);
    }

    @PostMapping("/blog/delete/{id}")
    @ResponseBody
    public String deleteBlogPage(@PathVariable("id") Long id){
        blogRepository.setActiveFalse(id);
        try {
            blogStorageService.delete(id + ".jpeg");
            Logger.getAnonymousLogger().info("Deleted jpeg file for blog id " + id);
        } catch (IOException e) {
            try {
                blogStorageService.delete(id + ".png");
                Logger.getAnonymousLogger().info("Deleted png file for blog id " + id);
            } catch (IOException e1) {
                Logger.getAnonymousLogger().info("No image file for blog id " + id);
            }
        }
        return "success";
    }

    @PostMapping("/blog/addImage/{id}")
    @ResponseBody
    public String handleFileUpload(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        if(file.getContentType() != null && file.getContentType().equals("image/jpeg")){
            blogStorageService.store(file, id.toString() + ".jpeg");
        } else if(file.getContentType() != null && file.getContentType().equals("image/png")){
            blogStorageService.store(file, id.toString() + ".png");
        } else {
            return "typeError";
        }
        return "success";
    }

    @GetMapping(value = "/modal/sendMail/{id}")
    public String sendMailModal(@PathVariable("id") Long id, Model model){
        model.addAttribute("id", id);
        return "admin/modals::sendMailModal";
    }
}
