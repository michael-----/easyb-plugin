package org.easyb.idea.runner;

import org.easyb.plugin.ui.swing.SwingEasybView;

import javax.swing.*;

public class EasybConsoleView extends ConsoleViewAdapter{
    private SwingEasybView view;

    public EasybConsoleView(SwingEasybView view) {
        this.view = view;
    }

    public JComponent getComponent() {
        return view;
    }
}
