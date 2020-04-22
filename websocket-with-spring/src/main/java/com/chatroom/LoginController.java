package com.chatroom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author cj
 * @date 2018-12-05
 */

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(String username, String password, HttpSession httpSession) {
        if (username.length() >= 3 && password.length() >= 3) {
            httpSession.setAttribute("username", username);
            return "chatroom";
        }else{
            return "login";
        }
    }

    @GetMapping("/login")
    public String index(){
        return "login";
    }

}
