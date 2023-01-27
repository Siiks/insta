package com.example.inst.Payload.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseFile {

    private String name;
    private String DownloadURL;
    private long size;

}
