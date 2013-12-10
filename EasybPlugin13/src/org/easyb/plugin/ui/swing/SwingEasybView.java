package org.easyb.plugin.ui.swing;

import com.intellij.ui.components.JBScrollPane;
import org.easyb.plugin.StepResult;
import org.easyb.plugin.ui.EasybView;
import org.easyb.plugin.ui.ViewEventListener;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import static org.easyb.plugin.Outcome.RUNNING;
import static org.easyb.util.BehaviorStepType.GENESIS;

public class SwingEasybView extends JPanel implements EasybView<SwingResultNode> {
    private JTextArea consoleTextArea;
    private SwingResultNode root;
    protected JTree tree;
    private JTextArea outputTextArea;

    public SwingEasybView() {
        setLayout(new BorderLayout());

        root = new SwingResultNode(new StepResult("Root", GENESIS, RUNNING, 0));
        tree = createTree(root);
        tree.setCellRenderer(new EasybNodeRenderer());
        tree.setRootVisible(false);

        outputTextArea = new JTextArea();
        consoleTextArea = new JTextArea();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Console", new JBScrollPane(consoleTextArea));
        tabbedPane.addTab("Output", new JBScrollPane(outputTextArea));

        JSplitPane pane = new JSplitPane(HORIZONTAL_SPLIT, new JBScrollPane(tree), tabbedPane);
        pane.setDividerLocation(300);
        add(pane);
    }

    protected JTree createTree(SwingResultNode node) {
        return new JTree(node);
    }

    public void addBehaviorResult(SwingResultNode result) {
        addBehaviorResult(root, result);
    }

    public void addBehaviorResult(SwingResultNode parent, SwingResultNode result) {
        getModel().insertNodeInto(result, parent, parent.getChildCount());
        refresh();
    }

    public void displayFailure(Throwable failure) {
    }

    public void writeOutput(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                outputTextArea.setText(text);
            }
        });
    }

    public void writeConsole(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                consoleTextArea.append(text);
            }
        });
    }

    public void refresh() {
        getModel().nodeStructureChanged(root);
        TreeUtil.expandAll(tree, true);
    }

    public void registerEventListener(final ViewEventListener listener) {
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent event) {
                SwingResultNode node = (SwingResultNode) tree.getLastSelectedPathComponent();
                listener.resultSelected(node);
            }
        });
    }

    private DefaultTreeModel getModel() {
        return ((DefaultTreeModel) tree.getModel());
    }
}
