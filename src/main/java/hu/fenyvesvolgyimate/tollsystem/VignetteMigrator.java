package hu.fenyvesvolgyimate.tollsystem;

import hu.fenyvesvolgyimate.tollsystem.dao.EmergencyVignetteStorage;
import hu.fenyvesvolgyimate.tollsystem.dao.SQLLiteVignetteMigrationStorage;
import hu.fenyvesvolgyimate.tollsystem.dao.VignetteFlatFileStorage;
import hu.fenyvesvolgyimate.tollsystem.entity.Vignette;
import hu.fenyvesvolgyimate.tollsystem.exception.TollSystemException;
import hu.fenyvesvolgyimate.tollsystem.validaton.MigrateVignetteValidator;

import java.util.List;

public class VignetteMigrator {
    MigrateVignetteValidator validator = new MigrateVignetteValidator();
    SQLLiteVignetteMigrationStorage vignetteOldStorage = new SQLLiteVignetteMigrationStorage();
    VignetteFlatFileStorage vignetteNewStorage = new VignetteFlatFileStorage();
    public void start() {
        List<String> registrationNumbers = vignetteOldStorage.listUniqueRegistrationNumbers();
        registrationNumbers.forEach(r -> {
            migrateVignetteByRegistrationNumber(r);
        });
    }

    private void migrateVignetteByRegistrationNumber(String registrationNumber) {
        List<Vignette> vignettes = vignetteOldStorage.findVignettesByRegistrationNumber(registrationNumber);
        validateVignettes(vignettes);
        vignetteNewStorage.saveAll(vignettes);
    }
    private void validateVignettes(List<Vignette> vignettes) {
        try {
            vignettes.forEach(vignette -> {
                validator.validate(vignette);
            });
        }catch (TollSystemException exception){
            System.out.println("Cannot migrate vignettes for registration number : " + vignettes.get(0).getRegistrationNumber());
        }
    }
}
