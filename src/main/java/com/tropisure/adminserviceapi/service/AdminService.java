package com.tropisure.adminserviceapi.service;

import com.tropisure.adminserviceapi.dto.CreateCarrierRequest;
import com.tropisure.adminserviceapi.dto.CreateMGARequest;
import com.tropisure.adminserviceapi.entity.CarrierProfile;
import com.tropisure.adminserviceapi.entity.MGAProfile;
import com.tropisure.adminserviceapi.repository.CarrierProfileRepository;
import com.tropisure.adminserviceapi.repository.MGAProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final MGAProfileRepository mgaRepo;
    private final CarrierProfileRepository carrierRepo;
    private final NotificationService notificationService;
    private final AuditLogService auditLogService;
    private final ICognitoClientService cognitoService;

    @Value("${aws.cognito.user-pool-id}")
    private String userPoolId;

    public MGAProfile createMGA(CreateMGARequest request, String performedBy) {

        // Create Cognito user
        String tempPassword = UUID.randomUUID().toString() + "Ab1!";
        String username = request.getContactEmail().split("@")[0];
        cognitoService.createUser(userPoolId, username, request.getContactEmail(), tempPassword, "MGA_ADMIN");

        MGAProfile mga = MGAProfile.builder()
                .name(request.getName())
                .taxId(request.getTaxId())
                .address(request.getAddress())
                .contactName(request.getContactName())
                .contactEmail(request.getContactEmail())
                .createdAt(LocalDateTime.now())
                .build();
        mga = mgaRepo.save(mga);



        // Notify
//        notificationService.sendEmail(request.getContactEmail(),
//                "Tropisure MGA Account Created",
//                "Your MGA account has been created. Temporary password: " + tempPassword);

        // Audit log
        auditLogService.log("CREATE_MGA", mga.getName(), performedBy, mga.getId());

        return mga;
    }

    public CarrierProfile createCarrier(CreateCarrierRequest request, String performedBy) {

        String tempPassword = UUID.randomUUID().toString() + "Ab1!";
        String username = request.getContactEmail().split("@")[0];
        cognitoService.createUser(userPoolId, username, request.getContactEmail(), tempPassword, "CARRIER_ADMIN");

//        notificationService.sendEmail(request.getContactEmail(),
//                "Tropisure Carrier Account Created",
//                "Your Carrier account has been created. Temporary password: " + tempPassword);

        CarrierProfile carrier = CarrierProfile.builder()
                .name(request.getName())
                .naicCode(request.getNaicCode())
                .country(request.getCountry())
                .contactEmail(request.getContactEmail())
                .createdAt(LocalDateTime.now())
                .build();
        carrier = carrierRepo.save(carrier);
        auditLogService.log("CREATE_CARRIER", carrier.getName(), performedBy, carrier.getId());

        return carrier;
    }
}
