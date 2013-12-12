package api;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import api.impl.RegionFile;
import api.impl.RegionPosition;


public class Dim {

  private final File dimFolder;
  private final Map<RegionPosition, RegionFile> regions = new HashMap<RegionPosition, RegionFile>();

  private static final int REGION_MASK = 0x1FF;
  private static final int REGION_SHIFT = 9;
  private final World world;
  private final String dimName;

  Dim(final World aWorld, final File aDimFolder, final String aDimName) {
    world = aWorld;
    dimFolder = aDimFolder;
    dimName = aDimName;
  }

  public Block getBlock(final int x, final int z, final int y) {
    final RegionFile region = findRegion(x, z);
    //System.out.println(region.getXr() + "**" + region.getZr());
    final Block block = region.getBlock(x & REGION_MASK, z & REGION_MASK, y);
    return block;
  }

  public void setBlock(final int x, final int z, final int y, final Block aBlock) {
    final RegionFile region = findRegion(x, z);
    region.setBlock(x & REGION_MASK, z & REGION_MASK, y, aBlock);
  }

  public void vypisSeckci(final int x, final int z, final int y) {
    final RegionFile region = findRegion(x, z);
    region.vypisSeckci(x & REGION_MASK, z & REGION_MASK, y);
  }

  void open() {
    if (! dimFolder.exists()) {
      return;
    }
    final File regionFolder = getRegionFolder();
    final File[] list = regionFolder.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(final File dir, final String name) {
        return name.endsWith(RegionFile.ANVIL_EXTENSION);
      }
    });

    if (list != null) {
      for (final File file : list) {
        final RegionFile region = new RegionFile(file);
        regions.put(region.getPozice(), region);
      }
    }

  }

  private File getRegionFolder() {
    return new File(dimFolder, "region");
  }


  private RegionFile findRegion(final int x, final int z) {
    final int xr = x >> REGION_SHIFT;
    final int zr = z >> REGION_SHIFT;
    //System.out.println("[" + x + "|" + z + "] === [" + xr + "|" + zr + "]" );

    final RegionPosition pozice = new RegionPosition(xr, zr);
    RegionFile region = regions.get(pozice);
    if (region == null) {
      region = new RegionFile(new File(getRegionFolder(), pozice.getFileName()));
      regions.put(region.getPozice(), region);
      //  throw new RuntimeException("Region jeste neni: " + pozice);
    }
    return region;
  }

  public void save() {
    for (final RegionFile region : regions.values()) {
      region.save();
    }

  }

  public Q start(final int x, final int z, final int y, final Facing facing) {
    return new Q(this, x, z, y, facing);

  }

  public World getWorld() {
    return world;
  }

  @Override
  public String toString() {
    return dimName;
  }
}
