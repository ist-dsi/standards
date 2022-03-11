/* 
* @(#)FileUtil.java 
* 
* Copyright 2012 Instituto Superior Tecnico 
* Founding Authors: Luis Cruz, Susana Fernandes 
*  
*      https://fenix-ashes.ist.utl.pt/ 
*  
*   This file is part of the JodaFinance library. 
* 
*   The JodaFinance library is free software: you can 
*   redistribute it and/or modify it under the terms of the GNU Lesser General 
*   Public License as published by the Free Software Foundation, either version  
*   3 of the License, or (at your option) any later version. 
* 
*   JodaFinance is distributed in the hope that it will be useful, 
*   but WITHOUT ANY WARRANTY; without even the implied warranty of 
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
*   GNU Lesser General Public License for more details. 
* 
*   You should have received a copy of the GNU Lesser General Public License 
*   along with JodaFinance. If not, see <http://www.gnu.org/licenses/>. 
*  
*/
package pt.ist.standards.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 
 * @author Luis Cruz
 * @author Susana Fernandes
 * 
 */
public class ResourceReader {

    public static String read(final String resource, final String encoding) {
        final InputStream inputStream = ResourceReader.class.getResourceAsStream(resource);
        try {
            final InputStreamReader fileReader = new InputStreamReader(inputStream, encoding);
            try {
                char[] buffer = new char[4096];
                final StringBuilder fileContents = new StringBuilder();
                for (int n = 0; (n = fileReader.read(buffer)) != -1; fileContents.append(buffer, 0, n)) {
                    ;
                }
                return fileContents.toString();
            } catch (final IOException ex) {
                throw new Error(ex);
            } finally {
                try {
                    fileReader.close();
                } catch (final IOException ex) {
                    throw new Error(ex);
                }
            }
        } catch (final UnsupportedEncodingException ex) {
            throw new Error(ex);
        }
    }

    public static String read(final String resource) {
        return read(resource, "UTF-8");
    }

    public static String[] readLines(final String resource, final String encoding) {
        return read(resource, encoding).split("\\r?\\n");
    }

    public static String[] readLines(final String resource) {
        return readLines(resource, "UTF-8");
    }

    public static JsonElement readJson(final String resource) {
        return new JsonParser().parse(read(resource, "UTF-8"));
    }

}
