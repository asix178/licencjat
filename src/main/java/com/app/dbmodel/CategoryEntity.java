package com.app.dbmodel;

import com.app.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID domainId;
    private String name;

    public static CategoryEntity fromDomain(Category category) {
        return new CategoryEntity(null,category.getId(),category.getName());
    }

    public Category toDomain() {
        return new Category(domainId,name);
    }
}
