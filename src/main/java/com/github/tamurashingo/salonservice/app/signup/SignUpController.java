package com.github.tamurashingo.salonservice.app.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("signup/index");
        return mv;
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ModelAndView register(ModelAndView mv) {
        mv.setViewName("signup/registered");
        return mv;
    }

}
