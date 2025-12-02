package com.tropisure.adminserviceapi.repository;

import com.tropisure.adminserviceapi.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {}
