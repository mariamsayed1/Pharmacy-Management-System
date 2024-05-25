package com.example.aswe.demo.Controllers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@RestController
@RequestMapping("/OCR")
public class OCRController {
    
// String inputFilePath = "C:/Users/Prof. Mahmoud Sayed/Desktop/Pharmacy-Management-System/demo/src/main/resources/static/IMAGES/Cataflam.jpg";


    @GetMapping("")
    public ModelAndView OCR() {         
        return new ModelAndView("ocr.html");
    }

    @PostMapping("")
    public ModelAndView OCR(@RequestParam ("file") String file) {

        ModelAndView mav = new ModelAndView("ocr.html");

        String directory = "C:/xamp/htdocs/Pharmacy-Management-System-2/demo/src/main/resources/static/IMAGES";

        // String fileName = file.getOriginalFilename();

        Path filePath = Paths.get(directory).resolve(file);

        // Print the resulting file path
        System.out.println("File Path: " + filePath.toString());


        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\xamp\\htdocs\\Pharmacy-Management-System-2\\demo\\src\\main\\resources\\tessdata");

            
        try {
            String ocrResult = tesseract.doOCR(new File(filePath.toString()));
            System.out.println(ocrResult);
            mav.addObject("ocrResult", ocrResult);
        } catch (TesseractException e) {
            e.printStackTrace();
        }

            return mav;
        // return new ModelAndView("redirect:/ocr/upload");
    }

}
