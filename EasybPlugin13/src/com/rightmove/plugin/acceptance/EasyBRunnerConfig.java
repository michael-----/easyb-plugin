package com.rightmove.plugin.acceptance;

import com.intellij.execution.configurations.RunConfigurationsSettings;
import com.intellij.openapi.options.UnnamedConfigurable;
import org.jetbrains.annotations.NotNull;

/**
 * User: antonion
 * Date: 05/12/13 11:34
 */
public class EasyBRunnerConfig implements RunConfigurationsSettings {
	@NotNull
	@Override
	public UnnamedConfigurable createConfigurable() {
		return null;
	}
}
