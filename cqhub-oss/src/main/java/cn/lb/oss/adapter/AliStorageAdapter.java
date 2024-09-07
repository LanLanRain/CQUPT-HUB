package cn.lb.oss.adapter;

import cn.lb.oss.entity.FileInfo;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 阿里云存储实现
 *
 * @author RainSoul
 * @create 2024-09-07
 */
public class AliStorageAdapter implements StorageAdapter{
    @Override
    public void createBucket(String bucket) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

    }

    @Override
    public void uploadFile(MultipartFile uploadFile, String bucket, String objectName) throws IOException {

    }

    @Override
    public List<String> getAllBucket() {
        return List.of();
    }

    @Override
    public List<FileInfo> getAllFile(String bucket) {
        return List.of();
    }

    @Override
    public InputStream downLoad(String bucket, String objectName) {
        return null;
    }

    @Override
    public void deleteBucket(String bucket) {

    }

    @Override
    public void deleteObject(String bucket, String objectName) {

    }

    @Override
    public String getUrl(String bucket, String objectName) {
        return "";
    }
}
