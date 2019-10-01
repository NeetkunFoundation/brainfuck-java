package com.github.rz7d.brainfuck.parser;

public interface Context {

    /**
     * Get the heap.
     *
     * @return Current heap memory.
     */
    Memory memory();

    /**
     * Get the current stack pointer.
     *
     * @return Current stack pointer.
     */
    int sp();

    /**
     * Set stack pointer to specified address.
     *
     * @param sp A new address.
     */
    void sp(int sp);

    /**
     * Get the current program counter.
     *
     * @return Current program counter.
     */
    int pc();

    /**
     * Set program counter to specified address.
     *
     * @param location A new address of executable
     */
    void pc(int location);

}
