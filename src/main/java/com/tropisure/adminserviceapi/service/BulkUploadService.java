package com.tropisure.adminserviceapi.service;

import com.opencsv.CSVReader;
import com.tropisure.adminserviceapi.dto.BulkUploadRequest;
import com.tropisure.adminserviceapi.dto.CreateCarrierRequest;
import com.tropisure.adminserviceapi.dto.CreateMGARequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.InputStreamReader;

@Service
@RequiredArgsConstructor
public class BulkUploadService {

    private final AdminService adminService;

    public void processBulkUpload(BulkUploadRequest request, String performedBy) throws Exception {
        try (CSVReader reader = new CSVReader(new InputStreamReader(request.getFile().getInputStream()))) {
            String[] line;
            reader.readNext(); // skip header
            while ((line = reader.readNext()) != null) {
                if ("MGA".equalsIgnoreCase(request.getType())) {
                    String[] finalLine1 = line;
                    adminService.createMGA(new CreateMGARequest() {{
                        setName(finalLine1[0]);
                        setTaxId(finalLine1[1]);
                        setAddress(finalLine1[2]);
                        setContactName(finalLine1[3]);
                        setContactEmail(finalLine1[4]);
                    }}, performedBy);
                } else if ("CARRIER".equalsIgnoreCase(request.getType())) {
                    String[] finalLine = line;
                    adminService.createCarrier(new CreateCarrierRequest() {{
                        setName(finalLine[0]);
                        setNaicCode(finalLine[1]);
                        setCountry(finalLine[2]);
                        setContactEmail(finalLine[3]);
                    }}, performedBy);
                }
            }
        }
    }
}


