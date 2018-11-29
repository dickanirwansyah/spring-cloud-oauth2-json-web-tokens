package com.dicka.springbootemail.controller;

import com.dicka.springbootemail.VerifyForm;
import com.dicka.springbootemail.service.VerifyTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private VerifyTokenService verifyTokenService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
        return "redirect:/email-verification";
    }

    @RequestMapping(value = "/email-verification")
    public String verifyForm(Model model){
        model.addAttribute("verificationForm", new VerifyForm());
        return "verification-form";
    }

    @PostMapping("/email-verification")
    public String formPost(@Valid VerifyForm verificationForm, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            model.addAttribute("noErrors", true);
        }
        model.addAttribute("verificationForm", verificationForm);

        verifyTokenService.createVerification(verificationForm.getEmail());
        return "verification-form";
    }

    @GetMapping("/verify-email")
    @ResponseBody
    public String verifyEmail(String code) {
        return verifyTokenService.verifyEmail(code).getBody();
    }
}
