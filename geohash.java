public Class Geohash {
  // characters for encoding
  private static final String BASE_32 = "0123456789bcdefghjkmnpqrstuvwxyz"

  public static String geohash(lat, lon, precision) {
    double[] latRange = new double[]{-90.0, 90.0};
    double[] lonRange = new double[]{-180.0, 180.0};

    // find column for lat and 'label' it with binary

    // find row for lon and 'label' it with binary

    // interleave (alternate) the lat and lon bits

    // encode the resulting number in base 32 (easier to use than a big long binary string!)

    return Null;
  }
}
