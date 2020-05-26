package org.example.es.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author HackerStar
 * @create 2020-05-26 14:55
 */
@Document(indexName = "esitem", type = "Item")
public class Item {
    @Id
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer id;
    @Field(index = true, store = true, type = FieldType.text, analyzer = "ik_smart")
    private String title;
    @Field(index = true, store = true, type = FieldType.text, analyzer = "ik_smart")
    private String content;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
