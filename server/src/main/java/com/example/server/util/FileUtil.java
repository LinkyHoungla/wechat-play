package com.example.server.util;

import com.example.server.constant.ApiError;
import com.example.server.exception.ApiException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static String uploadFile(String savePath, String filename, MultipartFile avatar) {
        String result;
        try {
            result = saveFile(avatar, savePath, filename);
        } catch (IOException e) {
            throw new ApiException(ApiError.E462);
        }

        return result;
    }

    private static String saveFile(MultipartFile f, String path, String filename) throws IOException {
        File dir = new File(path);
        if(!dir.exists()) dir.mkdir();

        File file = new File(path + filename);
        f.transferTo(file);

        return path + filename;
    }
}
