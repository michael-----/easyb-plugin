package org.easyb.idea.runner;

import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.actionSystem.AnAction;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * User: antonion
 * Date: 06/12/13 17:25
 */
public class ConsoleViewAdapter implements ConsoleView {
	@Override
	public void print(String s, ConsoleViewContentType consoleViewContentType) {

	}

	@Override
	public void clear() {

	}

	@Override
	public void scrollTo(int i) {

	}

	@Override
	public void attachToProcess(ProcessHandler processHandler) {

	}

	@Override
	public void setOutputPaused(boolean b) {

	}

	@Override
	public boolean isOutputPaused() {
		return false;
	}

	@Override
	public boolean hasDeferredOutput() {
		return false;
	}

	@Override
	public void performWhenNoDeferredOutput(Runnable runnable) {

	}

	@Override
	public void setHelpId(String s) {

	}

	@Override
	public void addMessageFilter(Filter filter) {

	}

	@Override
	public void printHyperlink(String s, HyperlinkInfo hyperlinkInfo) {

	}

	@Override
	public int getContentSize() {
		return 0;
	}

	@Override
	public boolean canPause() {
		return false;
	}

	@NotNull
	@Override
	public AnAction[] createConsoleActions() {
		return new AnAction[0];
	}

	@Override
	public void allowHeavyFilters() {

	}

	@Override
	public JComponent getComponent() {
		return null;
	}

	@Override
	public JComponent getPreferredFocusableComponent() {
		return null;
	}

	@Override
	public void dispose() {

	}
}
