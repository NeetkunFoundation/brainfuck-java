package com.github.rz7d.brainfuck.parser.ast;

import com.github.rz7d.brainfuck.parser.Context;
import com.github.rz7d.brainfuck.parser.Instruction;

public class Jump implements Instruction {

    protected final int target;

    public Jump(int target) {
        this.target = target;
    }

    @Override
    public void execute(Context context) {
        context.pc(target);
    }

    @Override
    public String toString() {
        return String.format("jmp 0x%08X", target);
    }

}
