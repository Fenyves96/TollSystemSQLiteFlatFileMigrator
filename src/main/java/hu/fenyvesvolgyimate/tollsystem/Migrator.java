package hu.fenyvesvolgyimate.tollsystem;

import hu.fenyvesvolgyimate.tollsystem.validaton.MigrateVignetteValidator;

public class Migrator {
    public static void main(String[] args) {
        VignetteMigrator migrator = new VignetteMigrator();
        migrator.start();
        System.out.println("Migration finished");
    }
}