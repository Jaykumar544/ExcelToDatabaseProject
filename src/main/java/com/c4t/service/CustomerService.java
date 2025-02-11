package com.c4t.service;

import com.c4t.entity.Customer;
import com.c4t.repository.CustomerRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService
{
    @Autowired
    CustomerRepository customerRepository;

    String path = "D:\\MyStudyFiles\\MyProjectStudy\\ExcelToDatabaseProject\\src\\main\\webapp\\reports";


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        List<Customer> customers = customerRepository.findAll();
        // loading and compiling file
        File file = ResourceUtils.getFile("classpath:customerReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customers);

        // Parameter, author info, optional
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("createdBy"," Jay ");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);

        //generating html, pdf, xlsx, csv report file

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\customer.html");
        } else if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\customer.pdf");
        } else if (reportFormat.equalsIgnoreCase("xlsx")) {
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\customer.xlsx"));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        } else if (reportFormat.equalsIgnoreCase("csv")) {
            JRCsvExporter exporter = new JRCsvExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleWriterExporterOutput(path+"\\customer.csv"));
            SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        }


        return reportFormat;
    }


    public String exportReportTable(String reportFormat) throws FileNotFoundException, JRException {
        List<Customer> customers = customerRepository.findAll();
        // loading and compiling file
        File file = ResourceUtils.getFile("classpath:customerReportTable.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customers);

        // Parameter, author info, optional
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("customerDataSet",dataSource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,new JREmptyDataSource());

        //generating html, pdf, xlsx, csv report file

        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\customer.html");
        } else if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\customer.pdf");
        } else if (reportFormat.equalsIgnoreCase("xlsx")) {
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path+"\\customer.xlsx"));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        } else if (reportFormat.equalsIgnoreCase("csv")) {
            JRCsvExporter exporter = new JRCsvExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleWriterExporterOutput(path+"\\customer.csv"));
            SimpleCsvExporterConfiguration configuration = new SimpleCsvExporterConfiguration();
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        }


        return reportFormat;
    }


}
