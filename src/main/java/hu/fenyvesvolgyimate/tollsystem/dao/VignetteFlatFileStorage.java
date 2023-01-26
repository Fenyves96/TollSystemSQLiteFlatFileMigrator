package hu.fenyvesvolgyimate.tollsystem.dao;

import hu.fenyvesvolgyimate.tollsystem.VignetteTimeConverter;
import hu.fenyvesvolgyimate.tollsystem.entity.Vignette;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class VignetteFlatFileStorage extends EmergencyVignetteStorage{
    VignetteTimeConverter converter = new VignetteTimeConverter();
    public void saveAll(List<Vignette> vignettes){
        vignettes.forEach(this::save);
    }
    private void save(Vignette vignette) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(vignetteFilePath, true);
            fileWriter.write(generateVignetteRow(vignette) + System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateVignetteRow(Vignette vignette) {
        return vignette.getRegistrationNumber() + SEPARATOR +
                vignette.getVehicleCategory() + SEPARATOR +
                vignette.getType() + SEPARATOR +
                vignette.getPrice() + SEPARATOR +
                converter.convertUTCToZuluDate(vignette.getDateOfPurchase())  + SEPARATOR +
                converter.convertUTCToZuluDate(vignette.getValidFrom()) + SEPARATOR +
                converter.convertUTCToZuluDate(vignette.getValidTo());
    }
}
