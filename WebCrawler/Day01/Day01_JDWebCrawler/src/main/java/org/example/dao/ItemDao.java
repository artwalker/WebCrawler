package org.example.dao;

import org.example.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author HackerStar
 * @create 2020-05-21 16:02
 */
public interface ItemDao extends JpaRepository<Item, Long> {
}
