package com.example.inst.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id ;

    @OneToOne(optional = false)
    @JoinColumn(name = "accounts_id", referencedColumnName = "id")
    @NotNull
    private Accounts accounts;
    @NotNull
    private boolean private_account;

    @NotNull
    private boolean notifications;

    @NotNull
    private boolean ads;


    public Settings(boolean private_account, boolean notifications, boolean ads, Accounts accounts) {
        this.private_account = private_account;
        this.notifications = notifications;
        this.ads = ads;
        this.accounts = accounts;
    }

}
