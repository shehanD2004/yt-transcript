package com.example.transcript.controller;

import com.example.transcript.service.TranscriptService;
import com.example.transcript.model.TranscriptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TranscriptController {

    @Autowired
    private TranscriptService transcriptService;

    @CrossOrigin
    @GetMapping("/transcript")
    public ResponseEntity<?> getTranscript(@RequestParam String videoId) {
        try {
            String transcript = transcriptService.fetchTranscript(videoId);
            return ResponseEntity.ok(new TranscriptResponse(transcript));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
