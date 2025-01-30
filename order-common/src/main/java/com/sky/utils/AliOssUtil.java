package com.sky.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.io.ByteArrayInputStream;

@Data
@AllArgsConstructor
@Slf4j
public class AliOssUtil {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /**
     * 文件上传
     * @param bytes 文件内容
     * @param objectName 目标文件名
     * @return 文件访问URL
     */
    public String upload(byte[] bytes, String objectName) {
        log.info("Uploading file to OSS: {}", objectName);

        // 1. 创建 OSS 客户端
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 2. 检查 objectName 是否为空
            if (objectName == null || objectName.trim().isEmpty()) {
                throw new IllegalArgumentException("ObjectName cannot be empty");
            }

            // 3. 上传文件
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));

            // 4. 生成访问 URL
            String fileUrl = String.format("https://%s.%s/%s", bucketName, endpoint, objectName);
            log.info("File uploaded successfully: {}", fileUrl);
            return fileUrl;

        } catch (OSSException oe) {
            log.error("OSS Exception: ErrorMessage={}, ErrorCode={}, RequestId={}, HostId={}",
                    oe.getErrorMessage(), oe.getErrorCode(), oe.getRequestId(), oe.getHostId());
            throw new RuntimeException("OSS Upload Failed: " + oe.getErrorMessage(), oe);

        } catch (ClientException ce) {
            log.error("Client Exception: {}", ce.getMessage());
            throw new RuntimeException("OSS Client Error: " + ce.getMessage(), ce);

        } catch (Exception e) {
            log.error("Unexpected Error: {}", e.getMessage(), e);
            throw new RuntimeException("File Upload Failed: " + e.getMessage(), e);

        } finally {
            // 5. 关闭客户端
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
