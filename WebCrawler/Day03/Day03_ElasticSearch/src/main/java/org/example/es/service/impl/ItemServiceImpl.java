package org.example.es.service.impl;

import org.example.es.dao.ItemRepository;
import org.example.es.pojo.Item;
import org.example.es.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-26 15:00
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }

    @Override
    public void saveAll(List<Item> items) {
        itemRepository.saveAll(items);
    }

    @Override
    public Iterable<Item> findAll() {
        Iterable<Item> items = itemRepository.findAll();
        return items;
    }

    @Override
    public Page<Item> findByPage(int page, int rows) {
        Page<Item> items = itemRepository.findAll(PageRequest.of(page - 1, rows));
        return items;
    }

    @Override
    public List<Item> findByTitleAndContent(String title, String content) {
        List<Item> items = itemRepository.findByTitleAndContent(title, content);
        return items;
    }

    @Override
    public Page<Item> findByTitleOrContent(String title, String content, Integer page, Integer rows) {
        Page<Item> items = itemRepository.findByTitleOrContent(title, content, PageRequest.of(page-1, rows));
        return items;
    }

    @Override
    public Page<Item> findByTitleAndContentAndIdBetween(String title, String content, Integer min, Integer max, int page, int rows) {
        Page<Item> items = itemRepository.findByTitleAndContentAndIdBetween(title, content, min, max, PageRequest.of(page-1, rows));
        return items;
    }
}
