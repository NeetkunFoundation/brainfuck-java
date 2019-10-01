package com.github.rz7d.brainfuck.parser;

public interface Memory {

    byte get(int index);

    void set(int index, byte value);

    Memory ensureCapacity(int size);

}
