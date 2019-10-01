package com.github.rz7d.brainfuck.interpreter;

import java.util.Arrays;

import com.github.rz7d.brainfuck.parser.Memory;

public class InterpreterMemory implements Memory, Cloneable {

    private byte[] buffer;

    public InterpreterMemory() {
        this(16);
    }

    public InterpreterMemory(int initialCapacity) {
        this.buffer = new byte[initialCapacity];
    }

    @Override
    public byte get(int index) {
        ensureCapacity(index + 1);
        return buffer[(int) index];
    }

    @Override
    public void set(int index, byte value) {
        ensureCapacity(index + 1);
        buffer[(int) index] = value;
    }

    @Override
    public Memory ensureCapacity(int required) {
        int cap = buffer.length;
        if (cap < required) {
            buffer = Arrays.copyOf(buffer, (int) required << 1);
        }
        return this;
    }

    @Override
    public String toString() {
        return Arrays.toString(buffer);
    }

    @Override
    public InterpreterMemory clone() {
        try {
            return (InterpreterMemory) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new InternalError(exception);
        }
    }

}
