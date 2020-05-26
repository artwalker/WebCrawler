package org.example.service.impl;

import org.example.dao.ItemDao;
import org.example.pojo.Item;
import org.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-21 16:06
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    @Override
    public List<Item> findAll(Item item) {
        //声明查询条件
        Example<Item> example = Example.of(item);
        //根据查询条件进行查询数据
        List<Item> all = itemDao.findAll(example);
        return all;
    }

    @Override
    @Transactional
    public void save(Item item) {
        itemDao.save(item);
    }
}
