package com.example.inst.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;


    @OneToOne(mappedBy = "accounts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Settings settings;
    @NotNull
    @Getter
    @Setter
    private String username;

    @NotNull
    @Getter
    @Setter
    private String email;

    @NotNull
    @Getter
    @Setter
    private String tlf;

    @NotNull
    @Getter
    @Setter
    private String password;

    @OneToMany(mappedBy = "accounts")
    @JsonIgnore
    @Nullable
    private List<Photos> photos;

    @ManyToMany(mappedBy = "following", cascade = CascadeType.ALL)
    @JsonIgnore
    @Nullable
    @Getter
    @Setter
    private Set<Accounts> followers = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="accounts_followers",
            joinColumns={@JoinColumn(name="followingId", referencedColumnName = "id")},
            inverseJoinColumns={@JoinColumn(name="followerId", referencedColumnName = "id")})
    @JsonIgnore
    @Nullable
    @Getter
    @Setter
    private Set<Accounts> following = new HashSet<>();


    public Accounts(Long id, String username, String email, String tlf, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.tlf = tlf;
        this.password = password;
    }

    public Accounts(String email, String username,  String password, String tlf) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.tlf = tlf;
    }


}
