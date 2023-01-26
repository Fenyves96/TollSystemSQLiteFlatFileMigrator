import hu.fenyvesvolgyimate.tollsystem.entity.Vignette;
import hu.fenyvesvolgyimate.tollsystem.exception.FutureTimeException;
import hu.fenyvesvolgyimate.tollsystem.exception.MissingRegistrationNumberException;
import hu.fenyvesvolgyimate.tollsystem.exception.MissingTypeException;
import hu.fenyvesvolgyimate.tollsystem.exception.NullVignetteException;
import hu.fenyvesvolgyimate.tollsystem.validaton.MigrateVignetteValidator;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class MigrateVignetteValidatorTests {
    MigrateVignetteValidator validator = new MigrateVignetteValidator();
    @Test
    public void test() throws ParseException {
        assertThrowsExactly(NullVignetteException.class, () -> validator.validate(null));
        Vignette vignette = new Vignette();
        vignette.setRegistrationNumber(null);
        vignette.setType("asd");
        assertThrowsExactly(MissingRegistrationNumberException.class,() ->  validator.validate(vignette));

        vignette.setRegistrationNumber("ABC-123");
        vignette.setType(null);
        assertThrowsExactly(MissingTypeException.class, () -> validator.validate(vignette));

        vignette.setType("asd");
        vignette.setValidFrom(new SimpleDateFormat("yyyy-MM-dd").parse("2024-01-01"));
        assertThrowsExactly(FutureTimeException.class,() ->  validator.validate(vignette));
    }
}
