package com.github.tamurashingo.salonservice.app.index;

import com.github.tamurashingo.salonservice.domain.service.StaticsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

//    StaticsService staticsService;
//
//    public IndexController(StaticsService staticsService) {
//        this.staticsService = staticsService;
//    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("index");
        return mv;
    }
}
