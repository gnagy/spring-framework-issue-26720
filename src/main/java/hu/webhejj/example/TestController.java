package hu.webhejj.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
class TestController {
    @GetMapping("/test")
    public String test() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error");
    }
}
