package com.pewa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;


/**
 * Created by phonik on 2017-05-10.
 */

@Controller
public class IndexController {

    @GetMapping("start")
    public String startHtml() {
        return "static/index.html";
    }

}
