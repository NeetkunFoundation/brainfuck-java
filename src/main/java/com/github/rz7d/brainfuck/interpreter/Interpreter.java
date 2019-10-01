package com.github.rz7d.brainfuck.interpreter;

import java.util.List;

import com.github.rz7d.brainfuck.debugger.Debugger;
import com.github.rz7d.brainfuck.parser.Instruction;

public final class Interpreter {

    public static Interpreter create() {
        return new Interpreter(new InterpreterContext(new InterpreterMemory()));
    }

    private final InterpreterContext context;

    public Interpreter(InterpreterContext context) {
        this.context = context;
    }

    public void execute(List<Instruction> program) {
        execute(program, null);
    }

    public void execute(List<Instruction> program, Debugger debugger) {
        final var context = this.context;
        if (debugger != null)
            debugger.onBoot(context, program);

        while (context.pc() < program.size()) {
            interpret(program.get((int) context.pc()), debugger);
        }

        if (debugger != null)
            debugger.onComplete(context, program);
    }

    public void interpret(Instruction instruction, Debugger debugger) {
        final var context = this.context;
        final var pc = context.pc();

        if (debugger != null)
            debugger.onPreExecute(context, instruction);

        instruction.execute(context);

        if (debugger != null)
            debugger.onPostExecute(context, instruction);

        if (context.pc() == pc)
            context.pc(pc + 1);
    }

    public InterpreterContext context() {
        return this.context;
    }

}
