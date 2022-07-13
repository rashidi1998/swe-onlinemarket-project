package edu.miu.eshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Lob
    @Column(nullable = false, length = 100000)
    private String attachmentURL;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Category> subCategories;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent", nullable = true)
    private Category parent;

    public Category() {
    }

    public Category(Long id, String name, List<Category> subCategories, Category parent) {
        this.id = id;
        this.name = name;
        this.subCategories = subCategories;
        this.parent = parent;
    }

    public String getAttachmentURL() {
        return attachmentURL;
    }

    public void setAttachmentURL(String attachmentURL) {
        this.attachmentURL = attachmentURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
