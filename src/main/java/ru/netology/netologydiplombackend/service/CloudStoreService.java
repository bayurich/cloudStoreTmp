package ru.netology.netologydiplombackend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.netology.netologydiplombackend.exception.InvalidCredentials;
import ru.netology.netologydiplombackend.model.auth.Login;
import ru.netology.netologydiplombackend.model.auth.Token;
import ru.netology.netologydiplombackend.model.file.FileInfo;
import ru.netology.netologydiplombackend.repository.CloudStoreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CloudStoreService {
    CloudStoreRepository cloudStoreRepository;

    private static final Logger LOG = LoggerFactory.getLogger(CloudStoreService.class);

    public CloudStoreService(CloudStoreRepository cloudStoreRepository) {
        this.cloudStoreRepository = cloudStoreRepository;
    }

    public Token login(Login login) {

        /*String password = cloudStoreRepository.getPassword(login.getLogin());
        if (password == null) {
            LOG.error("Unknown login: {}", login.getLogin());
            throw new InvalidCredentials("Bad credentials");
        }
        if (!password.equals(login.getPassword())) {
            LOG.error("Incorrect password [{}] for login: {}", login.getPassword(), login);
            throw new InvalidCredentials("Bad credentials");
        }*/

        //TODO сгенерить JWT токен и сохранить его
        String token = "ssssssssssssss";
        //cloudStoreRepository.addToken(getRawToken(token));
        return new Token(token);
    }

    public void logout(String token) {
        cloudStoreRepository.deleteToken(getRawToken(token));
    }


    public List<FileInfo> getFiles(String token, int limit) {
        /*if (!cloudStoreRepository.findToken(getRawToken(token))) {
            throw new InvalidCredentials("Unauthorized error");
        }*/
        //TODO проверить токен и получить инфо о пользователе

        List<FileInfo> fileInfoList = new ArrayList<>();
        fileInfoList.add(new FileInfo("file1", Long.valueOf("1111")));
        fileInfoList.add(new FileInfo("file2", Long.valueOf("2525252525252")));
        fileInfoList.add(new FileInfo("file3", Long.valueOf("44444444")));

        //TODO сходить за списком файлов пользователя
        return fileInfoList;
    }




    private String getRawToken(String token) {
        return token.replaceFirst("Bearer ", "");
    }
}
