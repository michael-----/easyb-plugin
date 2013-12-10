package org.easyb.plugin.ui.swing;

import org.easyb.plugin.StepResult;
import org.easyb.plugin.ui.NodeBuilder;
import org.easyb.plugin.ui.ResultNode;
import org.junit.Test;

import static org.easyb.plugin.Outcome.SUCCESS;
import static org.easyb.util.BehaviorStepType.GIVEN;
import static org.junit.Assert.assertEquals;

public class WhenBuildingSwingNode {
    @Test
    public void shouldConstructEasybTreeNode() {
        NodeBuilder<? extends ResultNode> builder = new SwingNodeBuilder();

        StepResult result = new StepResult("name", GIVEN, SUCCESS, 1);
        ResultNode expected = new SwingResultNode(result);
        ResultNode actual = builder.build(result);

        assertEquals(expected, actual);
        assertEquals(result, actual.getResult());
    }
}
