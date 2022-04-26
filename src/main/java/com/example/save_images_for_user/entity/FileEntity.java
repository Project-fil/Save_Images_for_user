package com.example.save_images_for_user.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "file_table")
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity implements Serializable {

    private static final long serialVersionUID = 6774470088092553797L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "size")
    private long size;

    @OneToOne(mappedBy = "fileEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private TagEntity tagEntity;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date cratedAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FileEntity that = (FileEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public FileEntity(String name, String path, String contentType, long size) {
        this.name = name;
        this.path = path;
        this.contentType = contentType;
        this.size = size;
    }
}
