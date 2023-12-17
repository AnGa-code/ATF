package properties;

import exceptions.BookSharingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final Logger logger = LogManager.getLogger(PropertyReader.class);

    private static final String CONFIG_FILE_PATH = "src/main/resources/application.properties";
    private static final Properties properties = new Properties();

    static {
        try {
            initProperties();
        } catch (BookSharingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void initProperties() throws BookSharingException {
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            if (fileInputStream == null) {
                throw new FileNotFoundException("Properties file not found");
            }
            properties.load(fileInputStream);
        } catch (IOException exception ) {
            throw new BookSharingException("Error saving properties file");
        }
    }

    public static String getProperty(PropertyKey propertyName) {

        try {
            String propertyValue = properties.getProperty(propertyName.getPropertyKey());

            if (propertyValue == null) {
                throw new BookSharingException("The property: " + propertyName.getPropertyKey() + " is not found");
            }
            return propertyValue;

        } catch (BookSharingException ex) {
            logger.error("The property: " + propertyName.getPropertyKey() + " is not found");
            return null;
        }
    }

    public static void setProperty(PropertyKey propertyName, String value) throws BookSharingException {

        properties.setProperty(propertyName.getPropertyKey(), value);

        }
    }