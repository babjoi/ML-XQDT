/*******************************************************************************
 * Copyright (c) 2008, 2009 28msec Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Gabriel Petrovay (28msec) - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xquery.debug.debugger.zorba.translator.messages;

import org.eclipse.wst.xquery.debug.debugger.zorba.translator.IErrorCodes;
import org.eclipse.wst.xquery.debug.debugger.zorba.translator.model.QueryLocation;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class FramesMessage extends AbstractCommandMessage {

    public FramesMessage() {
        super(ICommandSets.COMMAND_SET_DYNAMIC, ICommandSets.COMMAND_FRAMES);
    }

    @Override
    public ReplyMessage buildReplyMessage(int errorCode, Object data) {
        QueryLocation[] locations = (QueryLocation[])data;
        Gson g = new Gson();
        String json = g.toJson(new LocationContainer(locations));
        return new ReplyMessage(getId(), IErrorCodes.NO_ERROR, json.getBytes());
    }

    public QueryLocation[] readLocations(ReplyMessage reply) {
        byte[] data = reply.getData();
        if (data == null)
            throw new MessageFormatException("Invalid data format. (null data in a reply message)");

        LocationContainer container = new Gson().fromJson(new String(data), LocationContainer.class);
        return container.getLocations();
    }

    private class LocationContainer {

        @SerializedName("frames")
        private QueryLocation[] locations;

        public LocationContainer(QueryLocation[] locations) {
            this.locations = locations;
        }

        public QueryLocation[] getLocations() {
            return locations;
        }
    }

    public static void main(String[] args) {
        FramesMessage fm = new FramesMessage();
        ReplyMessage rm = fm.buildReplyMessage(IErrorCodes.NO_ERROR, new QueryLocation[] {
                new QueryLocation("file", 1, 2, 3, 4), new QueryLocation("filefile", 11, 22, 33, 44) });
        String s = new String(rm.getData());
        System.out.println(s);
    }

}
