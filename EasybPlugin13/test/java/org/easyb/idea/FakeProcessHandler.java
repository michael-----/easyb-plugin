/*
 * Copyright (c) 2007
 * Semantra, Inc. Addison, Texas 214.445.2900
 */
package org.easyb.idea;

import com.intellij.execution.process.ProcessHandler;

import java.io.OutputStream;

public class FakeProcessHandler extends ProcessHandler {
    protected void destroyProcessImpl() {
    }

    protected void detachProcessImpl() {
    }

    public boolean detachIsDefault() {
        return false;
    }

    public OutputStream getProcessInput() {
        return null;
    }
}
