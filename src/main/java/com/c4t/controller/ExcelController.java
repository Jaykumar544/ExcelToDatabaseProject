package com.c4t.controller;

import com.c4t.entity.Customer;
import com.c4t.parser.ExcelParser;
import com.c4t.repository.CustomerRepository;
import com.c4t.service.CustomerService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    private ExcelParser excelParser;

    @RequestMapping({"/","/home"})
    public String home()
    {
        return "index";
    }

    @PostMapping("/setExcel")
    public ModelAndView importExcel(@RequestParam("file") MultipartFile file) throws Exception
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        if(excelParser.isValidExcelFile(file))
        {
            // Parse the Excel file and extract data
            List<Customer> customers = excelParser.parse(file.getInputStream());

            // Save the extracted data to MongoDB
            customerRepository.saveAll(customers);

            modelAndView.addObject("successMsg","Saved successfully.");

            System.out.println("All data saved in DB.");
            return modelAndView;
        }
        else
        {
            modelAndView.addObject("failedMsg"," Failed. ");
            return modelAndView;
        }
    }

    @GetMapping("/getExcel")
    public void generateExcelReport(HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=customers.xls";
        httpServletResponse.setHeader(headerKey,headerValue);

        excelParser.generateExcel(httpServletResponse);

    }

    @RequestMapping("/report/{reportFormat}")
    public RedirectView generateReport(@PathVariable String reportFormat) throws JRException, FileNotFoundException {
        // Generate the report
//        customerService.exportReport(reportFormat);         -> for non tabular form report
        customerService.exportReportTable(reportFormat);      // -> for tabular form report

        // Define the web-accessible URL based on the report format
        String reportUrl = "/reports/customer." + reportFormat.toLowerCase();

        // Ensure the file exists on the file system
        File file = new File("E:\\Extra study files\\MongoDB\\Excel_To_Database\\src\\main\\webapp" + reportUrl);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        }

        // Redirect to the web-accessible URL
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(reportUrl);
        return redirectView;
    }


    // -> only download the given reports
//    public ResponseEntity<Resource> generateReport(@PathVariable String reportFormat) throws JRException, FileNotFoundException {
//        // Generate the report
//        customerService.exportReport(reportFormat);
//
//
//        // Define the file path based on the report format
//        String filePath = "E:\\Extra study files\\MongoDB\\Excel_To_Database\\src\\main\\webapp\\reports\\customer." + reportFormat.toLowerCase();
//
//        File file = new File(filePath);
//
//        // Create a Resource from the file
//        Resource resource = new FileSystemResource(file);
//
//        // Define the Content-Disposition header to prompt for download
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customer." + reportFormat.toLowerCase());
//
//        // Return the file as a ResponseEntity
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(resource);
//    }


}
