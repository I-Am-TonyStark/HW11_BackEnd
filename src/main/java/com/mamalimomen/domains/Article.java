package com.mamalimomen.domains;

import com.mamalimomen.base.domains.BaseEntity;
import com.mamalimomen.base.controller.utilities.NoValidDataException;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Entity
@Table(name = "article")
@NamedQueries({
        @NamedQuery(name = "Article.findAll", query = "select a from Article a"),
        @NamedQuery(name = "Article.findAllWherePublished", query = "select a from Article a where a.isPublished = true"),
        @NamedQuery(name = "Article.findOneByTitle", query = "select a from Article a where a.title =?1")
})
public class Article extends BaseEntity<Long> implements Comparable<Article> {

    @Transient
    private static final long serialVersionUID = -4740584035704263354L;

    @Transient
    private static long count = 4;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String brief;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(updatable = false, nullable = false)
    private Date createDate;

    @Temporal(TemporalType.DATE)
    private Date lastUpdateDate;

    @Temporal(TemporalType.DATE)
    private Date publishedDate;

    @Column(nullable = false)
    private Boolean isPublished;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "article_user", joinColumns = {@JoinColumn(name = "article_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> writers = new TreeSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "article_category", joinColumns = {@JoinColumn(name = "article_id")}, inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories = new TreeSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "article_tag", joinColumns = {@JoinColumn(name = "article_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags = new TreeSet<>();

    public Article() {
        this.setId(count);
        count++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws NoValidDataException {
        if (!title.matches("[a-zA-Z\\s.,&\\d\\(\\)]{5,}")) {
            throw new NoValidDataException("Title");
        }
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) throws NoValidDataException {
        if (brief.length() < 10) {
            throw new NoValidDataException("Brief");
        }
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) throws NoValidDataException {
        if (content.length() < 10) {
            throw new NoValidDataException("Content");
        }
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public Set<User> getWriters() {
        return writers;
    }

    public void setWriters(Set<User> writers) {
        this.writers = writers;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addWriter(User writer) {
        writers.add(writer);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public String getCategoriesTitle() {
        return categories.stream().map(Category::getTitle).collect(Collectors.joining(" & "));
    }

    public String getTagsTitle() {
        return tags.stream().map(Tag::getTitle).collect(Collectors.joining(" & "));
    }

    public String getWritersName() {
        return writers.stream().map(User::getUserName).collect(Collectors.joining(" & "));
    }

    public void printCompleteInformation() {
        System.out.printf("%n%s.%nCategories : %s%nTags : %s%nCreate Date : %s%nLastUpdate Date : %s%nPublished Date : %s%nWriters : %s%nContent : %s%n%n",
                getTitle(), getCategoriesTitle(), getTagsTitle(), getCreateDate(), getLastUpdateDate(), getPublishedDate(), getWritersName(), getContent());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Article article = (Article) obj;
        return this.hashCode() == article.hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s.%nBrief: %s%n", getTitle(), getBrief());
    }

    @Override
    public int compareTo(Article a) {
        return this.getTitle().compareTo(a.getTitle());
    }
}
