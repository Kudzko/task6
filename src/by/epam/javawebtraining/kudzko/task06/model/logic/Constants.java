package by.epam.javawebtraining.kudzko.task06.model.logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Constants {
    private static String[] consts = readConsts();

    public static final String ROOT = consts[0];
    public static final String ELEMENT_FLOWER = consts[1];
    public static final String ELEMENT_NAME = consts[2];
    public static final String ELEMENT_SOIL = consts[3];
    public static final String ELEMENT_ORIGIN = consts[4];
    public static final String ELEMENT_VISUAL_PARAM = consts[5];
    public static final String ELEMENT_SIZE = consts[6];
    public static final String ELEMENT_COLOR = consts[7];
    public static final String ELEMENT_LEAF_COLOR = consts[8];
    public static final String ELEMENT_GRWING_TIPS = consts[9];
    public static final String ELEMENT_TEMPERATURE = consts[10];
    public static final String ELEMENT_LIGHTING = consts[11];
    public static final String ELEMENT_WATERING = consts[12];
    public static final String ELEMENT_MULTIPLYING = consts[13];
    public static final String OUT_PUT_DOCUMENT = consts[14];


    private static String[] readConsts() {

        Properties properties = new Properties();
        try {
            FileInputStream constant = new FileInputStream
                    (".\\src\\by\\epam\\javawebtraining\\kudzko\\task06" +
                            "\\model\\logic\\constants.properties");
            properties.load(constant);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] consts = new String[15];

        consts[0] = properties.getProperty("ROOT");
        consts[1] = properties.getProperty("ELEMENT_FLOWER");
        consts[2] = properties.getProperty("ELEMENT_NAME");
        consts[3] = properties.getProperty("ELEMENT_SOIL");
        consts[4] = properties.getProperty("ELEMENT_ORIGIN");
        consts[5] = properties.getProperty("ELEMENT_VISUAL_PARAM");
        consts[6] = properties.getProperty("ELEMENT_SIZE");
        consts[7] = properties.getProperty("ELEMENT_COLOR");
        consts[8] = properties.getProperty("ELEMENT_LEAF_COLOR");
        consts[9] = properties.getProperty("ELEMENT_GRWING_TIPS");
        consts[10] = properties.getProperty("ELEMENT_TEMPERATURE");
        consts[11] = properties.getProperty("ELEMENT_LIGHTING");
        consts[12] = properties.getProperty("ELEMENT_WATERING");
        consts[13] = properties.getProperty("ELEMENT_MULTIPLYING");
        consts[14] = properties.getProperty("OUT_PUT_DOCUMENT");
        return consts;
            }

}
