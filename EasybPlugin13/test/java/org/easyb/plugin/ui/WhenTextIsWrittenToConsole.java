/*
 * Copyright (c) 2007
 * Semantra, Inc. Addison, Texas 214.445.2900
 */
package org.easyb.plugin.ui;

import org.easyb.plugin.ConsoleOutputListener;
import org.easyb.plugin.StubView;
import org.easyb.plugin.remoting.RemotableBehaviorStep;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.easyb.util.BehaviorStepType.*;
import static org.easymock.EasyMock.*;

public class WhenTextIsWrittenToConsole {
    private static final String TEXT = "expected output";

  @Test
  public void doNothing() {

  }

    @SuppressWarnings({"unchecked"})
    @Test
    public void shouldWriteOutputToView() {
        EasybView<StubResultNode> view = createMock(EasybView.class);
        view.writeConsole(TEXT);
        expectLastCall();
        replay(view);

        ConsoleOutputListener presenter = new EasybPresenter<StubResultNode>(view, new StubNodeBuilder());
        presenter.textAvailable(TEXT);

        verify(view);
    }

    @SuppressWarnings({"unchecked"})
    @Test
    public void shouldAppendTextFromSpecificationToResultNode() {
        StubView view = new StubView();
        EasybPresenter presenter = new EasybPresenter(view, new StubNodeBuilder());

        presenter.startStep(new RemotableBehaviorStep(SPECIFICATION, "spec"));
        presenter.startStep(new RemotableBehaviorStep(IT, "test"));
        presenter.textAvailable("Running spec specification (spec.specification)\n");
        presenter.textAvailable(TEXT);

        assertEquals(TEXT, view.getResultNode().getOutput());
    }

    @SuppressWarnings({"unchecked"})
    @Test
    public void shouldAppendTextFromStoryToResultNode() {
        StubView view = new StubView();
        EasybPresenter presenter = new EasybPresenter(view, new StubNodeBuilder());

        presenter.startStep(new RemotableBehaviorStep(STORY, "testing"));
        presenter.startStep(new RemotableBehaviorStep(GIVEN, "given"));
        presenter.textAvailable("Running testing story (Testing.story)\n");
        presenter.textAvailable(TEXT);

        assertEquals(TEXT, view.getResultNode().getOutput());
    }
}
