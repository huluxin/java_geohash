public class Geohash {
  // characters for encoding
  private static final String BASE_32 = "0123456789bcdefghjkmnpqrstuvwxyz";

  private static int divideRange(double value, double[] range) {
    double bisector;
    int label = 0;
    for (int i = 0; i < 30; i++) {
      bisector = (range[0] + range[1]) / 2;
      if (value > bisector) {
        label = label << 1 | 1;
        range[0] = bisector;
      } else {
        label = label << 1;
        range[1] = bisector;
      }
    }
    System.out.println(label);
    return label;
  }

  private static long interleave(int lonBits, int latBits) {
    long latLonBin = 0;
    int nextLonBit;
    int nextLatBit;
    for (int i = 0; i < 30; i ++) {
      nextLonBit = (lonBits >> (29 - i)) & 1;
      latLonBin = (latLonBin << 1) | nextLonBit;
      nextLatBit = (latBits >> (29 - i)) & 1;
      latLonBin = (latLonBin << 1) | nextLatBit;
    }
    return latLonBin;
  }

  private static String encodeBits(long latLonBin) {
    StringBuffer finalHash = new StringBuffer();
    long nextCharIndex;
    for (int i = 0; i < 12; i++) {
      nextCharIndex = (latLonBin >> (55 - i * 5)) & 31;
      finalHash.append(BASE_32.charAt((int) nextCharIndex));
    }
    return finalHash.toString();
  }

  public static String geohash(double lat, double lon, int precision) {
    double[] latRange = new double[]{-90.0, 90.0};
    double[] lonRange = new double[]{-180.0, 180.0};

    // find column for lon and 'label' it with binary
    int lonBits = divideRange(lon, lonRange);

    // find row for lat and 'label' it with binary
    int latBits = divideRange(lat, latRange);

    // interleave (alternate) the lon and lat bits
    long lonLatBin = interleave(lonBits, latBits);

    // encode the resulting number in base 32 (easier to use than a big long binary string!)
    String finalHash = encodeBits(lonLatBin);

    return finalHash;
  }

  public static void main(String[] args) {
    String hash1 = geohash(52.61911, -10.40744, 12);
    System.out.println(hash1);
  }
}
