package com.meeket.app.models.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meeket.app.models.user.User;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name ="myfile")
public class MyFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id" , referencedColumnName = "id")
    @JsonIgnore
    private User userId;

    @Column(name="name" , nullable = false)
    private String name;

    @Column(name="code" , nullable = false)
    private String code;

    @Column(name="size" , nullable = false)
    private long size;

    @UpdateTimestamp
    private Timestamp last_update;

    @UpdateTimestamp
    private Timestamp lock_time;

}
