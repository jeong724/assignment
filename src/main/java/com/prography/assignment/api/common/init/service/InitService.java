package com.prography.assignment.api.common.init.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InitService {

    private final DatabaseTableInitializerImpl initializer;

    @Transactional
    public void init(int seed, int quantity) {
        initializer.init(seed, quantity); //db 초기화

    }
}
