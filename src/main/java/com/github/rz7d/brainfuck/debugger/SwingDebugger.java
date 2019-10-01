package com.github.rz7d.brainfuck.debugger;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import com.github.rz7d.brainfuck.parser.Context;
import com.github.rz7d.brainfuck.parser.Instruction;

public final class SwingDebugger implements Debugger {

    private volatile JFrame frame;
    private final DefaultListModel<String> model = new DefaultListModel<>();

    @Override
    public void onBoot(Context context, List<Instruction> program) {
        for (int i = 0; i < program.size(); i++) {
            System.out.printf("0x%08X: %s%n", i, program.get(i));
        }

        model.clear();
        JFrame frame = this.frame;
        if (frame != null) {
            frame.dispose();
        }
        this.frame = frame = new JFrame("Debugger");
        frame.setSize(600, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new JList<>(model), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }

    @Override
    public void onPreExecute(Context context, Instruction instruction) {
        if (frame != null && frame.isVisible()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException exception) {
            }

            var pc = context.pc();
            var sp = context.sp();
            var memory = context.memory().toString();

            SwingUtilities.invokeLater(() -> {
                model.clear();
                model.addElement("PC: " + pc);
                model.addElement("SP: " + sp);
                model.addElement("MEMORY: " + memory);
                model.addElement(String.format("INST: %08X %s", pc, instruction.toString()));
            });
        }
    }

    @Override
    public void onComplete(Context context, List<Instruction> program) {
    }

    @Override
    public void close() throws Exception {
        var frame = this.frame;
        if (frame != null)
            frame.dispose();
    }

}
