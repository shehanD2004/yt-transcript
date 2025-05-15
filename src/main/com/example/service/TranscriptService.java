package com.example.transcript.service;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class TranscriptService {

    public String fetchTranscript(String videoId) throws Exception {
        String command = "yt-dlp --write-auto-sub --skip-download --sub-lang en --output - https://www.youtube.com/watch?v=" + videoId;
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        StringBuilder transcript = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            transcript.append(line).append("\n");
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Failed to fetch transcript.");
        }

        return transcript.toString();
    }
}
