package com.ptit.sqa_project_main.services;

import com.ptit.sqa_project_main.models.Client;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
public class StringService {
    public String decodeUrl(String input) {
        try {
            String output = URLDecoder.decode(input, StandardCharsets.UTF_8.toString());
            return output;
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
        return null;
    }
}
