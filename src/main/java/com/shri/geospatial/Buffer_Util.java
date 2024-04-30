package com.shri.geospatial;

import org.geotools.api.referencing.FactoryException;
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.api.referencing.operation.MathTransform;
import org.geotools.api.referencing.operation.TransformException;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Geometry;

import java.io.IOException;
import java.io.StringReader;

public class Buffer_Util {

    public static final double METER_TO_DEGREE_CONVERSION_FACTOR = 111319.5;

    public static void main(String[] args) throws IOException, FactoryException, TransformException {

        GeometryJSON gjson = new GeometryJSON();
        Geometry geometry = gjson.read(new StringReader(Constant.GEO_JSON_LINESTRING_SAL));
        // does not make any difference
        geometry.setSRID(4326);
        System.out.println("Original Geometry type : " + geometry.getGeometryType());
        System.out.println("Original Geometry : " + gjson.toString(geometry));
        double bufferDistanceInMeters = 1000.0;
        double bufferDistanceInDegrees = bufferDistanceInMeters / METER_TO_DEGREE_CONVERSION_FACTOR;
        Geometry bufferedGeometry = geometry.buffer(bufferDistanceInDegrees);
        System.out.println("Buffered Geometry using Simple factoring  : " + gjson.toString(bufferedGeometry));

        // Transform the geometry to a different CRS
        CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:4326", true);
        CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:3857", true);

        // Find a transformation between the source CRS and target CRS
        MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);

        // Transform the geometry to the target CRS
        Geometry geometryInMeters = JTS.transform(geometry, transform);

        // Buffer the geometry (in meters)
        double bufferDistanceInMeters1 = 1000.0;
        Geometry bufferedGeometryInMeters = geometryInMeters.buffer(bufferDistanceInMeters1);

        // If needed, transform the buffered geometry back to the original CRS
        MathTransform inverseTransform = transform.inverse();
        bufferedGeometry = JTS.transform(bufferedGeometryInMeters, inverseTransform);

        System.out.println("Original Geometry : " + gjson.toString(geometry));
        System.out.println("Buffered Geometry using CRS Conversion : " + gjson.toString(bufferedGeometry));

    }
}
