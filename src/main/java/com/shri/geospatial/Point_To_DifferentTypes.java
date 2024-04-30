package com.shri.geospatial;

import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKBWriter;
import org.locationtech.jts.io.WKTWriter;

import java.io.IOException;
import java.util.Base64;

public class Point_To_DifferentTypes {

    public static void main (String[] args) throws IOException {

        GeometryFactory geomFactory = new GeometryFactory();
        Point point = geomFactory.createPoint(new Coordinate(0.0, 50.0));

        /**
         * select ST_AsGeoJSON(ST_SetSRID(ST_Point(0, 50), 4326));
         */
        GeometryJSON gjson = new GeometryJSON();
        String json = gjson.toString(point);
        System.out.println("GeoJSON : " + json);

        /**
         * select ST_AsText(ST_SetSRID(ST_Point(0, 50), 4326));
         */
        WKTWriter wktWriter = new WKTWriter();
        System.out.println("WKT : " +wktWriter.write(point));

        /**
         * base 64 encoding of WKB in postgis
         *
         * select ST_AsBinary(ST_SetSRID(ST_Point(0, 50), 4326));
         * select encode(ST_AsBinary(ST_SetSRID(ST_Point(0.0, 50.0), 4326)), 'base64');
         *
         * reverse execution taking base64 and converting to GeoJSON for both
         * postgis and geotools base64 encoding of WKB
         *
         * select  ST_AsGeoJSON(ST_SetSRID (ST_GeomFromWKB( DECODE('AQEAAAAAAAAAAAAAAAAAAAAAAElA', 'base64')::geometry ), 4326));
         * select  ST_AsGeoJSON(ST_SetSRID (ST_GeomFromWKB( DECODE('AAAAAAEAAAAAAAAAAEBJAAAAAAAA', 'base64')::geometry ), 4326));
         */
        WKBWriter wkbWriter = new WKBWriter();
        byte[] wkb = wkbWriter.write(point);
        String base64Wkb = Base64.getEncoder().encodeToString(wkb);
        System.out.println("WKB : " + base64Wkb); //Outputs: "AQEAAAAAAAAAAAAAAAAAAAAAAElA"

        /**
         * SELECT encode(E'Hello, World!'::bytea, 'base64');  -- Outputs: "SGVsbG8sIFdvcmxkIQ=="
         */
        byte[] data = "Hello, World!".getBytes();
        String base64 = Base64.getEncoder().encodeToString(data);
        System.out.println("Base 64 encoding : " + base64);  // Outputs: "SGVsbG8sIFdvcmxkIQ=="
    }
}
