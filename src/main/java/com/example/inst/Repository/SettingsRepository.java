package com.example.inst.Repository;

import com.example.inst.Model.Photos;
import com.example.inst.Model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {


    Optional<Settings> findByAccountsId(Long id);
}
