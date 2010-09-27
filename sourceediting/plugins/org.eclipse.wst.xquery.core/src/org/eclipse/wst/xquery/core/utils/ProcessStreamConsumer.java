/*******************************************************************************
 * Copyright (c) 2010 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ProcessStreamConsumer {

    private class ProcessStreamReader implements Runnable {
        private StringBuffer fBuffer;
        private InputStream fInputStream;

        public ProcessStreamReader(InputStream inputStream) {
            fInputStream = inputStream;
        }

        public void run() {
            try {
                BufferedReader output = new BufferedReader(new InputStreamReader(fInputStream));
                for (String line; (line = output.readLine()) != null;) {
                    forwardWrite(line + "\n");
                    if (fBuffer == null) {
                        fBuffer = new StringBuffer();
                    }
                    fBuffer.append(line + "\n");
                }
            } catch (IOException e) {
                // abort("Exception while reading the the Semantic Validator output streams");
            }
        }

        public String getData() {
            if (fBuffer == null) {
                return null;
            }
            return fBuffer.toString();
        }
    }

    private ProcessStreamReader fProcessOutputReader;
    private ProcessStreamReader fProcessErrorReader;
    private Thread fOutputReader, fErrorReader;
    private OutputStream fCopyToStreamStream;

    public ProcessStreamConsumer(Process process) {
        this(process, null);
    }

    public ProcessStreamConsumer(Process process, OutputStream copyToStream) {
        if (process == null) {
            throw new IllegalArgumentException("The process can not be null.");
        }
        fProcessOutputReader = new ProcessStreamReader(process.getInputStream());
        fProcessErrorReader = new ProcessStreamReader(process.getErrorStream());
        fOutputReader = new Thread(fProcessOutputReader);
        fErrorReader = new Thread(fProcessErrorReader);
        fCopyToStreamStream = copyToStream;
    }

    public void start() {
        fOutputReader.start();
        fErrorReader.start();
    }

    public void join() {
        try {
            fOutputReader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            fErrorReader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getOutput() {
        return fProcessOutputReader.getData();
    }

    public String getError() {
        return fProcessErrorReader.getData();
    }

    private synchronized void forwardWrite(String data) {
        if (fCopyToStreamStream != null) {
            try {
                fCopyToStreamStream.write(data.getBytes());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
