package com.example.inst.Security.Services;

import com.example.inst.Model.Accounts;
import com.example.inst.Repository.AccountsRepository;
import com.example.inst.Service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    AccountsRepository accountsRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts accounts = accountsRepository.findAccountsByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException("User not found with username " + username));
    return AccountDetailsImpl.build(accounts);
    }
}
