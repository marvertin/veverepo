package api.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;

import api.Block;

import com.mojang.nbt.NbtIo;

public class ChunkHolder {

  private final RegionFile parentRegion;
  private final int xc;
  private final int zc;

  private Chunk chunkStrong;
  private SoftReference<Chunk> chunkSoft;
  /** Inicializuje se, jen pokud èank existuje */

  public ChunkHolder(final RegionFile aParentRegion, final int aXc, final int aZc) {
    parentRegion = aParentRegion;
    xc = aXc;
    zc = aZc;
  }

  public Chunk load() {
    try {
      Chunk chunk = chunkSoft == null ? null : chunkSoft.get();
      if (chunk != null) { // mame nacteno
        return chunk;
      }
      if (! parentRegion.hasChunk(xc, zc)) {
        return null; // chunk neni, nebudeme nacitat
      }
      final DataInputStream regionChunkInputStream = parentRegion.getChunkDataInputStream(xc, zc);
      if (regionChunkInputStream == null) {
        throw new RuntimeException("Failed to fetch input stream");
      }
      chunk = new Chunk(NbtIo.read(regionChunkInputStream));
      //System.out.println("Loading chunk: x=" + xc + ", z=" + zc);
      regionChunkInputStream.close();

      assert chunk.xPos == parentRegion.getXr() * 32 + xc : chunk.xPos + "=" + xc;
      assert chunk.zPos == parentRegion.getZr() * 32 + zc:  chunk.zPos + "=" + zc;
      //sectionsTag = levelTag.getCompound("Sections");
      //for (sectionsectionsTag.getAllTags()
      //      vypisVyskovouMapu(levelTag);
      //      zkontrolujVyskovouMapu(levelTag);
      //      aktualizujVyskovouMapu(levelTag);
      //chunkData.print(System.out);
      chunkSoft = new SoftReference<Chunk>(chunk);
      return chunk;
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }

  }


  public Block getBlock(final int xbc, final int zbc, final int y) {
    final Chunk chunk = load();
    if (chunk == null) {
      return Block.air();
    }
    final Block block = chunk.getBlock(xbc, zbc, y);
    return block;
  }

  int skyCit;

  public void setBlock(final int xbc, final int zbc, final int y, final Block block) {
    final Chunk chunk = load();
    if (chunk == null) {
      return; // èank není
    }
    chunk.setBlock(xbc, zbc, y, block);
    chunkStrong = chunk; //nesmí nám zmizet modifikovaný chunk
  }

  public void vypisSekci(final int xbc, final int zbc, final int y) {
    final Chunk chunk = load();
    if (chunk == null) {
      System.out.println("Chunk neni");
      return; // èank není
    }
    chunk.vypisSekci(xbc, zbc, y);
  }



  public void save() {
    try {
      if (chunkStrong != null) { // jen zmìnìné chunky ukládáme
        chunkStrong.aktualizujVyskovouMapu();
        final DataOutputStream chunkDataOutputStream = parentRegion.getChunkDataOutputStream(xc, zc);
        NbtIo.write(chunkStrong.chunkRoot, chunkDataOutputStream);
        chunkDataOutputStream.close();
        chunkStrong = null; // mùžeme klidnì uvolnit
      }
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }


}
