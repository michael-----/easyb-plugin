package org.easyb.idea.runner;

import com.intellij.execution.DefaultExecutionResult;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.JavaCommandLineState;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.util.JavaParametersUtil;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PathUtil;
import groovy.lang.GroovyObject;
import org.easyb.BehaviorRunner;
import org.easyb.plugin.remoting.RemoteExecutionListener;
import org.easyb.plugin.ui.swing.SwingEasybBuilder;
import org.jetbrains.annotations.NotNull;

public class EasybRunProfileState extends JavaCommandLineState {
	private Module module;
	private String specificationPath;
	private String jvmParameters;
	private SwingEasybBuilder builder;
	private NewEasybConfiguration newEasybConfiguration;

	protected EasybRunProfileState(ExecutionEnvironment environment, Module module, String specificationPath, String jvmParameters) {
		super(environment);
		this.module = module;
		this.specificationPath = specificationPath;
		this.jvmParameters = jvmParameters;
		this.builder = new SwingEasybBuilder();
	}

	public EasybRunProfileState(NewEasybConfiguration newEasybConfiguration, ExecutionEnvironment executionEnvironment, Module module, String specificationPath, String jvmParameters) {
		this(executionEnvironment, module, specificationPath, jvmParameters);
		this.newEasybConfiguration = newEasybConfiguration;
	}

	@Override
	public ExecutionResult execute(@NotNull Executor executor, @NotNull ProgramRunner runner) throws ExecutionException {
		EasybConsoleView console = new EasybConsoleView(builder.getView());
		ProcessHandler processHandler = startProcess();
		processHandler.addProcessListener(new EasybProcessListener(builder.getPresenter()));
		return new DefaultExecutionResult(console, processHandler, createActions(console, processHandler));
	}

	protected JavaParameters createJavaParameters() throws ExecutionException {
		RemoteExecutionListener listener = new RemoteExecutionListener();
		listener.setReceiver(builder.getPresenter());
		listener.start();
		JavaParameters javaParameters = new JavaParameters();

		JavaParametersUtil.configureModule(newEasybConfiguration.getConfigurationModule(),javaParameters,JavaParameters.JDK_AND_CLASSES_AND_TESTS,"");
		javaParameters.setJdk(ModuleRootManager.getInstance(module).getSdk());
		javaParameters.getClassPath().add(PathUtil.getJarPathForClass(BehaviorRunner.class));
		addProjectClasspath(javaParameters);
		javaParameters.configureByModule(module,JavaParameters.JDK_AND_CLASSES_AND_TESTS);
		javaParameters.getClassPath().add(PathUtil.getJarPathForClass(getClass()));
		javaParameters.getClassPath().add(PathUtil.getJarPathForClass(GroovyObject.class));
		javaParameters.setMainClass("org.easyb.plugin.remoting.RemoteRunner");
		javaParameters.getProgramParametersList().add(Integer.toString(listener.getPort()));
		javaParameters.getProgramParametersList().add(specificationPath);
		javaParameters.getVMParametersList().addParametersString(jvmParameters);
		return javaParameters;
	}

	private void addProjectClasspath(JavaParameters javaParameters) {
		for (VirtualFile file : getProjectClasspath()) {
			//intellij API stopped returning the class output path
			javaParameters.getClassPath().add(file.getPath()+"/target/test-classes");
			javaParameters.getClassPath().add(file.getPath()+"/target/classes");
		}
	}

	private VirtualFile[] getProjectClasspath() {
		return ProjectRootManager.getInstance(module.getProject()).getContentRootsFromAllModules();
	}
}
