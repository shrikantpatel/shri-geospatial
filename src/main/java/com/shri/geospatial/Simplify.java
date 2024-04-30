package com.shri.geospatial;

import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.simplify.DouglasPeuckerSimplifier;

import java.io.IOException;
import java.io.StringReader;

public class Simplify {

    public static void main(String[] args) throws IOException {

        GeometryJSON gjson = new GeometryJSON();
        Geometry geometry = gjson.read(new StringReader(Constant.GEO_JSON_POLYGON_COMPLEX));
        geometry.setSRID(4326);
        System.out.println("Original Geometry : " + gjson.toString(geometry));
        geometry = DouglasPeuckerSimplifier.simplify(geometry, 10);
        System.out.println("Simplified Geometry : " + gjson.toString(geometry));

    }
}
