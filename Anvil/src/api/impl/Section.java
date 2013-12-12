package api.impl;

import api.Block;

import com.mojang.nbt.CompoundTag;

public class Section {


  private final CompoundTag sectionTag;

  private byte[] arrayBlocks;
  private byte[] arrayAdd;
  private byte[] arrayData;

  private byte[] arrayBlockLight;

  private byte[] arraySkyLight;

  public Section(final CompoundTag aSectionTag) {
    sectionTag = aSectionTag;
    vytahniPole();
  }

  public Section(final int ys) {
    sectionTag = createEmptySection(ys);
    vytahniPole();
  }

  public Block getBlock(final int xbc, final int zbc, final int ybs) {
    final int blockPos = ybs*16*16 + zbc*16 + xbc;
    final int blockIdLow = arrayBlocks[blockPos] & 0xFF;
    final int blockIdHigh = arrayAdd == null ? 0 : nibble4(arrayAdd, blockPos);

    final int blockId = (blockIdHigh << 8) + blockIdLow;
    //      System.out.printf("GET [xbc=%d zbc=%d ybs=%d] = %s%n", xbc, zbc, ybs, blockId + " " + blockIdLow + " " + blockIdHigh);
    final Block block = new Block(blockId);
    block.data = nibble4(arrayData, blockPos);
    block.skyLight = nibble4(arraySkyLight, blockPos);
    block.blockLight = nibble4(arrayBlockLight, blockPos);
    //System.out.println(blockPos+ ": " + block.id);
    return block;
  }

  public void setBlock(final int xbc, final int zbc, final int ybs, final Block block) {
    final int blockPos = ybs*16*16 + zbc*16 + xbc;
    //      System.out.printf("SET [xbc=%d zbc=%d ybs=%d] = %d%n", xbc, zbc, ybs, block.id);
    if (block.id != null) {
      final int blockIdLow = block.id & 0xFF;
      final int blockIdHigh = block.id >> 8 & 0x0F;;
      if (blockIdHigh != 0 && arrayAdd == null) { // prvni blok s vysokým èíslem, zøídit pole
        arrayAdd = new byte[2048];
        sectionTag.putByteArray("Add", arrayAdd);
      }
      arrayBlocks[blockPos] = (byte) blockIdLow;
      if (arrayAdd != null) {
        nibble4(arrayAdd, blockPos, blockIdHigh);
      }
    }
    if (block.data != null) {
      nibble4(arrayData, blockPos, block.data);
    }
    if (block.skyLight != null) {
      nibble4(arraySkyLight, blockPos, block.skyLight);
    }
    if (block.blockLight != null) {
      nibble4(arraySkyLight, blockPos, block.blockLight);
    }
  }

  private void vytahniPole() {
    arrayBlocks = sectionTag.getByteArray("Blocks");
    arrayAdd = sectionTag.getByteArray("Add");
    if (arrayAdd != null && arrayAdd.length == 0) {
      arrayAdd = null;
    }
    //System.out.println("Add pole velikost: " + arrayAdd.length);
    arrayData  = sectionTag.getByteArray("Data");
    arrayBlockLight  = sectionTag.getByteArray("BlockLight");
    arraySkyLight  = sectionTag.getByteArray("SkyLight");
  }

  private static int nibble4(final byte[] arr, final int index){
    return index % 2 == 0 ? arr[index/2] & 0x0F : arr[index/2] >> 4 &0x0F;
  }

  private static void nibble4(final byte[] arr, final int index, final int dato){
    final int poz = index/2;
    if (index % 2 == 0) {
      arr[poz] = (byte) (arr[poz] & 0xF0 | dato & 0x0F);
    } else {
      arr[poz] = (byte) (arr[poz] & 0x0F | dato << 4 & 0xF0);
    }
  }

  private CompoundTag createEmptySection(final int ys) {
    final CompoundTag sekce = new CompoundTag();
    sekce.putByte("Y", (byte) ys);
    sekce.putByteArray("Blocks", new byte[4096]);
    sekce.putByteArray("Data", new byte[2048]);
    final byte[] skyLight = new byte[2048];
    for (int i = 0; i <skyLight.length; i++) { skyLight[i] = (byte) 0xFF; }
    sekce.putByteArray("SkyLight", skyLight);
    final byte[] blockLight = new byte[2048];
    //    for (int i = 0; i <blockLight.length; i++) { blockLight[i] = (byte) 0xFF; } // neplníme, mají být nuly
    sekce.putByteArray("BlockLight", blockLight);
    return sekce;
  }

  public CompoundTag getSectionTag() {
    return sectionTag;
  }

  public void vypisSekci(final int xbc, final int zbc, final int ybs) {
    sectionTag.print("ssss ",System.out);
    final byte[] pole = sectionTag.getByteArray("BlockLight");
    for (final byte b : pole) {
      System.out.print(b + " ");
    }
    System.out.println();
  }

}
