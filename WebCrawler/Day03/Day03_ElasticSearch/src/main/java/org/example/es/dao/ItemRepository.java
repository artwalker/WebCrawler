package org.example.es.dao;

import org.example.es.pojo.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-26 14:57
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Integer> {
    List<Item> findByTitleAndContent(String title, String content);

    Page<Item> findByTitleOrContent(String title, String content, Pageable pageable);

    Page<Item> findByTitleAndContentAndIdBetween(String title, String content, Integer min, Integer max, Pageable pageable);
}
