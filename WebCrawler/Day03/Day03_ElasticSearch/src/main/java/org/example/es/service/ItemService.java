package org.example.es.service;

import org.example.es.pojo.Item;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-26 14:59
 */
public interface ItemService {
    void save(Item item);

    void delete(Item item);

    void saveAll(List<Item> items);

    Iterable<Item> findAll();

    Page<Item> findByPage(int page, int rows);

    List<Item> findByTitleAndContent(String title, String content);

    Page<Item> findByTitleOrContent(String title, String content, Integer page, Integer rows);

    Page<Item> findByTitleAndContentAndIdBetween(String title, String content, Integer min, Integer max, int page, int rows);
}
