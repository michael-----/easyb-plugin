package org.easyb.plugin;

import org.easyb.plugin.remoting.RemotableBehaviorStep;
import org.easyb.plugin.ui.EasybPresenter;
import org.easyb.plugin.ui.StubNodeBuilder;
import org.easyb.plugin.ui.StubResultNode;
import org.easyb.result.Result;
import org.easyb.util.BehaviorStepType;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.easyb.plugin.Outcome.FAILURE;
import static org.easyb.plugin.ui.StubResultNode.nodeFor;
import static org.easyb.result.Result.*;
import static org.easyb.util.BehaviorStepType.*;

public class WhenASpecIsRun {
    private StubView view;
    private EasybPresenter presenter;

  @Test
  public void doNothing() {} // surefire complaints otherwise

    @Before
    public void setUp() {
        view = new StubView();
        presenter = new EasybPresenter<StubResultNode>(view, new StubNodeBuilder());
    }

    @Test
    public void shouldAddNodesToTree() {
        StubResultNode specNode = nodeFor(SPECIFICATION, "transferring funds", FAILURE);
        specNode.add(nodeFor(IT, "should withdraw funds", FAILURE));

        presenter.startStep(new RemotableBehaviorStep(SPECIFICATION, "transferring funds"));
        presenter.startStep(new RemotableBehaviorStep(IT, "should withdraw funds"));
        presenter.gotResult(new Result(FAILED));
        presenter.stopStep();
        presenter.stopStep();

        assertEquals(specNode.getResult().getStepName(), view.getResultNode().getResult().getStepName());
        assertEquals(specNode.getResult().getStepType(), view.getResultNode().getResult().getStepType());
        assertEquals(specNode.getResult().getOutcome(), view.getResultNode().getResult().getOutcome());
    }
}
