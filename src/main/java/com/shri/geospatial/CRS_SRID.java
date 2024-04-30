package com.shri.geospatial;

import org.geotools.api.referencing.FactoryException;
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.api.referencing.operation.MathTransform;
import org.geotools.api.referencing.operation.TransformException;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.geotools.util.factory.Hints;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.io.IOException;
import java.io.StringReader;

public class CRS_SRID {

    public static void main(String[] args) throws IOException, FactoryException, TransformException {

        String geoJson = """
                {"type":"Polygon","coordinates":[[[-1.43764890557E7,6401214.5768],[-1.43764890557E7,2800724.7965],[-6490633.7215,2800724.7965],[-6490633.7215,6401214.5768],[-1.43764890557E7,6401214.5768]]]}
                """;

        GeometryJSON gjson = new GeometryJSON();
        Geometry geometry = gjson.read(Constant.GEO_JSON_POLYGON_US_BORDER_3857);
        geometry.setSRID(3857);

        // Transform the geometry to a different CRS
        CoordinateReferenceSystem crs_4326 = CRS.decode("EPSG:4326", true);
        CoordinateReferenceSystem crs_3857 = CRS.decode("EPSG:3857", true);

        // Find a transformation between the source CRS and target CRS
        MathTransform transform = CRS.findMathTransform(crs_3857, crs_4326);

        // Transform the geometry to the target CRS
        Geometry tranformedGeometry = JTS.transform(geometry, transform);

        System.out.println("Original Geometry : " + gjson.toString(geometry));
        System.out.println("Transformed Geometry : " + gjson.toString(tranformedGeometry));
    }
}
