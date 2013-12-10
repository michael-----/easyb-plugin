package org.easyb.idea.runner;

import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * User: antonion
 * Date: 06/12/13 17:32
 */
public class NewEasybConfigurationType extends ConfigurationTypeBase {

	public static final String ID = "NewEasybConfigurationType";
	public static final String DISPLAY_NAME = "Easyb Specification";
	public static final String DESCRIPTION = "Easyb Specification";
	public static final Icon ICON = IconLoader.getIcon("/easyb.png");

	public NewEasybConfigurationType() {
		super(ID, DISPLAY_NAME, DESCRIPTION, ICON);
		addFactory( new EasybConfigurationFactory(this));
	}
}
