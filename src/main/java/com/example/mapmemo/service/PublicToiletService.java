package com.example.mapmemo.service;

import com.example.mapmemo.entity.PublicToilet;
import com.example.mapmemo.repository.PublicToiletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicToiletService {

    private final PublicToiletRepository toiletRepository;

    @Transactional
    public void save(MultipartFile file) throws Exception {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            String publicId = row.getCell(0).getStringCellValue();

            // 중복 방지
            if (toiletRepository.existsById(publicId)) continue;

            Double latitude = getDoubleCellValue(row, 20);
            Double longitude = getDoubleCellValue(row, 21);

            // 위경도 없으면 마커 못 찍으니 skip
            if (latitude == null || longitude == null) continue;

            PublicToilet toilet = new PublicToilet(
                    publicId,
                    row.getCell(3).getStringCellValue(),   // 화장실명
                    row.getCell(4).getStringCellValue(),   // 도로명주소
                    latitude,
                    longitude,
                    row.getCell(17).getStringCellValue()  // 개방시간
            );

            toiletRepository.save(toilet);
        }
    }

    // 🔥 핵심: 문자열/숫자 모두 처리
    private Double getDoubleCellValue(Row row, int index) {
        if (row.getCell(index) == null) return null;

        return switch (row.getCell(index).getCellType()) {
            case NUMERIC -> row.getCell(index).getNumericCellValue();
            case STRING -> {
                String value = row.getCell(index).getStringCellValue();
                yield value.isBlank() ? null : Double.parseDouble(value);
            }
            default -> null;
        };
    }

    public List<PublicToilet> findAll(){
        return toiletRepository.findAll();
    }

    public List<PublicToilet> findAllForMap() {
        return toiletRepository.findAll().stream()
                .filter(t -> t.getLatitude() != null && t.getLongitude() != null)
                .toList();
    }

    public List<PublicToilet> findNearBy(
            double lat,
            double lng,
            double radius
    ) {
        System.out.println("findNearBy called: " + lat + ", " + lng + ", " + radius);
        return toiletRepository.findNear(lat, lng, radius);
    }

    public List<PublicToilet> findInBounds(double minLat, double maxLat, double minLng, double maxLng) {
        return toiletRepository.findInBounds(minLat,maxLat,minLng,maxLng);
    }
}
