package hu.fenyvesvolgyimate.tollsystem.dao;

import hu.fenyvesvolgyimate.tollsystem.dao.SqlLiteVignetteStorage;
import hu.fenyvesvolgyimate.tollsystem.entity.Vignette;

import java.util.ArrayList;
import java.util.List;

public class SQLLiteVignetteMigrationStorage extends SqlLiteVignetteStorage {
    public List<String> listUniqueRegistrationNumbers(){
        List<Vignette> vignetteList = this.selectAll();
        List<String> registrationNumbers = new ArrayList<>();
        vignetteList.forEach(v -> {
            if(!registrationNumbers.contains(v.getRegistrationNumber())){
                registrationNumbers.add(v.getRegistrationNumber());
            }
        });
        return registrationNumbers;
    }
}
