package org.easyb.plugin.ui.swing;

import org.easyb.plugin.StepResult;
import org.easyb.plugin.ui.ViewEventListener;
import org.junit.Test;

import static org.easyb.plugin.Outcome.SUCCESS;
import static org.easyb.util.BehaviorStepType.THEN;
import static org.easymock.EasyMock.*;

public class WhenSwingNodeSelected {
    @Test
    public void shouldNotfyPresenter()
    {
        SwingNodeBuilder builder = new SwingNodeBuilder();
        StepResult result = new StepResult("name", THEN, SUCCESS, 1);
        SwingResultNode node = builder.build(result);

        ViewEventListener listener = createMock(ViewEventListener.class);
        listener.resultSelected(node);
        replay(listener);

        TestingSwingEasybView view = new TestingSwingEasybView();
        view.registerEventListener(listener);
        view.addBehaviorResult(node);
        view.selectNode(node);

        verify(listener);
    }
}
