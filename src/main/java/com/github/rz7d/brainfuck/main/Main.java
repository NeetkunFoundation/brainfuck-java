package com.github.rz7d.brainfuck.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;

import com.github.rz7d.brainfuck.debugger.Debugger;
import com.github.rz7d.brainfuck.debugger.SwingDebugger;
import com.github.rz7d.brainfuck.interpreter.Interpreter;
import com.github.rz7d.brainfuck.parser.Instruction;
import com.github.rz7d.brainfuck.parser.Parser;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to azure's Brainf*ck REPL");
        System.out.println("Commands:");
        System.out.println("- /exit: Shutdown the REPL");
        System.out.println("- /reset: Reset the interpreter");
        System.out.println();

        var interpreter = Interpreter.create();
        Debugger debugger = null;

        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (;;) {
                System.out.print("> ");
                var line = reader.readLine();
                if (line == null) {
                    break;
                }

                var command = line.trim();
                switch (command) {
                    case "/exit":
                        return;
                    case "/showcontext": {
                        var context = interpreter.context();
                        System.out.println("Context Information:");
                        System.out.printf("- Program Counter: %d (0x%08X)%n", context.pc(), context.pc());
                        System.out.printf("- Stack Pointer: %d (0x%08X)%n", context.sp(), context.sp());
                        System.out.printf("- Memory: %s%n", context.memory());
                        System.out.println();
                        break;
                    }
                    case "/debug":
                    case "/debug on":
                        debugger = new SwingDebugger();
                        System.out.println("Debug mode: on");
                        break;
                    case "/nodebug":
                    case "/debug off":
                        if (debugger != null)
                            debugger.close();
                        debugger = null;
                        System.out.println("Debug mode: off");
                        break;
                    case "/reset":
                        interpreter = Interpreter.create();
                        break;
                    default: {
                        List<Instruction> program;
                        try {
                            program = Parser.parse(line);
                        } catch (ParseException exception) {
                            exception.printStackTrace();
                            continue;
                        }
                        var context = interpreter.context();
                        context.pc(0);
                        if (debugger != null)
                            debugger.onBoot(context, program);
                        while (context.pc() < program.size()) {
                            interpreter.interpret(program.get(context.pc()), debugger);
                        }
                        if (debugger != null)
                            debugger.onComplete(context, program);
                        System.out.println();
                    }
                }
            }
        }
    }

}
