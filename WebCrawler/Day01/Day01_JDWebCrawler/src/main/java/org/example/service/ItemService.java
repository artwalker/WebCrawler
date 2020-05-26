package org.example.service;

import org.example.pojo.Item;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-21 16:04
 */
public interface ItemService {
    //根据条件查询数据
    public List<Item> findAll(Item item);
    //保存数据
    public void save(Item item);
}
