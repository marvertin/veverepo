package com.mojang.nbt;

/**
 * Copyright Mojang AB.
 * 
 * Don't do evil.
 */

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTag<T extends Tag> extends Tag implements Iterable<T> {
  private List<T> list = new ArrayList<T>();
  private byte type;

  public ListTag() {
    super("");
  }

  public ListTag(final String name) {
    super(name);
  }

  @Override
  void write(final DataOutput dos) throws IOException {
    if (list.size() > 0) {
      type = list.get(0).getId();
    } else {
      type = 1;
    }

    dos.writeByte(type);
    dos.writeInt(list.size());
    for (int i = 0; i < list.size(); i++) {
      list.get(i).write(dos);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  void load(final DataInput dis) throws IOException {
    type = dis.readByte();
    final int size = dis.readInt();

    list = new ArrayList<T>();
    for (int i = 0; i < size; i++) {
      final Tag tag = Tag.newTag(type, null);
      tag.load(dis);
      list.add((T) tag);
    }
  }

  @Override
  public byte getId() {
    return TAG_List;
  }

  @Override
  public String toString() {
    return "" + list.size() + " entries of type " + Tag.getTagName(type);
  }

  @Override
  public void print(String prefix, final PrintStream out) {
    super.print(prefix, out);

    out.println(prefix + "{");
    final String orgPrefix = prefix;
    prefix += "   ";
    for (int i = 0; i < list.size(); i++) {
      list.get(i).print(prefix, out);
    }
    out.println(orgPrefix + "}");
}

  public void add(final T tag) {
    type = tag.getId();
    list.add(tag);
  }

  public T get(final int index) {
    return list.get(index);
  }

  public int size() {
    return list.size();
  }

  @Override
  public Tag copy() {
    final ListTag<T> res = new ListTag<T>(getName());
    res.type = type;
    for (final T t : list) {
      @SuppressWarnings("unchecked")
      final
      T copy = (T) t.copy();
      res.list.add(copy);
    }
    return res;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean equals(final Object obj) {
    if (super.equals(obj)) {
      final ListTag o = (ListTag) obj;
      if (type == o.type) {
        return list.equals(o.list);
      }
    }
    return false;
  }

  @Override
  public Iterator<T> iterator() {
    return list.iterator();
  }

}
