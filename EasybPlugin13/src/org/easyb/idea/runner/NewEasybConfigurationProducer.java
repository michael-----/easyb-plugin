package org.easyb.idea.runner;

import com.intellij.execution.Location;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

/**
 * User: antonion
 * Date: 09/12/13 10:12
 */
public class NewEasybConfigurationProducer extends RunConfigurationProducer<NewEasybConfiguration> {

	private PsiElement sourceElement;

	// needs to be instantiated with no params from the plugin.xml
	public NewEasybConfigurationProducer() {
		super(new EasybConfigurationFactory(new NewEasybConfigurationType()));
	}

	@Override
	public boolean isConfigurationFromContext(NewEasybConfiguration configuration, ConfigurationContext context) {
		if (sourceElement == null || sourceElement.getContainingFile() == null) return false;
		return Comparing.equal(configuration.getSpecificationPath(), sourceElement.getContainingFile().getVirtualFile().getPath());
	}

	@Override
	protected boolean setupConfigurationFromContext(NewEasybConfiguration configuration, ConfigurationContext context, Ref ref) {
		PsiElement psiElement = context.getPsiLocation();
		if (!isSpec(psiElement)) {
			return false;
		}
		sourceElement = psiElement;

		System.out.println("(resolveSpecName(context.getLocation()) = " + resolveSpecName(context.getLocation()));
		// copied the logic for setting the module from com.intellij.execution.junit.JavaRuntimeConfigurationProducerBase
		final Module contextModule = context.getModule();
		final RunnerAndConfigurationSettings template = context.getRunManager().getConfigurationTemplate(getConfigurationFactory());
		final Module predefinedModule = ((ModuleBasedConfiguration) template.getConfiguration()).getConfigurationModule().getModule();
		if (predefinedModule != null) {
			configuration.setModule(predefinedModule);
		}
		else if (configuration.getConfigurationModule().getModule() == null && contextModule != null) {
			configuration.setModule(contextModule);
		}

		configuration.setSpecificationPath(resolveSpecPath(context.getLocation(), configuration));
		configuration.setName(resolveSpecName(context.getLocation()));
		return true;

	}

	private String resolveSpecName(Location location) {
		return location.getOpenFileDescriptor().getFile().getName();
	}

	private String resolveSpecPath(Location location, NewEasybConfiguration configuration) {
		String modulePath = configuration.getModule().getModuleFilePath();
		return location.getOpenFileDescriptor().getFile().getPath().replace(modulePath, "");
	}

	private boolean isSpec(PsiElement psiElement) {
		PsiFile file = psiElement.getContainingFile();
		return file != null && (file.getName().endsWith(".story") || file.getName().endsWith(".specification"));
	}
}
