package com.fileconverter;

import java.io.File;
import java.io.IOException;

public class LibreOfficeConverter {

	// Default LibreOffice installation path on Windows
    private static final String LIBREOFFICE_PATH =
            "C:\\Program Files\\LibreOffice\\program\\soffice.exe";

    /**
     * Converts a file using LibreOffice.
     *
     * @param inputFile The file to convert
     * @param outputFolder The folder where the converted file will be created
     * @param outputFormat The desired output format
     * @return true if the conversion was successful
     */
    
    public static boolean convert(
            String inputFile,
            String outputFolder,
            String outputFormat) {

        File input = new File(inputFile);
        File output = new File(outputFolder);

        // Check input file
        if (!input.exists()) {
            System.out.println("Input file does not exist.");
            return false;
        }

        // Check output folder
        if (!output.exists()) {
            System.out.println("Output folder does not exist.");
            return false;
        }

        // Check LibreOffice
        File libreOffice = new File(LIBREOFFICE_PATH);

        if (!libreOffice.exists()) {
            System.out.println(
                    "LibreOffice was not found at: "
                    + LIBREOFFICE_PATH
            );

            return false;
        }

        try {

            ProcessBuilder processBuilder = new ProcessBuilder(
                    LIBREOFFICE_PATH,
                    "--headless",
                    "--convert-to",
                    outputFormat.toLowerCase(),
                    "--outdir",
                    outputFolder,
                    inputFile
            );

            // Redirect libre output to javas console
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            // Wait until libre finishes
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Conversion successful.");
                return true;
            }

            System.out.println(
                    "Conversion failed. Exit code: "
                    + exitCode
            );

            return false;

        } catch (IOException e) {

            System.out.println(
                    "Could not start LibreOffice."
            );

            e.printStackTrace();

            return false;

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            System.out.println(
                    "Conversion was interrupted."
            );

            return false;
        }
    }
}