package com.pluralsight;

import com.github.lalyos.jfiglet.FigletFont;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


public class Main {

    final static Logger logger = (Logger) LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

            String asciiArt = FigletFont.convertOneLine("Helloooooo");
            System.out.println(asciiArt);

        // using font font2.flf, located somewhere in classpath under path /flf/font2.flf
        String asciiArt2 = FigletFont.convertOneLine(FigletFont.class.getResourceAsStream("/flf/font2.flf"), "WHOOOOP!");
        System.out.println(asciiArt2);

        logger.info("This is an INFO log");
        logger.warn("This is a warning");
        logger.error("This is an ERROR log.");
        logger.debug("This is a DEBUG log.");
        logger.info("Application finished.");


    }
}