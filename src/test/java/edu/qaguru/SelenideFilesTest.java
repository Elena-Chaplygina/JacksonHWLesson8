package edu.qaguru;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFilesTest {
    ClassLoader cl = SelenideFilesTest.class.getClassLoader();

    @Test
    void zipXlsParseTest() throws Exception {
        try (InputStream resource = cl.getResourceAsStream("AllCombinations.zip");
             ZipInputStream zis = new ZipInputStream(resource)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                byte[] bytes = zis.readAllBytes();
                XLS content = new XLS(bytes);
                assertThat(content.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue()).contains("byte");
            }
        }
    }


    @Test
    void zipPdfParseTest() throws Exception {
        try (InputStream resource = cl.getResourceAsStream("qa_guru.zip");
             ZipInputStream zis = new ZipInputStream(resource)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                CSVReader reader1 = new CSVReader(new InputStreamReader(zis));

                List<String[]> content = reader1.readAll();
                assertThat(content.get(0)[1]).contains("lesson");


            }

        }

    }


    @Test
    void zipCsvParseTest() throws Exception {
        try (InputStream resource = cl.getResourceAsStream("cli-se.zip");
             ZipInputStream zis = new ZipInputStream(resource)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                PDF content = new PDF(zis);
                assertThat(content.text.contains("Архитектура клиент-сервер"));



            }

        }

    }

}