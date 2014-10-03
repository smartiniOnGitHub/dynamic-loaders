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
package dynamicloaders.core.plugin.sample;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import dynamicloaders.core.plugin.AbstractPlugin;
import dynamicloaders.core.plugin.PluginInfo;

public class TimePlugin extends AbstractPlugin {

	private static final PluginInfo info = new PluginInfo(
			"timePlugin", "1.0.0", "Sample plugin that returns the current timestamp, as long"
	);


	public TimePlugin() {
		super(info);
	}

	@SuppressWarnings("static-method")
	protected long getValue() {
		return System.currentTimeMillis();
	}

	@Override
	public PluginInfo getPluginInfo() {
		return info;
	}

	@Override
	public Map<String, String> getPluginFeatures() {
		// return null;
		// sample implementation
		Map<String, String> features = new LinkedHashMap<>();
		features.put("execute", "true");
		return features;
	}

	@Override
	public Map<String, ?> doExecute(Map<String, String> params) {
		System.out.println(this.getClass().getName() + ": " + "doExecute");
		// sample implementation
		Map<String, String> results = new HashMap<>();
		results.put("time", String.valueOf(getValue()));
		return results;
	}

}
