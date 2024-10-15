package beppy;

import java.util.Objects;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;

final class RootDir
{
    private final static String WIN_MAC_DIR_NAME = "bptracker";
    private final static String NIX_DIR_NAME = '.' + WIN_MAC_DIR_NAME;

    final static Path PATH = generatePath();

    static boolean exists() { return Files.exists(RootDir.PATH); }

    static Path create()
    {
        try
        {
            return Files.createDirectory(RootDir.PATH);
        }
        catch(IOException err)
        {
            err.printStackTrace();
            System.exit(111);
            return null;
        }
    }

    private static Path generatePath()
    {
        final String lowerCaseOSName =
            Objects.requireNonNull(
                System.getProperty("os.name"),
                "Null \"os.name\" sys property.")
            .toLowerCase();

        if(lowerCaseOSName.contains("win"))
        {
            final String windowsAppDataDir =
                Objects.requireNonNull(
                    System.getenv("APPDATA"),
                    "Null Windows \"APPDATA\" env variable.");

            return Path.of(windowsAppDataDir, RootDir.WIN_MAC_DIR_NAME).toAbsolutePath();
        }

        if(lowerCaseOSName.contains("mac"))
        {
            final String macHomeDir =
                Objects.requireNonNull(
                    System.getProperty("user.home"),
                    "Null Mac \"user.home\" sys property.");

            return Path.of(macHomeDir, "Library", "Application Support", RootDir.WIN_MAC_DIR_NAME).toAbsolutePath();
        }

        if(lowerCaseOSName.contains("nix") || lowerCaseOSName.contains("linux"))
        {
            final String nixHomeDir =
                Objects.requireNonNull(
                    System.getProperty("user.home"),
                    "Null nix \"user.home\" sys property.");

            return Path.of(nixHomeDir, RootDir.NIX_DIR_NAME).toAbsolutePath();
        }

        throw new Error(String.format("Unrecognized operating system: \"%s\"", lowerCaseOSName));
    }
}
