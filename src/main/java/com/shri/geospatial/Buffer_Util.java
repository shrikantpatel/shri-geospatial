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

public class Buffer_Util {

    static final String EPSG_PREFIX= "EPSG:";
    static final String EPSG_PREFIXCODE = "326"; // For northern hemisphere. Use "327" for southern hemisphere
    public static final double METER_TO_DEGREE_CONVERSION_FACTOR = 111319.5;

    public static void main(String[] args) throws IOException, FactoryException, TransformException {

        double bufferDistanceInMeters = 1;
        GeometryJSON gjson = new GeometryJSON();
        Geometry geometry = gjson.read(new StringReader(Constant.GEO_JSON_POINT));
        // does not make any difference
        geometry.setSRID(4326);

        String epsgCode = getEPSGCodeForUTMZone(geometry);
        System.out.println("Target EPSG Code : " + epsgCode);

        System.out.println("Original Geometry type : " + geometry.getGeometryType());
        System.out.println("Original Geometry : " + gjson.toString(geometry));
        double bufferDistanceInDegrees = bufferDistanceInMeters / METER_TO_DEGREE_CONVERSION_FACTOR;
        Geometry bufferedGeometry = geometry.buffer(bufferDistanceInDegrees);
        System.out.println("Buffered Geometry using Simple factoring  : " + gjson.toString(bufferedGeometry));

        // Transform the geometry to a different CRS
        CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:4326", true);
        CoordinateReferenceSystem targetCRS = CRS.decode(epsgCode, true);

        // Find a transformation between the source CRS and target CRS
        MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);

        // Transform the geometry to the target CRS
        Geometry geometryInMeters = JTS.transform(geometry, transform);

        // Buffer the geometry (in meters)
        Geometry bufferedGeometryInMeters = geometryInMeters.buffer(bufferDistanceInMeters);

        // If needed, transform the buffered geometry back to the original CRS
        MathTransform inverseTransform = transform.inverse();
        bufferedGeometry = JTS.transform(bufferedGeometryInMeters, inverseTransform);

        System.out.println("Original Geometry : " + gjson.toString(geometry));
        System.out.println("Buffered Geometry using CRS Conversion : " + gjson.toString(bufferedGeometry));

    }

    public static String getEPSGCodeForUTMZone(Geometry geometry) {
        Coordinate centroid = geometry.getCentroid().getCoordinate();
        double longitude = centroid.x;
        int utmZone =  (int) ((longitude + 180) / 6) + 1;
        return EPSG_PREFIX + EPSG_PREFIXCODE + String.format("%02d", utmZone);
    }
}
