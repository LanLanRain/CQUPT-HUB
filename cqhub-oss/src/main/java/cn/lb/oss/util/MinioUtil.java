package cn.lb.oss.util;

import cn.lb.oss.entity.FileInfo;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

/**
 * MinIO文件操作工具类
 *
 * @author RainSoul
 * @create 2024-09-07
 */
@Component
public class MinioUtil {

    @Resource
    private MinioClient minioClient;

    /**
     * 创建MinIO存储桶
     * 如果存储桶不存在，则创建一个新的存储桶；如果存储桶已存在，则不执行任何操作
     *
     * @param bucketName 存储桶的名称，必须符合MinIO对存储桶名称的规范
     */
    public void createBucket(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 检查指定名称的存储桶是否已存在
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        // 如果存储桶不存在，则创建新的存储桶
        if (!bucketExists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    /**
     * 将文件上传到MinIO存储桶
     *
     * @param inputStream 文件的输入流，用于读取文件数据
     * @param bucketName  存储桶名称，用于指定文件存储的位置
     * @param fileName    文件名，用于指定上传后在存储桶中的名称
     */
    public void uploadFile(InputStream inputStream, String bucketName, String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 使用MinIO客户端的putObject方法上传文件，指定存储桶名称、文件名、输入流等参数
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .stream(inputStream, -1, 10485760)
                        // 设置响应内容的类型为二进制流，用于传输文件数据
                        .contentType("application/octet-stream")
                        .build());
    }

    /**
     * 获取所有存储桶的名称列表
     *
     * @return 包含所有存储桶名称的列表
     */
    public List<String> getAllBucketNames() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 使用MinIO客户端的listBuckets方法获取存储桶列表，然后提取每个存储桶的名称，最后将名称列表收集到一个列表中
        return minioClient.listBuckets().stream().map(bucket -> bucket.name()).toList();
    }

    /**
     * 获取指定存储桶中的所有文件信息
     *
     * @param bucketName 存储桶的名称
     *                   <p>
     *                   该方法通过MinIO客户端列出指定存储桶中的所有对象，并将它们的信息封装到FileInfo对象中
     */
    public List<FileInfo> getAllFiles(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 列出指定存储桶中的所有对象
        Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
        // 创建一个列表来存储文件信息
        List<FileInfo> fileInfoList = new LinkedList<>();
        // 遍历结果集，获取每个对象的信息
        for (Result<Item> result : results) {
            // 创建FileInfo对象来存储单个文件的信息
            FileInfo fileInfo = new FileInfo();
            // 获取当前对象
            Item item = result.get();
            // 设置文件信息的标志，表示是否为目录
            fileInfo.setDirectoryFlag(item.isDir());
            // 设置文件的ETag值
            fileInfo.setEtag(item.etag());
            // 设置文件名
            fileInfo.setFileName(item.objectName());
            // 将FileInfo对象添加到列表中
            fileInfoList.add(fileInfo);
        }
        // 返回包含所有文件信息的列表
        return fileInfoList;
    }

    /**
     * 从MinIO服务器下载指定文件并返回其输入流
     *
     * @param bucketName 存储桶名称，用于指定文件所在的存储桶
     * @param fileName   文件名，用于指定要下载的文件
     * @return 返回文件的输入流，以便后续处理
     */
    public InputStream downloadFile(String bucketName, String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 使用MinIO客户端的getObject方法下载文件，指定存储桶名称和文件名，并返回一个输入流
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
    }

    /**
     * 删除桶
     *
     * 本方法通过Minio客户端删除指定的桶
     *
     * @param bucket 要删除的桶的名称
     * @throws Exception 如果删除操作失败，将抛出异常
     */
    public void deleteBucket(String bucket) throws Exception {
        // 使用Minio客户端的removeBucket方法来删除指定的桶
        minioClient.removeBucket(
                RemoveBucketArgs.builder().bucket(bucket).build()
        );
    }


    /**
     * 删除文件
     * 该方法通过MinIO客户端删除指定桶中的指定对象
     *
     * @param bucket     存储桶的名称
     * @param objectName 对象的名称
     */
    public void deleteObject(String bucket, String objectName) throws Exception {
        // 使用MinIO客户端的removeObject方法删除对象，传入包含桶名和对象名的参数对象
        minioClient.removeObject(
                RemoveObjectArgs.builder().bucket(bucket).object(objectName).build()
        );
    }

    /**
     * 获取文件url
     *
     * 本方法用于生成文件的预览URL通过指定的bucket名称和object名称
     * 它构建了获取预签名对象URL的请求参数，并调用MinIO客户端的方法来获取该URL
     *
     * @param bucketName 存储桶名称，标识了文件所在的存储空间
     * @param objectName 对象名称，即文件在存储桶中的唯一标识符
     * @return 返回文件的预签名URL，该URL可以用于访问文件
     * @throws Exception 如果生成URL过程中发生错误，则抛出异常
     */
    public String getPreviewFileUrl(String bucketName, String objectName) throws Exception {
        // 构建获取预签名对象URL的请求参数
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET) // 使用GET方法访问文件
                .bucket(bucketName) // 设置存储桶名称
                .object(objectName) // 设置对象名称
                .build(); // 构建请求参数
        // 调用MinIO客户端方法，获取预签名对象URL
        return minioClient.getPresignedObjectUrl(args);
    }
}
