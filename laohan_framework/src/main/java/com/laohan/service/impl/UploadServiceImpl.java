package com.laohan.service.impl;

import com.google.gson.Gson;
import com.laohan.domain.ResponseResult;
import com.laohan.enums.AppHttpCodeEnum;
import com.laohan.exception.SystemException;
import com.laohan.service.UploadService;
import com.laohan.utils.PathUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class UploadServiceImpl implements UploadService {
    private String accessKey;
    private String secretKey;
    private String bucket;
    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        // TODO: 2022/6/4 判断文件类型或大小

        String filename = img.getOriginalFilename();
        if (!filename.endsWith(".png")){
            throw new SystemException(AppHttpCodeEnum.INVALID_FILE);
        }
        String filePath = PathUtils.generateFilePath(filename);
        //如果判断通过,上传文件到OSS
        String url = uploadOss(img,filePath);
        return ResponseResult.okResult(url);
    }

    public String uploadOss(MultipartFile img,String filePath){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filePath;
        try {
            InputStream inputStream=img.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return "http://rcxooztqg.hd-bkt.clouddn.com/"+key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传图片失败!";
    }



}
