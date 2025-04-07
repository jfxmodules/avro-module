/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.apache.avro;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chad Preisler
 */
public class ReadSchemaFileHelper {
    
    public static InputStream getSchema(String absolutePath) {
        return ReadSchemaFileHelper.class.getResourceAsStream(absolutePath);
    }
    
    public static File getFile(String absolutePath) {
        try {
            return new File(ReadSchemaFileHelper.class.getResource(absolutePath).toURI());
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReadSchemaFileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
