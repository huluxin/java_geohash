public Class Geohash {
  // characters for encoding
  private static final String BASE_32 = "0123456789bcdefghjkmnpqrstuvwxyz"

  public static String geohash(lat, lon, precision) {
    double[] latRange = new double[]{-90.0, 90.0};
    double[] lonRange = new double[]{-180.0, 180.0};

    // find column for lon and 'label' it with binary

    // find row for lat and 'label' it with binary

    // interleave (alternate) the lon and lat bits

    // encode the resulting number in base 32 (easier to use than a big long binary string!)

    return Null;
  }

  public static void main(String[] args) {
    String hash1 = geohash(52.61911, -10.40744, 12);
    System.out.println(hash1);
  }

}
