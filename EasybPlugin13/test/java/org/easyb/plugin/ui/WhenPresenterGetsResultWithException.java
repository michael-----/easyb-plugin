package org.easyb.plugin.ui;

import org.easyb.exception.VerificationException;
import org.easyb.plugin.StubView;
import org.easyb.plugin.remoting.RemotableBehaviorStep;
import org.easyb.result.Result;
import org.junit.Test;

import static org.easyb.util.BehaviorStepType.STORY;
import static org.junit.Assert.assertEquals;

public class WhenPresenterGetsResultWithException {
    @SuppressWarnings({"ThrowableInstanceNeverThrown"})
    @Test
    public void shouldAskViewToDisplayDetails() {
        StubView view = new StubView();
        StubNodeBuilder builder = new StubNodeBuilder();

        EasybPresenter<StubResultNode> presenter = new EasybPresenter<StubResultNode>(view, builder);
        VerificationException failure = new VerificationException("Result did not match expectation");
        presenter.startStep(new RemotableBehaviorStep(STORY, "test story"));
        presenter.gotResult(new Result(failure));

        assertEquals(failure, view.getResultNode().getResult().getCause());
    }
}
