package com.shri.geospatial;

public class Constant {

    final static String GEO_JSON_POINT = """
            { "type": "Point", "coordinates": [                                              -100,
                                                                                             40.17377948939199] }
            """;

    final static String WKT_POINT = "POINT (30 10)";

    final static String GEO_JSON_LINESTRING = """
            {
                "type": "LineString",
                "coordinates": [ [-73.801174, 40.7587], [-73.801775, 40.753873] ]
            }
            """;

    final static String GEO_JSON_LINESTRING_SAL = """
            {"type": "LineString","coordinates": [[-105.006509, 39.762119],[-105.025091, 39.762086]]}
            """;

    final static String WKT_POLYGON = "POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))";

    final static String GEO_JSON_POLYGON = """
            {
                "type": "Polygon",
                "coordinates": [
                    [
                        [30.0, 10.0],
                        [40.0, 40.0],
                        [20.0, 40.0],
                        [10.0, 20.0],
                        [30.0, 10.0]
                    ]
                ]
            }
            """;

    final static String GEO_JSON_MULTIPOINT = """
            {
                "type": "MultiPoint",
                    "coordinates": [
                [10.0, 40.0],
                [40.0, 30.0],
                [20.0, 20.0],
                [30.0, 10.0]
            ]
            }
            """;

    final static String GEO_JSON_MULTILINESTRING = """
            {
                "type": "MultiLineString",\s
                "coordinates": [
                    [
                        [10.0, 10.0],
                        [20.0, 20.0],
                        [10.0, 40.0]
                    ],
                    [
                        [40.0, 40.0],
                        [30.0, 30.0],
                        [40.0, 20.0],
                        [30.0, 10.0]
                    ]
                ]
            }
            """;

    final static String GEO_JSON_MULTILINEPOLYGON = """
            {
                "type": "MultiPolygon",
                "coordinates": [
                    [
                        [
                            [30.0, 20.0],
                            [45.0, 40.0],
                            [10.0, 40.0],
                            [30.0, 20.0]
                        ]
                    ],
                    [
                        [
                            [15.0, 5.0],
                            [40.0, 10.0],
                            [10.0, 20.0],
                            [5.0, 10.0],
                            [15.0, 5.0]
                        ]
                    ]
                ]
            }
            """;

    final static String GEO_JSON_POLYGON_US_BORDER_4326 = """
            {
                    "type": "Polygon"
                    "coordinates": [
                      [
                        [
                          -129.146198506786,
                          49.739100104658775
                        ],
                        [
                          -129.146198506786,
                          24.387717057025895
                        ],
                        [
                          -58.30635475678501,
                          24.387717057025895
                        ],
                        [
                          -58.30635475678501,
                          49.739100104658775
                        ],
                        [
                          -129.146198506786,
                          49.739100104658775
                        ]
                      ]
                    ]
                  }
                        """;

    final static String GEO_JSON_POLYGON_US_BORDER_3857 = """
            {
              "type": "Polygon",
              "coordinates": [
                [
                  [
                    -14376489.0557,
                    6401214.5768
                  ],
                  [
                    -14376489.0557,
                    2800724.7965
                  ],
                  [
                    -6490633.7215,
                    2800724.7965
                  ],
                  [
                    -6490633.7215,
                    6401214.5768
                  ],
                  [
                    -14376489.0557,
                    6401214.5768
                  ]
                ]
              ]
            }
            """;

    final static String GEO_JSON_POLYGON_COMPLEX = """
                 {
                    "type": "Polygon",
                    "coordinates": [
                      [
                        [
                          29.996,
                          9.992
                        ],
                        [
                          9.996,
                          10.992
                        ],
                        [
                          9.9945,
                          15.9929
                        ],
                        [
                          9.9932,
                          19.9941
                        ],
                        [
                          9.9922,
                          19.9956
                        ],
                        [
                          9.9915,
                          19.9972
                        ],
                        [
                          9.9911,
                          19.9989
                        ],
                        [
                          9.991,
                          20.0006
                        ],
                        [
                          9.9913,
                          20.0024
                        ],
                        [
                          9.992,
                          20.004
                        ],
                        [
                          19.992,
                          25.004
                        ],
                        [
                          19.9928,
                          30.0054
                        ],
                        [
                          19.994,
                          35.0066
                        ],
                        [
                          19.9953,
                          40.0076
                        ],
                        [
                          19.9968,
                          40.0084
                        ],
                        [
                          19.9984,
                          40.0088
                        ],
                        [
                          20,
                          40.009
                        ],
                        [
                          40,
                          40.009
                        ],
                        [
                          40.0017,
                          40.0088
                        ],
                        [
                          40.0033,
                          40.0083
                        ],
                        [
                          40.0048,
                          40.0076
                        ],
                        [
                          40.0062,
                          40.0065
                        ],
                        [
                          40.0073,
                          40.0053
                        ],
                        [
                          40.0081,
                          40.0038
                        ],
                        [
                          40.0087,
                          40.0022
                        ],
                        [
                          40.009,
                          40.0005
                        ],
                        [
                          40.0089,
                          39.9988
                        ],
                        [
                          40.0085,
                          39.9972
                        ],
                        [
                          30.0085,
                          30.9972
                        ],
                        [
                          30.0078,
                          25.9956
                        ],
                        [
                          30.0069,
                          9.9942
                        ],
                        [
                          30.0056,
                          9.993
                        ],
                        [
                          30.0042,
                          9.9921
                        ],
                        [
                          30.0026,
                          9.9914
                        ],
                        [
                          30.001,
                          9.9911
                        ],
                        [
                          29.9993,
                          9.991
                        ],
                        [
                          29.9976,
                          9.9914
                        ],
                        [
                          29.996,
                          9.992
                        ]
                      ]
                    ]
                  }
            """;
}
