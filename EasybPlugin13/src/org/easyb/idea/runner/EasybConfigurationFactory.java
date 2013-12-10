package org.easyb.idea.runner;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;

public class EasybConfigurationFactory extends ConfigurationFactory {
	public EasybConfigurationFactory(NewEasybConfigurationType easybRunConfigurationType) {
		super(easybRunConfigurationType);
	}

	@Override
	public RunConfiguration createTemplateConfiguration(Project project) {
		return new NewEasybConfiguration(project, this, "Easyb Spec");
	}
}