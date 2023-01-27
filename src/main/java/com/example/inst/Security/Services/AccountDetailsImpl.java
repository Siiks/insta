package com.example.inst.Security.Services;

import com.example.inst.Model.Accounts;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class AccountDetailsImpl implements UserDetails {
    public AccountDetailsImpl(Long id, String tlf, String username, String email, String password) {
        this.id = id;
        this.tlf = tlf;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    private static final long serialVersionUID = 1L;
    private Long id;
    private String tlf;
    private String username;
    private String email;

    @JsonIgnore
    private String password;



    public static AccountDetailsImpl build(Accounts account) {
        return new AccountDetailsImpl(
                account.getId(),
                account.getTlf(),
                account.getUsername(),
                account.getEmail(),
                account.getPassword());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getTlf(){
        return tlf;
    }
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AccountDetailsImpl accountDetails = (AccountDetailsImpl) o;
        return Objects.equals(id, accountDetails.id);
    }
}
