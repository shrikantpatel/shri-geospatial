package com.shri.geospatial;

import org.geotools.api.referencing.FactoryException;
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.api.referencing.operation.MathTransform;
import org.geotools.api.referencing.operation.TransformException;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import java.io.IOException;
import java.io.StringReader;

public class Area_Length_Util {

    static final String EPSG_PREFIX= "EPSG:";
    static final String EPSG_PREFIXCODE = "326"; // For northern hemisphere. Use "327" for southern hemisphere
    public static final double METER_TO_DEGREE_CONVERSION_FACTOR = 111319.5;

    public static void main(String[] args) throws IOException, FactoryException, TransformException {

        double bufferDistanceInMeters = 1;
        GeometryJSON gjson = new GeometryJSON();
        Geometry geometry = gjson.read(new StringReader(Constant.GEO_JSON_MULTILINEPOLYGON));
        // does not make any difference
        geometry.setSRID(4326);

        String epsgCode = getEPSGCodeForUTMZone(geometry);
        System.out.println(gjson.toString(geometry));
        System.out.println("Target EPSG Code : " + epsgCode);
        System.out.println("*****************************************");
        System.out.println("Geometry 4326 Area = " + geometry.getArea());
        System.out.println("Geometry 4326 Length = " + geometry.getLength());
        System.out.println("Geometry 4326 Dimension = " + geometry.getDimension());


        // Transform the geometry to a different CRS
        CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:4326", true);
        CoordinateReferenceSystem targetCRS = CRS.decode(epsgCode, true);

        // Find a transformation between the source CRS and target CRS
        MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);

        // Transform the geometry to the target CRS
        Geometry geometryInMeters = JTS.transform(geometry, transform);

        System.out.println("*****************************************");
        System.out.println("Geometry Updated Area = " + String.format("%.12f",geometryInMeters.getArea()));
        System.out.println("Geometry Updated Length = " + String.format("%.12f", geometryInMeters.getLength()));
        System.out.println("Geometry Updated Dimension = " + geometryInMeters.getDimension());

    }

    public static String getEPSGCodeForUTMZone(Geometry geometry) {
        Coordinate centroid = geometry.getCentroid().getCoordinate();
        double longitude = centroid.x;
        int utmZone =  (int) ((longitude + 180) / 6) + 1;
        return EPSG_PREFIX + EPSG_PREFIXCODE + String.format("%02d", utmZone);
    }

}
