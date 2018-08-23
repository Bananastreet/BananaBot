package com.discordbot.utils;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BarcodeGenerator {

    private String barCodePath = "./barcodes/";

    public void createBarCode128(String fileName) {
        try {
            Code128Bean bean = new Code128Bean();
            final int dpi = 160;

            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(2.8f / dpi));

            bean.doQuietZone(false);

            //Open output file
            File outputFile = new File(barCodePath + fileName + ".JPG");

            FileOutputStream out = new FileOutputStream(outputFile);

            BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

            //Generate the barcode
            bean.generateBarcode(canvas, fileName);

            //Signal end of generation
            canvas.finish();

            System.out.println("Bar Code is generated successfully…");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
