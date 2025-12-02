package com.tropisure.adminserviceapi.service;

import com.tropisure.adminserviceapi.entity.AuditLog;
import com.tropisure.adminserviceapi.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository repository;

    public void log(String action, String entity, String performedBy, UUID targetEntityId) {
        repository.save(AuditLog.builder()
                .action(action)
                .targetEntity(entity)
                .performedBy(performedBy)
                .createdAt(LocalDateTime.now())
                .targetEntityId(targetEntityId)
                .build());
    }
}

