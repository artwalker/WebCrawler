package org.example.test;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Set;

/**
 * @author HackerStar
 * @create 2020-05-21 10:46
 */
public class JsoupTest {
    @Test
    public void testJouspUrl() throws Exception {
        /**
         * 解析URL地址
         *      第一个参数：访问的url
         *      第二个参数：访问时候的超时时间
         */
        Document dom = Jsoup.parse(new URL("https://www.apple.com/cn"), 1000);
        //使用标签选择器，获取title标签中的内容
        String title = dom.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void testJsoupString() throws Exception {
        //使用工具类读取文件，获取字符串
        String html = FileUtils.readFileToString(new File("/Development/Java/IDEA_Project/WebCrawler/Day01/src/web/Test.html"), "UTF-8");
        //解析字符串
        Document dom = Jsoup.parse(html);
        //获取title的内容
        Element title = dom.getElementsByTag("title").first();
        System.out.println(title);
    }

    @Test
    public void testJsoupHtml() throws Exception {
        //解析文件
        Document dom = Jsoup.parse(new File("/Users/XinxingWang/Development/Java/IDEA_Project/WebCrawler/Day01/src/web/Test.html"), "UTF-8");
        //获取title的内容
        Element title = dom.getElementsByTag("title").first();
        System.out.println(title);
    }

    @Test
    public void testJsoupDom() throws Exception {
        //解析文件
        Document dom = Jsoup.parse(new File("/Development/Java/IDEA_Project/WebCrawler/Day01/src/web/Test.html"), "UTF-8");

        /**
         * 获取元素
         */
        //1.根据id查询元素getElementById
        //Element element = dom.getElementById("city_zz");
        //2.根据标签获取元素getElementsByTag
        //Element element = dom.getElementsByTag("span").first();
        //3.根据class获取元素getElementByClass
        //Element element = dom.getElementsByClass("class_a class_b").first();
        //Element element = dom.getElementsByClass("class_a").first();
        //4.根据属性获取原色getElementByAttribute
        //Element element = dom.getElementsByAttribute("href").first();
        Element element = dom.getElementsByAttribute("id").first();
        System.out.println(element);

    }

    @Test
    public void testData() throws Exception {
        //解析文件，获取Document
        Document dom = Jsoup.parse(new File("/Development/Java/IDEA_Project/WebCrawler/Day01/src/web/Test.html"), "UTF-8");
        //根据id获取元素
        Element element = dom.getElementById("test");
        String str = "";

        /**
         * 从元素中获取数据
         */
        //1.从元素中获取id
        str = element.id();
        System.out.println("获取到的id是：" + str);

        //2.从元素中获取className
        Set<String> strings = element.classNames();
        System.out.print("获取到的classNames是：" );
        strings.forEach( string-> System.out.print(string + "\t"));
        System.out.println();

        //3.从元素中获取属性的值attr
        String aClass = element.attr("class");
        String id = element.attr("id");
        System.out.println("获取的属性的值为:" + "id:" + id + "\tclass:" + aClass);

        //4.从元素中获取所有属性attributes
        Attributes attributes = element.attributes();
        System.out.println("所有属性：" + attributes);

        //5.从元素中获取文本内容text
        String text = element.text();
        System.out.println("文本内容是：" + text);
    }

    @Test
    public void testSelector() throws Exception {
        //解析文件，获取Document
        Document dom = Jsoup.parse(new File("/Users/XinxingWang/Development/Java/IDEA_Project/WebCrawler/Day01/src/web/Test.html"), "UTF-8");

        //tagname: 通过标签查找元素，比如：span
        Elements elements = dom.select("span");
        System.out.print("通过标签查找元素：");
        elements.forEach(element -> System.out.print(element + "\t"));
        System.out.println();

        //#id: 通过ID查找元素，比如：#test
        Element element = dom.select("#test").first();
        System.out.println("通过ID查找元素:" + element);

        //.class：通过class名称查找元素，比如：.class_a
        Element element1 = dom.select(".class_a").first();
        System.out.println("通过class名称查找元素:" + element1);

        //[attribute]: 利用属性查找元素，比如：[abc]
        Element element2 = dom.select("[abc]").first();
        System.out.println("利用属性查找元素:" + element2);

        //[attr=value]: 利用属性值来查找元素，比如：[class=s_name]
        Elements elements1 = dom.select("[class=s_name]");
        System.out.print("通过标签查找元素：");
        elements1.forEach(element3 -> System.out.print(element3 + "\t"));
        System.out.println();
    }

    @Test
    public void testSelector2() throws Exception {
        //解析文件，获取Document
        Document dom = Jsoup.parse(new File("/Development/Java/IDEA_Project/WebCrawler/Day01/src/web/Test.html"), "UTF-8");

        //el#id: 元素+ID，比如： h3#city_zz
        System.out.println("el#id:" + dom.select("h3#city_zz").first());
        //el.class: 元素+class，比如： li.class_a
        System.out.println("el.class:" + dom.select("li.class_a").first());
        //el[attr]: 元素+属性名，比如： span[abc]
        System.out.println("el[attr]:" + dom.select("span[abc]").first());
        //任意组合: 比如：span[abc].s_name
        System.out.println("任意组合: " + dom.select("pan[abc].s_name").first());
        //ancestor child: 查找某个元素下子元素，比如：.city_con li 查找"city_con"下的所有li
        System.out.println("ancestor child:\n" + dom.select(".city_con li"));
        //parent > child: 查找某个父元素下的直接子元素，比如：
        //.city_con > ul > li 查找city_con第一级（直接子元素）的ul，再找所有ul下的第一级li
        System.out.println("parent > child:\n" + dom.select(".city_con > ul > li"));
        //parent > *: 查找某个父元素下所有直接子元素
        System.out.println("parent > *:\n" + dom.select(".city_con > ul > *"));
    }
}
