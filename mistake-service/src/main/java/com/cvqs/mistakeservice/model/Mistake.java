package com.cvqs.mistakeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Table(name="Mistakes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE Mistakes SET is_deleted = true WHERE id=id")
@Where(clause = "is_deleted=false")
public class Mistake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "isDeleted")
    private boolean isDeleted=Boolean.FALSE;
}
