package org.easyb.idea.runner;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * User: antonion
 * Date: 09/12/13 12:36
 */
public class NewEasybRunConfigurationEditor extends SettingsEditor<NewEasybConfiguration> {
	private EasybRunConfigurationDialog dialog = new EasybRunConfigurationDialog();

	protected void resetEditorFrom(NewEasybConfiguration configuration) {
		dialog.specificationPathField.setText(configuration.getSpecificationPath());
		dialog.jvmParametersField.setText(configuration.getJvmParameters());

		dialog.moduleComboModel.removeAllElements();
		for (Module module : configuration.getValidModules()) {
			dialog.moduleComboModel.addElement(module);
		}
		dialog.moduleComboModel.setSelectedItem(configuration.getModule());
	}

	protected void applyEditorTo(NewEasybConfiguration configuration) throws ConfigurationException {
		configuration.setSpecificationPath(dialog.specificationPathField.getText());
		configuration.setJvmParameters(dialog.jvmParametersField.getText());
		configuration.setModule((Module) dialog.moduleCombo.getSelectedItem());
	}

	@NotNull
	protected JComponent createEditor() {
		dialog.moduleComboModel = new DefaultComboBoxModel();
		dialog.moduleCombo.setModel(dialog.moduleComboModel);

		dialog.moduleCombo.setRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList list, final Object value, int index, boolean isSelected, boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				final Module module = (Module) value;
				if (module != null) {
					setIcon(ModuleType.get(module).getNodeIcon(false));
					setText(module.getName());
				}
				return this;
			}
		});

		return dialog;
	}

	protected void disposeEditor() {
	}
}
