package com.shri.geospatial;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;

import java.io.IOException;

public class ReadWKTFormat {
    public static void main(String[] args) throws IOException, ParseException {

        String wkt = "POINT(-97.39850930935121 32973263866721396)";

        WKTReader reader = new WKTReader();
        Geometry geometry = reader.read(wkt);
        geometry.setSRID(4326);

        WKTWriter wktWriter = new WKTWriter();
        wktWriter.write(geometry);

        System.out.println("Converted WKT : " + wktWriter.write(geometry));
        System.out.println("Converted WKT : " + geometry.isValid());
    }
}