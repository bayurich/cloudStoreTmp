package ru.netology.netologydiplombackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.netology.netologydiplombackend.model.file.FileInfo;
import ru.netology.netologydiplombackend.model.auth.Login;
import ru.netology.netologydiplombackend.model.auth.Token;
import ru.netology.netologydiplombackend.service.CloudStoreService;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
public class CloudStoreController {
    CloudStoreService cloudStoreService;

    private static final Logger LOG = LoggerFactory.getLogger(CloudStoreController.class);

    public CloudStoreController(CloudStoreService cloudStoreService) {
        this.cloudStoreService = cloudStoreService;
    }

    @PostMapping("/login")
    public Token login(@RequestBody Login login) {
        LOG.info("Start endpoint login with login: {}", login.getLogin());

        return cloudStoreService.login(login);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("auth-token") @NotBlank String token) {
        LOG.info("Start endpoint logout with token: {}", token);
        cloudStoreService.logout(token);
    }



    @GetMapping("/list")
    public List<FileInfo> getFiles(@RequestHeader("auth-token") @NotBlank String token, @RequestParam("limit") int limit) {
        LOG.info("Start endpoint list with limit: {}", limit);
        LOG.info("Start endpoint list with token: {}", token);

        return cloudStoreService.getFiles(token, limit);
    }

}
