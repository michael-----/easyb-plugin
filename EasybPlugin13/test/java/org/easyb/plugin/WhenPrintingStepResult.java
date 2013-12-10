package org.easyb.plugin;

import org.junit.Test;

import static org.easyb.util.BehaviorStepType.SPECIFICATION;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class WhenPrintingStepResult {
    @Test
    public void shouldIncludeStepNameAndOutcomeInOutput() {
        StepResult result = new StepResult("foo", SPECIFICATION, Outcome.SUCCESS, 1);
        assertThat(result.toString(), containsString("foo"));
        assertThat(result.toString(), containsString("SUCCESS"));
    }
}
