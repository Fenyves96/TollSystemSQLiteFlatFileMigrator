package hu.fenyvesvolgyimate.tollsystem.validaton;


import hu.fenyvesvolgyimate.tollsystem.entity.Vignette;
import hu.fenyvesvolgyimate.tollsystem.exception.FutureTimeException;
import hu.fenyvesvolgyimate.tollsystem.exception.MissingRegistrationNumberException;
import hu.fenyvesvolgyimate.tollsystem.exception.MissingTypeException;
import hu.fenyvesvolgyimate.tollsystem.exception.NullVignetteException;

import java.util.Date;

public class MigrateVignetteValidator {
    public void validate(Vignette vignette){
        checkNulls(vignette);
        if(vignette.getValidFrom().after(new Date()))
            throw new FutureTimeException();
    }

    private static void checkNulls(Vignette vignette) {
        if(vignette == null)
            throw new NullVignetteException();
        if(vignette.getType() == null)
            throw new MissingTypeException();
        if(vignette.getRegistrationNumber() == null)
            throw new MissingRegistrationNumberException();
    }
}
