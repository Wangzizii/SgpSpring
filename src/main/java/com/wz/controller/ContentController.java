package com.wz.controller;

import com.wz.pojo.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/content")
public class ContentController {
    @GetMapping
    public Result<String> content(HttpServletResponse response) {

        return Result.success("I'm Content");
    }
}
