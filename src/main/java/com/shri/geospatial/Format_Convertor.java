package com.shri.geospatial;

import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTWriter;

import java.io.IOException;
import java.io.StringReader;

public class Format_Convertor {

    public static void main(String[] args) throws IOException {

        GeometryJSON gjson = new GeometryJSON();
        Geometry geometry = gjson.read(new StringReader(Constant.GEO_JSON_POLYGON));
        geometry.setSRID(4326);
        System.out.println("Original Geometry type : " + geometry.getGeometryType());
        System.out.println("Original Geometry : " + gjson.toString(geometry));

        WKTWriter wktWriter = new WKTWriter();
        wktWriter.write(geometry);

        System.out.println("Converted WKT : " + wktWriter.write(geometry));
    }
}
