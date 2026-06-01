package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigReader — loads config.properties from classpath.
 *
 * FIXED: Previously used a hardcoded relative path
 *   "src/test/resources/config/config.properties"
 * which breaks when Maven runs from a different working directory.
 *
 * FIX: Use getClassLoader().getResourceAsStream() which always
 * resolves from the classpath root regardless of working directory.
 * Maven/Eclipse both put src/test/resources on the test classpath,
 * so "config/config.properties" resolves correctly everywhere.
 */
public class ConfigReader {

    private static final Properties props = new Properties();

    static {
        // FIXED: classpath-based loading — works in both Maven CLI and Eclipse
        try (InputStream is = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config/config.properties")) {

            if (is == null) {
                throw new RuntimeException(
                    "config/config.properties not found on classpath. " +
                    "Ensure src/test/resources is marked as a test resource root."
                );
            }
            props.load(is);

        } catch (IOException e) {
            throw new RuntimeException("Cannot load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key not found in config.properties: [" + key + "]");
        }
        return value.trim();
    }
}
