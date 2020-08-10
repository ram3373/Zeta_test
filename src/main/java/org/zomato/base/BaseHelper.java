package org.zomato.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class BaseHelper {

    public int RESPONSE_STATUS_CODE_200 = 200;
    public int RESPONSE_STATUS_CODE_500 = 500;
    public int RESPONSE_STATUS_CODE_403 = 403;
    public int RESPONSE_STATUS_CODE_402 = 402;
    public int RESPONSE_STATUS_CODE_404 = 404;
    public Properties prop;


    public BaseHelper() {
        try {
            prop = new Properties();
            FileInputStream file_input_values = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\org\\zomato\\config\\config.properties");
            prop.load(file_input_values);
        } catch (FileNotFoundException f) {
            System.out.println("file not found");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
