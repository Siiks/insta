package com.example.inst.Service;

import com.example.inst.Model.Accounts;
import com.example.inst.Model.Photos;
import com.example.inst.Repository.AccountsRepository;
import com.example.inst.Repository.PhotosRepository;
import com.example.inst.Security.Services.AccountDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class PhotosService {
    @Autowired
    PhotosRepository photosRepository;
    @Autowired
    private AccountsRepository accountsRepository;

    public Photos store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        AccountDetailsImpl accountDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Accounts accounts = accountsRepository.findById(accountDetails.getId()).get();
        Photos photos = new Photos(fileName, file.getBytes(), accounts);

        return photosRepository.save(photos);
    }

    public Photos getFile(Long id) {
        return photosRepository.findById(id).get();
    }

    public Stream<Photos> getAllFiles() {
        return photosRepository.findAll().stream();
    }
}
