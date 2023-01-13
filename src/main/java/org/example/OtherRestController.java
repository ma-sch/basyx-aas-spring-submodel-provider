package org.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my-rest-path")
public class OtherRestController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String testRestEndpoint() {
        return "test";
    }

}
