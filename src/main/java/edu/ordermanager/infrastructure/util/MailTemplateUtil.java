package edu.ordermanager.infrastructure.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MailTemplateUtil {

    public static String loadAndFillTemplate(String templateName, Map<String, String> params) {
        try (InputStream is = MailTemplateUtil.class.getClassLoader()
                .getResourceAsStream("templates/" + templateName)) {
            if (is == null) throw new IllegalArgumentException("No se encuentra el template: " + templateName);
            String template = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            for (Map.Entry<String, String> entry : params.entrySet()) {
                template = template.replace("${" + entry.getKey() + "}", entry.getValue());
            }
            return template;
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el template de email", e);
        }
    }
}
