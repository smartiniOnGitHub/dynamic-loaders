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
 * Plugin statuses.<br/>
 * Usually set by developers.
 * 
 */
public enum PluginStatus {
	ERROR(-1, "error"), 
	UNKNOWN(0, "unknown"), 
	INSTALLED(1, "installed"), 
	LOADED(2, "loaded"), 
	UNINSTALLED(3, "uninstalled"), 
	UNLOADED(4, "unloaded"),
	EXECUTED(5, "executed");

	private final int value;
	private final String text;
	
	PluginStatus(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int value() {
    	return value;
    }
    
    public String text() {
    	return text;
    }

    public static final PluginStatus fromValue(int value) {
    	for (PluginStatus status : PluginStatus.values()) {
            if (status.value == value) 
                return status;
        }
		return null;
    }

    public static final int toValue(PluginStatus status) {
		Objects.requireNonNull(status);
		return status.value();
    }

}
