## Starter code:

Provides constants and the necessary steps to create a geohash string.


### Step one:

Write the main program, following the steps provided in comments.

Define the 'labels' for the column and row. We will need the lon/lat value plus the range for each.

```java
// find column for lon and 'label' it with binary
int lonBits = divideRange(lon, lonRange);

// find row for lat and 'label' it with binary
int latBits = divideRange(lat, latRange);
```

Interleave the labels to place the point in our quadtree:

```java
// interleave (alternate) the lon and lat bits
long lonLatBin = interleave(lonBits, latBits);
```

And encode - geohashing uses a special base-32 encoding, so we can't use a library here:

```java
// encode the resulting number in base 32 (easier to use than a big long binary string!)
String finalHash = encodeBits(lonLatBin);
```

Finally, return the final hash:

```java
return finalHash;
```

### Step two:

Write the 'divideRange' function:
The total number of bits we will need is determined by the number of characters we want in our hash (aka, the precision) times 5 (aka, log base 2 of the encoding). We will get half of those bits from longitude, half from latitude. For this example we are creating a 12-character hash, since that is the most precise we can be given the precision problem with floats. That means we need a total of 30 bits for each longitude and latitude label ((12 * 5) / 2). I'm using 30 as a magic number here, but you could define the precision differently if you wanted!

```java
private static int divideRange(double value, double[] range) {
  int label = 0;
  double bisector;
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
  return label;
}
```

### Step three:

Write the interleave function.
For each of the bits in lonBits and latBits, we must isolate that bit and append it to our bin.

```java
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
```

### Step four:

Encode the bits: isolate each string of 5 bits in order and encode it in base-32.

```java
private static String encodeBits(long latLonBin) {
  StringBuffer finalHash = new StringBuffer();
  long nextCharIndex;
  for (int i = 0; i < 12; i++) {
    nextCharIndex = (latLonBin >> (55 - i * 5)) & 31;
    finalHash.append(BASE_32.charAt((int) nextCharIndex));
  }
  return finalHash.toString();
}
```
