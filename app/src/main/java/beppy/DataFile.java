package beppy;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.List;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

final class DataFile
{
    final static List<String> HEADER_STRINGS = List.of("Systolic", "Diastolic", "CreatedAt");

    final static String NAME = "data.csv";

    final static Path DIR_PATH = RootDir.PATH;

    final static Path PATH = Path.of(RootDir.PATH.toString(), DataFile.NAME);

    static boolean exists() { return Files.exists(PATH); }

    static Path create()
    {
        try
        {
            if( ! RootDir.exists())
            {
                RootDir.create();
            }

            final Path file = Files.createFile(DataFile.PATH);
            final String header = String.join(", ", DataFile.HEADER_STRINGS);
            return Files.writeString(file, header + '\n');
        }
        catch(IOException err)
        {
            err.printStackTrace();
            System.exit(222);
            return null;
        }
    }

    static Path append(String systolic, String diastolic)
    {
        if( ! systolic.chars().allMatch(Character::isDigit))
        {
            throw new Error(String.format("Systolic contains non digit character: \"%s\"", systolic));
        }

        if( ! diastolic.chars().allMatch(Character::isDigit))
        {
            throw new Error(String.format("Diastolic contains non digit character: \"%s\"", diastolic));
        }

        return DataFile.append(Integer.parseInt(systolic), Integer.parseInt(diastolic));
    }

    static Path append(int systolic, int diastolic)
    {
        try
        {
            return Files.writeString(
                DataFile.PATH,
                String.format("%d, %d, %3$tY/%3$tm/%3$td-%3$tH:%3$tM:%3$tS\n", systolic, diastolic, LocalDateTime.now()),
                StandardOpenOption.APPEND
            );
        }
        catch (IOException err)
        {
            err.printStackTrace();
            System.exit(233);
            return null;
        }
    }

    static Stream<String> read(boolean includeHeader)
    {
        try
        {
            return includeHeader ? Files.lines(DataFile.PATH) : Files.lines(DataFile.PATH).skip(1);
        }
        catch (IOException err)
        {
            err.printStackTrace();
            System.exit(244);
            return null;
        }
    }

    static Stream<String> read()
    {
        return read(false);
    }

    static Stream<String[]> readTo2d(boolean includeHeader)
    {
        return DataFile.read(includeHeader)
            .map(line ->
                Arrays.stream(line.split(","))
                    .map(String::strip).toArray(String[]::new)
            );
    }

    static Stream<String[]> readTo2d()
    {
        return readTo2d(false);
    }
}
