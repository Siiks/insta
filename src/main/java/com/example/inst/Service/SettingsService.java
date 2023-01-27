package com.example.inst.Service;

import com.example.inst.Model.Accounts;
import com.example.inst.Model.Settings;
import com.example.inst.Repository.AccountsRepository;
import com.example.inst.Repository.SettingsRepository;
import com.example.inst.Security.Services.AccountDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SettingsService {

    @Autowired
    SettingsRepository settingsRepository;

    @Autowired
    AccountsRepository accountsRepository;

    public Settings updateSettingsByAccountId(Settings settings){
        AccountDetailsImpl accountDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Settings newSettings = settingsRepository.findByAccountsId(accountDetails.getId()).get();
        if (settings.isAds() != newSettings.isAds()) {
            newSettings.setAds(settings.isAds());
        }else{
            newSettings.setPrivate_account(newSettings.isPrivate_account());
        }
        if (settings.isPrivate_account() != newSettings.isPrivate_account()){
            newSettings.setPrivate_account(settings.isPrivate_account());
        }else{
            newSettings.setAds(newSettings.isAds());
        }
        if (settings.isNotifications() != newSettings.isNotifications()){
            newSettings.setNotifications(settings.isNotifications());
        }else{
            newSettings.setNotifications(newSettings.isNotifications());
        }
        return settingsRepository.save(newSettings);
    }
}
