package com.github.rz7d.brainfuck.parser.ast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.github.rz7d.brainfuck.parser.Context;
import com.github.rz7d.brainfuck.parser.Instruction;

public final class Read implements Instruction {

    public static final Read INSTANCE = new Read();

    private final BufferedReader reader;

    private Read() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void execute(Context context) {
        // TODO: UTF-8
        try {
            var c = reader.read();
            if (c == -1)
                c = 0x1A;
            context.memory().set(context.sp(), (byte) c);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return ",";
    }

}
