package com.example.inst.Controller;

import com.example.inst.Model.Accounts;
import com.example.inst.Model.Settings;
import com.example.inst.Repository.SettingsRepository;
import com.example.inst.Service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    SettingsService settingsService;

    @PutMapping("/updateSettings")
    public ResponseEntity<Settings> updateAccount(@ModelAttribute Settings settings) {
        settingsService.updateSettingsByAccountId(settings);
        return new ResponseEntity<>(settings, HttpStatus.OK);
    }
}
