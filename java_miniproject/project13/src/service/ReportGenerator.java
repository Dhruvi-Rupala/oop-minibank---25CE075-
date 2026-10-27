package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ReportGenerator {

    public static void generate(Path logsFolder, Path reportFile)
            throws IOException {

        long totalDeposits = 0;
        long totalWithdrawals = 0;

        List<String> report = new ArrayList<>();

        report.add("===== END OF DAY REPORT =====");

        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(logsFolder)) {

            for (Path file : stream) {

                if (!Files.isRegularFile(file)
                        || !file.getFileName().toString().endsWith(".log")
                        || Files.size(file) == 0) {
                    continue;
                }

                BasicFileAttributes attributes =
                        Files.readAttributes(file, BasicFileAttributes.class);

                report.add("");
                report.add("File: " + file.getFileName());
                report.add("Size: " + attributes.size() + " bytes");
                report.add("Last Modified: " + attributes.lastModifiedTime());

                try (BufferedReader reader = Files.newBufferedReader(file)) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.trim().split("\\s+");

                        if (parts.length < 3) {
                            continue;
                        }

                        try {
                            long amount = Long.parseLong(parts[2]);

                            if (amount < 0) {
                                continue;
                            }

                            if (parts[0].equals("DEPOSIT")) {
                                totalDeposits += amount;
                            } else if (parts[0].equals("WITHDRAW")) {
                                totalWithdrawals += amount;
                            }
                        } catch (NumberFormatException e) {
                            report.add("Invalid transaction: " + line);
                        }
                    }
                }
            }
        }

        report.add("");
        report.add("Total Deposits: " + totalDeposits);
        report.add("Total Withdrawals: " + totalWithdrawals);
        report.add("Net Change: " + (totalDeposits - totalWithdrawals));

        Path parent = reportFile.toAbsolutePath().getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        Files.write(
            reportFile,
            report,
            java.nio.charset.StandardCharsets.UTF_8,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING
        );
    }
}