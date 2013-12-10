package com.example;

import com.intellij.jsf.model.xml.component.Component;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;

import javax.swing.*;

/**
 * User: antonion
 * Date: 05/12/13 11:21
 */
public class HelloWorld implements ToolWindowFactory {
	@Override
	public void createToolWindowContent(Project project, ToolWindow toolWindow) {
		JComponent component = toolWindow.getComponent();
		component.getParent().add(new JLabel("Hello, World!"));
	}
}
