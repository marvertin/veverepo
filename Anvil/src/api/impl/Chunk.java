package api.impl;

import api.Block;

import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.ListTag;
import com.mojang.nbt.Tag;

public class Chunk {

  final CompoundTag chunkRoot;
  final ListTag<CompoundTag> sectionsTag;
  //final CompoundTag[] sectionsNechceme;
  final Section[] sections = new Section[16];
  final int xPos;
  final int zPos;

  public Chunk(final CompoundTag aChunkRoot) {
    chunkRoot = aChunkRoot;
    final CompoundTag levelTag = chunkRoot.getCompound("Level");
    @SuppressWarnings("unchecked")
    final
    ListTag<CompoundTag> list = (ListTag<CompoundTag>) levelTag.getList("Sections");
    sectionsTag = list;
    for (final Tag tag : sectionsTag) {
      final CompoundTag tagc = (CompoundTag) tag;
      final int ys = tagc.getByte("Y");
      sections[ys] = new Section(tagc);
    }
    xPos = levelTag.getInt("xPos");
    zPos = levelTag.getInt("zPos");
  }

  public Block getBlock(final int xbc, final int zbc, final int y) {
    final int ybs = y % 16;
    final int ys = y / 16;
    final Section section = sections[ys];
    if (section == null) {
      return Block.air();
    }
    final Block block = section.getBlock(xbc, zbc, ybs);
    return block;
  }

  public void setBlock(final int xbc, final int zbc, final int y, final Block block) {
    final int ybs = y % 16;
    final int ys = y / 16;
    final Section section = getSection(ys);
    section.setBlock(xbc, zbc, ybs, block);
  }

  private Section getSection(final int ys) {
    Section section = sections[ys];
    if (section == null) { // založení prázdné sekce
      section = new Section(ys);
      sections[ys] = section;
      sectionsTag.add(section.getSectionTag());
    }
    return section;
  }


  public void vypisSekci(final int xbc, final int zbc, final int y) {
    if (chunkRoot == null) {
      System.out.println("Chunk neni");
      return; // èank není
    }
    final int ybs = y % 16;
    final int ys = y / 16;
    getSection(ys).vypisSekci(xbc, zbc, ybs);
  }


  int nibble4(final byte[] arr, final int index){
    return index % 2 == 0 ? arr[index/2] & 0x0F : arr[index/2] >> 4 &0x0F;
  }

  void nibble4(final byte[] arr, final int index, final int dato){
    final int poz = index/2;
    if (index % 2 == 0) {
      arr[poz] = (byte) (arr[poz] & 0xF0 | dato & 0x0F);
    } else {
      arr[poz] = (byte) (arr[poz] & 0x0F | dato << 4 & 0xF0);
    }
  }




  void aktualizujVyskovouMapu() {
    final CompoundTag levelTag = chunkRoot.getCompound("Level");
    final int[] vysky = levelTag.getIntArray("HeightMap");
    //    final int xPos = levelTag.getInt("xPos");
    //    final int zPos = levelTag.getInt("zPos");
    //System.out.printf("Ulozeni vyskovky: x=%d z=%d%n",xPos,zPos);
    for (int xbc=0; xbc<16; xbc++) {
      for (int zbc=0; zbc<16; zbc++) {
        final int vyskaSpocitana = spocitejVyskuZBloku(xbc, zbc);
        vysky[zbc*16 + xbc]  = vyskaSpocitana;
        //System.out.printf("%3d",vyskaSpocitana - vyskaUlozena);
      }
      //System.out.println();
    }
    //System.out.println();
  }


  @SuppressWarnings("unused")
  private void zkontrolujVyskovouMapu(final CompoundTag aLevelTag) {
    final int[] vysky = aLevelTag.getIntArray("HeightMap");
    final int xPos = aLevelTag.getInt("xPos");
    final int zPos = aLevelTag.getInt("zPos");

    System.out.printf("Kontrola vyskovky: x=%d z=%d%n",xPos,zPos);
    for (int xbc=0; xbc<16; xbc++) {
      for (int zbc=0; zbc<16; zbc++) {
        final int vyskaUlozena = vysky[zbc*16 + xbc];
        final int vyskaSpocitana = spocitejVyskuZBloku(xbc, zbc);
        System.out.printf("%3d",vyskaSpocitana - vyskaUlozena);
      }
      System.out.println();
    }
    System.out.println();
  }
  @SuppressWarnings("unused")
  private void vypisVyskovouMapu(final CompoundTag aLevelTag) {
    final int[] vysky = aLevelTag.getIntArray("HeightMap");
    final int xPos = aLevelTag.getInt("xPos");
    final int zPos = aLevelTag.getInt("zPos");

    System.out.printf("x=%d z=%d%n",xPos,zPos);
    for (int x=0; x<16; x++) {
      for (int z=0; z<16; z++) {
        System.out.printf("%3d",vysky[z*16 + x]);
      }
      System.out.println();
    }
    System.out.println();
  }

  private int spocitejVyskuZBloku(final int xbc, final int zbc) {
    for (int y = 255; y >= 0; y--) {
      if(getBlock(xbc, zbc, y).id != 0) {
        return y + 1;
      }
    }
    return 0;
  }

}
