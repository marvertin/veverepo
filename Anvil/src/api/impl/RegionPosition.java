package api.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegionPosition {
  final int xr;
  final int zr;


  public RegionPosition(final int aXr, final int aZr) {
    super();
    xr = aXr;
    zr = aZr;
  }

  public RegionPosition(final String aFileName) {
    // Zjištìní souøadnic regionu
    final Pattern pat = Pattern.compile("^r\\.(.+)\\.(.+)\\.mca$");
    System.out.println(aFileName);
    final Matcher mat = pat.matcher(aFileName);
    if (! mat.matches()) {
      throw new RuntimeException("File " + aFileName + " has wrong name!");
    }
    xr = Integer.parseInt(mat.group(1));
    zr = Integer.parseInt(mat.group(2));
  }

  public String getFileName() {
    return "r." + xr + "." + zr + ".mca";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + xr;
    result = prime * result + zr;
    return result;
  }
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final RegionPosition other = (RegionPosition) obj;
    if (xr != other.xr) {
      return false;
    }
    if (zr != other.zr) {
      return false;
    }
    return true;
  }
  @Override
  public String toString() {
    return "RegionPosition [xr=" + xr + ", zr=" + zr + "] " + getFileName();
  }



}
