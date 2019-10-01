package com.github.rz7d.brainfuck.parser.ast;

import com.github.rz7d.brainfuck.parser.Context;
import com.github.rz7d.brainfuck.parser.Instruction;

public final class Print implements Instruction {

    @Override
    public void execute(Context context) {
        System.out.print((char) context.memory().get(context.sp()));
    }

    @Override
    public String toString() {
        return ".";
    }

}
