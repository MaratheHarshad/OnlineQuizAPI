package com.app.onlinequizapi.controller;

import com.app.onlinequizapi.model.Option;
import com.app.onlinequizapi.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/options")
public class OptionController {

    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @PostMapping
    public ResponseEntity<Option> createOption(@RequestBody Option option) {
        Option createdOption = optionService.createOption(option);
        return ResponseEntity.status(201).body(createdOption);
    }

    @GetMapping
    public ResponseEntity<List<Option>> getAllOptions() {
        List<Option> options = optionService.getAllOptions();
        return ResponseEntity.ok(options);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Option> getOptionById(@PathVariable Long id) {
        return optionService.getOptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
        return ResponseEntity.noContent().build();
    }
}
