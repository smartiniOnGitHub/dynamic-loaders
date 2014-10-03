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
 * Configuration of a PluginManager.<br/>
 * Usually set by developers.
 * 
 * @see PluginManager
 */
public class PluginManagerConfiguration {

	boolean failOnError;
	boolean runByPlugin;


	public PluginManagerConfiguration() {
		this(true, true);
	}

	public PluginManagerConfiguration(boolean failOnError, boolean runByPlugin) {
		this.failOnError = failOnError;
		this.runByPlugin = runByPlugin;
	}

	public boolean isFailOnError() {
		return failOnError;
	}

	public void setFailOnError(boolean failOnError) {
		this.failOnError = failOnError;
	}

	public boolean isRunByPlugin() {
		return runByPlugin;
	}

	public void setRunByPlugin(boolean runByPlugin) {
		this.runByPlugin = runByPlugin;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{ ");
		// sb.append("\"PluginManagerConfiguration\": { ");
		sb.append("failOnError: " + failOnError);
		sb.append(", runByPlugin: " + runByPlugin);
		sb.append(" }");
		// sb.append(" }");

		return sb.toString();
	}

}
