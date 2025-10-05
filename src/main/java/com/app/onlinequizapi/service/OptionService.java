package com.app.onlinequizapi.service;

import com.app.onlinequizapi.model.Option;
import com.app.onlinequizapi.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionService {

    private final OptionRepository optionRepository;

    @Autowired
    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public Option createOption(Option option) {
        if (option.getText() == null || option.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Option text cannot be empty");
        }
        return optionRepository.save(option);
    }

    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    public Optional<Option> getOptionById(Long id) {
        return optionRepository.findById(id);
    }

    public void deleteOption(Long id) {
        optionRepository.deleteById(id);
    }
}
