/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dynamicloaders.core.plugin;

/**
 * Configuration of a Plugin.<br/>
 * Usually set by developers.
 * 
 * @see Plugin
 */
public class PluginConfiguration {

	int status;  // duplicated here ...
	String uri;
	boolean enabled;


	public PluginConfiguration() {
		this(0, "", true);
	}

	public PluginConfiguration(int status, String uri, boolean enabled) {
		if (status < PluginStatus.UNKNOWN.value())
			throw new IllegalArgumentException("invalid initial status: " + status);

		this.status = status;
		this.uri = uri;
		this.enabled = enabled;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{ ");
		// sb.append("\"PluginConfiguration\": { ");
		sb.append("status: " + status);
		sb.append(", uri: \"" + ((uri != null) ? uri : "") + "\"");
		sb.append(", enabled: " + enabled);
		sb.append(" }");
		// sb.append(" }");

		return sb.toString();
	}

}
