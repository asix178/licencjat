package com.app.dbmodel;

import com.app.model.Prize;
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
@Table(name = "prize")
public class PrizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID domainId;
    private String name;
    @ManyToOne
    private CategoryEntity category;

    public static PrizeEntity fromDomain(Prize prize) {
        return new PrizeEntity(null, prize.getId(), prize.getName(), null);
    }

    public Prize toDomain() {
        return new Prize(domainId, name, category.toDomain());
    }
}
