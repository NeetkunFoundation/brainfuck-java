package com.github.rz7d.brainfuck.debugger;

import java.util.List;

import com.github.rz7d.brainfuck.parser.Context;
import com.github.rz7d.brainfuck.parser.Instruction;

public interface Debugger extends AutoCloseable {

    default void onBoot(Context context, List<Instruction> program) {

    }

    default void onPreExecute(Context context, Instruction instruction) {

    }

    default void onPostExecute(Context context, Instruction instruction) {

    }

    default void onComplete(Context context, List<Instruction> program) {

    }

}
