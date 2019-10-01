package com.github.rz7d.brainfuck.parser.ast;

import com.github.rz7d.brainfuck.parser.Context;
import com.github.rz7d.brainfuck.parser.Instruction;

public final class Backward implements Instruction {

    @Override
    public void execute(Context context) {
        context.sp(context.sp() - 1);
    }

    @Override
    public String toString() {
        return "dec ptr";
    }

}
