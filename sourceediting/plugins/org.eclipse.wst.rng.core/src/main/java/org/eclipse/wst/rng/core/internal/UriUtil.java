/**********************************************************************
 * Copyright (c) 2009 Martin Schmied and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Schmied - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.rng.core.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.provider.FileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public final class UriUtil {
	private UriUtil() {}
	
	public static String convertRelativePathToResourceUri(String path) {
		if (path.startsWith("/") || path.startsWith("\\")) {
			path = path.substring(1);
		}
		return "platform:/resource/" + path;
	}
	
	/**
	 * 
	 * @param unresolvedUri
	 * @return resolved URI or null if the resolution fails for any reason
	 */
	public static URI resolveUri(String unresolvedUri) {
		unresolvedUri = unresolvedUri.replaceAll(" ", "%20");
		URL resolvedURL;
		try {
			resolvedURL = FileLocator.resolve(new URL(unresolvedUri));
			return resolvedURL.toURI();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		} catch (URISyntaxException e) {}
		
		return null;
	}
	
	public static boolean resourceExists(String unresolvedUri) {
		if (unresolvedUri.startsWith("platform:/plugin/")) {
			BundleAndPath bundleAndPath = getBundleAndPath(unresolvedUri);
			return FileLocator.find(bundleAndPath.bundle, bundleAndPath.path, null) != null;
		}
		URI resolvedUri = resolveUri(unresolvedUri);
		if (resolvedUri == null) {
			return false;
		}
		try {
			IFileStore fileStore = EFS.getStore(resolvedUri);
			return fileStore.fetchInfo().exists();
		} catch (CoreException e) {
			return false;
		}
	}

	private static BundleAndPath getBundleAndPath(String unresolvedUri) {
		String bundleAndPath = unresolvedUri.substring(17);
		int separatorIdx = bundleAndPath.indexOf('/');
		String bundleSymbolicName = bundleAndPath.substring(0, separatorIdx);
		String path = bundleAndPath.substring(separatorIdx);
		Bundle bundle = Platform.getBundle(bundleSymbolicName);
		return new BundleAndPath(bundle, new Path(path)); 
	}
	
	public static InputStream openResource(URI resolvedUri) throws IOException {
		IFileStore fileStore;
		try {
			fileStore = EFS.getStore(resolvedUri);
			return fileStore.openInputStream(EFS.NONE, null);
		} catch (CoreException e) {
			throw new IOException("Exception on attempt to read the schema file. Cause: " + e.getMessage());
		}
	}
	
	public static InputStream openResource(String unresolvedUri) throws IOException {
		if (unresolvedUri.startsWith("platform:/plugin/")) {
			BundleAndPath bundleAndPath = getBundleAndPath(unresolvedUri);
			return FileLocator.openStream(bundleAndPath.bundle, bundleAndPath.path, false);
		}
		URI resolvedUri = resolveUri(unresolvedUri);
		if (resolvedUri == null) {
			throw new FileNotFoundException();
		}
		return openResource(resolvedUri);
	}
	
	public static IFileInfo fetchFileInfo(URI resolvedUri) {
		try {
			return EFS.getStore(resolvedUri).fetchInfo();
		} catch (CoreException e) {
			return new FileInfo();
		}
	}
	
	private static class BundleAndPath {
		Bundle bundle;
		IPath path;
		
		public BundleAndPath(Bundle bundle, IPath path) {
			super();
			this.bundle = bundle;
			this.path = path;
		}
	}
}
