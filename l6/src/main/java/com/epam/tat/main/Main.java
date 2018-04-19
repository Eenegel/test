package com.epam.tat.main;

import java.io.FileNotFoundException;
import java.util.*;
import com.epam.tat.shape.Shape;
import com.epam.tat.parse.Parse;
import com.epam.tat.file.FileWorker;
import org.apache.commons.cli.*;
import com.epam.tat.shape.ShapeComparator;

/**
 * @author Tatiana Ermolitskaya
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        Options options = new Options();
        options.addOption("a", "path", true, "File path");
        options.addOption("b", "plain", true, "Arguments");
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cmd = commandLineParser.parse(options, args);
        int n = cmd.getArgs().length + 1;
        String[] parts = new String[n];
        if (cmd.hasOption("path")) {
            String filePath  = cmd.getOptionValue("path");
            String dataFromFile = FileWorker.read(filePath);
            System.out.println(dataFromFile);
            parts = dataFromFile.split("\n");
        } else {
            String dataArgs = cmd.getOptionValue("plain");
            String[] remainder = cmd.getArgs();
            parts[0] = dataArgs;
            System.arraycopy(remainder, 0, parts, 1, remainder.length);
        }
        Set<Shape> shape = new HashSet<Shape>();
        for (String p : parts) {
            shape.add(Parse.createShape(p));
        }
        ShapeComparator comp = new ShapeComparator();
        Set<Shape> sortedSet = new TreeSet<Shape>(comp);
        sortedSet.addAll(shape);
        for (Shape s : sortedSet) {
            System.out.println(s);
        }
    }
}
