package com.example.inst.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class Photos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private String name;


    @Lob
    @NotNull
    private byte[] image;



    @ManyToOne
    @JoinColumn(name="accounts_id", nullable=false, referencedColumnName = "id")
    private Accounts accounts;


    public Photos(String title) {
        this.name = title;
    }

    public Photos(String fileName, byte[] bytes, Accounts accounts) {
        this.name = fileName;
        this.accounts = accounts;
        this.image = bytes;
    }
}
