package com.github.tamurashingo.salonservice.app.salon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SalonController {

    @RequestMapping(value = "/salon", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("salon/index");
        return mv;
    }

    @RequestMapping(value = "/salon/create", method = RequestMethod.POST)
    public ModelAndView create(ModelAndView mv) {
        mv.setViewName("salon/create");
        return mv;
    }

    @RequestMapping(value = "/salon/search", method = RequestMethod.GET)
    public ModelAndView search(ModelAndView mv) {
        mv.setViewName("salon/search");
        return mv;
    }
}
