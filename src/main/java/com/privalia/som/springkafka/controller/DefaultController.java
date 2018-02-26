package com.privalia.som.springkafka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Default controller
 *
 * @author david.amigo
 */
@Controller
public class DefaultController {

    /**
     * Homepage action
     *
     * @return the name of the tempplate to load (homepage.html).
     */
    @RequestMapping("/")
    public String homepage() {
        return "homepage";
    }
}
