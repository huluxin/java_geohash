public Class Geohash {
  // define some variables:
    // characters for encoding
  private static final String BASE_32 = "0123456789bcdefghjkmnpqrstuvwxyz"

    // ranges - for finding the bin a coordinate belongs to
  private static final double[] lat_range = [-90, 90]
  private static final double[] lon_range = [-180, 180]


  public static String geohash(lat, lon, precision) {
    // find bin for lat and lon and 'label' it with binary

    // interleave (alternate) the lat and lon bits

    // encode the resulting number in base 32 (easier to use than a big long binary string!)

    return "";
  }
}
