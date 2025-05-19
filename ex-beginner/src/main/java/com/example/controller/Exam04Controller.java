package com.example.controller;

import com.example.domain.User;
import com.example.form.UserForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/exam04")
public class Exam04Controller {
    @GetMapping("")
    public String index(UserForm form){

        return "exam04";
    }

    @PostMapping("/register")
    public String register(
            @Validated UserForm userForm
            , BindingResult result
            , RedirectAttributes redirectAttributes
            , Model model
    ){
        //もし一つでもエラーがあれば
        if(result.hasErrors()){
            return index(userForm);
        }
        User user = new User();

        //自動で変換
        BeanUtils.copyProperties(userForm, user);

        model.addAttribute("user", user);
        return "exam04-result";
    }
}
