package api;

/**
 * Only structure, which carry information about block.
 * When the block is obtained from world, it has never null attributes.
 * When you are puting block to word, then attributes, which are null will not change.
 */
public class Block {
  public Integer id;
  public Integer data;
  public Integer blockLight;
  public Integer skyLight;

  public Block(final int aBlockId) {
    id = aBlockId;
  }

  public Block(final int aBlockId, final int aData) {
    this(aBlockId);
    data = aData;
  }

  public static final Block air() {
    return new Block(0);
  }

  @Override
  public String toString() {
    return "Block [id=" + id + ", data=" + data + ", blockLight=" + blockLight + ", skyLight=" + skyLight + "]";
  }
}


