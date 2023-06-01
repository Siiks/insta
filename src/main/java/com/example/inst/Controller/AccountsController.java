package com.example.inst.Controller;

import com.example.inst.Model.Accounts;
import com.example.inst.Model.Settings;
import com.example.inst.Repository.AccountsRepository;
import com.example.inst.Service.AccountsService;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class AccountsController {

    @Autowired
    AccountsService accountsService;
    @Autowired
    private AccountsRepository accountsRepository;

    @GetMapping("/accounts")
    public ResponseEntity<List<Accounts>> listAccounts(){
        return new ResponseEntity<>(accountsService.findAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Accounts> account(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(accountsService.findAccountsById(id), HttpStatus.OK);
    }

    @GetMapping("/account/followers/{following_id}")
    public ResponseEntity<List<Accounts>> accountFollowers(@PathVariable Long following_id) throws Exception {
        return new ResponseEntity<>(accountsService.findFollowersByUserId(following_id), HttpStatus.OK);
    }
    @GetMapping("/account/followings/{id}")
    public ResponseEntity<List<Accounts>> accountFollowings(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(accountsService.findFollowingByUserId(id), HttpStatus.OK);
    }
    @GetMapping("/account/settings/{id}")
    public ResponseEntity<Settings> accountSettings(@PathVariable Long id){
        return new ResponseEntity<>(accountsService.findSettingByAccId(id), HttpStatus.OK);
    }
    @DeleteMapping("/delete/account/{id}")
    public String deleteUser(@PathVariable Long id) throws Exception {
        Accounts accounts = accountsService.findAccountsById(id);
        try{
            accountsService.deleteAccountById(id);
            return "El usuario " + accounts.getUsername() + " ha sido borrado correctamente.";
        }catch (Exception e){
            return e.toString();
        }

    }

    @PutMapping("/update/account/{id}")
    public ResponseEntity<Accounts> updateAccount(@RequestBody Accounts accounts, @PathVariable Long id){
        accountsService.updateAccountById(accounts,id);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    @PutMapping("/accounts/follow/{user_id}/{follow_id}")
    public ResponseEntity<Set<Accounts>> followAccount(@PathVariable Long user_id, @PathVariable Long follow_id){
        return new ResponseEntity<>(accountsService.followUser(user_id, follow_id), HttpStatus.OK);
    }
    @DeleteMapping("/accounts/unfollow/{user_id}/{follow_id}")
    public ResponseEntity<Set<Accounts>> unfollowAccount(@PathVariable Long user_id, @PathVariable Long follow_id){
        return new ResponseEntity<>(accountsService.unfollowUser(user_id, follow_id), HttpStatus.OK);
    }
}
