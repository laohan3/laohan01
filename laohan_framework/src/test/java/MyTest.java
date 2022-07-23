import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.google.gson.Gson;
import com.laohan.domain.entity.Article;
import com.laohan.domain.entity.User;
import com.laohan.domain.vo.HotArticleVo;
import com.laohan.utils.BeanCopyUtils;
import org.apache.poi.ss.formula.functions.T;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MyTest {

    @Test
    public void testUtils(){
        Article article=new Article();
        article.setId(20L);
        article.setViewCount(100L);
        HotArticleVo articleVo = BeanCopyUtils.copyBean(article, HotArticleVo.class);
        System.out.println(articleVo);

        List<String> stringList=new ArrayList<>();
        stringList.add("laohan");
        //BeanCopyUtils.copyBeanList(stringList,String.class);

    }

    @Test
    public void testCopyList(){
        List<Article> articleList=new ArrayList<>();
        Article article1 = new Article();Article article2 = new Article();Article article3 = new Article();
        article1.setId(1L);article1.setViewCount(10L);
        article2.setId(2L);article2.setViewCount(20L);
        article3.setId(3L);article3.setViewCount(30L);
        articleList.add(article1);articleList.add(article2);articleList.add(article3);
        List<HotArticleVo> hotArticleVoList = BeanCopyUtils.copyBeanList(articleList, HotArticleVo.class);
        for (HotArticleVo ele:hotArticleVoList){
            System.out.println(ele);
        }
    }
    @Test
    public void testJavaSe(){
//        String s1="abc";
//        String s2=new String("abc");
//        String s3=new String("abc");
//        String s4="abc";
//        System.out.println(s1==s2);//f
//        System.out.println(s1==s3);//f
//        System.out.println(s1==s4);//t
//        System.out.println(s2==s3);//f
//        System.out.println(s2==s4);//f
//        System.out.println(s3==s4);//f
//        float a=(float) 1.0;float b=(float) 0.965;
//        System.out.println(a-b);//0.035000026
//        int a=2147483647;
//        int b=a+1;
//        System.out.println(b);
        BigDecimal a=new BigDecimal("100");
        long l1=10;long l2=10L;
        System.out.println(l1);
        System.out.println(l2);

    }
    @Test
    public void TestAnother(){
        Abc abc=new Abc();
        Object method = abc.method(new Object());
    }


}
class C1 extends C2{


    String c1Name;
}
class C2 extends C3{
    String c2Name;


}
class C3  {
    String c3Name="c3Name";


}
class Abc<T>{
    private String name;
    public T method(T t){
        return null;

    }
}


