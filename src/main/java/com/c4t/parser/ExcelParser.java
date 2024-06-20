package com.c4t.parser;

import com.c4t.entity.Customer;
import com.c4t.repository.CustomerRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
public class ExcelParser
{
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> parse(InputStream inputStream) throws IOException {
        List<Customer> customers = new ArrayList<>();

        try
        {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("sheet1");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Customer customer = new Customer();
                    customer.setName(row.getCell(0).getStringCellValue());
                    customer.setEmail(row.getCell(1).getStringCellValue());

                    // setting into format for PhoneNo.
                    DecimalFormat decimalFormat = new DecimalFormat("#");
                    String phone = decimalFormat.format(row.getCell(2).getNumericCellValue());
                    customer.setPhone(phone);

                    customers.add(customer);
                }
            }
            workbook.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return customers;
    }

    public boolean isValidExcelFile(MultipartFile file)
    {
        return Objects.equals(file.getContentType(),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public void generateExcel(HttpServletResponse httpServletResponse) throws IOException {
        List<Customer> customers = customerRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("customer_info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("NAME");
        row.createCell(2).setCellValue("EMAIL");
        row.createCell(3).setCellValue("PHONE NO");

        int dataRowIndex=1;
        for(Customer customer : customers)
        {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(customer.getId());
            dataRow.createCell(1).setCellValue(customer.getName());
            dataRow.createCell(2).setCellValue(customer.getEmail());
            dataRow.createCell(3).setCellValue(customer.getPhone());
            dataRowIndex++;
        }

        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.close();
    }
}
