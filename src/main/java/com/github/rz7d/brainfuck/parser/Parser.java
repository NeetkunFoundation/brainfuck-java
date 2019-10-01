package com.github.rz7d.brainfuck.parser;

import java.nio.CharBuffer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.github.rz7d.brainfuck.parser.ast.Backward;
import com.github.rz7d.brainfuck.parser.ast.Decrement;
import com.github.rz7d.brainfuck.parser.ast.Forward;
import com.github.rz7d.brainfuck.parser.ast.Increment;
import com.github.rz7d.brainfuck.parser.ast.Jump;
import com.github.rz7d.brainfuck.parser.ast.JumpIfZero;
import com.github.rz7d.brainfuck.parser.ast.Print;
import com.github.rz7d.brainfuck.parser.ast.Read;

public final class Parser {

    public static List<Instruction> parse(CharSequence input) throws ParseException {
        return parse(CharBuffer.wrap(input));
    }

    public static List<Instruction> parse(CharBuffer input) throws ParseException {
        List<Instruction> program = new ArrayList<>();
        if (parseBlock(input, program, 0) != -1) {
            throw new InternalError("Corrupted implementation");
        }
        return program;
    }

    private static int parseBlock(CharBuffer input, List<Instruction> program, int offset) throws ParseException {
        while (input.hasRemaining()) {
            char mnemonic = input.get();

            final Instruction instruction = interpret(mnemonic);
            if (instruction != null) {
                program.add(instruction);
                continue;
            }

            switch (mnemonic) {
                case ']':
                    if (offset == 0)
                        throw new ParseException("Invalid end of loop marker", input.position() - 1);
                    return program.size() + 1;
                case '[':
                    var begin = offset + program.size();
                    List<Instruction> buffer = new ArrayList<>();
                    var end = parseBlock(input, buffer, begin + 1);

                    program.add(new JumpIfZero(begin + end + 1));
                    program.addAll(buffer);
                    program.add(new Jump(begin));
                    break;
                default:
                    if (!Character.isWhitespace(mnemonic))
                        System.err.printf("Warning: Unrecognized character '%s' at '%d'%n",
                            mnemonic, input.position());
            }
        }
        return -1;
    }

    private static Instruction interpret(char mnemonic) {
        switch (mnemonic) {
            case '+':
                return new Increment();
            case '-':
                return new Decrement();
            case '<':
                return new Backward();
            case '>':
                return new Forward();
            case ',':
                return Read.INSTANCE;
            case '.':
                return new Print();
        }
        return null;
    }

    private Parser() {
    }

}
