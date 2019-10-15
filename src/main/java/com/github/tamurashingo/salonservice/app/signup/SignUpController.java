package com.github.tamurashingo.salonservice.app.signup;

import com.github.tamurashingo.salonservice.domain.model.userregister.UserRegisterModel;
import com.github.tamurashingo.salonservice.domain.service.signup.SignUpService;
import com.github.tamurashingo.salonservice.domain.service.signup.SignUpServiceException;
import com.github.tamurashingo.salonservice.domain.service.userregister.UserRegisterService;
import com.github.tamurashingo.salonservice.domain.service.userregister.UserRegisterServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {

    private UserRegisterService userRegisterService;

    public SignUpController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("signup/index");
        return mv;
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute SignUpForm signUpForm, ModelAndView mv) {
        try {
            UserRegisterModel model = convertSignUpModel(signUpForm);
            userRegisterService.register(model);
        }
        catch (UserRegisterServiceException ex) {
            System.out.println(ex);
        }
        mv.setViewName("signup/registered");
        return mv;
    }



    private UserRegisterModel convertSignUpModel(SignUpForm form) {
        return new UserRegisterModel(form.getUserEmail(), form.getUserName(), form.getPassword());
    }

}
