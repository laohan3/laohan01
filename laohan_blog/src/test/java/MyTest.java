import com.google.gson.Gson;
import com.laohan.LaoHanBlogApplication;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.*;
@SpringBootTest(classes = LaoHanBlogApplication.class)
@ConfigurationProperties(prefix = "oass")

public class MyTest {
    private String accessKey;
    private String secretKey;
    private String bucket;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    @Test
    public void testOss(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {
//            byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
//            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
            InputStream inputStream=new FileInputStream("C:\\Users\\88430\\Pictures\\Camera Roll\\OIP-C (7).jfif");
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
class Demo1 {

    private String aString;

    private  static final ThreadLocal<String> localVar =new ThreadLocal<String>();

    public void  print(String string){

        //打印当前线程中本地内存中本地变量的值
        System.out.println(string + " :" + localVar.get());
        //清除本地内存中的本地变量
        localVar.remove();
    }

    public static ThreadLocal<String> getLocalVar() {
        return localVar;
    }

    @Test
    public  void  test1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                /*
                 * 实际上,是设置当前thread的ThreadLocal.ThreadLocalMap属性
                 *threadLocalMap是个hashMap,其key为当前ThreadLocal对象,value是设置的值
                 * 设置一个变量在当前线程中,后续只要一直使用同一个线程,就一直能从这个线程取到值,
                 * 如果值是引用变量 ,在某个地方改变依旧能改变这个线程的值
                 * */
                Demo1.localVar.set("测试测试测试测试");
                //  demo1.print("A");
                /*
                 * 实际上是获取当前thread的ThreadLocal.ThreadLocalMap然后threadLocalMap是个hashMap,
                 * 然后用当前的threadLocal为key获取value值
                 *
                 * */
/*
                String  aa =Demo1.localVar.get();
                System.out.println(aa);*/
            }
        },"A").start();


    }

}
