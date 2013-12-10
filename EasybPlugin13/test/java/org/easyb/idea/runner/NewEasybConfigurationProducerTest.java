package org.easyb.idea.runner;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * User: antonion
 * Date: 09/12/13 10:36
 */
@RunWith(MockitoJUnitRunner.class)
public class NewEasybConfigurationProducerTest {

	@Mock NewEasybConfiguration runConfiguration;
	@Mock ConfigurationContext configurationContext;
	@Mock Ref ref;
	@Mock PsiElement psiElement;
	@Mock PsiFile psiFile;

	private NewEasybConfigurationProducer producer;

	@Before
	public void setUp() throws Exception {
		producer = new NewEasybConfigurationProducer();

		when(configurationContext.getPsiLocation()).thenReturn(psiElement);
		when(psiElement.getContainingFile()).thenReturn(psiFile);
	}

	@Test
	public void configurationContextDoesNotPointToAEasybStory() throws Exception {
		when(psiFile.getName()).thenReturn("something.java");

		boolean fromContext = producer.setupConfigurationFromContext(runConfiguration, configurationContext, ref);

		assertThat(fromContext, is(false));
		verifyZeroInteractions(runConfiguration);
		verifyZeroInteractions(ref);
	}

//	@Test
//	public void configurationContextPointsToAEasybStory() throws Exception {
//		when(psiFile.getName()).thenReturn("something.story");
//
//		boolean fromContext = producer.setupConfigurationFromContext(runConfiguration, configurationContext, ref);
//
//		assertThat(fromContext, is(true));
//
//
//	}
}
