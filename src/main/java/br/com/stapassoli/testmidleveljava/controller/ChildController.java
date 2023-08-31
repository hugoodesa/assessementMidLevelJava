package br.com.stapassoli.testmidleveljava.controller;

import br.com.stapassoli.testmidleveljava.service.ChildrenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/child")
@RequiredArgsConstructor
public class ChildController {

    private final ChildrenService childService;

    @PostMapping
    public ResponseEntity<?> createChild(@RequestParam("file") MultipartFile file) {
        return childService.createParents(file);
    }

    @GetMapping
    public ResponseEntity<String> getFathersName() {
        return childService.getFathersName();
    }

}
