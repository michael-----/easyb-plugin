package org.easyb.idea.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizer;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;

/**
 * User: antonion
 * Date: 06/12/13 17:44
 */
public class NewEasybConfiguration extends ModuleBasedConfiguration {

	private String specificationPath;
	private String jvmParameters;

	protected NewEasybConfiguration(Project project, ConfigurationFactory factory, String name) {
		super(name, new RunConfigurationModule(project), factory);
	}

	@Override
	public void readExternal(Element element) throws InvalidDataException {
		super.readExternal(element);
		readModule(element);
		specificationPath = JDOMExternalizer.readString(element, "specificationPath");
		jvmParameters = JDOMExternalizer.readString(element, "jvmParameters");
	}

	@Override
	public void writeExternal(Element element) throws WriteExternalException {
		super.writeExternal(element);
		writeModule(element);
		JDOMExternalizer.write(element, "specificationPath",specificationPath);
		JDOMExternalizer.write(element, "jvmParameters",jvmParameters);
	}

	@NotNull
	@Override
	public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
		return new NewEasybRunConfigurationEditor();
	}

	@Nullable
	@Override
	public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) throws ExecutionException {
		return new EasybRunProfileState(this,executionEnvironment, getModule(), specificationPath, jvmParameters);
	}

	@Override
	public Collection<Module> getValidModules() {
		return Arrays.asList(ModuleManager.getInstance(getProject()).getModules());
	}

	public Module getModule() {
		return getConfigurationModule().getModule();
	}

	public void setSpecificationPath(String specPath) {
		this.specificationPath = specPath;
	}

	public void setJvmParameters(String jvmParameters) {
		this.jvmParameters = jvmParameters;
	}

	public String getSpecificationPath() {
		return specificationPath;
	}

	public String getJvmParameters() {
		return jvmParameters;
	}
}
