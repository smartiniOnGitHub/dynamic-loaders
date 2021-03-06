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

import java.util.Objects;

/**
 * Plugin general info.<br/>
 * Usually set by developers.
 * 
 * @see Plugin
 */
public class PluginInfo {

	String name;
	String version;
	String description;
	String author;
	String url;


	public PluginInfo() {
		this("", "", null, null, null);
	}

	public PluginInfo(String name, String version, String description) {
		this(name, version, description, null, null);
	}

	public PluginInfo(String name, String version, String description, 
			String author, String url
	) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(version);

		this.name = name;
		this.version = version;
		this.description = description;
		this.author = author;
		this.url = url;
	}


	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public String getDescription() {
		return description;
	}

	public String getAuthor() {
		return author;
	}

	public String getUrl() {
		return url;
	}


	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{ ");
		// sb.append("\"PluginInfo\": { ");
		sb.append("name: \"" + name + "\"");
		sb.append(", version: \"" + version + "\"");
		sb.append(", description: \"" + ((description != null) ? description : "") + "\"");
		sb.append(", author: \"" + ((author != null) ? author : "") + "\"");
		sb.append(", url: \"" + ((url != null) ? url : "") + "\"");
		sb.append(" }");
		// sb.append(" }");

		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PluginInfo))
			return false;
		PluginInfo other = (PluginInfo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;

		return true;
	}


}
