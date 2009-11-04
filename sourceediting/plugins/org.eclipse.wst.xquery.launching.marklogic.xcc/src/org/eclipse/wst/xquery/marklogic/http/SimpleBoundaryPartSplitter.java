/*
 * Copyright (c) 2003-2009 Mark Logic Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Mark Logic, Inc.
 */
package org.eclipse.wst.xquery.marklogic.http;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleBoundaryPartSplitter implements MultipartSplitter {
	private static final int MIN_BUFFER_SIZE = 2 * 1024;
	private static final int MAX_BUFFER_SIZE = 10 * 1024 * 1024;

	private final BoundaryBuffer bbuf;
	private final InputStream httpStream;
	private final Logger logger;

	public SimpleBoundaryPartSplitter(InputStream inputStream, byte[] boundary,
			int bufSize, Logger loggerArg) {
		if (loggerArg == null) {
			logger = Logger.getLogger(getClass().getName());
		} else {
			logger = loggerArg;
		}

		httpStream = inputStream;
		bbuf = new BoundaryBuffer(inputStream, boundary, bufferSize(bufSize),
				logger);

		if (logger.isLoggable(Level.FINER)) {
			logger.finer("Constructed: bufsize=" + bbuf.capacity()
					+ ", boundary='" + new String(boundary) + "'");
		}
	}

	private int bufferSize(int bufSize) {
		if (bufSize == 0)
			return DEF_BUFFER_SIZE;
		if (bufSize < MIN_BUFFER_SIZE)
			return MIN_BUFFER_SIZE;
		if (bufSize > MAX_BUFFER_SIZE)
			return MAX_BUFFER_SIZE;

		return bufSize;
	}

	public SimpleBoundaryPartSplitter(InputStream inputStream, byte[] boundary,
			int bufSize) {
		this(inputStream, boundary, bufSize, null);
	}

	// -------------------------------------------------------------
	// MultipartSplitter interface

	public synchronized boolean hasNext() throws IOException {
		return bbuf.hasNext();
	}

	public synchronized void next() throws IOException {
		bbuf.next();
	}

	public synchronized int read(byte[] bytes, int offset, int length)
			throws IOException {
		return bbuf.read(bytes, offset, length);
	}

	public void close() throws IOException {
		// Skip to end of stream, but don't close it.
		// This stream is a "view" of the HTTP socket stream,
		// we don't want the close to propagate to the "real"
		// socket stream.

		long skipped = httpStream.skip(Long.MAX_VALUE);

		if (skipped > 0) {
			if (logger.isLoggable(Level.FINEST))
				logger.finest("flushed " + skipped + " bytes on close");
		}
	}

	// ----------------------------------------------------
	// ----------------------------------------------------

	static class BoundaryBuffer {
		private static final byte[] BOUNDARY_LEADIN = "\n--".getBytes();
		private static final byte[] BOUNDARY_LEADOUT = "--\n".getBytes();

		private InputStream is;
		private final ByteBuffer byteBuffer;
		private final byte[] buffer;
		private final byte[] boundary;
		private final Logger logger;
		private int boundaryPosition = -1;
		private int scanPoint = 0;

		public BoundaryBuffer(InputStream is, byte[] boundary, int bufSize,
				Logger logger) {
			this.is = is;
			this.logger = logger;
			this.boundary = new byte[boundary.length + BOUNDARY_LEADIN.length];

			System.arraycopy(BOUNDARY_LEADIN, 0, this.boundary, 0,
					BOUNDARY_LEADIN.length);
			System.arraycopy(boundary, 0, this.boundary,
					BOUNDARY_LEADIN.length, boundary.length);

			byteBuffer = ByteBuffer.allocate(Math.max(bufSize,
					this.boundary.length + BOUNDARY_LEADOUT.length));
			buffer = byteBuffer.array();

			if (logger.isLoggable(Level.FINER)) {
				logger.finer("constructed: bufsize=" + byteBuffer.capacity()
						+ ", boundary=" + new String(boundary));
			}
		}

		// --------------------------------------------------------

		public int capacity() {
			return byteBuffer.capacity();
		}

		public boolean hasNext() throws IOException {
			flushToBoundary();

			return (!atTerminalBoundary());
		}

		public int read(byte[] bytes, int offset, int length)
				throws IOException {
			boolean logFine = logger.isLoggable(Level.FINEST);

			if (logFine)
				logger.finest("enter");

			int toRead = length;
			int bytesRead = 0;

			while (toRead > 0) {
				fillBuffer();
				scanForBoundary(byteBuffer.position());

				int fetchCount = Math.min(toRead, readableBytes());

				if (fetchCount == 0) {
					if (!atBoundary()) {
						throw new IOException(
								"Premature end-of-stream reading result item");
					}

					if (logFine)
						logger.finest("end of part, breaking");

					break;
				}

				if (bytes != null) {
					if (logFine)
						logger.finest("copying out " + fetchCount
								+ " bytes, offset=" + (offset + bytesRead));

					System.arraycopy(buffer, 0, bytes, offset + bytesRead,
							fetchCount);
				}

				compactBuffer(fetchCount);

				bytesRead += fetchCount;
				toRead -= fetchCount;
			}

			if (bytesRead == 0)
				bytesRead = -1;

			if (logFine)
				logger.finest("leave: bytesRead=" + bytesRead);

			return bytesRead;
		}

		public int read(byte[] bytes) throws IOException {
			return (read(bytes, 0, (bytes == null) ? Integer.MAX_VALUE
					: bytes.length));
		}

		public boolean next() throws IOException {
			logger.finest("enter");

			if (!atBoundary()) {
				flushToBoundary();
			}

			if (atTerminalBoundary()) {
				logger.finest("terminal, false");
				return false;
			}

			compactBuffer(boundary.length + 1); // eat NL following inter-part
												// boundary

			logger.finest("true");

			return true;
		}

		private boolean atBoundary() {
			return boundaryPosition == scanPoint;
		}

		private boolean atTerminalBoundary() {
			return atBoundary()
					&& boundaryIsHere(BOUNDARY_LEADOUT, boundary.length,
							byteBuffer.position());
		}

		private int readableBytes() {
			if (logger.isLoggable(Level.FINEST))
				logger.finest("scanPoint=" + scanPoint);

			return (scanPoint);
		}

		private void flushToBoundary() throws IOException {
			boolean logFine = logger.isLoggable(Level.FINEST);

			if (logFine)
				logger.finest("flushing");

			int rc;

			// FIXME: use skip
			while ((rc = read(null)) != -1) {
				if (logFine)
					logger.finest("  flushed " + rc + " bytes");
			}

			if (logFine)
				logger.finest("done");
		}

		private void compactBuffer(int byteCount) {
			boolean logFine = logger.isLoggable(Level.FINEST);

			if (logFine)
				logger.finest("byteCount=" + byteCount + ", buffer="
						+ byteBuffer);

			byteBuffer.flip();
			byteBuffer.position(byteCount);
			byteBuffer.compact();

			scanPoint = Math.max(0, scanPoint - byteCount);
			boundaryPosition = Math.max(-1, boundaryPosition - byteCount);

			if (logFine) {
				logger.finest("compacted by " + byteCount + ", scanPoint="
						+ scanPoint + ", boundaryPosition=" + boundaryPosition);
			}
		}

		private void fillBuffer() throws IOException {
			boolean logFine = logger.isLoggable(Level.FINEST);

			if (logFine) {
				logger.finest("enter: is null?=" + (is == null) + ", count="
						+ byteBuffer.position() + ", remaining="
						+ byteBuffer.remaining() + ", hasRemaining="
						+ byteBuffer.hasRemaining() + ", capacity="
						+ byteBuffer.capacity() + ", scanPoint=" + scanPoint
						+ ", boundaryPosition=" + boundaryPosition);
			}

			while ((is != null) && byteBuffer.hasRemaining()) {
				int position = byteBuffer.position();
				int rc = is.read(buffer, position, byteBuffer.remaining());

				if (logFine)
					logger.finest("read: rc=" + rc);

				if (rc == -1) {
					if (logFine)
						logger.finest("EOS, nulling stream");

					is = null;

					break;
				}

				byteBuffer.position(position + rc);

				if (logFine) {
					logger.finest(" added " + rc + " bytes to buffer: pos="
							+ byteBuffer.position() + ", remaining="
							+ byteBuffer.remaining() + ", scanPoint="
							+ scanPoint + ", boundaryPosition="
							+ boundaryPosition);
				}
			}

			if (logFine)
				logger.finest("leave: count=" + byteBuffer.position());
		}

		private void scanForBoundary(int limit) {
			boolean logFine = logger.isLoggable(Level.FINEST);

			if (boundaryPosition != -1) {
				if (logFine)
					logger.finest("boundaryPosition already set: "
							+ boundaryPosition);
				return;
			}

			if (logFine)
				logger.finest("scanning, beginning at " + scanPoint
						+ ", limit=" + limit);

			byte first = boundary[0];

			for (; scanPoint < limit; scanPoint++) {
				if (buffer[scanPoint] == first) {
					if ((scanPoint + boundary.length) > limit) {
						if (logFine)
							logger
									.finest("breaking, less than boundary size left");
						break;
					}

					if (boundaryIsHere(boundary, scanPoint, limit)) {
						if (logFine)
							logger.finest("boundary found, breaking");
						boundaryPosition = scanPoint;
						break;
					}
				}
			}

			if (logFine)
				logger.finest("done, scanPoint=" + scanPoint
						+ ", boundaryPosition=" + boundaryPosition);
		}

		boolean boundaryIsHere(byte[] boundary, int position, int limit) {
			boolean logFine = logger.isLoggable(Level.FINEST);

			if (logFine)
				logger.finest("enter: position=" + position + ", limit="
						+ limit);

			for (int i = 0; i < boundary.length; i++) {
				if (boundary[i] != buffer[position + i]) {
					if (logFine)
						logger.finest("leave: false (2)");

					return (false);
				}
			}

			if (logFine)
				logger.finest("leave: true");

			return (true);
		}
	}
}
