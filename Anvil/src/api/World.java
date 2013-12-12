package api;

import java.io.File;

import api.impl.Const;
import api.impl.LevelDat;

public class World {

  private Dim overworld;
  private Dim nether;
  private Dim ender;

  LevelDat levelDat;

  public static World open(final File aWorldDir) {
    final World world = new World();
    world._open(aWorldDir);
    return world;
  }
  /**
   * Naloaduje svìt z daného souboru. Napøíklad: c:\Users\veverka\AppData\Roaming\.minecraft\saves\Tatosvet
   * @param aWorldDir
   */
  private void _open(final File aWorldDir) {
    overworld = new Dim(this, aWorldDir, "overworld");
    overworld.open();
    nether = new Dim(this, new File(aWorldDir, Const.NETHER_FOLDER), "nether");
    nether.open();
    ender = new Dim(this, new File(aWorldDir, Const.ENDER_FOLDER), "ender");
    ender.open();
    levelDat = new LevelDat(aWorldDir);
    levelDat.open();
  }

  /**
   * Uloží zmìny
   */
  public void save() {
    overworld.save();
    nether.save();
    ender.save();
  }

  public Dim getOverworld() {
    return overworld;
  }

  public Dim getNether() {
    return nether;
  }

  public Dim getEnder() {
    return ender;
  }

  private Dim getPlayerDimension() {
    switch (levelDat.getPlayerDimension()) {
    case -1: return getNether();
    case 1 : return getEnder();
    default: return getOverworld();
    }

  }

  public Q getPlayerPosition() {
    return levelDat._getPlayerPosition(getPlayerDimension());
  }


}
