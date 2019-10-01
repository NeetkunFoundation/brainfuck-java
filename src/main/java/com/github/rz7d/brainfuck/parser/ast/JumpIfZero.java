package com.github.rz7d.brainfuck.parser.ast;

import com.github.rz7d.brainfuck.parser.Context;
import com.github.rz7d.brainfuck.parser.Memory;

public final class JumpIfZero extends Jump {

    public JumpIfZero(int target) {
        super(target);
    }

    @Override
    public void execute(Context context) {
        final Memory memory = context.memory();
        if (memory.get(context.sp()) == 0)
            context.pc(target);
    }

    @Override
    public String toString() {
        return String.format("jz 0x%08X", target);
    }

}
