package com.github.rz7d.brainfuck.parser.ast;

import com.github.rz7d.brainfuck.parser.Context;
import com.github.rz7d.brainfuck.parser.Instruction;
import com.github.rz7d.brainfuck.parser.Memory;

public final class Increment implements Instruction {

    @Override
    public void execute(Context context) {
        final Memory memory = context.memory();
        final var sp = context.sp();
        memory.set(sp, (byte) (memory.get(sp) + 1));
    }

    @Override
    public String toString() {
        return "inc";
    }

}
