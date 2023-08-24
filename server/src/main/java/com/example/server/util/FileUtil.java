package com.example.server.util;

import com.example.server.constant.ApiError;
import com.example.server.exception.ApiException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class FileUtil {

    public static String uploadFile(String old, String savePath, String update, MultipartFile file) {
        // 删除旧文件
        if (old != null)
            deleteOldFile(savePath, old);

        // 新文件名
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null)
            throw new ApiException(ApiError.E462);

        String filename = update + "-" + UuidUtil.generateUniqueId() + "." + originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        String result;
        try {
            result = saveFile(file, savePath, filename);
        } catch (IOException e) {
            throw new ApiException(ApiError.E464);
        }

        return result;
    }

    public static void deleteOldFile(String path, String filename) {
        File oldFile = new File(path + filename);

        if (oldFile.exists())
            if (!oldFile.delete())
                throw new ApiException(ApiError.E463);
    }

    private static String saveFile(MultipartFile f, String path, String filename) throws IOException {
        File dir = new File(path);

        if(!dir.exists())
            if (!dir.mkdir())
                throw new ApiException(ApiError.E462);

        File file = new File(path + filename);
        f.transferTo(file);

        return filename;
    }
}
