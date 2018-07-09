package pl.pawelskrzypkowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Paweł Skrzypkowski
 * Wojskowa Akademia Techniczna im. Jarosława Dąbrowskiego, Warszawa 2018.
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }
}
