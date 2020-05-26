package org.example.es.test;

import org.example.es.pojo.Item;
import org.example.es.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-26 15:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ESTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 创建索引和映射
     */
    @Test
    public void createIndex() {
        elasticsearchTemplate.createIndex(Item.class);
        elasticsearchTemplate.putMapping(Item.class);
    }

    /**
     * 新增
     */
    @Test
    public void testSave() {
        Item item = new Item();
        item.setId(100);
        item.setTitle("ElasticSearch");
        item.setContent("使用SpringData ES完成搜索功能。");
        itemService.save(item);
    }

    /**
     * 删除
     */
    @Test
    public void testDelete() {
        Item item = new Item();
        item.setId(100);
        itemService.delete(item);
    }

    /**
     * 批量保存
     */
    @Test
    public void saveAll() {
        List<Item> items = new ArrayList<>();
        for (int i = 1; i < 101; i++) {
            Item item = new Item();
            item.setId(i);
            item.setTitle("elasticSearch"+ i +".0版本发布..，更新");
            item.setContent("ElasticSearch批量插入第" + i + "次");
            items.add(item);
        }
        itemService.saveAll(items);
    }

    /**
     * 查询所有
     */
    @Test
    public void findAll() {
        Iterable<Item> items = itemService.findAll();

        items.forEach(item -> System.out.println(item));
    }

    /**
     * 分页查询
     */
    @Test
    public void findBypage() {
        Page<Item> items = itemService.findByPage(1, 20);
        int pageNumber = 0;//判断查询了多少条
        for (Item item :
                items.getContent()) {
            pageNumber++;
            System.out.println(item);
        }
        System.out.println("查询了" + pageNumber + "条");
    }

    /**
     * 根据title和Content查询
     */
    @Test
    public void findByTitleAndContent() {
        List<Item> items = itemService.findByTitleAndContent("更新", "批量");
        items.forEach(item -> System.out.println(item));
    }

    /**
     * 根据title或Content分页查询
     */
    @Test
    public void findByTitleOrContent(){
        Page<Item> items = itemService.findByTitleOrContent("更新", "批量", 2, 20);
        int pageNumber = 0;//判断查询了多少条
        for (Item item :
                items.getContent()) {
            System.out.println(item);
        }
    }

    /**
     * 根据title和Content和id范围分页查询
     */
    @Test
    public void findByIdBetween() {
        Page<Item> items = itemService.findByTitleAndContentAndIdBetween("版本", "批量", 10, 20, 1, 10);
        int pageNumber = 0;//判断查询了多少条
        for (Item item :
                items.getContent()) {
            System.out.println(item);
            pageNumber++;
        }
        System.out.println(pageNumber);
    }
}
