package com.example.inst.Service;

import com.example.inst.Model.Accounts;
import com.example.inst.Model.Photos;
import com.example.inst.Model.Settings;
import com.example.inst.Repository.AccountsRepository;
import com.example.inst.Repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountsService {

    @Autowired
    AccountsRepository accountsRepository;
    @Autowired
    private SettingsRepository settingsRepository;

    public List<Accounts> findAllAccounts(){
        return accountsRepository.findAll();
    }
    public Accounts findAccountsById(Long id) throws Exception {
        if (accountsRepository.findById(id).isPresent()) {
            return accountsRepository.findById(id).get();
        }else{
            throw new Exception("No ha funcionado");
        }
    }
    public void deleteAccountById(Long id){
        if (accountsRepository.findById(id).isPresent()){
            accountsRepository.deleteById(id);
        }else{
            System.out.println("Account does not exist");
        }
    }

    public Accounts updateAccountById(Accounts accounts, Long id){
        Accounts newAcc = accountsRepository.findById(id).get();
        newAcc.setUsername(accounts.getUsername());
        newAcc.setTlf(accounts.getTlf());
        newAcc.setEmail(accounts.getEmail());
        newAcc.setPassword(accounts.getPassword());
        accountsRepository.save(newAcc);
        return newAcc;
    }

    public Settings findSettingByAccId(Long id){
        Optional<Accounts> acc = accountsRepository.findById(id);
        Optional<Settings> settings = settingsRepository.findByAccountsId(acc.get().getId());
        return settings.get();
    }

    public List<Accounts> findFollowersByUserId(Long following_id) throws Exception {
        if ( accountsRepository.findById(following_id).isPresent()) {
            List<Accounts> list = accountsRepository.findFollowersByUserId(following_id);
            return list;
        }else{
            throw new Exception();
        }
    }

    public List<Accounts> findFollowingByUserId(Long id) throws Exception {
        if (accountsRepository.findById(id).isPresent()) {
            List<Accounts> list = accountsRepository.findFollowingsByUserId(id);
            return list;
        }else {
            throw new Exception();
        }

    }


    @Transactional
    public Set<Accounts> followUser(Long accId, Long followed_id){
        Optional<Accounts> accounts = accountsRepository.findById(accId);
        Optional<Accounts> followedAcc = accountsRepository.findById(followed_id);

        if (accounts.isPresent() && followedAcc.isPresent()){
            accounts.get().getFollowing().add(followedAcc.get());
            accountsRepository.save(accounts.get());
            System.out.println("Haz seguido al usuario: " + followed_id);
        }else {
            System.out.println("No funca nene");
        }
        return accounts.get().getFollowing();
    }

    public Set<Accounts> unfollowUser(Long accId, Long followed_id){
        Optional<Accounts> accounts = accountsRepository.findById(accId);
        Optional<Accounts> followedAcc = accountsRepository.findById(followed_id);

        if (accounts.isPresent() && followedAcc.isPresent()){
            accounts.get().getFollowing().remove(followedAcc.get());
            accountsRepository.save(accounts.get());
            System.out.println("Haz seguido al usuario: " + followed_id);
        }else {
            System.out.println("No funca nene");
        }
        return accounts.get().getFollowing();
    }

}
