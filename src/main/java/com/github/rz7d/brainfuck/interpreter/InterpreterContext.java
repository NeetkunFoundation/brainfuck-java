package com.github.rz7d.brainfuck.interpreter;

import com.github.rz7d.brainfuck.parser.Context;
import com.github.rz7d.brainfuck.parser.Memory;

public class InterpreterContext implements Context, Cloneable {

    private final Memory heap;

    private int sp;
    private int pc;

    public InterpreterContext(Memory heap) {
        this.heap = heap;
    }

    @Override
    public Memory memory() {
        return heap;
    }

    @Override
    public int sp() {
        return sp;
    }

    @Override
    public void sp(int sp) {
        this.sp = sp;
    }

    @Override
    public int pc() {
        return pc;
    }

    @Override
    public void pc(int pc) {
        this.pc = pc;
    }

    @Override
    public InterpreterContext clone() {
        try {
            return (InterpreterContext) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new InternalError(exception);
        }
    }

}
